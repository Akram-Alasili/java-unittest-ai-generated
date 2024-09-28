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

package functions.sorting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit tests for the QuickSelect class.
 */
class QuickSelectTest {
  private final PrintStream originalOut = System.out;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private QuickSelect quickSelect;
  private int[] array;

  /**
   * Sets up the test data before each test.
   */
  @BeforeEach
  void setUp() {
    quickSelect = new QuickSelect();
    array = new int[]{-10, 4, 6, 4, 8, -13, 1, 3};
  }

  /**
   * Tears down the test data after each test.
   */
  @AfterEach
  void tearDown() {
    quickSelect = null;
    array = null;
  }

  /**
   * Tests quickSelect method for a valid k value.
   */
  @Test
  void shouldReturnKthLargestElementWhenKIsValid() {
    // Arrange
    int k = 3;

    // Act
    Integer result = quickSelect.quickSelect(array, k);

    // Assert
    assertEquals(1, result, "Expected 3rd largest element to be 1");
  }

  /**
   * Tests quickSelect method for null array.
   */
  @Test
  void shouldReturnNullWhenArrayIsNull() {
    // Arrange
    int k = 3;
    int[] nullArray = null;

    // Act
    Integer result = quickSelect.quickSelect(nullArray, k);

    // Assert
    assertNull(result, "Expected result to be null when array is null");
  }

  /**
   * Tests quickSelect method for k value greater than array length.
   */
  @Test
  void shouldReturnNullWhenKIsGreaterThanArrayLength() {
    // Arrange
    int k = 10;

    // Act
    Integer result = quickSelect.quickSelect(array, k);

    // Assert
    assertNull(result, "Expected result to be null when k is greater than array length");
  }

  /**
   * Tests quickSelect method for k value less than 1.
   */
  @Test
  void shouldReturnNullWhenKIsLessThanOne() {
    // Arrange
    int k = 0;

    // Act
    Integer result = quickSelect.quickSelect(array, k);

    // Assert
    assertNull(result, "Expected result to be null when k is less than 1");
  }

  /**
   * Tests quickSelect method for an array with one element.
   */
  @Test
  void shouldHandleSingleElementArray() {
    // Arrange
    int[] singleElementArray = new int[]{5};
    int k = 1;

    // Act
    Integer result = quickSelect.quickSelect(singleElementArray, k);

    // Assert
    assertEquals(5, result, "Expected single element to be 5");
  }

  /**
   * Tests quickSelect method for an empty array.
   */
  @Test
  void shouldHandleEmptyArray() {
    // Arrange
    int[] emptyArray = new int[]{};
    int k = 1;

    // Act
    Integer result = quickSelect.quickSelect(emptyArray, k);

    // Assert
    assertNull(result, "Expected result to be null for empty array");
  }
  /**
   * Tests the runMain method for a positive scenario.
   * Should print the kth smallest element when valid array is provided.
   */
  @Test
  void shouldPrintThirdLargestElement() {
    // Arrange
    int[] array = {-10, 4, 6, 4, 8, -13, 1, 3};
    QuickSelect quickSelect = new QuickSelect();
    String expectedOutput = ("1" + System.lineSeparator()).replace("\r\n", "\n").replace("\r", "\n"); // Normalize line separators in expected output
    System.setOut(new PrintStream(outContent));

    // Act
    QuickSelect.main(null); // main method is static, typically no args are passed for this test

    // Assert
    String actualOutput = outContent.toString().replace("\r\n", "\n").replace("\r", "\n"); // Normalize line separators
    assertEquals(expectedOutput, actualOutput);

    // Reset the standard output to its original
    System.setOut(originalOut);
  }
}
