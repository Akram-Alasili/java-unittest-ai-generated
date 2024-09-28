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

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.*;

public class BitManipulationsTest {

  @Test
  public void testSetBit() {
    assertThat(BitManipulations.setBit(0, 0)).isEqualTo(0b1);
    assertThat(BitManipulations.setBit(0, 1)).isEqualTo(0b10);
    assertThat(BitManipulations.setBit(0, 2)).isEqualTo(0b100);
    assertThat(BitManipulations.setBit(0, 3)).isEqualTo(0b1000);
    assertThat(BitManipulations.setBit(0, 4)).isEqualTo(0b10000);
    assertThat(BitManipulations.setBit(0, 5)).isEqualTo(0b100000);
  }

  @Test
  public void testPowerOfTwo() {
    assertThat(BitManipulations.isPowerOfTwo(0)).isFalse();
    assertThat(BitManipulations.isPowerOfTwo(-1)).isFalse();
    assertThat(BitManipulations.isPowerOfTwo(7)).isFalse();
    assertThat(BitManipulations.isPowerOfTwo(9)).isFalse();
    assertThat(BitManipulations.isPowerOfTwo(123456789)).isFalse();

    assertThat(BitManipulations.isPowerOfTwo(1)).isTrue();
    assertThat(BitManipulations.isPowerOfTwo(2)).isTrue();
    assertThat(BitManipulations.isPowerOfTwo(4)).isTrue();
    assertThat(BitManipulations.isPowerOfTwo(2048)).isTrue();
    assertThat(BitManipulations.isPowerOfTwo(1 << 20)).isTrue();
  }

  @Test
  public void testClearBit() {
    assertThat(BitManipulations.clearBit(0b0000, 1)).isEqualTo(0);
    assertThat(BitManipulations.clearBit(0b0100, 2)).isEqualTo(0);
    assertThat(BitManipulations.clearBit(0b0001, 0)).isEqualTo(0);
    assertThat(BitManipulations.clearBit(0b1111, 0)).isEqualTo(14);
  }
}
