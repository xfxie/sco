/**
 * Description: Benchmark function (Michalewicz's G9).
 * f* = 680.630
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

public class Michalewicz_G9 extends ProblemEncoder {
  static final int NX = 7;
  static final int NY = 5;
  public Michalewicz_G9() throws Exception {
    super(NX, NY);
    for(int i=0; i<NX; i++) {
      setDefaultXAt(i, -10, 10);
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
        value = (VX[0]-10)*(VX[0]-10)+5*(VX[1]-12)*(VX[1]-12)+VX[2]*VX[2]*VX[2]*VX[2]+3*(VX[3]-11)*(VX[3]-11)
        +10*VX[4]*VX[4]*VX[4]*VX[4]*VX[4]*VX[4]+7*VX[5]*VX[5]+VX[6]*VX[6]*VX[6]*VX[6]-4*VX[5]*VX[6]-10*VX[5]-8*VX[6];
        break;
      case 1:
        value = 127-2*VX[0]*VX[0]-3*VX[1]*VX[1]*VX[1]*VX[1]-VX[2]-4*VX[3]*VX[3]-5*VX[4];
        break;
      case 2:
        value = 282-7*VX[0]-3*VX[1]-VX[2]-10*VX[2]*VX[2]-VX[3]+VX[4];
        break;
      case 3:
        value = 196-23*VX[0]-VX[1]*VX[1]-6*VX[5]*VX[5]+8*VX[6];
        break;
      case 4:
        value = -4*VX[0]*VX[0]-VX[1]*VX[1]+3*VX[0]*VX[1]-2*VX[2]*VX[2]-5*VX[5]+11*VX[6];
        break;
      default:
        return Double.NaN;
      }
      return value;
    }
}