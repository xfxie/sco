/**
 * Description: For unconstrained function
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @version 1.0
 * @Since MAOS1.0
 */

package problem;

public abstract class UnconstrainedProblemEncoder extends ProblemEncoder {
  protected UnconstrainedProblemEncoder(int NX) throws Exception {
    super(NX, 1);
    setDefaultYAt(0, MINBOUND, MINBOUND);
  }

  protected double calcTargetAt(int index, double[] VX) {
    return calcTarget(VX);
  }
  abstract protected double calcTarget(double[] VX);
}
