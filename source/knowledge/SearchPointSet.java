/**
 * @Description: a set of knowledge points (states).
 * 
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @version 1.1
 * @Since MAOS1.0
 */

package knowledge;

import problem.*;

public class SearchPointSet implements IStateSetEngine {
  private SearchPoint[] libPoints = new SearchPoint[0];
  
  public SearchPointSet(SearchPoint[] libPoints) {
    this.libPoints = libPoints;
  }

  public SearchPointSet(int number, ProblemEncoder problemEncoder){
    libPoints = new SearchPoint[number];
    for (int i=0; i<number; i++) {
      libPoints[i] = problemEncoder.getEncodedSearchPoint();
    }
  }

  public int getSize() {
    return libPoints.length;
  }

  public SearchPoint getSelectedPoint(int index) {
    return libPoints[index];
  }
}



