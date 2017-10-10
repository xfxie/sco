#Software description:
Social cognitive optimization (SCO) for solving numerical optimization problems

#Compile: Type "ant" to build, and the output file will be dist/depso.jar.

#Command line: 
java SCO [NAME=VALUE] ...
See release\RUNExample.bat for more information.

#Supported parameters:

No. NAME    VALUE_type  Description
1  Problem  String      The problem to be solved, some examples are located at the directories: problem/constrained and problem/unconstrained
2  isACR    boolean     Defines the Comparator: if TRUE, then be ACRComparator, else be BCHComparator (default)
3  N        integer     The number of agents (N>=1), the default value is 70
4  NL       integer     The number of SearchPoints in the external library (L), the default value is 3*N = 210
5  T        integer     The maximum learning cycles, the default value is 2000
6  Tout     integer     The inteval cycles for output the running messages (not important), the default value is 100

#Examples for existing problems
java SCO N=70 T=2000 NL=280 Problem=problem.constrained.Michalewicz_G1 
java SCO N=70 T=2000 NL=280 Problem=problem.constrained.Michalewicz_G2 
java SCO T=100 N=10 NL=40 Tout=10 Problem=problem.unconstrained.GoldsteinPrice 

#Output
The program outputs information of the best point every "Tout" cycles.
At the end, it outputs the location and optimum values of the best point.
//Vcon: the weighted constraint violation value (>=0): if Vcon==0, then no violation
//Vopt: the value of objective function

#References:
[1] X.-F. Xie, W.-J. Zhang, Z.-L. Yang. Social cognitive optimization for nonlinear programming
  problems. International Conference on Machine Learning and Cybernetics. Beijing, China, 
  2002: 779-783
[2] X.-F. Xie, W.-J. Zhang. Solving engineering design problems by social cognitive optimization. 
  Genetic and Evolutionary Computation Conference, LNCS 3102, Seattle, WA, USA, 2004: 261-262
[3] Xiao-Feng Xie, Jiming Liu, Zun-Jing Wang. A cooperative group optimization system. Soft Computing, 
  2014, 18(3): 469-495.

# License description:
 *******************************************************************
 * See the Creative Commons Non-Commercial License 3.0 for more details.
 *
 * Please acknowledge the author(s) if you use this code in any way.
 *******************************************************************

#Contact information:
URL:   http://www.wiomax.com/sco
EMAIL: xie@wiomax.com
