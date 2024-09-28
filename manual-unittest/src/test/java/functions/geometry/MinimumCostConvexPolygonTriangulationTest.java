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

import static com.google.common.truth.Truth.assertThat;

import java.awt.geom.*;
import org.junit.jupiter.api.*;

public class MinimumCostConvexPolygonTriangulationTest {

  private static final double TOLERANCE = 1e-3;

  @Test
  public void MinimumCostConvexPolygonTriangulationBasicTest() {
    Point2D[] pts = new Point2D[5];

    pts[0] = new Point2D.Double(0, 0);
    pts[1] = new Point2D.Double(1, 0);
    pts[2] = new Point2D.Double(2, 1);
    pts[3] = new Point2D.Double(1, 2);
    pts[4] = new Point2D.Double(0, 2);

    double cost = MinimumCostConvexPolygonTriangulation.minimumCostTriangulation(pts);
    assertThat(cost).isWithin(TOLERANCE).of(15.3);
  }

  @Test
  public void MinimumCostConvexPolygonTriangulationInvalidTest() {
    Point2D[] pts = new Point2D[2];

    pts[0] = new Point2D.Double(0, 0);
    pts[1] = new Point2D.Double(1, 0);

    double cost = MinimumCostConvexPolygonTriangulation.minimumCostTriangulation(pts);
    assertThat(cost).isEqualTo(0);
  }

  @Test
  public void MinimumCostConvexPolygonTriangulationConvex() {
    Point2D[] pts = new Point2D[6];

    pts[0] = new Point2D.Double(0, 0);
    pts[1] = new Point2D.Double(4, 0);
    pts[2] = new Point2D.Double(4, 2);
    pts[3] = new Point2D.Double(1, 3);
    pts[4] = new Point2D.Double(0, 2);
    pts[5] = new Point2D.Double(0, 1);

    double cost = MinimumCostConvexPolygonTriangulation.minimumCostTriangulation(pts);
    assertThat(cost).isWithin(TOLERANCE).of(31.386);
  }
}
