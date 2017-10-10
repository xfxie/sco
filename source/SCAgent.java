/**
 * Description: The description of social cognitive (SC) agent.
 *
 * @ Declarative information sources: 
 *   b) the individual memory; b) public information (external library) 
 * 
 * @ Behaviors: a) the generating behavior by the function generateBehavior(); 
 *              b) the updating behavior by the function updateBehavior()
 *              c) release non-private knowledge by the function getNonprivatePoint()
 *              
 *
 * @Coefficients: TaoB and TaoW
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @License description:
 *
 *  See the Creative Commons Non-Commercial License 3.0 for more details.
 *  Please acknowledge the author(s) if you use this code in any way. 
 *
 * @version 1.0
 * @Since MAOS1.0
 *
 * @References:
 * [1] X.-F. Xie, W.-J. Zhang, Z.-L. Yang. Social cognitive optimization for nonlinear programming
 *   problems. International Conference on Machine Learning and Cybernetics (ICMLC). Beijing, China, 
 *   2002: 779-783 
 * [2] X.-F. Xie, W.-J. Zhang. Solving engineering design problems by social cognitive optimization. 
 *   Genetic and Evolutionary Computation Conference (GECCO), LNCS 3102, Seattle, WA, USA, 2004: 261-262
 */

import Global.*;
import space.*;
import goodness.*;
import problem.*;
import knowledge.*;

public class SCAgent {

  //Describes the problem to be solved (encode the point into intermediate information)
  protected ProblemEncoder problemEncoder;
  //Forms the runtime goodness landscape
  protected IGoodnessCompareEngine specComparator;

  //buffer memory: only temperately store the newly generated point
  protected SearchPoint trialPoint;
  
  //individual memory: only stores the point that generated in last learning cycle
  protected SearchPoint personalPoint;

  //public information: the referred library
  protected IStateSetEngine referLib;

  //the parameter for selecting a model point
  protected int TaoB = 2;
  
  public SCAgent() {}

  public void setProblemEncoder(ProblemEncoder encoder) {
    problemEncoder = encoder;
    trialPoint = problemEncoder.getFreshSearchPoint();
    // initialize the individual memory
    personalPoint = problemEncoder.getEncodedSearchPoint();
  }

  public void setSpecComparator(IGoodnessCompareEngine comparer) {
    specComparator = comparer;
  }

  public void setPublicInformation(IStateSetEngine lib) {
    referLib = lib;
  }
  
  //update the individual memory
  // Replace the personal point by the generated point. Thus personalPoint simply
  // stores the state generated in the most recent cycle 
  final public void updateBehavior() {
    personalPoint.importPoint(trialPoint);
  }

  final public SearchPoint generateBehavior(int t) {
    //generate a new point
    generatePoint(trialPoint, t);
    //encode the goodness value of the newly generated point
    problemEncoder.evaluate(trialPoint);
    return trialPoint;
  }

  // socially-biased individual learning (SBIL): generates a new point in the search space (S) 
  // based on its individual memory (personalPoint) and the public information (referLib)
  protected void generatePoint(SearchPoint trialPoint, int t) {
    SearchPoint Xmodel, Xrefer, libBPoint;

    // Select a better point (libBPoint) from the external library (L) based on tournament selection
    int xb = StateInfoHandler.tournamentSelection(specComparator, referLib, TaoB, true);
    libBPoint = referLib.getSelectedPoint(xb);
    
    // Compares personalPoint with libBPoint: the better one becomes model point (Xmodel) 
    // & The worse one becomes reference point (Xrefer)
    if(specComparator.compare(personalPoint.getEncodeInfo(), libBPoint.getEncodeInfo())==IGoodnessCompareEngine.LARGER_THAN) {
      Xmodel = libBPoint;
      Xrefer = personalPoint;
    } else {
      Xmodel = personalPoint;
      Xrefer = libBPoint;
    }

    inferPoint(trialPoint, Xmodel, Xrefer, problemEncoder.getDesignSpace());
  }

  // observational learning: generates a new point near the model point, which
  // the variation range is decided by the difference between Xmodel and Xrefer
  // 1---model point, 2---reference point
  private boolean inferPoint(ILocationEngine newPoint, ILocationEngine point1, ILocationEngine point2, DesignSpace space){
    double[] newLoc = newPoint.getLocation();
    double[] real1 = point1.getLocation();
    double[] real2 = point2.getLocation();
  
    for (int i=0; i<newLoc.length; i++) {
      newLoc[i] = real1[i]*2-real2[i];
      //boundary-handling: ensure the validity (within the search space)
      newLoc[i] = space.boundAdjustAt(newLoc[i], i);
      
      newLoc[i] = RandomGenerator.doubleRangeRandom(newLoc[i], real2[i]);
    }
    return true;
  }
  
  public SearchPoint getNonprivatePoint() {
    return this.personalPoint;
  }

}

