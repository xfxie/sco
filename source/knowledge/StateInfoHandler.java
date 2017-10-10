/**
 * @Description: For handling information of search states.
 * 
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @version 1.1
 * @Since MAOS1.0
 */

package knowledge;

import Global.*;
import goodness.*;

public class StateInfoHandler {
  
  public static int tournamentSelection(IGoodnessCompareEngine comparator, IStateSetEngine stateSet, int times, boolean isBetter) {
    int[] indices = RandomGenerator.randomSelection(stateSet.getSize(), times);
    int currentIndex = indices[0];
    for (int i=1; i<indices.length; i++) {
      int compareValue = comparator.compare(stateSet.getSelectedPoint(indices[i]).getEncodeInfo(), stateSet.getSelectedPoint(currentIndex).getEncodeInfo());
      if (isBetter == (compareValue<IGoodnessCompareEngine.LARGER_THAN)) {
        currentIndex = indices[i];
      }
    }
    return currentIndex;
  }

  public static double getExtremalVcon(IStateSetEngine stateSet, boolean isMAX) {
    double val=BasicBound.MINDOUBLE;
    for(int i=0; i<stateSet.getSize(); i++) {
      if(stateSet.getSelectedPoint(i).getEncodeInfo()[0]>val==isMAX) {
        val = stateSet.getSelectedPoint(i).getEncodeInfo()[0];
      }
    }
    return val;
  }

  public static int getVconThanNum(IStateSetEngine stateSet, double allowedCons) {
    int num=0;
    for(int i=0; i<stateSet.getSize(); i++) {
      if(stateSet.getSelectedPoint(i).getEncodeInfo()[0]<=allowedCons) {
        num++;
      }
    }
    return num;
  }

  public static void replace(IGoodnessCompareEngine comparator, SearchPoint donorPoint, SearchPoint acceptPoint) {
    if(comparator.compare(donorPoint.getEncodeInfo(), acceptPoint.getEncodeInfo())<IGoodnessCompareEngine.LARGER_THAN) {
      acceptPoint.importPoint(donorPoint);
    }
  }
}



