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
 * Performs Boyer-Moore search on a given string with a given pattern
 *
 * <p>./gradlew run -Palgorithm=strings.BoyerMooreStringSearch
 */
package functions.strings;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.List;

public class BoyerMooreStringSearch {

  private static final int MAX_ALPHABET_SIZE = 256;

  /**
   * Performs Boyer-Moore search on a given string with a given pattern
   *
   * @param text the string being searched in
   * @param pattern the string being searched for
   * @return List of indexes where the pattern occurs
   */
  public List<Integer> findOccurrences(String text, String pattern) {
    if (isNull(text)
        || isNull(pattern)
        || pattern.length() > text.length()
        || pattern.length() == 0) {
      return new ArrayList<>();
    }
    List<Integer> occurrences = new ArrayList<>();
    int[] skipTable = generateSkipTable(pattern);

    int n = pattern.length();
    for (int textIndex = n - 1, patternIndex = n - 1; textIndex < text.length(); ) {
      // Found a match!
      if (patternIndex >= 0 && pattern.charAt(patternIndex) == text.charAt(textIndex)) {
        if (patternIndex == 0) {
          occurrences.add(textIndex);
        } else {
          textIndex--;
        }
        patternIndex--;
      } else {
        textIndex += n - min(max(patternIndex, 0), skipTable[text.charAt(textIndex)] + 1);
        patternIndex = n - 1;
      }
    }
    return occurrences;
  }

  private int[] generateSkipTable(String pattern) {
    int[] skipTable = new int[MAX_ALPHABET_SIZE];
    for (int i = 0; i < pattern.length(); i++) {
      skipTable[(int) pattern.charAt(i)] = i;
    }
    return skipTable;
  }

  public static void main(String[] args) {
    BoyerMooreStringSearch searcher = new BoyerMooreStringSearch();
    String t = "ABABAAABAABAB";
    String p = "AA";

    // Prints: [4, 5, 8]
    System.out.println(searcher.findOccurrences(t, p));
  }
}