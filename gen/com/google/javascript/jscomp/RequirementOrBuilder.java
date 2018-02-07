// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: conformance.proto

package com.google.javascript.jscomp;

public interface RequirementOrBuilder extends
    // @@protoc_insertion_point(interface_extends:jscomp.Requirement)
    com.google.protobuf.GeneratedMessageV3.
        ExtendableMessageOrBuilder<Requirement> {

  /**
   * <pre>
   * Required: The message to report when a requirement is violated. This should
   * reference a document describing the reasoning for the requirement
   * and contacts.
   * </pre>
   *
   * <code>optional string error_message = 1;</code>
   */
  boolean hasErrorMessage();
  /**
   * <pre>
   * Required: The message to report when a requirement is violated. This should
   * reference a document describing the reasoning for the requirement
   * and contacts.
   * </pre>
   *
   * <code>optional string error_message = 1;</code>
   */
  java.lang.String getErrorMessage();
  /**
   * <pre>
   * Required: The message to report when a requirement is violated. This should
   * reference a document describing the reasoning for the requirement
   * and contacts.
   * </pre>
   *
   * <code>optional string error_message = 1;</code>
   */
  com.google.protobuf.ByteString
      getErrorMessageBytes();

  /**
   * <pre>
   * Optional: A list of source path prefixes that are exempt from the
   * requirement.
   * </pre>
   *
   * <code>repeated string whitelist = 2;</code>
   */
  java.util.List<java.lang.String>
      getWhitelistList();
  /**
   * <pre>
   * Optional: A list of source path prefixes that are exempt from the
   * requirement.
   * </pre>
   *
   * <code>repeated string whitelist = 2;</code>
   */
  int getWhitelistCount();
  /**
   * <pre>
   * Optional: A list of source path prefixes that are exempt from the
   * requirement.
   * </pre>
   *
   * <code>repeated string whitelist = 2;</code>
   */
  java.lang.String getWhitelist(int index);
  /**
   * <pre>
   * Optional: A list of source path prefixes that are exempt from the
   * requirement.
   * </pre>
   *
   * <code>repeated string whitelist = 2;</code>
   */
  com.google.protobuf.ByteString
      getWhitelistBytes(int index);

  /**
   * <pre>
   * Optional: A list of source paths regexs that are exempt from the
   * requirement.
   * </pre>
   *
   * <code>repeated string whitelist_regexp = 3;</code>
   */
  java.util.List<java.lang.String>
      getWhitelistRegexpList();
  /**
   * <pre>
   * Optional: A list of source paths regexs that are exempt from the
   * requirement.
   * </pre>
   *
   * <code>repeated string whitelist_regexp = 3;</code>
   */
  int getWhitelistRegexpCount();
  /**
   * <pre>
   * Optional: A list of source paths regexs that are exempt from the
   * requirement.
   * </pre>
   *
   * <code>repeated string whitelist_regexp = 3;</code>
   */
  java.lang.String getWhitelistRegexp(int index);
  /**
   * <pre>
   * Optional: A list of source paths regexs that are exempt from the
   * requirement.
   * </pre>
   *
   * <code>repeated string whitelist_regexp = 3;</code>
   */
  com.google.protobuf.ByteString
      getWhitelistRegexpBytes(int index);

  /**
   * <pre>
   * Optional: A list of source paths that will be checked for the requirement
   * (the opposite of whitelist).
   * </pre>
   *
   * <code>repeated string only_apply_to = 4;</code>
   */
  java.util.List<java.lang.String>
      getOnlyApplyToList();
  /**
   * <pre>
   * Optional: A list of source paths that will be checked for the requirement
   * (the opposite of whitelist).
   * </pre>
   *
   * <code>repeated string only_apply_to = 4;</code>
   */
  int getOnlyApplyToCount();
  /**
   * <pre>
   * Optional: A list of source paths that will be checked for the requirement
   * (the opposite of whitelist).
   * </pre>
   *
   * <code>repeated string only_apply_to = 4;</code>
   */
  java.lang.String getOnlyApplyTo(int index);
  /**
   * <pre>
   * Optional: A list of source paths that will be checked for the requirement
   * (the opposite of whitelist).
   * </pre>
   *
   * <code>repeated string only_apply_to = 4;</code>
   */
  com.google.protobuf.ByteString
      getOnlyApplyToBytes(int index);

  /**
   * <pre>
   * Optional: A list of source path regexps that will be checked for
   * the requirement (the opposite of whitelist_regexp).
   * </pre>
   *
   * <code>repeated string only_apply_to_regexp = 5;</code>
   */
  java.util.List<java.lang.String>
      getOnlyApplyToRegexpList();
  /**
   * <pre>
   * Optional: A list of source path regexps that will be checked for
   * the requirement (the opposite of whitelist_regexp).
   * </pre>
   *
   * <code>repeated string only_apply_to_regexp = 5;</code>
   */
  int getOnlyApplyToRegexpCount();
  /**
   * <pre>
   * Optional: A list of source path regexps that will be checked for
   * the requirement (the opposite of whitelist_regexp).
   * </pre>
   *
   * <code>repeated string only_apply_to_regexp = 5;</code>
   */
  java.lang.String getOnlyApplyToRegexp(int index);
  /**
   * <pre>
   * Optional: A list of source path regexps that will be checked for
   * the requirement (the opposite of whitelist_regexp).
   * </pre>
   *
   * <code>repeated string only_apply_to_regexp = 5;</code>
   */
  com.google.protobuf.ByteString
      getOnlyApplyToRegexpBytes(int index);

  /**
   * <pre>
   * Required: The type of requirement.
   * </pre>
   *
   * <code>optional .jscomp.Requirement.Type type = 6;</code>
   */
  boolean hasType();
  /**
   * <pre>
   * Required: The type of requirement.
   * </pre>
   *
   * <code>optional .jscomp.Requirement.Type type = 6;</code>
   */
  com.google.javascript.jscomp.Requirement.Type getType();

  /**
   * <pre>
   * The value banned, optional for "custom" requirements.
   * </pre>
   *
   * <code>repeated string value = 7;</code>
   */
  java.util.List<java.lang.String>
      getValueList();
  /**
   * <pre>
   * The value banned, optional for "custom" requirements.
   * </pre>
   *
   * <code>repeated string value = 7;</code>
   */
  int getValueCount();
  /**
   * <pre>
   * The value banned, optional for "custom" requirements.
   * </pre>
   *
   * <code>repeated string value = 7;</code>
   */
  java.lang.String getValue(int index);
  /**
   * <pre>
   * The value banned, optional for "custom" requirements.
   * </pre>
   *
   * <code>repeated string value = 7;</code>
   */
  com.google.protobuf.ByteString
      getValueBytes(int index);

  /**
   * <pre>
   * Strategy to use for matching types in the value parameter (e.g. for
   * BANNED_CODE_PATTERN checks).
   * </pre>
   *
   * <code>optional .jscomp.Requirement.TypeMatchingStrategy type_matching_strategy = 13 [default = LOOSE];</code>
   */
  boolean hasTypeMatchingStrategy();
  /**
   * <pre>
   * Strategy to use for matching types in the value parameter (e.g. for
   * BANNED_CODE_PATTERN checks).
   * </pre>
   *
   * <code>optional .jscomp.Requirement.TypeMatchingStrategy type_matching_strategy = 13 [default = LOOSE];</code>
   */
  com.google.javascript.jscomp.Requirement.TypeMatchingStrategy getTypeMatchingStrategy();

  /**
   * <pre>
   * For "custom" requirements, the Java class used to enforce the requirement.
   * Ignored otherwise.
   * </pre>
   *
   * <code>optional string java_class = 8;</code>
   */
  boolean hasJavaClass();
  /**
   * <pre>
   * For "custom" requirements, the Java class used to enforce the requirement.
   * Ignored otherwise.
   * </pre>
   *
   * <code>optional string java_class = 8;</code>
   */
  java.lang.String getJavaClass();
  /**
   * <pre>
   * For "custom" requirements, the Java class used to enforce the requirement.
   * Ignored otherwise.
   * </pre>
   *
   * <code>optional string java_class = 8;</code>
   */
  com.google.protobuf.ByteString
      getJavaClassBytes();

  /**
   * <pre>
   * Gives the rule an unique ID that can be used for extending in other rules
   * through 'extends'. An example of ID is 'closure:innerHtml'.
   * </pre>
   *
   * <code>optional string rule_id = 9;</code>
   */
  boolean hasRuleId();
  /**
   * <pre>
   * Gives the rule an unique ID that can be used for extending in other rules
   * through 'extends'. An example of ID is 'closure:innerHtml'.
   * </pre>
   *
   * <code>optional string rule_id = 9;</code>
   */
  java.lang.String getRuleId();
  /**
   * <pre>
   * Gives the rule an unique ID that can be used for extending in other rules
   * through 'extends'. An example of ID is 'closure:innerHtml'.
   * </pre>
   *
   * <code>optional string rule_id = 9;</code>
   */
  com.google.protobuf.ByteString
      getRuleIdBytes();

  /**
   * <pre>
   * Allows extending whitelists of rules with the specified rule_id. If this
   * field is specified then all fields except whitelist, whitelist_regexp,
   * only_apply_to and only_apply_to_regexp are ignored.
   * </pre>
   *
   * <code>optional string extends = 10;</code>
   */
  boolean hasExtends();
  /**
   * <pre>
   * Allows extending whitelists of rules with the specified rule_id. If this
   * field is specified then all fields except whitelist, whitelist_regexp,
   * only_apply_to and only_apply_to_regexp are ignored.
   * </pre>
   *
   * <code>optional string extends = 10;</code>
   */
  java.lang.String getExtends();
  /**
   * <pre>
   * Allows extending whitelists of rules with the specified rule_id. If this
   * field is specified then all fields except whitelist, whitelist_regexp,
   * only_apply_to and only_apply_to_regexp are ignored.
   * </pre>
   *
   * <code>optional string extends = 10;</code>
   */
  com.google.protobuf.ByteString
      getExtendsBytes();

  /**
   * <pre>
   * Whether to report possible violations when type information is not exact.
   * Normally, violations on parent types are reported as possible violations.
   * This field allows to ignore them and report only violations on exact types.
   * This changes the balance between the false positives and the false
   * negatives. With the default value, there might be lots of false positives
   * (possible violations) but there shouldn't be any false negatives. Without
   * reporting the loose type violations, there will be less false positives but
   * there can also be false negatives (an actual violation that is not
   * reported).
   * </pre>
   *
   * <code>optional bool report_loose_type_violations = 11 [default = true];</code>
   */
  boolean hasReportLooseTypeViolations();
  /**
   * <pre>
   * Whether to report possible violations when type information is not exact.
   * Normally, violations on parent types are reported as possible violations.
   * This field allows to ignore them and report only violations on exact types.
   * This changes the balance between the false positives and the false
   * negatives. With the default value, there might be lots of false positives
   * (possible violations) but there shouldn't be any false negatives. Without
   * reporting the loose type violations, there will be less false positives but
   * there can also be false negatives (an actual violation that is not
   * reported).
   * </pre>
   *
   * <code>optional bool report_loose_type_violations = 11 [default = true];</code>
   */
  boolean getReportLooseTypeViolations();

  /**
   * <code>optional .jscomp.Requirement.Severity severity = 12 [default = WARNING];</code>
   */
  boolean hasSeverity();
  /**
   * <code>optional .jscomp.Requirement.Severity severity = 12 [default = WARNING];</code>
   */
  com.google.javascript.jscomp.Requirement.Severity getSeverity();
}
