import xml.etree.ElementTree as ET
import pandas as pd

# Define the ruleset from the provided PMD ruleset
pmd_ruleset = {
    "AvoidBranchingStatementAsLastInLoop": {
        "description": "Avoid branching statements (return, break, continue) as the last statement in a loop.",
        "rule_ref": "category/java/errorprone.xml/AvoidBranchingStatementAsLastInLoop"
    },
    "EmptyCatchBlock": {
        "description": "Avoid empty catch blocks to ensure exceptions are handled properly.",
        "rule_ref": "category/java/errorprone.xml/EmptyCatchBlock"
    },
    "JUnitTestContainsTooManyAsserts": {
        "description": "JUnit tests should not contain too many asserts. Split complex tests into smaller ones.",
        "rule_ref": "category/java/bestpractices.xml/JUnitTestContainsTooManyAsserts"
    },
    "JUnitTestsShouldIncludeAssert": {
        "description": "JUnit tests should include at least one assert statement.",
        "rule_ref": "category/java/bestpractices.xml/JUnitTestsShouldIncludeAssert"
    },
    "MethodNamingConventions": {
        "description": "Test method names should follow the pattern: 'shouldDoSomethingWhenCondition'.",
        "rule_ref": "category/java/codestyle.xml/MethodNamingConventions",
        "expected_pattern": "^should[A-Z][a-zA-Z0-9]*$"
    },
    "ClassNamingConventions": {
        "description": "Test class names should end with 'Test'.",
        "rule_ref": "category/java/codestyle.xml/ClassNamingConventions",
        "expected_pattern": "^[A-Z][a-zA-Z0-9]*Test$"
    }
}

def parse_pmd_report(file_path):
    """
    Parse the PMD analysis report.
    Args:
        file_path (str): Path to the PMD XML report.
    Returns:
        pd.DataFrame: DataFrame containing rule violation results.
    """
    # Parse XML file and check for namespaces
    tree = ET.parse(file_path)
    root = tree.getroot()

    # Detect namespace if present
    namespaces = {'ns': root.tag.split('}')[0].strip('{')} if '}' in root.tag else {}

    data = []
    for file in root.findall(".//ns:file" if namespaces else ".//file", namespaces):
        file_name = file.attrib.get("name", "Unknown")
        for violation in file.findall("ns:violation" if namespaces else "violation", namespaces):
            rule = violation.attrib.get("rule", "Unknown")
            ruleset = violation.attrib.get("ruleset", "Unknown")
            priority = violation.attrib.get("priority", "Unknown")
            begin_line = violation.attrib.get("beginline", "Unknown")
            end_line = violation.attrib.get("endline", "Unknown")
            description = violation.text.strip() if violation.text else "No description"

            # Only process violations related to the provided ruleset
            if rule in pmd_ruleset:
                data.append({
                    "file": file_name,
                    "rule": rule,
                    "description": pmd_ruleset[rule]["description"],
                    "rule_ref": pmd_ruleset[rule]["rule_ref"],
                    "priority": priority,
                    "begin_line": begin_line,
                    "end_line": end_line,
                    "violation_description": description
                })

    report_df = pd.DataFrame(data)
    print(f"Parsed {len(report_df)} rule violations from {file_path}")
    print(report_df.head())  # Print the first few rows for inspection
    return report_df

def generate_pmd_rule_report(report_df, title):
    """
    Generate a rule violation summary table.
    Args:
        report_df (pd.DataFrame): DataFrame with PMD rule violation results.
        title (str): Title for the report.
    """
    # Create a summary DataFrame for all rules
    summary_data = []
    for rule, details in pmd_ruleset.items():
        rule_count = len(report_df[report_df["rule"] == rule])
        summary_data.append({
            "Rule": rule,
            "Description": details["description"],
            "Rule Reference": details["rule_ref"],
            "Violation Count": rule_count
        })

    summary_df = pd.DataFrame(summary_data)
    summary_df = summary_df.sort_values(by="Violation Count", ascending=False)
    
    print(f"\nPMD Rule Violation Summary for {title}")
    print(summary_df.to_string(index=False))  # Display the table without the index

    return summary_df

def compare_pmd_rule_reports(manual_df, ai_df):
    """
    Compare PMD rule violations between manual and AI-generated reports.
    Args:
        manual_df (pd.DataFrame): DataFrame with manual PMD rule violations.
        ai_df (pd.DataFrame): DataFrame with AI-generated PMD rule violations.
    """
    # Create a comparison DataFrame for all rules
    comparison_data = []
    for rule, details in pmd_ruleset.items():
        manual_count = len(manual_df[manual_df["rule"] == rule])
        ai_count = len(ai_df[ai_df["rule"] == rule])
        comparison_data.append({
            "Rule": rule,
            "Description": details["description"],
            "Rule Reference": details["rule_ref"],
            "Manual Violation Count": manual_count,
            "AI Violation Count": ai_count,
            "Difference": ai_count - manual_count
        })

    comparison_df = pd.DataFrame(comparison_data)
    comparison_df = comparison_df.sort_values(by="Difference", ascending=False)
    
    print("\nPMD Rule Violations Comparison (AI-Generated vs. Manual)")
    print(comparison_df.to_string(index=False))  # Display the table without the index
    
    return comparison_df

# Parse reports
manual_pmd_report = parse_pmd_report("manual-unittest-pmd-report.xml")
ai_pmd_report = parse_pmd_report("ai-generated-unittest-pmd-report.xml")

# Generate reports if data is parsed
if not manual_pmd_report.empty:
    manual_summary_df = generate_pmd_rule_report(manual_pmd_report, "Manual Unit Tests")
if not ai_pmd_report.empty:
    ai_summary_df = generate_pmd_rule_report(ai_pmd_report, "AI-Generated Unit Tests")

# Compare reports if data is parsed
if not manual_pmd_report.empty and not ai_pmd_report.empty:
    comparison_df = compare_pmd_rule_reports(manual_pmd_report, ai_pmd_report)

    # Save comparison to CSV
    comparison_df.to_csv("pmd_rule_violation_comparison.csv", index=False)
    print("PMD rule violation comparison saved to pmd_rule_violation_comparison.csv")
