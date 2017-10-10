/**
 * Description: Compares the quality (goodness) of any two states.
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @version 1.0
 * @Since MAOS1.0
 */

package goodness;

public abstract interface IGoodnessCompareEngine {
  public static final int LARGER_THAN = 2;
  public static final int EQUAL_TO = 1;
  public static final int LESS_THAN = 0;

  /**
   * check the magnitude of two IEncodeEngine
   * LARGER_THAN: goodness1 is worse than goodness2
   * LESS_THAN:   goodness1 is better than goodness2
   * EQUAL_TO :   goodness1 is equal to goodness2
   **/
  public abstract int compare(double[] goodness1, double[] goodness2);
}
