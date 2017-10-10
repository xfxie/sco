/**
 * Description: provide an bound, and corresponding operations
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 */

package Global;

public class BasicBound {
  public static final double MINDOUBLE= -1e308;
  public static final double MAXDOUBLE= 1e308;

  public double minValue = MINDOUBLE;
  public double maxValue = MAXDOUBLE;
  
  public BasicBound(double min, double max) {
    minValue = Math.min(min, max);
    maxValue = Math.max(min, max);
  }

  public double getLength() {
    return maxValue-minValue;
  }

  public boolean isSatisfyCondition(double child){
    return (child <= maxValue && child >= minValue);
  }

}
