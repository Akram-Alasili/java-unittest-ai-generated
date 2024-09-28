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

package functions.utils;

import java.util.*;

public final class TestUtils {

  // Generates an array of random values where every number is between
  // [min, max) and there are possible repeats.
  public static int[] randomIntegerArray(int sz, int min, int max) {
    int[] ar = new int[sz];
    for (int i = 0; i < sz; i++) ar[i] = randValue(min, max);
    return ar;
  }

  // Generates an array of random values where every number is between
  // [min, max) and there are possible repeats.
  public static long[] randomLongArray(int sz, long min, long max) {
    long[] ar = new long[sz];
    for (int i = 0; i < sz; i++) ar[i] = randValue(min, max);
    return ar;
  }

  // Generates a list of random values where every number is between
  // [min, max) and there are possible repeats.
  public static List<Integer> randomIntegerList(int sz, int min, int max) {
    List<Integer> lst = new ArrayList<>(sz);
    for (int i = 0; i < sz; i++) lst.add(randValue(min, max));
    return lst;
  }

  // Generates a list of shuffled values where every number in the array
  // is in the range of [0, sz)
  public static List<Integer> randomUniformUniqueIntegerList(int sz) {
    List<Integer> lst = new ArrayList<>(sz);
    for (int i = 0; i < sz; i++) lst.add(i);
    Collections.shuffle(lst);
    return lst;
  }

  // Generates a random number between [min, max)
  public static int randValue(int min, int max) {
    return min + (int) (Math.random() * ((max - min)));
  }

  // Generates a random number between [min, max)
  public static long randValue(long min, long max) {
    return min + (long) (Math.random() * ((max - min)));
  }
}
