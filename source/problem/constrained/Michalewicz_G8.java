/**
 * Description: Benchmark function (Michalewicz's G8).
 * f* = 0.095825
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @References:
 * [1] Koziel S, Michalewicz Z. Evolutionary algorithms, homomorphous
 * mappings, and constrained parameter optimization. Evolutionary
 * Computation, 1999, 7: 19-44
 */

package problem.constrained;

import problem.*;
import Global.*;

public class Michalewicz_G8 extends ProblemEncoder {
  static final int NX = 2;
  static final int NY = 3;
  public Michalewicz_G8() throws Exception {
    super(NX, NY);
    for(int i=0; i<NX; i++) {
      setDefaultXAt(i, 0, 10);
    }

    setDefaultYAt(0, BasicBound.MAXDOUBLE, BasicBound.MAXDOUBLE);
    for(int i=1; i<NY; i++) {
      setDefaultYAt(i, BasicBound.MINDOUBLE, 0);
    }
  }
  protected double calcTargetAt(int index, double[] VX) {
    double value = 0;
    double sx1;
    switch(index) {
      case 0:
        sx1 = Math.sin(2*Math.PI*VX[0]);
        value = sx1*sx1*sx1*Math.sin(2*Math.PI*VX[1])/(VX[0]*VX[0]*VX[0]*(VX[0]+VX[1]));
        break;
      case 1:
        value = VX[0]*VX[0]-VX[1]+1;
        break;
      case 2:
        value = 1-VX[0]+(VX[1]-4)*(VX[1]-4);
        break;
      default:
        return Double.NaN;
      }
      return value;
    }

}