/**
 * MIT License
 *
 * Copyright (c) 2017 William Fiset

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package functions.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the InterpolationSearch class.
 */
class InterpolationSearchTest {

  // Helper method to capture System.out.println output
  private String captureOutput(Runnable runnable) {
    java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
    java.io.PrintStream originalOut = System.out;
    System.setOut(new java.io.PrintStream(outContent));

    // Run the provided runnable
    runnable.run();

    // Restore original System.out
    System.setOut(originalOut);

    return outContent.toString();
  }

  /**
   * Tests if the interpolationSearch method finds the value when present in the array.
   */
  @Test
  void shouldFindValueAtCorrectIndexWhenPresent() {
    // Arrange
    int[] values = {10, 20, 25, 35, 50, 70, 85, 100, 110, 120, 125};
    int valueToFind = 25;
    int expectedIndex = 2;

    // Act
    int actualIndex = InterpolationSearch.interpolationSearch(values, valueToFind);

    // Assert
    assertEquals(expectedIndex, actualIndex, "Expected to find value 25 at index 2");
  }

  /**
   * Tests if the interpolationSearch method returns -1 when the value is not present in the array.
   */
  @Test
  void shouldReturnNegativeOneWhenValueNotPresent() {
    // Arrange
    int[] values = {10, 20, 25, 35, 50, 70, 85, 100, 110, 120, 125};
    int valueToFind = 111;
    int expectedIndex = -1;

    // Act
    int actualIndex = InterpolationSearch.interpolationSearch(values, valueToFind);

    // Assert
    assertEquals(expectedIndex, actualIndex, "Expected to not find value 111 and return -1");
  }

  /**
   * Tests if the interpolationSearch method finds the first value in the array.
   */
  @Test
  void shouldFindFirstValueInArray() {
    // Arrange
    int[] values = {10, 20, 25, 35, 50, 70, 85, 100, 110, 120, 125};
    int valueToFind = 10;
    int expectedIndex = 0;

    // Act
    int actualIndex = InterpolationSearch.interpolationSearch(values, valueToFind);

    // Assert
    assertEquals(expectedIndex, actualIndex, "Expected to find value 10 at index 0");
  }

  /**
   * Tests if the interpolationSearch method finds the last value in the array.
   */
  @Test
  void shouldFindLastValueInArray() {
    // Arrange
    int[] values = {10, 20, 25, 35, 50, 70, 85, 100, 110, 120, 125};
    int valueToFind = 125;
    int expectedIndex = 10;

    // Act
    int actualIndex = InterpolationSearch.interpolationSearch(values, valueToFind);

    // Assert
    assertEquals(expectedIndex, actualIndex, "Expected to find value 125 at index 10");
  }

  /**
   * Tests if the main method of InterpolationSearch prints the correct output.
   */
  @Test
  void shouldPrintCorrectOutputForMainMethod() {
    // Arrange
    String expectedOutput = "2\n-1\n";

    // Act
    String actualOutput = captureOutput(() -> InterpolationSearch.main(null)).replaceAll("\\r\\n?", "\n");

    // Assert
    assertEquals(expectedOutput, actualOutput, "Expected main method to print correct output");
  }

  /**
   * Tests if the interpolationSearch method returns -1 when the value is not found in the array.
   */
  @Test
  void shouldReturnNegativeOneWhenValueNotFound() {
    // Arrange
    int[] nums = {1, 2, 3, 4, 5};
    int val = 6;

    // Act
    int result = InterpolationSearch.interpolationSearch(nums, val);

    // Assert
    assertEquals(-1, result, "Value not in array should return -1");
  }

}
