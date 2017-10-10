/**
 * Description: Benchmark function (Michalewicz's G2).
 * X*(Optimal point):
 * X1=3.16215255  X2=3.12817483  X3=3.09502235  X4=3.06133802  X5=3.02826842
 * X6=2.9939775   X7=2.95839222  X8=2.92204548  X9=0.49527256  X10=0.48869229
 * X11=0.48196674 X12=0.47690625 X13=0.4714034  X14=0.46580564 X15=0.46039198
 * X16=0.45733127 X17=0.4524957  X18=0.44864981 X19=0.44406217 X20=0.44032687
 * Y*(Optimal value)
 * Y1=0.8036188218162693
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @version 1.0
 * @Since MAOS1.0
 *
 * @References:
 * [1] Koziel S, Michalewicz Z. Evolutionary algorithms, homomorphous
 * mappings, and constrained parameter optimization. Evolutionary
 * Computation, 1999, 7: 19-44
 */

package problem.constrained;

import problem.*;
import Global.*;

public class Michalewicz_G2 extends ProblemEncoder {
  static final int NX = 20;
  static final int NY = 3;
  public Michalewicz_G2() throws Exception {
    super(NX, NY);
    for(int i=0; i<NX; i++) {
      setDefaultXAt(i, 0, 10);
    }

    setDefaultYAt(0, BasicBound.MAXDOUBLE, BasicBound.MAXDOUBLE); // Maximize Objective
    setDefaultYAt(1, 0, BasicBound.MAXDOUBLE);    //Largethan constraints (>0)
    setDefaultYAt(2, BasicBound.MINDOUBLE, 0);    //Lessthan constraints  (<0)
  }

  protected double calcTargetAt(int index, double[] VX) {
    double value = 0;
    switch(index) {
      case 0:
        double value1 = 0;
        double value2 = 1;
        double value3 = 0;
        for (int j=0; j<NX; j++) {
          value1 += Math.pow(Math.cos(VX[j]),4);
          value2 *= Math.pow(Math.cos(VX[j]),2);
          value3 += (j+1)*Math.pow(VX[j],2);
        }
        value = Math.abs((value1-2*value2)/Math.sqrt(value3));
        break;
      case 1:
        value = 1;
        for (int j=0; j<NX; j++) {
          value *= VX[j];
        }
        value -= 0.75;
        break;
      case 2:
        value = 0;
        for (int j=0; j<NX; j++) {
          value += VX[j];
        }
        value-=7.5*NX;
        break;
    default:
      return Double.NaN;
    }
    return value;
  }
}

