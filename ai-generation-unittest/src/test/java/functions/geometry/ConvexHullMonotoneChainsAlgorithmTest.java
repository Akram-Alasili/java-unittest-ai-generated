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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.geom.Point2D;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Tests the ConvexHullMonotoneChainsAlgorithm for various input scenarios to ensure correctness.
 */
class ConvexHullMonotoneChainsAlgorithmTest {

  /**
   * Tests the convexHull method with a simple set of points forming a square.
   * Ensures that the correct convex hull is returned.
   */
  @Test
  void shouldReturnConvexHullWhenGivenSquarePoints() {
    // Arrange
    Point2D[] points = {
            new Point2D.Double(0, 0),
            new Point2D.Double(0, 1),
            new Point2D.Double(1, 0),
            new Point2D.Double(1, 1)
    };
    Point2D[] expectedHull = {
            new Point2D.Double(0, 0),
            new Point2D.Double(1, 0),
            new Point2D.Double(1, 1),
            new Point2D.Double(0, 1)
    };

    // Act
    Point2D[] resultHull = ConvexHullMonotoneChainsAlgorithm.convexHull(points);

    // Assert
    Arrays.sort(expectedHull, new ConvexHullMonotoneChainsAlgorithm.PointComparator());
    Arrays.sort(resultHull, new ConvexHullMonotoneChainsAlgorithm.PointComparator());
    assertArrayEquals(expectedHull, resultHull, "Expected the convex hull of the square points to match.");
  }

  /**
   * Tests the convexHull method with a set of collinear points.
   * Ensures that only the endpoints are returned.
   */
  @Test
  void shouldReturnEndpointsWhenGivenCollinearPoints() {
    // Arrange
    Point2D[] points = {
            new Point2D.Double(0, 0),
            new Point2D.Double(1, 1),
            new Point2D.Double(2, 2),
            new Point2D.Double(3, 3)
    };
    Point2D[] expectedHull = {
            new Point2D.Double(0, 0),
            new Point2D.Double(3, 3)
    };

    // Act
    Point2D[] resultHull = ConvexHullMonotoneChainsAlgorithm.convexHull(points);

    // Assert
    Arrays.sort(expectedHull, new ConvexHullMonotoneChainsAlgorithm.PointComparator());
    Arrays.sort(resultHull, new ConvexHullMonotoneChainsAlgorithm.PointComparator());
    assertArrayEquals(expectedHull, resultHull, "Expected the convex hull of the collinear points to include only the endpoints.");
  }

  /**
   * Tests the convexHull method with a single point.
   * Ensures that the single point is returned as the hull.
   */
  @Test
  void shouldReturnSinglePointWhenGivenSinglePoint() {
    // Arrange
    Point2D[] points = { new Point2D.Double(1, 1) };
    Point2D[] expectedHull = { new Point2D.Double(1, 1) };

    // Act
    Point2D[] resultHull = ConvexHullMonotoneChainsAlgorithm.convexHull(points);

    // Assert
    assertArrayEquals(expectedHull, resultHull, "Expected the convex hull of a single point to be the point itself.");
  }

  /**
   * Tests the convexHull method with an empty array.
   * Ensures that an empty array is returned.
   */
  @Test
  void shouldReturnEmptyArrayWhenGivenEmptyArray() {
    // Arrange
    Point2D[] points = {};
    Point2D[] expectedHull = {};

    // Act
    Point2D[] resultHull = ConvexHullMonotoneChainsAlgorithm.convexHull(points);

    // Assert
    assertArrayEquals(expectedHull, resultHull, "Expected the convex hull of an empty array to be empty.");
  }

