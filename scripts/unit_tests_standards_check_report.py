import os
import re
import json
import pandas as pd
import matplotlib.pyplot as plt
import xml.etree.ElementTree as ET

# Load the standards from the JSON file
with open('standards.json', 'r') as f:
    data = json.load(f)
    standards = data['standards']  # Extract the list of standards

# Check the content of the standards variable
print("Standards loaded from JSON:\n", standards)  # Debug print

# Ensure standards is a list
if not isinstance(standards, list):
    raise ValueError("The standards JSON file must contain a list of dictionaries.")

def parse_build_log(file_path):
    """
    Parse the build log to extract the total time.
    
    Args:
        file_path (str): Path to the build log file.
    
    Returns:
        str: The extracted total time as a string, or None if not found.
    """
    try:
        with open(file_path, 'r') as file:
            total_time_count = 0
            for line in file:
                if '[INFO] Total time:' in line:
                    total_time_count += 1
                    if total_time_count == 2:
                        # Extract the total time using regular expression
                        match = re.search(r'\[INFO\] Total time: (.+)', line)
                        if match:
                            return match.group(1)
    except FileNotFoundError:
        return None
    except Exception as e:
        return None

def convert_time_to_float(time_str):
    """
    Convert the time string to a float.
    
    Args:
        time_str (str): The time string to convert.
    
    Returns:
        float: The converted time as a float, or None if conversion fails.
    """
    try:
        time_str = time_str.strip().replace(' s', '')
        return float(time_str)
    except ValueError:
        return None

def convert_to_custom_format(time_float):
    """
    Convert the time float to a custom format based on defined thresholds.

    Args:
        time_float (float): The time float to convert.

    Returns:
        float: The custom formatted value.
        
    This function takes a float representing the time in seconds and converts it to a custom 
    score based on predefined thresholds. The purpose of this conversion is to represent 
    the execution time as a compliance score for the standard that tests should be fast. 
    The faster the test execution time, the higher the compliance score, with execution times 
    above 12 seconds resulting in a score of 0.00 (0% compliance), indicating that the standard 
    for fast test execution is not met.
    """
    if isinstance(time_float, float):  # Ensure the input is a float
        if time_float >= 12.0:  # If the execution time is 12 seconds or more
            return 0.00  # Return 0% compliance
        elif time_float >= 10.0:  # If the execution time is between 10 and 12 sec-onds
            return 0.20  # Return 20% compliance
        elif time_float >= 9.0:  # If the execution time is between 9 and 10 sec-onds
            return 0.30  # Return 30% compliance
        elif time_float >= 8.0:  # If the execution time is between 8 and 9 seconds
            return 0.40  # Return 40% compliance
        elif time_float >= 6.0:  # If the execution time is between 6 and 8 seconds
            return 0.60  # Return 60% compliance
        elif time_float >= 4.0:  # If the execution time is between 4 and 6 seconds
            return 0.80  # Return 80% compliance
        elif time_float >= 2.0:  # If the execution time is between 2 and 4 seconds
            return 0.85  # Return 85% compliance
        elif time_float >= 1.5:  # If the execution time is between 1.5 and 2 sec-onds
            return 0.90  # Return 90% compliance
        elif time_float >= 1.0:  # If the execution time is between 1 and 1.5 sec-onds
            return 0.95  # Return 95% compliance
        elif time_float >= 0.1:  # If the execution time is between 0.1 and 1 sec-onds
            return 1.00  # Return 100% compliance
        else:  # If the execution time is less than 0.1 seconds
            return 0.00  # Return 0% compliance (this case might be unrealistic but serves as a boundary check)
    return 0.00  # If the input is not a float, return 0% compliance as a default case

