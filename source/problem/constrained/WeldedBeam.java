/**
 * Description: Benchmark function (Welded Beam design).
 * X*(Optimal point):
 * X1=0.2443689534483812
 * X2=6.218606918428793
 * X3=8.291471769712778
 * X4=0.24436895344838122
 * Y*(Optimal value)
 * Y1=2.3811341168917894
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @version 1.0
 * @Since MAOS1.0
 *
 * @References:
 * [1] Deb K. An efficient constraint handling method for genetic algorithms.
 * Computer Methods in Applied Mechanics and Engineering, 2000, 186(2-4): 311-338
 * [2] Farmani R, Wright J A. Self-adaptive fitness formulation for constrained
 * optimization. IEEE Trans. on Evolutionary Computation. 2003, 7 (5): 445-455
 */

package problem.constrained;

import problem.*;
import Global.*;

public class WeldedBeam extends ProblemEncoder {
  static final int NX = 4;
  static final int NY = 6;
  public WeldedBeam() throws Exception {
    super(NX, NY);

    setDefaultXAt(0, 0.125, 10);
    for(int i=1; i<NX; i++) {
      setDefaultXAt(i, 0.1, 10);
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
        value = 1.10471*VX[0]*VX[0]*VX[1]+0.04811*VX[2]*VX[3]*(14.0+VX[1]);
        break;
      case 1:
        double tao, tao1, tao2;
        tao1=6000.0/(Math.sqrt(2.0)*VX[0]*VX[1]);
        double tao2Numerator =6000.0*(14.0+0.5*VX[1])*Math.sqrt(0.25*(VX[1]*VX[1]+Math.pow(VX[0]+VX[2], 2)));
        double tao2Denominator = 2*(0.707*VX[0]*VX[1]*(VX[1]*VX[1]/12.0+0.25*Math.pow(VX[0]+VX[2], 2)));
        tao2 = tao2Numerator/tao2Denominator;
        tao = Math.sqrt(tao1*tao1+tao2*tao2+VX[1]*tao1*tao2/Math.sqrt(0.25*(VX[1]*VX[1]+Math.pow(VX[0]+VX[2], 2))));
        value = 13600-tao;
        break;
      case 2:
        double sigma = 504000/(VX[2]*VX[2]*VX[3]);
        value = 30000-sigma;
        break;
      case 3:
        value = VX[3]-VX[0];
        break;
      case 4:
        double P = 64746.022*(1-0.0282346*VX[2])*VX[2]*Math.pow(VX[3], 3);
        value = P-6000.0;
        break;
      case 5:
        double delta = 2.1952/(Math.pow(VX[2], 3)*VX[3]);
        value = 0.25-delta;
        break;
    default:
      return Double.NaN;
    }
    return value;
  }
}