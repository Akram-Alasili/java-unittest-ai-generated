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

package functions.strings;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.*;

public class ZAlgorithmTest {
  private ZAlgorithm underTest;

  @BeforeEach
  public void setup() {
    underTest = new ZAlgorithm();
  }

  @Test
  public void shouldReturnEmptyArrayOnNullOrEmptyInput() {
    assertThat(underTest.calculateZ(null)).isEmpty();
    assertThat(underTest.calculateZ("")).isEmpty();
  }

  @Test
  public void textContainsASingleCharacterRepeated() {
    assertThat(underTest.calculateZ("aaaaaaa")).isEqualTo(new int[] {7, 6, 5, 4, 3, 2, 1});
    assertThat(underTest.calculateZ("bbbbbbbb")).isEqualTo(new int[] {8, 7, 6, 5, 4, 3, 2, 1});
  }

  @Test
  public void textContainsAllDistinctCharacters() {
    assertThat(underTest.calculateZ("abcdefgh")).isEqualTo(new int[] {8, 0, 0, 0, 0, 0, 0, 0});
  }

  @Test
  public void textContainsRepeatedPattern() {
    assertThat(underTest.calculateZ("abababab")).isEqualTo(new int[] {8, 0, 6, 0, 4, 0, 2, 0});
    assertThat(underTest.calculateZ("ababababa")).isEqualTo(new int[] {9, 0, 7, 0, 5, 0, 3, 0, 1});
    assertThat(underTest.calculateZ("abcabcabca"))
        .isEqualTo(new int[] {10, 0, 0, 7, 0, 0, 4, 0, 0, 1});
  }
}
