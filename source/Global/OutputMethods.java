/**
 * Description: Output methods for Array
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 */

package Global;


public class OutputMethods {

  public OutputMethods() {
  }

  public static String outputVectorAsStr(double[] vector){
    if(vector==null) return "NULL";
    String totalStr = "";
    for(int i=0;i<vector.length;i++){
      totalStr += vector[i];
      if(i!=vector.length-1) {
        totalStr += "\t";
      }
    }
    totalStr+="\r\n";
    return totalStr;
  }

  public static void outputVector(double[] vector){
    for(int i=0;i<vector.length;i++){
      System.out.print(vector[i]+" \t");
    }
    System.out.println("");
  }
}
