/*
 * Copyright 2006 The Closure Compiler Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.javascript.jscomp;

import com.google.javascript.jscomp.CompilerOptions.LanguageMode;

public final class CheckSideEffectsTest extends CompilerTestCase {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    setAcceptedLanguage(LanguageMode.ECMASCRIPT_2017);
    enableParseTypeInfo();
    allowExternsChanges();
  }

  @Override
  protected int getNumRepetitions() {
    return 1;
  }

  @Override
  protected CompilerPass getProcessor(Compiler compiler) {
    return new CheckSideEffects(compiler, true, true);
  }

  private final DiagnosticType e = CheckSideEffects.USELESS_CODE_ERROR;

  public void testUselessCode() {
    testSame("function f(x) { if(x) return; }");
    testWarning("function f(x) { if(x); }", e);
    testSame("var f = x=>x");
    testWarning("var f = (x)=>{ if(x); }", e);

    testSame("if(x) x = y;");
    test("if(x) x == bar();", "if(x) JSCOMPILER_PRESERVE(x == bar());", warning(e));

    testSame("x = 3;");
    test("x == 3;", "JSCOMPILER_PRESERVE(x == 3);", warning(e));

    testSame("var x = 'test'");
    test(
        "var x = 'test'\n'Breakstr'",
        "var x = 'test'\nJSCOMPILER_PRESERVE('Breakstr')",
        warning(e));

    testSame("");
    testSame("foo();;;;bar();;;;");

    testSame("var a, b; a = 5, b = 6");
    test("var a, b; a = 5, b == 6", "var a, b; a = 5, JSCOMPILER_PRESERVE(b == 6)", warning(e));
    test("var a, b; a = (5, 6)", "var a, b; a = (JSCOMPILER_PRESERVE(5), 6)", warning(e));
    test(
        "var a, b; a = (bar(), 6, 7)",
        "var a, b; a = (bar(), JSCOMPILER_PRESERVE(6), 7)",
        warning(e));
    test(
        "var a, b; a = (bar(), bar(), 7, 8)",
        "var a, b; a = (bar(), bar(), JSCOMPILER_PRESERVE(7), 8)",
        warning(e));
    testSame("var a, b; a = (b = 7, 6)");
    testSame(
        LINE_JOINER.join(
            "function x(){}",
            "function f(a, b){}",
            "f(1,(x(), 2));"));
    test(LINE_JOINER.join(
    "function x(){}",
    "function f(a, b){}",
    "f(1,(2, 3));"), LINE_JOINER.join(
    "function x(){}",
    "function f(a, b){}",
    "f(1,(JSCOMPILER_PRESERVE(2), 3));"), warning(e));

    test(
        "var x = `TemplateA`\n'TestB'",
        "var x = `TemplateA`\nJSCOMPILER_PRESERVE('TestB')",
        warning(e));
    test("`LoneTemplate`", "JSCOMPILER_PRESERVE(`LoneTemplate`)", warning(e));
    test(
        LINE_JOINER.join("var name = 'Bad';", "`${name}Template`;"),
        LINE_JOINER.join("var name = 'Bad';", "JSCOMPILER_PRESERVE(`${name}Template`)"),
        warning(e));
    test(
        LINE_JOINER.join("var name = 'BadTail';", "`Template${name}`;"),
        LINE_JOINER.join("var name = 'BadTail';", "JSCOMPILER_PRESERVE(`Template${name}`)"),
        warning(e));
    testSame(
        LINE_JOINER.join(
            "var name = 'Good';",
            "var templateString = `${name}Template`;"));
    testSame("var templateString = `Template`;");
    testSame("tagged`Template`;");
    testSame("tagged`${name}Template`;");

    testSame(LINE_JOINER.join(
        "var obj = {",
        "  itm1: 1,",
        "  itm2: 2",
        "}",
        "var { itm1: de_item1, itm2: de_item2 } = obj;"));
    testSame(LINE_JOINER.join(
        "var obj = {",
        "  itm1: 1,",
        "  itm2: 2",
        "}",
        "var { itm1, itm2 } = obj;"));
    testSame(LINE_JOINER.join(
        "var arr = ['item1', 'item2', 'item3'];",
        "var [ itm1 = 1, itm2 = 2 ] = arr;"));
    testSame(LINE_JOINER.join(
        "var arr = ['item1', 'item2', 'item3'];",
        "var [ itm1 = 1, itm2 = 2 ] = badArr;"));
    testSame(LINE_JOINER.join(
        "var arr = ['item1', 'item2', 'item3'];",
        "function f(){}",
        "var [ itm1 = f(), itm2 = 2 ] = badArr;"));

    testSame("function c(a, b = 1) {}; c(1);");
    testSame("function c(a, b = f()) {}; c(1);");
    testSame("function c(a, {b, c}) {}; c(1);");
    testSame("function c(a, {b, c}) {}; c(1, {b: 2, c: 3});");

    testWarning("var f = s => {key:s}", e);
    testWarning("var f = s => {key:s + 1}", e);
    testWarning("var f = s => {s}", e);
  }

  public void testUselessCodeInFor() {
    testSame("for(var x = 0; x < 100; x++) { foo(x) }");
    testSame("for(; true; ) { bar() }");
    testSame("for(foo(); true; foo()) { bar() }");
    test(
        "for(void 0; true; foo()) { bar() }",
        "for(JSCOMPILER_PRESERVE(void 0); true; foo()) { bar() }",
        warning(e));
    test(
        "for(foo(); true; void 0) { bar() }",
        "for(foo(); true; JSCOMPILER_PRESERVE(void 0)) { bar() }",
        warning(e));
    test(
        "for(foo(); true; (1, bar())) { bar() }",
        "for(foo(); true; (JSCOMPILER_PRESERVE(1), bar())) { bar() }",
        warning(e));

    testSame("for(foo in bar) { foo() }");
    testSame("for (i = 0; el = el.previousSibling; i++) {}");
    testSame("for (i = 0; el = el.previousSibling; i++);");
  }

  public void testTypeAnnotations() {
    test("x;", "JSCOMPILER_PRESERVE(x);", warning(e));
    test("a.b.c.d;", "JSCOMPILER_PRESERVE(a.b.c.d);", warning(e));
    testSame("/** @type {Number} */ a.b.c.d;");
    testSame("if (true) { /** @type {Number} */ a.b.c.d; }");

    test(
        "function A() { this.foo; }",
        "function A() { JSCOMPILER_PRESERVE(this.foo); }",
        warning(e));
    testSame("function A() { /** @type {Number} */ this.foo; }");
  }

  public void testJSDocComments() {
    testSame("function A() { /** This is a JsDoc comment */ this.foo; }");
    test(
        "function A() { /* This is a normal comment */ this.foo; }",
        "function A() { /* This is a normal comment */ JSCOMPILER_PRESERVE(this.foo); }",
        warning(e));
  }

  public void testIssue80() {
    testSame("(0, eval)('alert');");
    test("(0, foo)('alert');", "(JSCOMPILER_PRESERVE(0), foo)('alert');", warning(e));
  }

  public void testIsue504() {
    test(
        "void f();",
        "JSCOMPILER_PRESERVE(void f());",
        warning(e, "Suspicious code. The result of the 'void' operator is not being used."));
  }

  public void testExternFunctions() {
    String externs = LINE_JOINER.join(
        "/** @return {boolean}",
        "  * @nosideeffects */",
        "function noSideEffectsExtern(){}",
        "/** @return {boolean}",
        "  * @nosideeffects */",
        "var noSideEffectsExtern2 = function(){};",
        "/** @return {boolean} */ function hasSideEffectsExtern(){}",
        "/** @return {boolean} */ var hasSideEffectsExtern2 = function(){}");

    testSame(externs, "alert(noSideEffectsExtern());");

    test(externs,
        "noSideEffectsExtern();",
        "JSCOMPILER_PRESERVE(noSideEffectsExtern());",
        warning(e, "Suspicious code. The result of the extern function call "
            + "'noSideEffectsExtern' is not being used."));

    test(externs,
        "noSideEffectsExtern2();",
        "JSCOMPILER_PRESERVE(noSideEffectsExtern2());",
        warning(e, "Suspicious code. The result of the extern function call "
            + "'noSideEffectsExtern2' is not being used."));

    testSame(externs, "hasSideEffectsExtern()");

    testSame(externs, "hasSideEffectsExtern2()");

    // Methods redefined in inner scopes should not trigger a warning
    testSame(
        externs, "(function() { function noSideEffectsExtern() {}; noSideEffectsExtern(); })()");
  }

  public void testExternPropertyFunctions() {
    String externs = LINE_JOINER.join(
        "/** @const */ var foo = {};",
        "/** @return {boolean}",
        "  * @nosideeffects */",
        "foo.noSideEffectsExtern = function(){}");

    testSame(externs, "alert(foo.noSideEffectsExtern());");

    test(externs,
        "foo.noSideEffectsExtern();",
        "JSCOMPILER_PRESERVE(foo.noSideEffectsExtern());",
        warning(e, "Suspicious code. The result of the extern function call "
            + "'foo.noSideEffectsExtern' is not being used."));

    // Methods redefined in inner scopes should not trigger a warning
    testSame(
        externs(externs),
        srcs(LINE_JOINER.join(
            "(function() {",
            "  var foo = {};",
            "  foo.noSideEffectsExtern = function() {};",
            "  noSideEffectsExtern();",
            " })()")));
  }
}
