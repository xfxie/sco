/**
 * Description: Benchmark function (Michalewicz's G7).
 * Y*(Optimal value)
 * Y1=24.3062
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
 */

package problem.constrained;

import problem.*;
import Global.*;

public class Michalewicz_G7 extends ProblemEncoder {
  static final int NX = 10;
  static final int NY = 9;
  public Michalewicz_G7() throws Exception {
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
        value = VX[0]*VX[0]+VX[1]*VX[1]+VX[0]*VX[1]-14*VX[0]-16*VX[1]+(VX[2]-10)*(VX[2]-10)+4*(VX[3]-5)*(VX[3]-5)
            +(VX[4]-3)*(VX[4]-3)+2*(VX[5]-1)*(VX[5]-1)+5*VX[6]*VX[6]+7*(VX[7]-11)*(VX[7]-11)+2*(VX[8]-10)*(VX[8]-10)
            +(VX[9]-7)*(VX[9]-7)+45;
        break;
      case 1:
        value = 105-4*VX[0]-5*VX[1]+3*VX[6]-9*VX[7];
        break;
      case 2:
        value = -10*VX[0]+8*VX[1]+17*VX[6]-2*VX[7];
        break;
      case 3:
        value = 8*VX[0]-2*VX[1]-5*VX[8]+2*VX[9]+12;
        break;
      case 4:
        value = -3*(VX[0]-2)*(VX[0]-2)-4*(VX[1]-3)*(VX[1]-3)-2*VX[2]*VX[2]+7*VX[3]+120;
        break;
      case 5:
        value = -5*VX[0]*VX[0]-8*VX[1]-(VX[2]-6)*(VX[2]-6)+2*VX[3]+40;
        break;
      case 6:
        value = -1*VX[0]*VX[0]-2*(VX[1]-2)*(VX[1]-2)+2*VX[0]*VX[1]-14*VX[4]+6*VX[5];
        break;
      case 7:
        value = -0.5*(VX[0]-8)*(VX[0]-8)-2*(VX[1]-4)*(VX[1]-4)-3*VX[4]*VX[4]+VX[5]+30;
        break;
      case 8:
        value = 3*VX[0]-6*VX[1]-12*(VX[8]-8)*(VX[8]-8)+7*VX[9];
        break;
    default:
      return Double.NaN;
    }
    return value;
  }
}