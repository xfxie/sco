/**
 * Description: For accessing a set of knowledge points (states).
 *
 * @ Author        Create/Modi     Note
 * Xiao-Feng Xie   Oct 01, 2017
 *
 * @version 1.0
 * @Since MAOS1.0
 */

package knowledge;

public interface IStateSetEngine {
  public int getSize();

  public SearchPoint getSelectedPoint(int index);
}



