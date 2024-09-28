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

package functions.strings;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

/**
 * Unit tests for ZAlgorithm class.
 */
class ZAlgorithmTest {

  /**
   * Tests that the calculateZ method returns the correct Z-array
   * for a given string.
   */
  @Test
  void shouldCalculateZArrayForGivenString() {
    // Arrange
    ZAlgorithm zAlgorithm = new ZAlgorithm();
    String text = "aabcaabxaaaz";
    int[] expected = {12, 1, 0, 0, 3, 1, 0, 0, 2, 2, 1, 0}; // Adjusted expected result

    // Act
    int[] actual = zAlgorithm.calculateZ(text);

    // Assert
    assertArrayEquals(expected, actual, "Expected Z-array to be correctly calculated for the given string");
  }

  /**
   * Tests that the calculateZ method returns an empty array
   * when the input string is null.
   */
  @Test
  void shouldReturnEmptyArrayWhenNullString() {
    // Arrange
    ZAlgorithm zAlgorithm = new ZAlgorithm();
    String text = null;
    int[] expected = {};

    // Act
    int[] actual = zAlgorithm.calculateZ(text);

    // Assert
    assertArrayEquals(expected, actual, "Expected an empty array when the input string is null");
  }

  /**
   * Tests that the calculateZ method returns the correct Z-array
   * for an empty string.
   */
  @Test
  void shouldReturnEmptyArrayWhenEmptyString() {
    // Arrange
    ZAlgorithm zAlgorithm = new ZAlgorithm();
    String text = "";
    int[] expected = {};

    // Act
    int[] actual = zAlgorithm.calculateZ(text);

    // Assert
    assertArrayEquals(expected, actual, "Expected an empty array when the input string is empty");
  }

  /**
   * Tests that the calculateZ method returns the correct Z-array
   * for a single character string.
   */
  @Test
  void shouldReturnCorrectZArrayWhenSingleCharacterString() {
    // Arrange
    ZAlgorithm zAlgorithm = new ZAlgorithm();
    String text = "a";
    int[] expected = {1};

    // Act
    int[] actual = zAlgorithm.calculateZ(text);

    // Assert
    assertArrayEquals(expected, actual, "Expected Z-array to be correctly calculated for a single character string");
  }

  /**
   * Tests that the calculateZ method returns the correct Z-array
   * for a string with repeated characters.
   */
  @Test
  void shouldReturnCorrectZArrayWhenStringWithRepeatedCharacters() {
    // Arrange
    ZAlgorithm zAlgorithm = new ZAlgorithm();
    String text = "aaaaaa";
    int[] expected = {6, 5, 4, 3, 2, 1};

    // Act
    int[] actual = zAlgorithm.calculateZ(text);

    // Assert
    assertArrayEquals(expected, actual, "Expected Z-array to be correctly calculated for a string with repeated characters");
  }

  /**
   * Tests that the calculateZ method returns the correct Z-array
   * for a string with no repeated characters.
   */
  @Test
  void shouldReturnCorrectZArrayWhenStringWithNoRepeatedCharacters() {
    // Arrange
    ZAlgorithm zAlgorithm = new ZAlgorithm();
    String text = "abcdef";
    int[] expected = {6, 0, 0, 0, 0, 0};

    // Act
    int[] actual = zAlgorithm.calculateZ(text);

    // Assert
    assertArrayEquals(expected, actual, "Expected Z-array to be correctly calculated for a string with no repeated characters");
  }
}