  /**
   * Tests the convexHull method with random points.
   * Ensures that the correct convex hull is returned.
   */
  @Test
  void shouldReturnConvexHullWhenGivenRandomPoints() {
    // Arrange
    Point2D[] points = {
            new Point2D.Double(0, 3),
            new Point2D.Double(2, 2),
            new Point2D.Double(1, 1),
            new Point2D.Double(2, 1),
            new Point2D.Double(3, 0),
            new Point2D.Double(0, 0),
            new Point2D.Double(3, 3)
    };
    Point2D[] expectedHull = {
            new Point2D.Double(0, 0),
            new Point2D.Double(3, 0),
            new Point2D.Double(3, 3),
            new Point2D.Double(0, 3)
    };

    // Act
    Point2D[] resultHull = ConvexHullMonotoneChainsAlgorithm.convexHull(points);

    // Assert
    Arrays.sort(expectedHull, new ConvexHullMonotoneChainsAlgorithm.PointComparator());
    Arrays.sort(resultHull, new ConvexHullMonotoneChainsAlgorithm.PointComparator());
    assertArrayEquals(expectedHull, resultHull, "Expected the convex hull of the random points to match.");
  }

  /**
   * Tests the orientation method for collinear points.
   * Ensures that the correct orientation is returned.
   */
  @Test
  void shouldReturnZeroWhenPointsAreCollinear() {
    // Arrange
    Point2D a = new Point2D.Double(0, 0);
    Point2D b = new Point2D.Double(1, 1);
    Point2D c = new Point2D.Double(2, 2);

    // Act
    int orientation = ConvexHullMonotoneChainsAlgorithm.orientation(a, b, c);

    // Assert
    assertEquals(0, orientation, "Expected orientation to be 0 for collinear points.");
  }

  /**
   * Tests the orientation method for clockwise points.
   * Ensures that the correct orientation is returned.
   */
  @Test
  void shouldReturnMinusOneWhenPointsAreClockwise() {
    // Arrange
    Point2D a = new Point2D.Double(0, 0);
    Point2D b = new Point2D.Double(1, 1);
    Point2D c = new Point2D.Double(2, 0);

    // Act
    int orientation = ConvexHullMonotoneChainsAlgorithm.orientation(a, b, c);

    // Assert
    assertEquals(-1, orientation, "Expected orientation to be -1 for clockwise points.");
  }

  /**
   * Tests the orientation method for counter-clockwise points.
   * Ensures that the correct orientation is returned.
   */
  @Test
  void shouldReturnPlusOneWhenPointsAreCounterClockwise() {
    // Arrange
    Point2D a = new Point2D.Double(0, 0);
    Point2D b = new Point2D.Double(1, 1);
    Point2D c = new Point2D.Double(0, 2);

    // Act
    int orientation = ConvexHullMonotoneChainsAlgorithm.orientation(a, b, c);

    // Assert
    assertEquals(1, orientation, "Expected orientation to be 1 for counter-clockwise points.");
  }

  /**
   * Tests the PointComparator for points with the same x coordinate.
   * Ensures correct comparison based on y coordinate.
   */
  @Test
  void shouldComparePointsBasedOnYWhenXCoordinatesAreEqual() {
    // Arrange
    Point2D p1 = new Point2D.Double(1, 2);
    Point2D p2 = new Point2D.Double(1, 3);

    // Act
    int result = new ConvexHullMonotoneChainsAlgorithm.PointComparator().compare(p1, p2);

    // Assert
    assertEquals(-1, result, "Expected comparison based on y coordinate when x coordinates are equal.");
  }

  /**
   * Tests the PointComparator for points with different x coordinates.
   * Ensures correct comparison based on x coordinate.
   */
  @Test
  void shouldComparePointsBasedOnXWhenXCoordinatesAreDifferent() {
    // Arrange
    Point2D p1 = new Point2D.Double(1, 2);
    Point2D p2 = new Point2D.Double(2, 2);

    // Act
    int result = new ConvexHullMonotoneChainsAlgorithm.PointComparator().compare(p1, p2);

    // Assert
    assertEquals(-1, result, "Expected comparison based on x coordinate when x coordinates are different.");
  }

  /**
   * Tests the PointComparator for points that are effectively the same.
   * Ensures they are considered equal.
   */
  @Test
  void shouldReturnZeroWhenPointsAreEffectivelyEqual() {
    // Arrange
    Point2D p1 = new Point2D.Double(1, 2);
    Point2D p2 = new Point2D.Double(1 + 1e-6, 2 + 1e-6);

    // Act
    int result = new ConvexHullMonotoneChainsAlgorithm.PointComparator().compare(p1, p2);

    // Assert
    assertEquals(0, result, "Expected comparison to consider points effectively equal.");
  }
}
