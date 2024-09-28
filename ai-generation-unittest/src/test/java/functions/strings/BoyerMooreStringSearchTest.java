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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BoyerMooreStringSearch class.
 */
class BoyerMooreStringSearchTest {

  private BoyerMooreStringSearch searcher;

  /**
   * Setup method to initialize the BoyerMooreStringSearch object before each test.
   */
  @BeforeEach
  void setUp() {
    searcher = new BoyerMooreStringSearch();
  }

  /**
   * Teardown method to clean up after each test.
   */
  @AfterEach
  void tearDown() {
    searcher = null;
  }

  /**
   * Tests that findOccurrences returns correct indexes when pattern is found.
   */
  @Test
  void shouldReturnCorrectIndexesWhenPatternIsFound() {
    // Arrange
    String text = "ABABAAABAABAB";
    String pattern = "AA";

    // Act
    List<Integer> result = searcher.findOccurrences(text, pattern);

    // Assert
    List<Integer> expected = Arrays.asList(4, 5, 8); // Update this line
    assertEquals(expected, result, "The method should return correct indexes when pattern is found.");
  }

  /**
   * Tests that findOccurrences returns empty list when pattern is not found.
   */
  @Test
  void shouldReturnEmptyListWhenPatternIsNotFound() {
    // Arrange
    String text = "ABABAAABAABAB";
    String pattern = "XYZ";

    // Act
    List<Integer> result = searcher.findOccurrences(text, pattern);

    // Assert
    assertTrue(result.isEmpty(), "The method should return an empty list when the pattern is not found.");
  }

  /**
   * Tests that findOccurrences returns empty list when text is null.
   */
  @Test
  void shouldReturnEmptyListWhenTextIsNull() {
    // Arrange
    String text = null;
    String pattern = "AA";

    // Act
    List<Integer> result = searcher.findOccurrences(text, pattern);

    // Assert
    assertTrue(result.isEmpty(), "The method should return an empty list when the text is null.");
  }

  /**
   * Tests that findOccurrences returns empty list when pattern is null.
   */
  @Test
  void shouldReturnEmptyListWhenPatternIsNull() {
    // Arrange
    String text = "ABABAAABAABAB";
    String pattern = null;

    // Act
    List<Integer> result = searcher.findOccurrences(text, pattern);

    // Assert
    assertTrue(result.isEmpty(), "The method should return an empty list when the pattern is null.");
  }

  /**
   * Tests that findOccurrences returns empty list when pattern length is greater than text length.
   */
  @Test
  void shouldReturnEmptyListWhenPatternLengthGreaterThanTextLength() {
    // Arrange
    String text = "AB";
    String pattern = "AAA";

    // Act
    List<Integer> result = searcher.findOccurrences(text, pattern);

    // Assert
    assertTrue(result.isEmpty(), "The method should return an empty list when the pattern length is greater than the text length.");
  }

  /**
   * Tests that findOccurrences returns empty list when pattern length is zero.
   */
  @Test
  void shouldReturnEmptyListWhenPatternLengthIsZero() {
    // Arrange
    String text = "ABABAAABAABAB";
    String pattern = "";

    // Act
    List<Integer> result = searcher.findOccurrences(text, pattern);

    // Assert
    assertTrue(result.isEmpty(), "The method should return an empty list when the pattern length is zero.");
  }

  /**
   * Tests that the skip table is correctly generated.
   */
  @Test
  void shouldGenerateCorrectSkipTable() {
    // Arrange
    String pattern = "ABCD";
    int[] expectedSkipTable = new int[256];
    expectedSkipTable['A'] = 0;
    expectedSkipTable['B'] = 1;
    expectedSkipTable['C'] = 2;
    expectedSkipTable['D'] = 3;

    // Act
    int[] skipTable = searcher.generateSkipTable(pattern);

    // Assert
    assertArrayEquals(expectedSkipTable, skipTable, "The skip table should be correctly generated.");
  }

  /**
   * Tests the main method by capturing its output.
   */
  @Test
  void shouldPrintCorrectOutputWhenMainIsExecuted() {
    // Arrange
    String[] args = {};
    PrintStream originalOut = System.out;
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    // Act
    BoyerMooreStringSearch.main(args);

    // Assert
    String expectedOutput = "[4, 5, 8]";
    assertTrue(outContent.toString().trim().endsWith(expectedOutput), "The main method should print the correct output.");

    // Restore the original System.out
    System.setOut(originalOut);
  }
}
