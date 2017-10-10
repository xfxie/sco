/**
 * Description: provide the information for the search space (S)
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 */

package space;
import Global.*;

public class DesignSpace {
  //The information of all the dimension
  private DesignDim[] dimProps;

  public DesignSpace(int dim) {
    dimProps = new DesignDim[dim];
  }

  public DesignDim getDimAt(int index) {
    return dimProps[index];
  }

  public void setElemAt(DesignDim elem, int index) {
    dimProps[index] = elem;
  }

  public int getDimension() {
    if (dimProps==null) {
      return -1;
    }
    return dimProps.length;
  }

  public double boundAdjustAt(double val, int dim){
    return dimProps[dim].boundAdjust(val);
  }
  
  //v1-v2 in the cycled dimension
  public double getCycledDistance(double x1, double x2, int dimID) {
    double length = dimProps[dimID].getLength();
    double x12 = x1-x2;
    if (x12<-length/2.0) {
      return length+x12;
    } else if (x12>length/2.0) {
      return length-x12;
    } else {
      return x12;
    }
  }

  public void annulusAdjust (double[] location){
    for (int i=0; i<getDimension(); i++) {
      location[i] = dimProps[i].annulusAdjust(location[i]);
    }
  }

  public void randomAdjust (double[] location){
    for (int i=0; i<getDimension(); i++) {
      location[i] = dimProps[i].randomAdjust(location[i]);
    }
  }

  public boolean satisfyCondition(double[] location){
    for (int i=0; i<getDimension(); i++) {
      if (!dimProps[i].isSatisfyCondition(location[i])) {
        return false;
      }
    }
    /*If the limits are not violated, return TRUE*/
    return(true);
  }

  public void mutationAt(double[] location, int i){
    location[i] = dimProps[i].getRandomValue();
  }

  public double mutationUniformAtPointAsCenter (double pointX, int i){
    double length = this.getMagnitudeIn(i)/2;
    pointX += RandomGenerator.doubleRangeRandom(-1*length, length);

    return pointX;
  }

  public double getUpValueAt(int dimensionIndex) {
    return dimProps[dimensionIndex].maxValue;
  }

  public double getLowValueAt(int dimensionIndex) {
    return dimProps[dimensionIndex].minValue;
  }

  public double getMagnitudeIn(int dimensionIndex) {
    return dimProps[dimensionIndex].getLength();
  }


  public boolean initilizeGeneAtPointAsCenter(double[] tempX){
    if (tempX.length!=this.getDimension()) {
      return false;
    }
    for(int i=0;i<tempX.length;i++) {
      double length = this.getMagnitudeIn(i)/2;
      tempX[i]+=RandomGenerator.doubleRangeRandom(-1*length, length);
    }
    return true;
  }

  public void initializeRandomPoint(double[] tempX){
    for(int i=0;i<tempX.length;i++) tempX[i] =  dimProps[i].getRandomValue(); //Global.RandomGenerator.doubleRangeRandom(9.8, 10);
  }

  public double[] getFreshGene() {
    double[] tempX = new double[this.getDimension()];
    initializeRandomPoint(tempX);
    return tempX;
  }
  
  public void getMappingPoint(double[] point) {
    for(int i=0; i<getDimension(); i++) {
      point[i] = dimProps[i].annulusAdjust(point[i]);
    }
  }

  public double[] getRealLoc(double[] imageLoc) {
    double[] realLoc = new double[imageLoc.length];
    for (int i=0; i<imageLoc.length; i++) {
      realLoc[i] = imageLoc[i];
    }
    annulusAdjust(realLoc);
    return realLoc;
  }
}

