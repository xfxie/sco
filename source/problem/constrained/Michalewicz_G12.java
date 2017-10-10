/**
 * Description: Benchmark function (Michalewicz's G12).
 * f* = 1
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

public class Michalewicz_G12 extends ProblemEncoder {
  static final int NX = 3;
  static final int NY = 2;
  public Michalewicz_G12() throws Exception {
    super(NX, NY);

    for(int i=0; i<NX; i++) {
      setDefaultXAt(i, 0, 10);
    }

    setDefaultYAt(0, BasicBound.MAXDOUBLE, BasicBound.MAXDOUBLE);
    setDefaultYAt(1, BasicBound.MINDOUBLE, 0);
  }

  protected double calcTargetAt(int index, double[] VX) {
    double value = BasicBound.MAXDOUBLE;
    double tempVp, tempVpq, tempVpqr;
    int p, q, r;

    switch(index) {
    case 0:
      value = (100-(VX[0]-5)*(VX[0]-5)-(VX[1]-5)*(VX[1]-5)-(VX[2]-5)*(VX[2]-5))/100 ;
      break;
    case 1:
      /* constraints */
      for (p=1;p<=9;p++) {
        tempVp =(VX[0]-p)*(VX[0]-p)-0.0625;
        if(tempVp<value) {
          for (q=1;q<=9;q++) {
            tempVpq = tempVp+(VX[1]-q)*(VX[1]-q);
            if(tempVpq<value) {
              for (r=1;r<=9;r++) {
                tempVpqr = tempVpq+(VX[2]-r)*(VX[2]-r);
                if (tempVpqr<value) {
                  value = tempVpqr;
                }
              }
            }
          }
        }
      }
      break;
   default:
      return Double.NaN;
    }
    return value;
  }
}