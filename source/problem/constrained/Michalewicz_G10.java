/**
 * Description: Benchmark function (Michalewicz's G10).
 * f*=7049.248
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @version 1.0
 * @Since MAOS1.0
 *
 * @References:
 * [1] Hock W, Schittkowski K. Test Examples for Nonlinear Programming
 * Codes. Springer-Verlag, 1981, Lecture Notes in Econ. and Math. Syst.
 * [2] Koziel S, Michalewicz Z. Evolutionary algorithms, homomorphous
 * mappings, and constrained parameter optimization. Evolutionary
 * Computation, 1999, 7: 19-44
 */

package problem.constrained;

import problem.*;
import Global.*;

public class Michalewicz_G10 extends ProblemEncoder {
  static final int NX = 8;
  static final int NY = 7;
  public Michalewicz_G10() throws Exception {
    super(NX, NY);

    setDefaultXAt(0, 100, 10000);
    setDefaultXAt(1, 1000, 10000);
    setDefaultXAt(2, 1000, 10000);
    for(int i=3; i<NX; i++) {
      setDefaultXAt(i, 10, 1000);
    }

    setDefaultYAt(0, BasicBound.MINDOUBLE, BasicBound.MINDOUBLE);
    for(int i=1; i<NY; i++) {
      setDefaultYAt(i, 0, BasicBound.MAXDOUBLE);
    }
  }

  protected double calcTargetAt(int index, double[] VX) {
    double value = 0;
    switch(index) {
      case 0:
        value = VX[0]+VX[1]+VX[2];
        break;
      case 1:
        value = 1-0.0025*(VX[3]+VX[5]);
        break;
      case 2:
        value = 1-0.0025*(VX[4]+VX[6]-VX[3]);
        break;
      case 3:
        value = 1-0.01*(VX[7]-VX[4]);
        break;
      case 4:
        value = VX[0]*VX[5]-833.33252*VX[3]-100*VX[0]+83333.333;
        break;
      case 5:
        value = VX[1]*VX[6]-1250*VX[4]-VX[1]*VX[3]+1250*VX[3];
        break;
      case 6:
        value = VX[2]*VX[7]-1250000-VX[2]*VX[4]+2500*VX[4];
        break;
    default:
      return Double.NaN;
    }
    return value;
  }
}