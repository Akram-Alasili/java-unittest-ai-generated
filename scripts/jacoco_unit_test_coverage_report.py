import xml.etree.ElementTree as ET
import pandas as pd
import matplotlib.pyplot as plt

def parse_jacoco_report(file_path):
    tree = ET.parse(file_path)
    root = tree.getroot()

    data = []
    for package in root.findall(".//package"):
        for class_ in package.findall("class"):
            class_name = class_.attrib["name"]
            for counter in class_.findall("counter"):
                data.append({
                    "class": class_name,
                    "type": counter.attrib["type"],
                    "missed": int(counter.attrib["missed"]),
                    "covered": int(counter.attrib["covered"]),
                })
    return pd.DataFrame(data)

def generate_coverage_report(report_df, title):
    # Calculate coverage percentages
    report_df["coverage"] = report_df["covered"] / (report_df["covered"] + report_df["missed"])

    # Generate summary report
    summary = report_df.groupby("type").agg({
        "missed": "sum",
        "covered": "sum"
    })
    summary["coverage"] = summary["covered"] / (summary["covered"] + summary["missed"])
    summary["coverage"] = summary["coverage"] * 100  # Convert to percentage
    summary["coverage"] = summary["coverage"].round(2)  # Round to two decimal places
    print(f"Coverage Summary for {title}")
    print(summary)

    # Plot coverage
    summary.reset_index(inplace=True)
    plt.figure(figsize=(10, 6))
    plt.bar(summary['type'], summary['coverage'], color='skyblue')
    plt.xlabel('Coverage Type')
    plt.ylabel('Coverage Percentage (%)')
    plt.title(f'Code Coverage Report for {title}')
    plt.ylim(0, 100)
    for i in range(len(summary)):
        plt.text(i, summary['coverage'][i], f"{summary['coverage'][i]:.2f}%", ha='center', va='bottom')
    plt.show()

# Parse reports
manual_report = parse_jacoco_report("manual-unitest-report-jacoco.xml")
ai_report = parse_jacoco_report("ai-generated-unittest-report-jacoco.xml")

# Generate reports
generate_coverage_report(manual_report, "Manual Unit Tests")
generate_coverage_report(ai_report, "AI-Generated Unit Tests")

# Compare coverage
comparison = manual_report.merge(ai_report, on=["class", "type"], suffixes=("_manual", "_ai"))
comparison["coverage_manual"] = comparison["covered_manual"] / (comparison["covered_manual"] + comparison["missed_manual"])
comparison["coverage_ai"] = comparison["covered_ai"] / (comparison["covered_ai"] + comparison["missed_ai"])
comparison["coverage_manual"] = (comparison["coverage_manual"] * 100).round(2)  # Convert to percentage and round
comparison["coverage_ai"] = (comparison["coverage_ai"] * 100).round(2)  # Convert to percentage and round

# Save to CSV for further analysis
comparison.to_csv("coverage_comparison.csv", index=False)
print("Coverage comparison saved to coverage_comparison.csv")
