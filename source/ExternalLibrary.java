/**
 * @Description: Library is a quasi-autonomous entity that maintains 
 *   a set of knowledge points (states).
 * 
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @version 1.1
 * @Since MAOS1.0
 */


import java.util.Vector;

import goodness.IGoodnessCompareEngine;
import knowledge.IStateSetEngine;
import knowledge.SearchPoint;
import knowledge.SearchPointSet;
import knowledge.StateInfoHandler;
import problem.ProblemEncoder;


public class ExternalLibrary {
  //The early version set TaoW as the library size, but 4 is often enough
  protected int TaoW = 4;
  
  //Form the goodness landscape
  private IGoodnessCompareEngine specComparator;
  
  //long-term memory: hold knowledge in the environment
  private SearchPointSet libPoints;

  //buffer: store newly generated states by the agents
  private Vector<SearchPoint> bufPoints = new Vector<SearchPoint>();
  
  public ExternalLibrary(int number, ProblemEncoder problemEncoder, IGoodnessCompareEngine specComparator){
    libPoints = new SearchPointSet(number, problemEncoder);
    this.specComparator = specComparator;
  }

  public void receiveBehavior(SearchPoint point) {
    bufPoints.add(point);
  }
  
  public IStateSetEngine getStateSet() {
    return this.libPoints;
  }
  
  //Update the new states into the external library in the one-by-one mode
  public void updateBehavior() {
    for (int i=0; i<bufPoints.size(); i++) {
      SearchPoint point = (SearchPoint)bufPoints.elementAt(i);
      //Selects a bad point xw from TaoW points in Library
      int xw = StateInfoHandler.tournamentSelection(specComparator, libPoints, TaoW, false);
      //Replaces the bad point xw
      libPoints.getSelectedPoint(xw).importPoint(point);
    }
    bufPoints.clear();
  }
}



