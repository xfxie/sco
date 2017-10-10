/**
 * Description: Adaptive constraints relaxing rule (ACR)
 *   Defines a dynamic landscape, by adaptively relaxing the limit for  
 *   the constraint violation value based on the runtime information.
 *
 * @ Applied domain: efficiently for the feasible space of the ridge class, such as
 *  the problem with equality constraints
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @version 1.0
 * @Since MAOS1.2
 *
 * @Reference
 * [1] X.-F. Xie, W.-J. Zhang, D.-C. Bi. Handling equality constraints by adaptive
 *     relaxing rule for swarm algorithms. Congress on Evolutionary Computation (CEC),
 *     Portland, OR, USA, 2004: 2012-2016.
 */

package goodness;

import knowledge.*;

public class ACRComparator implements IGoodnessCompareEngine, IUpdateCycleEngine {
  private IStateSetEngine socialPool; //the runtime information

  private double epsilon_t = 0;  //the dynamic relaxing value
  private double T = -1;         //maximum number of cycles

  private double RU = 0.75;      //1<RU<RL<0
  private double RL = 0.25;
  private double BETAF = 0.618;  // (0,1)
  private double BETAL = 0.618;  // (0,1)
  private double BETAU = 1.382;  // >1

  private double TthR = 0.5;     // (0,1)

  public ACRComparator(IStateSetEngine socialPool, int T) {
    this.socialPool = socialPool;
    this.T = T;
    //set epsilon_t as the maximum CONS value among the search points
    epsilon_t = StateInfoHandler.getExtremalVcon(socialPool, true);
  }

  static public int compare(double data1, double data2) {
    if (data1 < data2)
      return LESS_THAN;
    else if (data1 > data2)
      return LARGER_THAN;
    else
      return EQUAL_TO;
  }

  public int compare(double[] fit1, double[] fit2) {
    if(Math.max(fit1[0], fit2[0])<=Math.max(0, epsilon_t)) { //epsilon>0
      return compare(fit1[1], fit2[1]);
    } else {
      return compare(fit1[0], fit2[0]);
    }
  }

  public void updateCycle(int t) {
    //calculates the ratio
    double rn = (double)StateInfoHandler.getVconThanNum(socialPool, epsilon_t)/(double)socialPool.getSize();
    if(t>TthR*T &&T!=-1) { //Forcing sub-rule
      epsilon_t *= BETAF;
    } else {  	          //Ratio-keeping sub-rules
      if(rn>RU) {
        epsilon_t *= BETAL;  //Shrink
      }
      if(rn<RL) {
        epsilon_t *= BETAU;  //Relax
      }
    }
  }
}
