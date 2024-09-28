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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for BitManipulations.
 * Ensures that the bit manipulation methods perform as expected.
 */
class BitManipulationsTest {

  /**
   * Tests setBit method to set a specific bit.
   */
  @Test
  void shouldSetBitWhenGivenPosition() {
    // Arrange
    int value = 0b0000;
    int position = 2;

    // Act
    int result = BitManipulations.setBit(value, position);

    // Assert
    assertEquals(0b0100, result, "Expected bit at position 2 to be set");
  }

  /**
   * Tests isSet method to check if a specific bit is set.
   */
  @Test
  void shouldReturnTrueWhenBitIsSet() {
    // Arrange
    int value = 0b0100;
    int position = 2;

    // Act
    boolean result = BitManipulations.isSet(value, position);

    // Assert
    assertTrue(result, "Expected bit at position 2 to be set");
  }

  /**
   * Tests isSet method to check if a specific bit is not set.
   */
  @Test
  void shouldReturnFalseWhenBitIsNotSet() {
    // Arrange
    int value = 0b0000;
    int position = 2;

    // Act
    boolean result = BitManipulations.isSet(value, position);

    // Assert
    assertFalse(result, "Expected bit at position 2 to not be set");
  }

  /**
   * Tests clearBit method to clear a specific bit.
   */
  @Test
  void shouldClearBitWhenGivenPosition() {
    // Arrange
    int value = 0b0100;
    int position = 2;

    // Act
    int result = BitManipulations.clearBit(value, position);

    // Assert
    assertEquals(0b0000, result, "Expected bit at position 2 to be cleared");
  }

  /**
   * Tests toggleBit method to toggle a specific bit.
   */
  @Test
  void shouldToggleBitWhenGivenPosition() {
    // Arrange
    int value = 0b0100;
    int position = 2;

    // Act
    int result = BitManipulations.toggleBit(value, position);

    // Assert
    assertEquals(0b0000, result, "Expected bit at position 2 to be toggled");
  }

  /**
   * Tests setAll method to set the first n bits to 1.
   */
  @Test
  void shouldSetAllBitsWhenGivenNumber() {
    // Arrange
    int numberOfBits = 3;

    // Act
    int result = BitManipulations.setAll(numberOfBits);

    // Assert
    assertEquals(0b0111, result, "Expected first 3 bits to be set");
  }

  /**
   * Tests isPowerOfTwo method to check if a number is a power of two.
   */
  @Test
  void shouldReturnTrueWhenNumberIsPowerOfTwo() {
    // Arrange
    int number = 8;

    // Act
    boolean result = BitManipulations.isPowerOfTwo(number);

    // Assert
    assertTrue(result, "Expected 8 to be a power of two");
  }

  /**
   * Tests isPowerOfTwo method to check if a number is not a power of two.
   */
  @Test
  void shouldReturnFalseWhenNumberIsNotPowerOfTwo() {
    // Arrange
    int number = 7;

    // Act
    boolean result = BitManipulations.isPowerOfTwo(number);

    // Assert
    assertFalse(result, "Expected 7 to not be a power of two");
  }

  /**
   * Tests isPowerOfTwo method with zero.
   */
  @Test
  void shouldReturnFalseWhenNumberIsZero() {
    // Arrange
    int number = 0;

    // Act
    boolean result = BitManipulations.isPowerOfTwo(number);

    // Assert
    assertFalse(result, "Expected 0 to not be a power of two");
  }

  /**
   * Tests isPowerOfTwo method with negative number.
   */
  @Test
  void shouldReturnFalseWhenNumberIsNegative() {
    // Arrange
    int number = -8;

    // Act
    boolean result = BitManipulations.isPowerOfTwo(number);

    // Assert
    assertFalse(result, "Expected -8 to not be a power of two");
  }
}
