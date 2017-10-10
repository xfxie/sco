/**
 * Description: Benchmark function (Michalewicz's G6).
 * 
 * f*=-6961.81388
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @version 1.0
 * @Since MAOS1.0
 *
 * @References:
 * [1] Floundas C, Pardalos P. A Collection of Test Problems for Constrained
 * Global Optimization. Springer-Verlag, LNCS, 1987, 455
 * [2] Koziel S, Michalewicz Z. Evolutionary algorithms, homomorphous
 * mappings, and constrained parameter optimization. Evolutionary
 * Computation, 1999, 7: 19-44
 */

package problem.constrained;

import problem.*;
import Global.*;

public class Michalewicz_G6 extends ProblemEncoder {
  public Michalewicz_G6() throws Exception {
    super(2, 3);
    setDefaultXAt(0, 13, 100);
    setDefaultXAt(1, 0, 100);
    for(int i=0; i<2; i++) {
      setDefaultXAt(i, 0, 1200);
    }
    setDefaultYAt(0, BasicBound.MINDOUBLE, BasicBound.MINDOUBLE);
    setDefaultYAt(1, 0, BasicBound.MAXDOUBLE);
    setDefaultYAt(2, 0, BasicBound.MAXDOUBLE);
  }

  protected double calcTargetAt(int index, double[] VX) {
    double value = 0;
    double x0m, x1m;
    switch(index) {
    case 0:
      x0m = VX[0]-10;
      x1m = VX[1]-20;
      value = x0m*x0m*x0m+x1m*x1m*x1m;
      break;
    case 1:
      x0m = VX[0]-5;
      x1m = VX[1]-5;
      value = x0m*x0m+x1m*x1m-100;
      break;
    case 2:
      x0m = VX[0]-6;
      x1m = VX[1]-5;
      value = -1*x0m*x0m-x1m*x1m+82.81;
      break;
    default:
      return Double.NaN;
    }
    return value;
  }
}