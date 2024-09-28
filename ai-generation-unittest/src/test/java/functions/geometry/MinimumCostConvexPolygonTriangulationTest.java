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

package functions.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;
import org.junit.jupiter.api.Test;

/**
 * Unit test class for MinimumCostConvexPolygonTriangulation.
 * Ensures that the triangulation method returns the correct minimum cost for various polygons.
 */
class MinimumCostConvexPolygonTriangulationTest {

  /**
   * Tests minimumCostTriangulation method with a triangle (the smallest polygon).
   * The cost for a triangle is its perimeter.
   */
  @Test
  void shouldReturnPerimeterWhenPolygonIsTriangle() {
    // Arrange
    Point2D[] triangle = {
            new Point2D.Double(0, 0),
            new Point2D.Double(1, 0),
            new Point2D.Double(0, 1)
    };

    // Act
    double result = MinimumCostConvexPolygonTriangulation.minimumCostTriangulation(triangle);

    // Assert
    double expectedPerimeter = 1 + 1 + Math.sqrt(2);
    assertEquals(expectedPerimeter, result, 0.0001, "Expected perimeter for a triangle");
  }

  /**
   * Tests minimumCostTriangulation method with a quadrilateral.
   * The minimum cost is calculated based on the optimal triangulation.
   */
  @Test
  void shouldReturnMinimumCostWhenPolygonIsQuadrilateral() {
    // Arrange
    Point2D[] quadrilateral = {
            new Point2D.Double(0, 0),
            new Point2D.Double(2, 0),
            new Point2D.Double(2, 2),
            new Point2D.Double(0, 2)
    };

    // Act
    double result = MinimumCostConvexPolygonTriangulation.minimumCostTriangulation(quadrilateral);

    // Assert
    double expectedValue = 13.65685424949238; // Corrected expected value
    assertEquals(expectedValue, result, 0.0001, "Expected minimum cost for quadrilateral");
  }

  /**
   * Tests minimumCostTriangulation method with a pentagon.
   * The minimum cost is calculated based on the optimal triangulation.
   */
  @Test
  void shouldReturnMinimumCostWhenPolygonIsPentagon() {
    // Arrange
    Point2D[] pentagon = {
            new Point2D.Double(0, 0),
            new Point2D.Double(2, 0),
            new Point2D.Double(3, 2),
            new Point2D.Double(1, 3),
            new Point2D.Double(-1, 2)
    };

    // Act
    double result = MinimumCostConvexPolygonTriangulation.minimumCostTriangulation(pentagon);

    // Assert
    double expectedValue = 23.59338255067268; // Corrected expected value
    assertEquals(expectedValue, result, 0.001, "Expected minimum cost for pentagon");
  }

  /**
   * Tests minimumCostTriangulation method with an edge case of an empty array.
   * The cost for an empty polygon is 0.
   */
  @Test
  void shouldReturnZeroWhenPolygonIsEmpty() {
    // Arrange
    Point2D[] emptyPolygon = {};

    // Act
    double result = MinimumCostConvexPolygonTriangulation.minimumCostTriangulation(emptyPolygon);

    // Assert
    assertEquals(0, result, 0.0001, "Expected cost for an empty polygon to be 0");
  }

  /**
   * Tests minimumCostTriangulation method with an edge case of a single point.
   * The cost for a single point is 0.
   */
  @Test
  void shouldReturnZeroWhenPolygonIsSinglePoint() {
    // Arrange
    Point2D[] singlePoint = { new Point2D.Double(0, 0) };

    // Act
    double result = MinimumCostConvexPolygonTriangulation.minimumCostTriangulation(singlePoint);

    // Assert
    assertEquals(0, result, 0.0001, "Expected cost for a single point to be 0");
  }

  /**
   * Tests minimumCostTriangulation method with an edge case of two points.
   * The cost for two points is 0.
   */
  @Test
  void shouldReturnZeroWhenPolygonIsTwoPoints() {
    // Arrange
    Point2D[] twoPoints = {
            new Point2D.Double(0, 0),
            new Point2D.Double(1, 1)
    };

    // Act
    double result = MinimumCostConvexPolygonTriangulation.minimumCostTriangulation(twoPoints);

    // Assert
    assertEquals(0, result, 0.0001, "Expected cost for two points to be 0");
  }

  /**
   * Tests minimumCostTriangulation method with an invalid input (null).
   * The method should throw a NullPointerException.
   */
  @Test
  void shouldThrowNullPointerExceptionWhenPolygonIsNull() {
    // Arrange
    Point2D[] nullPolygon = null;

    // Act & Assert
    assertThrows(NullPointerException.class, () -> {
      MinimumCostConvexPolygonTriangulation.minimumCostTriangulation(nullPolygon);
    }, "Expected NullPointerException when polygon is null");
  }
}
