package junit3.core;

/**
 * Created by zhanghaojie on 2017/8/14.
 */
public class Assert {

  public static void assertTrue(String message, boolean condition) {
    if (!condition) {
      fail(message);
    }
  }

  public static void assertTrue(boolean condition) {
    assertTrue(null, condition);
  }

  public static void assertNotNull(Object o) {
    assertTrue(o != null);
  }

  public static void fail(String message) {
    throw new AssertionFailedError(message);
  }

}
