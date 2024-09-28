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
 * An implementation of Radix Sort.
 *
 * <p>See https://en.wikipedia.org/wiki/Radix_sort for details on runtime and complexity Radix sorts
 * operates in O(nw) time, where n is the number of keys, and w is the key length where w is
 * constant on primitive types like Integer which gives it a better performance than other
 * compare-based sort algorithms, like i.e. QuickSort
 *
 * <p>Time Complexity: O(nw)
 *
 * <p>Run with:
 *
 * <p>$ ./gradlew run -Palgorithm=sorting.RadixSort
 *
 * @author EAlexa
 */
package functions.sorting;

public class RadixSort implements InplaceSort {


  public void sort(int[] values) {
    RadixSort.radixSort(values);
  }

  static int getMax(int[] array) {
    int max = array[0];
    for (int i = 0; i < array.length; i++) {
      if (array[i] > max) {
        max = array[i];
      }
    }
    return max;
  }

  static int calculateNumberOfDigits(int number) {
    return (int) Math.log10(number) + 1;
  }

  // Requires all numbers to be greater than or equal to 1
  public static void radixSort(int[] numbers) {
    if (numbers == null || numbers.length <= 1) {
      return;
    }
    int maximum = getMax(numbers);
    int numberOfDigits = calculateNumberOfDigits(maximum);
    int placeValue = 1;
    while (numberOfDigits-- > 0) {
      countSort(numbers, placeValue);
      placeValue *= 10;
    }
  }

  private static void countSort(int[] numbers, int placeValue) {
    int range = 10;

    int[] frequency = new int[range];
    int[] sortedValues = new int[numbers.length];

    for (int i = 0; i < numbers.length; i++) {
      int digit = (numbers[i] / placeValue) % range;
      frequency[digit]++;
    }

    for (int i = 1; i < range; i++) {
      frequency[i] += frequency[i - 1];
    }

    for (int i = numbers.length - 1; i >= 0; i--) {
      int digit = (numbers[i] / placeValue) % range;
      sortedValues[frequency[digit] - 1] = numbers[i];
      frequency[digit]--;
    }

    System.arraycopy(sortedValues, 0, numbers, 0, numbers.length);
  }

  public static void main(String[] args) {
    InplaceSort sorter = new RadixSort();
    int[] numbers = {387, 468, 134, 123, 68, 221, 769, 37, 7, 890, 1, 587};
    sorter.sort(numbers);
    // Prints:
    // [1, 7, 37, 68, 123, 134, 221, 387, 468, 587, 769, 890]
    System.out.println(java.util.Arrays.toString(numbers));
  }
}
