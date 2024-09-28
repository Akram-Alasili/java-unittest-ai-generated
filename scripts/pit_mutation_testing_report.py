import xml.etree.ElementTree as ET
import pandas as pd
import matplotlib.pyplot as plt

def parse_pit_report(file_path):
    """
    Parse the PIT mutation testing report.
    Args:
        file_path (str): Path to the PIT XML report.
    Returns:
        pd.DataFrame: DataFrame containing mutation results.
    """
    tree = ET.parse(file_path)
    root = tree.getroot()

    data = []
    for mutation in root.findall(".//mutation"):
        # Safely get the text from each element, handling missing elements
        mutated_class = mutation.find("mutatedClass").text if mutation.find("mutatedClass") is not None else "Unknown"
        mutated_method = mutation.find("mutatedMethod").text if mutation.find("mutatedMethod") is not None else "Unknown"
        line_number = int(mutation.find("lineNumber").text) if mutation.find("lineNumber") is not None else -1
        mutator = mutation.find("mutator").text if mutation.find("mutator") is not None else "Unknown"
        status = mutation.attrib.get("status", "UNKNOWN")  
        
        detected = mutation.attrib.get("detected", "false") == "true"
        killing_test = mutation.find("killingTest")
        killed = status == "KILLED"  

        # A mutation is considered no coverage if the status is explicitly marked as NO_COVERAGE
        no_coverage = status == "NO_COVERAGE"

        data.append({
            "class": mutated_class,
            "method": mutated_method,
            "line_number": line_number,
            "mutator": mutator,
            "status": status,
            "detected": detected,
            "killed": killed,
            "no_coverage": no_coverage
        })

    report_df = pd.DataFrame(data)
    print(f"Parsed {len(report_df)} mutations from {file_path}")
    print(report_df.head())  
    return report_df

def generate_mutation_report(report_df, title):
    """
    Generate a mutation testing summary and plot.
    Args:
        report_df (pd.DataFrame): DataFrame with mutation testing results.
        title (str): Title for the report.
    """
    # Calculate mutation scores
    total_mutations = len(report_df)
    killed_mutations = len(report_df[report_df["status"] == "KILLED"])  
    survived_mutations = len(report_df[report_df["status"] == "SURVIVED"])
    no_coverage_mutations = len(report_df[report_df["status"] == "NO_COVERAGE"])  #
    mutation_score = (killed_mutations / total_mutations) * 100 if total_mutations > 0 else 0

    # Adjust test strength calculation to match pipeline logic
    test_strength = ((killed_mutations + no_coverage_mutations) / total_mutations) * 100 if total_mutations > 0 else 0

    summary = {
        "Total Mutations": total_mutations,
        "Killed Mutations": killed_mutations,
        "Survived Mutations": survived_mutations,
        "Mutations with No Coverage": no_coverage_mutations,
        "Mutation Score (%)": round(mutation_score, 2),
        "Test Strength (%)": round(test_strength, 2)
    }

    print(f"Mutation Testing Summary for {title}")
    for key, value in summary.items():
        print(f"{key}: {value}")

    # Plot mutation scores
    labels = ['Killed', 'Survived', 'No Coverage']
    sizes = [killed_mutations, survived_mutations, no_coverage_mutations]
    colors = ['#66b3ff', '#ff9999', '#99ff99']
    explode = (0.1, 0, 0)  # explode the first slice

    if sum(sizes) > 0:  # Check if there is any data to plot
        plt.figure(figsize=(8, 6))
        plt.pie(sizes, explode=explode, labels=labels, colors=colors,
                autopct='%1.1f%%', shadow=True, startangle=140)
        plt.title(f'Mutation Testing Results for {title}')
        plt.show()
    else:
        print(f"No data to plot for {title}: all values are zero.")

# Parse reports with refined logic
manual_pit_report = parse_pit_report("manual-unittest-pit-report.xml")
ai_pit_report = parse_pit_report("ai-generated-unittest-pit-report.xml")

# Print detected issues in no_coverage
print("Manual Unit Tests No Coverage Count Check:")
print(manual_pit_report[manual_pit_report["no_coverage"] == True].head())
print("AI-Generated Unit Tests No Coverage Count Check:")
print(ai_pit_report[ai_pit_report["no_coverage"] == True].head())

# Generate reports
generate_mutation_report(manual_pit_report, "Manual Unit Tests")
generate_mutation_report(ai_pit_report, "AI-Generated Unit Tests")

# Compare mutation scores
comparison = pd.DataFrame({
    "Metric": ["Total Mutations", "Killed Mutations", "Survived Mutations", "Mutations with No Coverage", "Mutation Score (%)", "Test Strength (%)"],
    "Manual Unit Tests": [
        len(manual_pit_report),
        len(manual_pit_report[manual_pit_report["status"] == "KILLED"]),
        len(manual_pit_report[manual_pit_report["status"] == "SURVIVED"]),
        len(manual_pit_report[manual_pit_report["status"] == "NO_COVERAGE"]),
        round((len(manual_pit_report[manual_pit_report["status"] == "KILLED"]) / len(manual_pit_report)) * 100, 2) if len(manual_pit_report) > 0 else 0,
        round(((len(manual_pit_report[manual_pit_report["status"] == "KILLED"]) + 
                len(manual_pit_report[manual_pit_report["status"] == "NO_COVERAGE"])) / 
               len(manual_pit_report)) * 100, 2) 
        if len(manual_pit_report) > 0 else 0
    ],
    "AI-Generated Unit Tests": [
        len(ai_pit_report),
        len(ai_pit_report[ai_pit_report["status"] == "KILLED"]),
        len(ai_pit_report[ai_pit_report["status"] == "SURVIVED"]),
        len(ai_pit_report[ai_pit_report["status"] == "NO_COVERAGE"]),
        round((len(ai_pit_report[ai_pit_report["status"] == "KILLED"]) / len(ai_pit_report)) * 100, 2) if len(ai_pit_report) > 0 else 0,
        round(((len(ai_pit_report[ai_pit_report["status"] == "KILLED"]) + 
                len(ai_pit_report[ai_pit_report["status"] == "NO_COVERAGE"])) / 
               len(ai_pit_report)) * 100, 2) 
        if len(ai_pit_report) > 0 else 0
    ]
})

# Save to CSV for further analysis in the current directory or specify a custom directory
comparison.to_csv("mutation_coverage_comparison.csv", index=False)
print("Mutation coverage comparison saved to mutation_coverage_comparison.csv")
