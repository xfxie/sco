/**
 * Description: The main program for executing SCGroup. 
 *   See the class SCGroup for more information.
 *
 * @Contact:
 *  URL:   http://www.wiomax.com/sco
 *  EMAIL: xie@wiomax.com
 *  
 * @Author         Create/Modi     Note
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
 * [3] X.-F. Xie, J. Liu, Z.-J. Wang. A cooperative group optimization system. Soft Computing, 
 *   2014, 18(3): 469-495.
 *
 */

import Global.*;
import knowledge.*;
import problem.*;
import problem.constrained.*;

public class SCO implements ICycleOutputEngine {
  // the problem to be solved, some examples are located at the directories: 
  // problem/constrained and problem/unconstrained
  private ProblemEncoder problemEncoder = new Michalewicz_G2();

  private int N = 70;
  private int T = 2000;

  private int NL = 210;  //recommends: NL=3L && NL>30

  private int Tout = 100; // only for output interval, not related to performance

  /* temp variables */
  private SearchPoint totalBestPoint;

  public SCO() throws Exception {}

  public static void main(String[] args) {
    try  {
      SCO sco = new SCO();
      sco.run(args);
    }
    catch (Exception e) {
      System.out.println("@ERROR: "+e.toString());
      System.exit(-1);
    }
  }

  protected String getHeadName() {
    return "Social Cognitive Optimization (SCO)";
  }

  private void printHeadInformation()  {
    System.out.println("@ MAOS-TOY V1.0.001, all rights reserved.");
    System.out.println("@ URL: http://www.wiomax.com");
    System.out.println();
    System.out.println("@Start Module ["+getHeadName()+"]");
    System.out.println();
  }

  public void run(String[] args) throws Exception {
    long time = System.currentTimeMillis();
    printHeadInformation();
    parserInputs(args);
    SCGroup group = new SCGroup();
    group.NL = NL;
    group.N = this.N;
    group.T = this.T;
    group.initGroup(problemEncoder);
    totalBestPoint = group.getTotalBestPoint();
    printInitInformation();
    System.out.println("[Runtime Information]");
    group.runLearningCycles(this);
    outputSummaryInfo();
    time = System.currentTimeMillis()-time;
    System.out.println("@ Total "+((double)time/1000.0)+" second costed.");
  }

  private void parserInputs(String[] args) {
    if(args.length>0) {
      System.out.println("->Initiate input parameters");
      for(int i=0; i<args.length; i++) {
        System.out.println(" "+args[i]);
        String[] vals = GlobalString.tokenize(args[i], GlobalString.EQUAL_TAG);
        if(vals.length == 2) {
          try {
            if(!parserInput(vals[0], vals[1])) {
              System.out.println(" @ERROR: No such parameter: "+vals[0]);
            }
          } catch (Exception e) {
            System.out.println(" @ERROR: "+e.getMessage()+", use default value");
          }
        }
      }
    } else {
      System.out.println("@The initial parameters can be changed by command line [name=value]");
    }
    System.out.println();
  }
  
  //output runtime information
  public void outputCycle(int cycleNum) {
    if (cycleNum%Tout==0) {
      if (problemEncoder instanceof UnconstrainedProblemEncoder) {
        System.out.println("t="+cycleNum+": Vopt="+totalBestPoint.getEncodeInfo()[1]);
      } else {
        System.out.println("t="+cycleNum+": Vcon="+totalBestPoint.getEncodeInfo()[0]+": Vopt="+totalBestPoint.getEncodeInfo()[1]);
      }
    }
  }
  
  /**
   * Support the command-line inputs, format at follows: Name=value
   *  # Name        valueType             example
   *  Problem       String                Problem=problem.constrained.Michalewicz_G2
   *  
   *  N             integer               N=40
   *  T             integer               T=500
   *  Tout          integer               Tout=100
   *  
   *  NL            integer               NL>1  //size of the external library
   */
  protected boolean parserInput(String name, String value) throws Exception {
    boolean isParsed = true;
    if(name.equalsIgnoreCase("T")) T = GlobalString.toInteger(value);
    else if(name.equalsIgnoreCase("N")) N = GlobalString.toInteger(value);
    else if(name.equalsIgnoreCase("Tout")) Tout = GlobalString.toInteger(value);
    else if(name.equalsIgnoreCase("Problem")) problemEncoder = (ProblemEncoder)GlobalString.toObject(value);
    else if(name.equalsIgnoreCase("NL"))  NL = GlobalString.toInteger(value);
    else isParsed=false;
    return isParsed;
  }

  protected String getSpecParamString() {
    return "NL="+NL;
  }

  void printInitInformation() {
    System.out.println("[INPUT information]");
    System.out.println("->Problem="+problemEncoder.getClass().getName());
    System.out.println("->General Parameters: N="+N+"; T="+T);
    String specString = getSpecParamString();
    if (specString.trim().length()>0) System.out.println("->Special Parameters: " + specString);
    System.out.println();
  }

  //output the final summary
  public void outputSummaryInfo() {
    //print the best point
    System.out.println();
    System.out.println("[Summary Information]");
    System.out.println("@Best Solution ever found:");
    totalBestPoint.outputSelf();
  }
}

