/**
 * Description: provide the information for evaluating a set of targets values
 * into encoded information (For formation the goodness landscape by comparing)
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @References:
 * [1] Deb K. An efficient constraint handling method for genetic algorithms.
 * Computer Methods in Applied Mechanics and Engineering, 2000, 186(2-4): 311-338
 */

package encode;

public class EvalStruct {
  // The information for evaluating all the responses
  public EvalElement[] evalElems = null;

  public EvalStruct(int elemsNum) {
    evalElems = new EvalElement[elemsNum];
  }
  public int getSize() {
    return evalElems.length;
  }

  public void setElemAt(EvalElement dim, int index) {
    evalElems[index] = dim;
  }

  //convert response values into encoded information double[2]
  public void evaluate(double[] evalRes, double[] targetValues) {
    evalRes[0] = evalRes[1] = 0;
    for(int i=0; i<evalElems.length; i++) {
      if (evalElems[i].isOptType()) {
        //The objectives (OPTIM type)
        //multi-objective will be translated into single-objective
        evalRes[1] += evalElems[i].evaluateOPTIM(targetValues[i]);
      } else {
        //The constraints (CONS type)
        //If evalRes[0] equals to 0, then be a feasible point, i.e. satisfies
        // all the constraints
        evalRes[0] += evalElems[i].evaluateCONS(targetValues[i]);
      }
    }
  }
}

