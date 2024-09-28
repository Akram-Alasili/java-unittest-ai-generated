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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the TestUtils class.
 */
class TestUtilsTest {

    /**
     * Tests that randomIntegerArray generates an array of the specified size
     * with values within the specified range.
     */
    @Test
    @DisplayName("Should generate random integer array when given size and range")
    void shouldGenerateRandomIntegerArrayWhenGivenSizeAndRange() {
        // Arrange
        int sz = 10;
        int min = 0;
        int max = 100;

        // Act
        int[] array = TestUtils.randomIntegerArray(sz, min, max);

        // Assert
        assertEquals(sz, array.length, "Array size should match");
        for (int value : array) {
            assertTrue(value >= min && value < max, "Each value should be within range");
        }
    }

    /**
     * Tests that randomLongArray generates an array of the specified size
     * with values within the specified range.
     */
    @Test
    @DisplayName("Should generate random long array when given size and range")
    void shouldGenerateRandomLongArrayWhenGivenSizeAndRange() {
        // Arrange
        int sz = 10;
        long min = 0L;
        long max = 100L;

        // Act
        long[] array = TestUtils.randomLongArray(sz, min, max);

        // Assert
        assertEquals(sz, array.length, "Array size should match");
        for (long value : array) {
            assertTrue(value >= min && value < max, "Each value should be within range");
        }
    }

    /**
     * Tests that randomIntegerList generates a list of the specified size
     * with values within the specified range.
     */
    @Test
    @DisplayName("Should generate random integer list when given size and range")
    void shouldGenerateRandomIntegerListWhenGivenSizeAndRange() {
        // Arrange
        int sz = 10;
        int min = 0;
        int max = 100;

        // Act
        List<Integer> list = TestUtils.randomIntegerList(sz, min, max);

        // Assert
        assertEquals(sz, list.size(), "List size should match");
        for (int value : list) {
            assertTrue(value >= min && value < max, "Each value should be within range");
        }
    }

    /**
     * Tests that randomUniformUniqueIntegerList generates a list of unique integers
     * of the specified size.
     */
    @Test
    @DisplayName("Should generate random uniform unique integer list when given size")
    void shouldGenerateRandomUniformUniqueIntegerListWhenGivenSize() {
        // Arrange
        int sz = 10;

        // Act
        List<Integer> list = TestUtils.randomUniformUniqueIntegerList(sz);

        // Assert
        assertEquals(sz, list.size(), "List size should match");
        Set<Integer> uniqueValues = new HashSet<>(list);
        assertEquals(sz, uniqueValues.size(), "List should contain unique values");
        for (int value : list) {
            assertTrue(value >= 0 && value < sz, "Each value should be within range");
        }
    }

    /**
     * Tests that randValue generates an integer within the specified range.
     */
    @Test
    @DisplayName("Should generate random integer when given range")
    void shouldGenerateRandomIntegerWhenGivenRange() {
        // Arrange
        int min = 0;
        int max = 100;

        // Act
        int value = TestUtils.randValue(min, max);

        // Assert
        assertTrue(value >= min && value < max, "Value should be within range");
    }

    /**
     * Tests that randValue generates a long value within the specified range.
     */
    @Test
    @DisplayName("Should generate random long when given range")
    void shouldGenerateRandomLongWhenGivenRange() {
        // Arrange
        long min = 0L;
        long max = 100L;

        // Act
        long value = TestUtils.randValue(min, max);

        // Assert
        assertTrue(value >= min && value < max, "Value should be within range");
    }

    // Additional test cases to cover exceptions, edge cases, and more conditions

    /**
     * Tests that randomIntegerArray handles the edge case of zero size.
     */
    @Test
    @DisplayName("Should handle zero size for random integer array")
    void shouldHandleZeroSizeForRandomIntegerArray() {
        // Arrange
        int sz = 0;
        int min = 0;
        int max = 100;

        // Act
        int[] array = TestUtils.randomIntegerArray(sz, min, max);

        // Assert
        assertEquals(0, array.length, "Array size should be zero");
    }

    /**
     * Tests that randomLongArray handles the edge case of zero size.
     */
    @Test
    @DisplayName("Should handle zero size for random long array")
    void shouldHandleZeroSizeForRandomLongArray() {
        // Arrange
        int sz = 0;
        long min = 0L;
        long max = 100L;

        // Act
        long[] array = TestUtils.randomLongArray(sz, min, max);

        // Assert
        assertEquals(0, array.length, "Array size should be zero");
    }

    /**
     * Tests that randomIntegerList handles the edge case of zero size.
     */
    @Test
    @DisplayName("Should handle zero size for random integer list")
    void shouldHandleZeroSizeForRandomIntegerList() {
        // Arrange
        int sz = 0;
        int min = 0;
        int max = 100;

        // Act
        List<Integer> list = TestUtils.randomIntegerList(sz, min, max);

        // Assert
        assertEquals(0, list.size(), "List size should be zero");
    }

    /**
     * Tests that randomUniformUniqueIntegerList handles the edge case of zero size.
     */
    @Test
    @DisplayName("Should handle zero size for random uniform unique integer list")
    void shouldHandleZeroSizeForRandomUniformUniqueIntegerList() {
        // Arrange
        int sz = 0;

        // Act
        List<Integer> list = TestUtils.randomUniformUniqueIntegerList(sz);

        // Assert
        assertEquals(0, list.size(), "List size should be zero");
    }

}