def evaluate_test(file_path, coverage_metrics, build_time_score):
    """
    Evaluate a single test file against standards and calculate compliance.
    
    Args:
        file_path (str): Path to the test file.
        coverage_metrics (dict): Dictionary of coverage metrics.
        build_time_score (float): Score based on build time.
    
    Returns:
        dict: Compliance scores for the standards.
    """
    compliance = {standard['id']: 0 for standard in standards}
    
    with open(file_path, 'r') as file:
        content = file.read()
        
        for standard in standards:
            if standard['id'] == 'namingConventions':
                if re.search(r'should[A-Z][a-z]+[A-Z][a-z]+', content):
                    compliance[standard['id']] = 1
            
            elif standard['id'] == 'arrangeActAssert':
                if re.search(r'// Arrange.*// Act.*// Assert', content, re.DOTALL):
                    compliance[standard['id']] = 1

            elif standard['id'] == 'oneAssertionPerTest':
                test_methods = re.findall(r'@Test\s+public\s+void\s+\w+\s*\([^)]*\)\s*{[^}]*}', content, re.DOTALL)
                for method in test_methods:
                    matches = re.findall(r'assert\w+\(', method)
                    print(f"File: {file_path}, Method: {method.splitlines()[0]}, Assertions found: {len(matches)}")  # Debug print
                    if len(matches) == 1:
                        compliance[standard['id']] = 1
                        break

            elif standard['id'] == 'testDataIsolation':
                if '@BeforeEach' in content and '@AfterEach' in content:
                    compliance[standard['id']] = 1

            elif standard['id'] == 'mockExternalServices':
                if 'Mockito' in content or re.search(r'\bmock\b|\bwhen\b', content):
                    compliance[standard['id']] = 1

            elif standard['id'] == 'coverage':
                total_coverage = sum(coverage_metrics.values()) / len(coverage_metrics)
                compliance[standard['id']] = total_coverage

            elif standard['id'] == 'exceptionHandling':
                if '@Test(expected' in content or 'assertThrows' in content:
                    compliance[standard['id']] = 1

            elif standard['id'] == 'documentation':
                if '/**' in content or '/*' in content:
                    compliance[standard['id']] = 1

            elif standard['id'] == 'assertions':
                matches = re.findall(r'assert\w+\(([^,]+),\s*"([^"]+)"\s*\)', content)
                if matches:
                    compliance[standard['id']] = 1

            elif standard['id'] == 'edgeCaseCoverage':
                edge_cases = ['empty', 'single', 'null', 'zero', 'boundary']
                for case in edge_cases:
                    if case in content.lower():
                        compliance[standard['id']] = 1
                        break

            elif standard['id'] == 'loopConditionCoverage':
                loop_conditions = ['for', 'while']
                for loop in loop_conditions:
                    if loop in content:
                        compliance[standard['id']] = 1
                        break

            elif standard['id'] == 'fileNameConvention':
                if file_path.endswith('Test.java'):
                    compliance[standard['id']] = 1

            elif standard['id'] == 'mainMethodCoverage':
                if 'main' in content.lower() and 'jacoco' in content.lower():
                    compliance[standard['id']] = 1
                elif re.search(r'/\*\*.*main method.*\*/', content, re.DOTALL):
                    compliance[standard['id']] = 1
                elif re.search(r'public.*main', content):
                    compliance[standard['id']] = 1

            elif standard['id'] == 'exceptionCoverage':
                if 'assertThrows' in content:
                    compliance[standard['id']] = 1

            elif standard['id'] == 'testsShouldBeFast':
                compliance[standard['id']] = build_time_score

    print(f"Compliance for {file_path}: {compliance}")  # Debug print
    return compliance  # Return the compliance

def evaluate_directory(directory, coverage_metrics, build_time_score):
    """
    Evaluate all test files in a directory against standards.
    
    Args:
        directory (str): Path to the directory containing test files.
        coverage_metrics (dict): Dictionary of coverage metrics.
        build_time_score (float): Score based on build time.
    
    Returns:
        list: List of compliance results for each test file.
    """
    results = []
    print(f"Evaluating directory: {directory}")  # Debug print
    for root, _, files in os.walk(directory):
        for file in files:
            if file.endswith('.java'):
                file_path = os.path.join(root, file)
                print(f"Evaluating file: {file_path}")  # Debug print
                compliance = evaluate_test(file_path, coverage_metrics, build_time_score)
                compliance['file'] = file_path
                results.append(compliance)
    return results

def parse_jacoco_report(report_path):
    """
    Parse the JaCoCo XML report to extract coverage metrics.
    
    Args:
        report_path (str): Path to the JaCoCo XML report file.
    
    Returns:
        dict: Dictionary of coverage metrics.
    """
    tree = ET.parse(report_path)
    root = tree.getroot()

    coverage_metrics = {
        'statementCoverage': 0,
        'functionCoverage': 0,
        'branchCoverage': 0,
        'pathCoverage': 0
    }

    for counter in root.findall('.//counter'):
        type = counter.get('type')
        missed = int(counter.get('missed'))
        covered = int(counter.get('covered'))

        if type == 'INSTRUCTION':
            coverage_metrics['statementCoverage'] = covered / (covered + missed)
        elif type == 'METHOD':
            coverage_metrics['functionCoverage'] = covered / (covered + missed)
        elif type == 'BRANCH':
            coverage_metrics['branchCoverage'] = covered / (covered + missed)
        elif type == 'LINE':
            coverage_metrics['pathCoverage'] = covered / (covered + missed)

    return coverage_metrics

