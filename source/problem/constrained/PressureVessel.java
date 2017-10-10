/**
 * Description: Benchmark function (Pressure Vessel).
 * X*(Optimal point):
 * X1=42.09844546451396
 * X2=176.63659783549562
 * X3=13  (Discrete variable)
 * X4=7   (Discrete variable)
 * Y*(Optimal value)
 * Y1=6059.714359589802
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @version 1.0
 * @Since MAOS1.0
 *
 * @References:
 * [1]	Coello C A C. Theoretical and numerical constraint-handling techniques
 * used with evolutionary algorithms: a survey of the state of the art. Computer
 * Methods in Applied Mechanics and Engineering, 2002, 191(11-12): 1245-1287
 */

package problem.constrained;

import problem.*;
import Global.*;

public class PressureVessel extends ProblemEncoder {
  static final int NX = 4;
  static final int NY = 5;
  public PressureVessel() throws Exception {
    super(NX, NY);

    for(int i=0; i<2; i++) {
      setDefaultXAt(i, 10, 200);
    }
    for(int i=2; i<NX; i++) {
      setDefaultXAt(i, 1, 99);  //[1, 99] The integer variables: set grain=1
    }

    setDefaultYAt(0, BasicBound.MINDOUBLE, BasicBound.MINDOUBLE); // Minimize Objective
    for(int i=1; i<NY; i++) {
      setDefaultYAt(i, BasicBound.MINDOUBLE, 0);
    }
  }

  protected double calcTargetAt(int index, double[] VX) {
    double value = 0;
    double V2 = 0.0625*Math.rint(VX[2]); //rint for integer variable
    double V3 =  0.0625*Math.rint(VX[3]); //rint for integer variable
    switch(index) {
      case 0:
        value =0.6224*V2*VX[1]*VX[0]+1.7781*V3*VX[0]*VX[0]+V2*V2*(3.1661*VX[1])+19.84*V2*V2*VX[0];
        break;
      case 1:
        value = 0.0193*VX[0]-V2;
        break;
      case 2:
        value = 0.00954*VX[0]-V3;
        break;
      case 3:
        value = 750.0*1728.0-Math.PI*VX[0]*VX[0]*VX[1]-Math.PI*VX[0]*VX[0]*VX[0]*4.0/3.0;
        break;
      case 4:
        value = VX[1]-240;
        break;
      default:
        return Double.NaN;
    }
    return value;
  }
}