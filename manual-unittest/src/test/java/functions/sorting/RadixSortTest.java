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

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class RadixSortTest {
  static Random random = new Random();

  @Test
  public void testGetMax() {
    int[] array = {5, 7, 1, 13, 1013, 287, 912};
    assertThat(RadixSort.getMax(array)).isEqualTo(1013);
  }

  @Test
  public void testCalculateNumberOfDigits() {
    assertThat(RadixSort.calculateNumberOfDigits(1089)).isEqualTo(4);
    assertThat(RadixSort.calculateNumberOfDigits(19)).isEqualTo(2);
  }

  @Test
  public void randomRadixSort_smallNumbers() {
    for (int size = 0; size < 1000; size++) {
      int[] values = new int[size];
      for (int i = 0; i < size; i++) {
        values[i] = randInt(1, 50);
      }
      int[] copy = values.clone();

      Arrays.sort(values);
      RadixSort.radixSort(copy);

      assertThat(values).isEqualTo(copy);
    }
  }

  @Test
  public void randomRadixSort_largeNumbers() {
    for (int size = 0; size < 1000; size++) {
      int[] values = new int[size];
      for (int i = 0; i < size; i++) {
        values[i] = randInt(1, Integer.MAX_VALUE);
      }
      int[] copy = values.clone();

      Arrays.sort(values);
      RadixSort.radixSort(copy);

      assertThat(values).isEqualTo(copy);
    }
  }

  // return a random number between [min, max]
  static int randInt(int min, int max) {
    return random.nextInt((max - min) + 1) + min;
  }
}
