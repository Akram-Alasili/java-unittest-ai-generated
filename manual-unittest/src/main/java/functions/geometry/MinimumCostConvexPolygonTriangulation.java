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
 * This file shows you how to find the minimum cost convex polygon triangulation of a set of points.
 * Points must be in either clockwise or counterclockwise order.
 *
 * <p>Time Complexity: O(n^3)
 *
 * @author Bryan Bowles
 */
package functions.geometry;

import java.awt.geom.Point2D;

// Problem explanation: https://www.geeksforgeeks.org/minimum-cost-polygon-triangulation/
public class MinimumCostConvexPolygonTriangulation {

  // Returns the perimeter (cost) of the triangle
  private static double cost(Point2D i, Point2D j, Point2D k) {
    return i.distance(j) + i.distance(k) + j.distance(k);
  }

  // Input must be a convex polygon with points in CW or CCW order.
  public static double minimumCostTriangulation(Point2D[] polygon) {
    int len = polygon.length;
    if (len < 3) return 0;

    double[][] dp = new double[len][len];
    for (int i = 2; i < len; i++) {
      for (int j = 0; j + i < len; j++) {
        dp[j][j + i] = Integer.MAX_VALUE;
        for (int k = j + 1; k < j + i; k++) {
          dp[j][j + i] =
              Math.min(
                  dp[j][j + i],
                  dp[j][k] + dp[k][j + i] + cost(polygon[j], polygon[j + i], polygon[k]));
        }
      }
    }
    return dp[0][len - 1];
  }
}
