/**
 * Description: Griewank function
 * X*(Optimal point):
 * X1=0
 * X2=-1
 * Y*(Optimal value)
 * Y1=3
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @version 1.0
 * @Since MAOS1.0
 *
 * @References:
 * [1] Shi Y H, Eberhart R C. Empirical study of particle swarm optimization.
 * Proc. Congress on Evolutionary Computation, 1999: 1945-1950
 */

package problem.unconstrained;

import problem.*;

public class Griewank extends UnconstrainedProblemEncoder {
  private static int NX = 30;
  public Griewank() throws Exception {
    super(NX);
    for(int i=0; i<NX; i++) {
      setDefaultXAt(i, -600, 600);
    }
  }

  public double calcTarget(double[] VX) {
    double value1 = 0;
    double value2 = 1;
    for (int i=0; i<NX; i++) {
      value1 += Math.pow(VX[i],2)/4000.0;
      value2 *= Math.cos(VX[i]/Math.sqrt(i+1));
    }
    return value1-value2+1;
  }
}
