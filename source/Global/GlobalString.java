/**
 * Description: operations for the a text string.
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 */

package Global;

import java.util.*;

public class GlobalString {
  public static final String EQUAL_TAG = "=";

/**
  * Tokenize a String with given key.
  * @param      input      the String to be tokenized.
  * @param      tokenKey   the delimiters.
  * @return  a String array that include the elements of input string that
  * divided by the tokenKey.
  */
  public static String[] tokenize(String input , String tokenKey) {
    Vector<String> v = new Vector<String>();
    StringTokenizer t = new StringTokenizer(input, tokenKey);
    String cmd[];
    while (t.hasMoreTokens())
      v.addElement(t.nextToken());
    cmd = new String[v.size()];
    for (int i = 0; i < cmd.length; i++)
      cmd[i] = (String) v.elementAt(i);
    return cmd;
  }

  static public int toInteger(Object oVal) throws Exception {
    if(oVal==null) throw new Exception("Null string");
    return new Integer(oVal.toString()).intValue();
  }

  static public double toDouble(Object oVal) throws Exception {
    if(oVal==null) throw new Exception("Null string");
    return new Double(oVal.toString()).doubleValue();
  }

  public static Object toObject(String key) throws Exception{
    Class<?> cls = Class.forName(key);
    return cls.newInstance();
  }
}
