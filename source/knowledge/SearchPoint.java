/**
 * Description: provide the location and its goodness information
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * Please acknowledge the author(s) if you use this code in any way.
 */
package knowledge;

import Global.*;
import space.*;
import encode.*;

public class SearchPoint extends BasicPoint implements IEncodeEngine {
  //store the encode information for goodness evaluation
  //encodeInfo[0]: the sum of constraints (if it equals to 0, then be a feasible point)
  //encodeInfo[1]: the value of objective function
  private double[] encodeInfo = new double[2];

  public SearchPoint(int dim) {
    super(dim);
    for(int i=0; i<encodeInfo.length; i++) {
      encodeInfo[i] = BasicBound.MAXDOUBLE;
    }
  }

  public double[] getEncodeInfo() {
    return encodeInfo;
  }

  private void importEncodeInfo(double[] info) {
    for(int i=0; i<encodeInfo.length; i++) {
      encodeInfo[i] = info[i];
    }
  }

  private void importEncodeInfo(IEncodeEngine point) {
    importEncodeInfo(point.getEncodeInfo());
  }

  //Replace self by given point
  public void importPoint(SearchPoint point) {
    importLocation(point);
    importEncodeInfo(point);
  }

  public void outputSelf() {
    System.out.println("#--> Location:");
    OutputMethods.outputVector(getLocation());
    System.out.println("#--> (CON & OPTIM):");
    OutputMethods.outputVector(getEncodeInfo());
  }
}