# Define the file locations
ai_file_location = 'ai-generated-unittest_build-log.txt'
manual_file_location = 'manual-unittest_build-log.txt'

# Parse the build logs
ai_build_time_str = parse_build_log(ai_file_location)
manual_build_time_str = parse_build_log(manual_file_location)

# Convert the build times to floats
ai_build_time_float = convert_time_to_float(ai_build_time_str)
manual_build_time_float = convert_time_to_float(manual_build_time_str)

# Convert the build times to the custom format
ai_build_time_score = convert_to_custom_format(ai_build_time_float)
manual_build_time_score = convert_to_custom_format(manual_build_time_float)

# Parse JaCoCo reports for both directories
ai_coverage = parse_jacoco_report('ai-generation-unittest/target/site/jacoco/jacoco.xml')
manual_coverage = parse_jacoco_report('manual-unittest/target/site/jacoco/jacoco.xml')

# Evaluate both AI-generated and manually written test directories
ai_results = evaluate_directory('ai-generation-unittest/src/test/java/functions', ai_coverage, ai_build_time_score)
manual_results = evaluate_directory('manual-unittest/src/test/java/functions', manual_coverage, manual_build_time_score)

# Add coverage metrics to the results
for result in ai_results:
    result.update(ai_coverage)
for result in manual_results:
    result.update(manual_coverage)

# Convert results to DataFrame
df_ai = pd.DataFrame(ai_results)
df_manual = pd.DataFrame(manual_results)

# Print DataFrames to debug
print("AI DataFrame:\n", df_ai.head())  # Debug print
print("Manual DataFrame:\n", df_manual.head())  # Debug print

# Ensure all columns are of type int or float
if not df_ai.empty:
    df_ai = df_ai.astype({col: 'float' for col in df_ai.columns if col != 'file'})
if not df_manual.empty:
    df_manual = df_manual.astype({col: 'float' for col in df_manual.columns if col != 'file'})

# Calculate total compliance scores
if not df_ai.empty:
    df_ai['total_score'] = df_ai[list(df_ai.columns.difference(['file']))].mean(axis=1)
if not df_manual.empty:
    df_manual['total_score'] = df_manual[list(df_manual.columns.difference(['file']))].mean(axis=1)

# Save results to CSV
if not df_ai.empty:
    df_ai.to_csv('ai_compliance_results.csv', index=False)
if not df_manual.empty:
    df_manual.to_csv('manual_compliance_results.csv', index=False)

# Calculate overall compliance scores
ai_scores = df_ai.mean(numeric_only=True) if not df_ai.empty else pd.Series(dtype=float)
manual_scores = df_manual.mean(numeric_only=True) if not df_manual.empty else pd.Series(dtype=float)

# Convert scores to percentages
ai_scores = ai_scores * 100
manual_scores = manual_scores * 100

# Format the scores to display only two decimal places
ai_scores = ai_scores.apply(lambda x: f"{x:.2f}")
manual_scores = manual_scores.apply(lambda x: f"{x:.2f}")

# Print scores for comparison
print("AI-generated tests compliance scores (%):\n", ai_scores)
print("Manually written tests compliance scores (%):\n", manual_scores)

# Plot the comparison
if not df_ai.empty and not df_manual.empty:
    plt.figure(figsize=(10, 5))
    plt.plot(ai_scores.astype(float), label='AI-generated Tests', marker='o')
    plt.plot(manual_scores.astype(float), label='Manually Written Tests', marker='x')
    plt.xlabel('Standards')
    plt.ylabel('Compliance Score (%)')
    plt.title('Compliance Comparison of AI-generated and Manually Written Tests')
    plt.legend()
    plt.xticks(rotation=90)
    plt.tight_layout()
    plt.savefig('compliance_comparison.png')
    plt.show()
else:
    print("No data available to plot.")
