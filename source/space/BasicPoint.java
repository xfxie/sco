/**
 * Description: provide the location information of a point
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 */

package space;

public class BasicPoint implements Cloneable, ILocationEngine {
  //store the location information in the search space (S)
  private double[] location;

  public BasicPoint(int dim) {
    location = new double[dim];
  }

  public double[] getLocation() {
    return location;
  }

  public void importLocation(double[] pointLoc) {
    System.arraycopy(pointLoc, 0, location, 0, pointLoc.length);
  }

  public void importLocation(ILocationEngine point) {
    importLocation(point.getLocation());
  }
}
