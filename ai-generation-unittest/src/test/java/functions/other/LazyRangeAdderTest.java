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

package functions.other;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit test class for LazyRangeAdder.
 * Ensures that the LazyRangeAdder class performs range addition updates correctly.
 */
class LazyRangeAdderTest {

  private LazyRangeAdder lazyRangeAdder;
  private int[] array;

  /**
   * Sets up the initial state before each test.
   */
  @BeforeEach
  void setUp() {
    array = new int[]{10, 4, 6, 13, 8, 15, 17, 22};
    lazyRangeAdder = new functions.other.LazyRangeAdder(array);
  }

  /**
   * Tests that the add method updates the array correctly for a valid range.
   */
  @Test
  void shouldUpdateArrayWhenRangeAndValueAreValid() {
    // Arrange
    int l = 1;
    int r = 4;
    int x = 10;

    // Act
    lazyRangeAdder.add(l, r, x);
    lazyRangeAdder.done();

    // Assert
    int[] expectedArray = {10, 14, 16, 23, 18, 15, 17, 22};
    assertArrayEquals(expectedArray, array, "Expected array to be updated correctly");
  }

  /**
   * Tests that the add method updates the array correctly for multiple range updates.
   */
  @Test
  void shouldUpdateArrayWhenMultipleRangeUpdatesArePerformed() {
    // Arrange
    lazyRangeAdder.add(1, 4, 10);
    lazyRangeAdder.done();
    lazyRangeAdder.add(3, 6, -5);
    lazyRangeAdder.add(0, 7, 12);

    // Act
    lazyRangeAdder.done();

    // Assert
    int[] expectedArray = {22, 26, 28, 30, 25, 22, 24, 34};
    assertArrayEquals(expectedArray, array, "Expected array to be updated correctly after multiple updates");
  }

  /**
   * Tests that the add method throws an ArrayIndexOutOfBoundsException for a negative index.
   */
  @Test
  void shouldThrowArrayIndexOutOfBoundsExceptionWhenIndexIsNegative() {
    // Arrange
    int l = -1;
    int r = 3;
    int x = 10;

    // Act & Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
      lazyRangeAdder.add(l, r, x);
    }, "Expected ArrayIndexOutOfBoundsException when index is negative");
  }

  /**
   * Tests that the add method throws an ArrayIndexOutOfBoundsException for an index out of bounds.
   */
  @Test
  void shouldThrowArrayIndexOutOfBoundsExceptionWhenIndexIsOutOfBounds() {
    // Arrange
    int l = 0;
    int r = 8;
    int x = 10;

    // Act & Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
      lazyRangeAdder.add(l, r, x);
    }, "Expected ArrayIndexOutOfBoundsException when index is out of bounds");
  }

  /**
   * Tests that the done method does not change the array when no add operations are performed.
   */
  @Test
  void shouldNotChangeArrayWhenNoAddOperationsPerformed() {
    // Act
    lazyRangeAdder.done();

    // Assert
    int[] expectedArray = {10, 4, 6, 13, 8, 15, 17, 22};
    assertArrayEquals(expectedArray, array, "Expected array to remain unchanged");
  }

  /**
   * Tests the main method for correct range updates.
   */
  @Test
  void shouldUpdateArrayCorrectlyWhenMainMethodIsCalled() {
    // Arrange
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    // Act
    LazyRangeAdder.main(null);

    // Assert
    String expectedOutput = "[10, 14, 16, 23, 18, 15, 17, 22]" + System.lineSeparator() +
            "[22, 26, 28, 30, 25, 22, 24, 34]" + System.lineSeparator();
    assertEquals(expectedOutput.replace(System.lineSeparator(), "\n"),
            outContent.toString().replace(System.lineSeparator(), "\n"),
            "Expected output to match the result of main method");

    // Restore original System.out
    System.setOut(originalOut);
  }
}
