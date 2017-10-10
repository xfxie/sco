/**
 * @Description: Social Cognitive Optimization (SCO) Group.
 *
 * @ Principle: The complex collective behavior can emerge from a group of N
 * autonomous entities, called agents. Each agent acquires knowledge in
 * socially-biased individual learning (SBIL), i.e., generates new knowledge 
 * based on its personal memory and socially public knowledge.
 *
 * @ Applied Problems: single objective (constrained) numerical optimization
 *
 * @ Implements new problems to be solved
 *  Please refers to the source code that located at the directories: 
 *    problem/constrained and problem/unconstrained
 *
 * @ Parameters: N, NL, T
 * @ Maximum Evaluation times: N*T+NL
 *
 * @Contact:
 *  URL:   http://www.wiomax.com/sco
 *  EMAIL: xie@wiomax.com
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
 * [3] Xiao-Feng Xie, Jiming Liu, Zun-Jing Wang. A cooperative group optimization system. Soft Computing, 
 *   2014, 18(3): 469-495.
 */

import goodness.*;
import knowledge.*;
import problem.*;

public class SCGroup {
/**
 * The dual landscapes: envComparator & specComparator
 * envComparator: forms a natural landscape faithful to the problem landscape.
 *   It is used for identifying the truly better state among any two states 
 *   in the search space
 * specComparator: forms a runtime landscape, which is not necessarily  
 *   faithful to the problem landscape. It is used by search components 
 *   during the runtime. 
 */
  private IGoodnessCompareEngine envComparator = new BCHComparator();
  protected IGoodnessCompareEngine specComparator = new BCHComparator();
  
  protected SCAgent[] agents; //a set of agents

  public int N = 70;    //the number of agents
  public int T = 2000;  //the maximum running cycles

  private SearchPoint totalBestPoint;
    
  //The SC agents acquire social sharing information only from a sharing external
  //medium, called library (L) (comprises of a set of search points), or called
  //the external library from the viewpoint of all the agents
  protected ExternalLibrary extLib; //the external library 

  public int NL = 210;  //recommends: NL=3L && NL>30

  public SCGroup() throws Exception {}

  protected void updateExternalPublicInformation() {
    for(int i=0; i<agents.length; i++) {
       //submit the non-private knowledge point to the external library
       extLib.receiveBehavior(agents[i].getNonprivatePoint());
     }
     
     //update the public information (external library)
     extLib.updateBehavior();
  }

  //external information
  protected IStateSetEngine initPublicInformation(ProblemEncoder problemEncoder) {
    extLib = new ExternalLibrary(NL, problemEncoder, specComparator);
    return extLib.getStateSet();
  }

  //initiate the group
  public void initGroup(ProblemEncoder problemEncoder) {
    totalBestPoint = problemEncoder.getFreshSearchPoint();

    agents = new SCAgent[N];
    for(int i=0; i<agents.length; i++) {
      agents[i] = new SCAgent();
      agents[i].setProblemEncoder(problemEncoder);
      StateInfoHandler.replace(envComparator, agents[i].getNonprivatePoint(), totalBestPoint);
    }
        
    IStateSetEngine referLib = initPublicInformation(problemEncoder);
    for(int i=0; i<agents.length; i++) {
      agents[i].setSpecComparator(specComparator);
      agents[i].setPublicInformation(referLib);
    }
  }
  
  public SearchPoint getTotalBestPoint() {
    return totalBestPoint;
  }

  //run in the iterative mode.
  public void runLearningCycles(ICycleOutputEngine cycleOutputEngine) {
    cycleOutputEngine.outputCycle(0);
    for(int i=1; i<T+1; i++) {
      trailLearningCycle(i);
      cycleOutputEngine.outputCycle(i);
    }
  }
  
  //each cycle
  protected void trailLearningCycle(int t) {
    for(int i=0; i<agents.length; i++) {
      // generates a new point in the search space
      SearchPoint point = agents[i].generateBehavior(t);
      //store best result as the solution based on the envComparator
      StateInfoHandler.replace(envComparator, point, totalBestPoint);
    }
    
    updateExternalPublicInformation();

    for(int i=0; i<agents.length; i++) {
      //update the external library with the new point
      agents[i].updateBehavior();
    }
    
    //update the Comparator
    if (specComparator instanceof IUpdateCycleEngine) {
      ((IUpdateCycleEngine)specComparator).updateCycle(t);
    }
  }
}

