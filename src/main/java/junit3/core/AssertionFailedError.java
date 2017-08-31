package junit3.core;

/**
 * Created by zhanghaojie on 2017/8/14.
 */
public class AssertionFailedError extends Error {

  public AssertionFailedError() {
  }

  public AssertionFailedError(String message) {
    super(message);
  }
}
