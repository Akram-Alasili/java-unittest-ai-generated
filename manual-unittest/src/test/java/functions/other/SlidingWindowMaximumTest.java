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

public class SlidingWindowMaximumTest {

  final int TESTS = 1500;

  @Test
  public void smallWindowTest() {

    int[] values = {1, 2, 1, 3, 0, 4};
    SlidingWindowMaximum w = new SlidingWindowMaximum(values);

    w.advance();
    assertThat(w.getMax()).isEqualTo(1);
    w.advance();
    assertThat(w.getMax()).isEqualTo(2);
    w.advance();
    assertThat(w.getMax()).isEqualTo(2);
    w.shrink();
    assertThat(w.getMax()).isEqualTo(2);
    w.shrink();
    assertThat(w.getMax()).isEqualTo(1);
    w.advance();
    assertThat(w.getMax()).isEqualTo(3);
    w.advance();
    assertThat(w.getMax()).isEqualTo(3);
    w.advance();
    assertThat(w.getMax()).isEqualTo(4);
    w.shrink();
    assertThat(w.getMax()).isEqualTo(4);
    w.shrink();
    assertThat(w.getMax()).isEqualTo(4);
    w.shrink();
    assertThat(w.getMax()).isEqualTo(4);
  }

  @Test
  public void randomizedSlidingWindowTest() {
    for (int sz = 1; sz <= TESTS; sz++) {
      randomizedTest(sz);
    }
  }

  private static void fillRandom(int[] ar) {
    for (int i = 0; i < ar.length; i++) {
      if (Math.random() < 0.5) {
        ar[i] = (int) (Math.random() * +25);
      } else {
        ar[i] = (int) (Math.random() * -25);
      }
    }
  }

  public static void randomizedTest(int n) {

    double r = Math.max(0.1, Math.random());
    int[] ar = new int[n];
    fillRandom(ar);

    SlidingWindowMaximum window = new SlidingWindowMaximum(ar);
    int lo = 0, hi = 0;
    while (hi < n) {

      // increase hi
      if (Math.random() < r) {
        window.advance();
        hi++;

        // increase lo if we can
      } else {
        if (lo + 1 < hi) {
          lo++;
          window.shrink();
        }
      }

      // Ignore invalid queries
      if (window.lo == window.hi) continue;

      // Manually find the window maximum
      int max = Integer.MIN_VALUE;
      for (int i = lo; i < hi; i++) max = Math.max(max, ar[i]);

      assertThat(window.getMax()).isEqualTo(max);
    }
  }
}
