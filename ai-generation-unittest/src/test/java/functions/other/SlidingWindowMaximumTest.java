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

/**
 * Unit test class for SlidingWindowMaximum.
 * Ensures that the SlidingWindowMaximum class correctly computes the maximum values in a sliding window.
 */
class SlidingWindowMaximumTest {

  private SlidingWindowMaximum slidingWindow;

  /**
   * Sets up the initial state before each test.
   */
  @BeforeEach
  void setUp() {
    int[] values = {1, 3, -1, -3, 5, 3, 6, 7};
    slidingWindow = new SlidingWindowMaximum(values);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException for null values.
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenValuesAreNull() {
    assertThrows(IllegalArgumentException.class, () -> {
      new SlidingWindowMaximum(null);
    });
  }

  /**
   * Tests that the advance method updates the deque and hi pointer correctly.
   */
  @Test
  void shouldAdvanceWindowAndUpdateDequeCorrectly() {
    // Arrange
    slidingWindow.hi = 0;

    // Act
    slidingWindow.advance();
    slidingWindow.advance();

    // Assert
    assertEquals(2, slidingWindow.hi, "Expected hi to be updated to 2");
    assertEquals(Integer.valueOf(1), slidingWindow.deque.peekFirst(), "Expected deque to have the correct values");
  }

  /**
   * Tests that the shrink method updates the deque and lo pointer correctly.
   */
  @Test
  void shouldShrinkWindowAndUpdateDequeCorrectly() {
    // Arrange
    slidingWindow.hi = 0;
    slidingWindow.advance(); // hi = 1, deque = [0]
    slidingWindow.advance(); // hi = 2, deque = [1]
    slidingWindow.advance(); // hi = 3, deque = [1, 2]
    slidingWindow.shrink();  // lo = 1, deque = [1, 2]

    // Act
    slidingWindow.shrink();  // lo = 2, deque = [2]

    // Assert
    assertEquals(2, slidingWindow.lo, "Expected lo to be updated to 2");
    assertEquals(1, slidingWindow.deque.size(), "Expected deque to have one element");
    assertEquals(Integer.valueOf(2), slidingWindow.deque.peekFirst(), "Expected deque to have the correct value");
  }

  /**
   * Tests that the getMax method returns the correct maximum value in the window.
   */
  @Test
  void shouldReturnCorrectMaxValueInWindow() {
    // Arrange
    slidingWindow.hi = 0;
    slidingWindow.advance();
    slidingWindow.advance();
    slidingWindow.advance();

    // Act
    int max = slidingWindow.getMax();

    // Assert
    assertEquals(3, max, "Expected max value to be 3");
  }

  /**
   * Tests that the getMax method throws an IllegalStateException when lo >= hi.
   */
  @Test
  void shouldThrowIllegalStateExceptionWhenLoGreaterOrEqualHi() {
    assertThrows(IllegalStateException.class, () -> {
      slidingWindow.getMax();
    });
  }

  /**
   * Tests that the getMax method correctly handles edge cases.
   */
  @Test
  void shouldHandleEdgeCasesCorrectly() {
    // Arrange
    int[] values = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    SlidingWindowMaximum edgeCaseWindow = new SlidingWindowMaximum(values);

    // Act
    for (int i = 0; i < values.length; i++) {
      edgeCaseWindow.advance();
    }

    // Assert
    assertEquals(10, edgeCaseWindow.getMax(), "Expected max value to be 10");
  }

  /**
   * Tests the sliding window maximum algorithm with a larger dataset.
   */
  @Test
  void shouldComputeMaxForLargerDataset() {
    // Arrange
    int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    SlidingWindowMaximum largerWindow = new SlidingWindowMaximum(values);

    // Act
    for (int i = 0; i < values.length; i++) {
      largerWindow.advance();
    }

    // Assert
    assertEquals(10, largerWindow.getMax(), "Expected max value to be 10");
  }

  /**
   * Tests that the deque is properly maintained for increasing values.
   */
  @Test
  void shouldMaintainDequeCorrectlyForIncreasingValues() {
    // Arrange
    int[] values = {1, 2, 3, 4, 5};
    SlidingWindowMaximum increasingWindow = new SlidingWindowMaximum(values);

    // Act
    for (int i = 0; i < values.length; i++) {
      increasingWindow.advance();
    }

    // Assert
    assertEquals(5, increasingWindow.getMax(), "Expected max value to be 5");
  }

  /**
   * Tests that the deque is properly maintained for decreasing values.
   */
  @Test
  void shouldMaintainDequeCorrectlyForDecreasingValues() {
    // Arrange
    int[] values = {5, 4, 3, 2, 1};
    SlidingWindowMaximum decreasingWindow = new SlidingWindowMaximum(values);

    // Act
    for (int i = 0; i < values.length; i++) {
      decreasingWindow.advance();
    }

    // Assert
    assertEquals(5, decreasingWindow.getMax(), "Expected max value to be 5");
  }
}
