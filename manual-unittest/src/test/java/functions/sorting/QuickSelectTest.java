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

import functions.utils.TestUtils;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class QuickSelectTest {

  @Test
  public void testQuickSelect() {
    for (int size = 1; size < 500; size++) {
      // Given
      QuickSelect quickSelect = new QuickSelect();
      int[] values = TestUtils.randomIntegerArray(size, -100, 100);

      for (int k = 1; k <= size; k++) {
        int[] copy = values.clone();
        Arrays.sort(values);

        // When
        int kthLargestElement = quickSelect.quickSelect(copy, k);

        // Then
        assertThat(kthLargestElement).isEqualTo(values[k - 1]);
      }
    }
  }
}
