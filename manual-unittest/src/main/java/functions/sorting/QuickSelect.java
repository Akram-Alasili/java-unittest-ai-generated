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

public class QuickSelect {

  public Integer quickSelect(int[] ar, int k) {
    if (ar == null) return null;
    if (k > ar.length) return null;
    if (k < 1) return null;
    return quickSelect(ar, k, 0, ar.length - 1);
  }

  // Sort interval [lo, hi] inplace recursively, returns value when splitPoint == k - 1
  private static Integer quickSelect(int[] ar, int k, int lo, int hi) {
    int index = k - 1;
    if (lo < hi) {
      int splitPoint = partition(ar, lo, hi);
      if (splitPoint == index) {
        return ar[splitPoint];
      } else if (splitPoint > index) {
        return quickSelect(ar, k, lo, splitPoint);
      }
      return quickSelect(ar, k, splitPoint + 1, hi);
    }
    return ar[lo];
  }

  // Performs Hoare partition algorithm for quick select, taken from QuickSelect implementation
  private static int partition(int[] ar, int lo, int hi) {
    int pivot = ar[lo];
    int i = lo - 1, j = hi + 1;
    while (true) {
      do {
        i++;
      } while (ar[i] < pivot);
      do {
        j--;
      } while (ar[j] > pivot);
      if (i < j) swap(ar, i, j);
      else return j;
    }
  }

  // Swap two elements
  private static void swap(int[] ar, int i, int j) {
    int tmp = ar[i];
    ar[i] = ar[j];
    ar[j] = tmp;
  }

  public static void main(String[] args) {
    QuickSelect quickSelect = new QuickSelect();
    int[] array = {-10, 4, 6, 4, 8, -13, 1, 3};
    int kthLargestElement = quickSelect.quickSelect(array, 3);
    // Prints: 1
    System.out.println(kthLargestElement);
  }
}
