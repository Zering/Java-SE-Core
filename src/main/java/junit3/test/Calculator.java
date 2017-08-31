package junit3.test;

/**
 * Created by zhanghaojie on 2017/8/14.
 */
public class Calculator {

  private int result;

  public int add(int add) {
    return result += add;
  }

  public int getResult() {
    return result;
  }
}
