/**
 * Description: provide the information for a dimension of design space
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 */

package space;
import Global.*;

public class DesignDim extends BasicBound {
  
  public DesignDim(double min, double max) {
    super(min, max);
  }

  public double boundAdjust(double value){
    if(value > maxValue) {
      value = maxValue;
    } else if (value < minValue) {
      value = minValue;
    }
    return value;
  }

  public double annulusAdjust (double value) {
    if(value > maxValue) {
      return minValue+(value-maxValue)%getLength();
    } else if (value < minValue) {
      return maxValue-(minValue-value)%getLength();
    } else {
      return value;
    }
  }

  public double randomAdjust (double value){
    if(value > maxValue || value < minValue) {
      value =  getRandomValue();
    }
    return value;
  }

  public double getRandomValue(){
    return RandomGenerator.doubleRangeRandom(minValue, maxValue);
  }
}

