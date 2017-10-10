/**
 * Description: Benchmark function (Michalewicz's G4).
 * X*(Optimal point):
 * X1=78
 * X2=33
 * X3=29.99525602568159
 * X4=45
 * X5=36.77581290578819
 * Y*(Optimal value)
 * Y1=-30665.53867
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @version 1.0
 * @Since MAOS1.0
 *
 * @References:
 * [1] Himmelblau D. Applied Nonlinear Programming. New York:McGraw-Hill, 1972
 * [2] Koziel S, Michalewicz Z. Evolutionary algorithms, homomorphous
 * mappings, and constrained parameter optimization. Evolutionary
 * Computation, 1999, 7: 19-44
 */

package problem.constrained;

import problem.*;
import Global.*;

public class Michalewicz_G4 extends ProblemEncoder {
  static final int NX = 5;
  static final int NY = 4;
  public Michalewicz_G4() throws Exception {
    super(NX, NY);

    setDefaultXAt(0, 78, 102);
    setDefaultXAt(1, 33, 45);
    for(int i=2; i<NX; i++) {
      setDefaultXAt(i, 27, 45);
    }

    setDefaultYAt(0, BasicBound.MINDOUBLE, BasicBound.MINDOUBLE);
    setDefaultYAt(1, 0, 92);     //Region constraints: [0, 92]
    setDefaultYAt(2, 90, 110);   //Region constraints: [90, 110]
    setDefaultYAt(3, 20, 25);    //Region constraints: [20, 25]
  }

  protected double calcTargetAt(int index, double[] VX) {
    double value = 0;
    switch(index) {
      case 0:
        value = 5.3578547*VX[2]*VX[2]+0.8356891*VX[0]*VX[4]+37.293239*VX[0]-40792.141;
        break;
      case 1:
        value = 85.334407+0.0056858*VX[1]*VX[4]+0.0006262*VX[0]*VX[3]-0.0022053*VX[2]*VX[4];
        break;
      case 2:
        value = 80.51249+0.0071317*VX[1]*VX[4]+0.0029955*VX[0]*VX[1]+0.0021813*VX[2]*VX[2];
        break;
      case 3:
        value = 9.300961+0.0047026*VX[2]*VX[4]+0.0012547*VX[0]*VX[2]+0.0019085*VX[2]*VX[3];
        break;
    default:
      return Double.NaN;
    }
    return value;
  }
}