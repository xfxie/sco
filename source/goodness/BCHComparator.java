/**
 * Description: Basic Constraints Handling rule (BCH)
 *   Defines a natural goodness landscape, by comparing
 *   the quality (goodness) of any two states in the search space.
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @version 1.0
 * @Since MAOS1.0
 *
 * @Reference
 * [1] K. Deb. An efficient constraint handling method for genetic algorithms.
 *     Computer Methods in Applied Mechanics and Engineering, 2000, 186(2-4): 311-338
 * [2] X.-F. Xie, W.-J. Zhang, D.-C. Bi. Handling boundary constraints for numerical 
 *     optimization by particle swarm flying in periodic search space. Congress on 
 *     Evolutionary Computation (CEC), Portland, OR, USA, 2004: 2307-2311.
 */

package goodness;

public class BCHComparator implements IGoodnessCompareEngine {

/* check the magnitude of two array, the front element is more important
 **/
  public static int compareArray(double[] fit1, double[] fit2) {
    for (int i=0; i<fit1.length; i++) {
      if (fit1[i]>fit2[i]) {
        return LARGER_THAN; //Large than
      } else if (fit1[i]<fit2[i]){
        return LESS_THAN; //Less than
      }
    }
    return IGoodnessCompareEngine.EQUAL_TO; //same
  }

  public int compare(double[] fit1, double[] fit2) {
    return compareArray(fit1, fit2);
  }
}
