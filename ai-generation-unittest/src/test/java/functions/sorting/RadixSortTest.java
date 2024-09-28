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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Unit tests for the RadixSort class.
 */
class RadixSortTest {

  private RadixSort sorter;

  @BeforeEach
  void setUp() {
    sorter = new RadixSort();
  }

  /**
   * Test radix sort with a typical array of positive integers.
   */
  @Test
  void shouldSortArrayOfPositiveIntegers() {
    // Arrange
    int[] numbers = {387, 468, 134, 123, 68, 221, 769, 37, 7, 890, 1, 587};
    int[] expected = {1, 7, 37, 68, 123, 134, 221, 387, 468, 587, 769, 890};

    // Act
    sorter.sort(numbers);

    // Assert
    assertArrayEquals(expected, numbers);
  }

  /**
   * Test radix sort with an empty array.
   */
  @Test
  void shouldHandleEmptyArray() {
    // Arrange
    int[] numbers = {};
    int[] expected = {};

    // Act
    sorter.sort(numbers);

    // Assert
    assertArrayEquals(expected, numbers);
  }

  /**
   * Test radix sort with an array of a single element.
   */
  @Test
  void shouldHandleSingleElementArray() {
    // Arrange
    int[] numbers = {42};
    int[] expected = {42};

    // Act
    sorter.sort(numbers);

    // Assert
    assertArrayEquals(expected, numbers);
  }

  /**
   * Test radix sort with an array of already sorted elements.
   */
  @Test
  void shouldHandleAlreadySortedArray() {
    // Arrange
    int[] numbers = {1, 2, 3, 4, 5};
    int[] expected = {1, 2, 3, 4, 5};

    // Act
    sorter.sort(numbers);

    // Assert
    assertArrayEquals(expected, numbers);
  }

  /**
   * Test radix sort with an array containing duplicates.
   */
  @Test
  void shouldSortArrayWithDuplicateElements() {
    // Arrange
    int[] numbers = {5, 3, 8, 3, 5, 9, 1, 8};
    int[] expected = {1, 3, 3, 5, 5, 8, 8, 9};

    // Act
    sorter.sort(numbers);

    // Assert
    assertArrayEquals(expected, numbers);
  }

  /**
   * Test radix sort with the maximum integer values.
   */
  @Test
  void shouldSortArrayWithMaxIntegerValues() {
    // Arrange
    int[] numbers = {Integer.MAX_VALUE, Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 2};
    int[] expected = {Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, Integer.MAX_VALUE};

    // Act
    sorter.sort(numbers);

    // Assert
    assertArrayEquals(expected, numbers);
  }

  /**
   * Test radix sort with a null array.
   */
  @Test
  void shouldHandleNullArray() {
    // Arrange
    int[] numbers = null;

    // Act
    sorter.sort(numbers);

    // Assert
    assertArrayEquals(null, numbers);
  }

  /**
   * Test the main method of RadixSort.
   */
  @Test
  void shouldSortArrayUsingMainMethod() {
    // Arrange
    String[] args = {};
    int[] expected = {1, 7, 37, 68, 123, 134, 221, 387, 468, 587, 769, 890};

    // Act
    RadixSort.main(args);

    // Assert
    int[] numbers = {387, 468, 134, 123, 68, 221, 769, 37, 7, 890, 1, 587};
    Arrays.sort(numbers);
    assertArrayEquals(expected, numbers);
  }
}
