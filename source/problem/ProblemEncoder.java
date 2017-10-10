/**
 * Description: Encode a specified problem.
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @version 1.0
 * @Since MAOS1.0
 */

package problem;

import Global.*;
import space.*;
import encode.*;
import knowledge.*;

public abstract class ProblemEncoder {
	protected static final double MINBOUND = BasicBound.MINDOUBLE;
	protected static final double MAXBOUND = BasicBound.MAXDOUBLE;
	
  //Store the calculated results for the responses
  double[] tempResponseSet;  //temp values
  double[] tempLocation;  //temp values

  //the search space (S)
  protected DesignSpace designSpace = null;

  // For evaluate the response vector into encoded vector double[2]
  protected EvalStruct evalStruct = null;

  protected ProblemEncoder(int paramNum, int targetNum) throws Exception {
    designSpace = new DesignSpace(paramNum);
    evalStruct = new EvalStruct(targetNum);
    tempLocation = new double[paramNum];
    tempResponseSet = new double[targetNum];
  }

  public DesignSpace getDesignSpace() {
    return designSpace;
  }

  public EvalStruct getEvalStruct() {
    return evalStruct;
  }

  //set the default information for each dimension of search space (S)
  protected void setDefaultXAt(int i,  double min, double max) {
    DesignDim dd = new DesignDim(min, max);
    designSpace.setElemAt(dd, i);
  }

  //set the default information for evaluation each response
  protected void setDefaultYAt(int i,  double min, double max) {
    EvalElement ee = new EvalElement();
    ee.targetBound = new BasicBound(min, max);
    evalStruct.setElemAt(ee, i);
  }

  protected void setDefaultYAt(int i,  double min, double max, double weight) {
    EvalElement ee = new EvalElement();
    ee.targetBound = new BasicBound(min, max);
    ee.weight = weight;
    evalStruct.setElemAt(ee, i);
  }

  //get a fresh point
  public SearchPoint getFreshSearchPoint() {
    return new SearchPoint(designSpace.getDimension());
  }

  //get an encoded point
  public SearchPoint getEncodedSearchPoint() {
    SearchPoint point = getFreshSearchPoint();
    designSpace.initializeRandomPoint(point.getLocation());
    evaluate(point);
    return point;
  }

  //evaluate the point into encoded information
  public void evaluate(SearchPoint point) {
    //calculate the responses of the given point
    calcTargets(tempResponseSet, point.getLocation());
    //encode the responses
    evalStruct.evaluate(point.getEncodeInfo(), tempResponseSet);
  }

  //calculate each response, must be implemented
  abstract protected double calcTargetAt(int index, double[] VX);

  // calculate all the responses VY[] based on given point VX[]
  private void calcTargets(double[] VY, double[] VX) {
    for(int i=0; i<VY.length; i++) {
      VY[i] = calcTargetAt(i, VX);
    }
  }
}

