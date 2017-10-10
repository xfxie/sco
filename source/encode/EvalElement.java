/**
 * Description: provide the information for evaluating of a response (target)
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 */

package encode;

import Global.*;

public class EvalElement {

  //The weight for each response (target)
  public double weight = 1;
  /**
   * The expected range of the response value, forms the following objective:
   *
   * NO minValue   maxValue : THE ELEMENT OF BasicBound
   * 1  MINDOUBLE, MINDOUBLE: the minimize objective
   * 2  MAXDOUBLE, MAXDOUBLE: the maximize objective
   * 3  MINDOUBLE, v        : the less-than constraint   (<v)
   * 4  v        , MAXDOUBLE: the larger-than constraint (>v)
   * 5  v1       , v2       : the region constraint, i.e. belongs to [v1, v2]
   *
   * OPTIM type: the No.1 and No.2
   * CONS  type: the last three
   *
   */
  public BasicBound targetBound = new BasicBound(BasicBound.MINDOUBLE, BasicBound.MINDOUBLE);

  public EvalElement() {};

  public boolean isOptType() {
    return ((targetBound.minValue==BasicBound.MINDOUBLE&&targetBound.maxValue==BasicBound.MINDOUBLE)||
            (targetBound.minValue==BasicBound.MAXDOUBLE&&targetBound.maxValue==BasicBound.MAXDOUBLE));
  }

  public double evaluateCONS(double targetValue) {
    if(targetValue<targetBound.minValue) {
      return weight*(targetBound.minValue-targetValue);
    }
    if(targetValue>targetBound.maxValue) {
      return weight*(targetValue-targetBound.maxValue);
    }
    return 0;
  }

  public double evaluateOPTIM(double targetValue) {
    if(targetBound.maxValue==BasicBound.MINDOUBLE) { //min mode
      return weight*targetValue;
    } else { //max
      return -weight*targetValue;
    }
  }
}

