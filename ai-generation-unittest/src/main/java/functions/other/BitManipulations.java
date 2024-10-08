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
/**
 * Fundamental bit manipulation operations you must know Time Complexity: O(1)
 *
 * @author Micah Stairs
 */
package functions.other;

public class BitManipulations {

  // Sets the i'th bit to 1
  public static int setBit(int set, int i) {
    return set | (1 << i);
  }

  // Checks if the i'th is set
  public static boolean isSet(int set, int i) {
    return (set & (1 << i)) != 0;
  }

  // Sets the i'th bit to zero
  public static int clearBit(int set, int i) {
    return set & ~(1 << i);
  }

  // Toggles the i'th bit from 0 -> 1 or 1 -> 0
  public static int toggleBit(int set, int i) {
    return set ^ (1 << i);
  }

  // Returns a number with the first n bits set to 1
  public static int setAll(int n) {
    return (1 << n) - 1;
  }

  // Verifies if a number n is a power of two
  public static boolean isPowerOfTwo(int n) {
    return n > 0 && (n & (n - 1)) == 0;
  }
}
