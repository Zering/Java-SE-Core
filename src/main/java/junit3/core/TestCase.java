package junit3.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by zhanghaojie on 2017/8/14.
 */
public class TestCase extends Assert implements Test {

  private String tname;

  public TestCase(String tname) {
    this.tname = tname;
  }

  @Override
  public void run(TestResult testResult) {
    testResult.run(this);
  }

  public void runBare() throws Throwable {
    setUp();
    try {
      runTest();
    } finally {
      tearDown();
    }
  }

  public void runTest() throws Throwable {
    assertNotNull(tname);
    Method method = null;
    try {
      method = getClass().getMethod(tname, null);
    } catch (NoSuchMethodException e) {
      fail("Method " + tname + " not found");
    }

    if (!Modifier.isPublic(method.getModifiers())) {
      fail("Method " + tname + " is not public");
    }

    try {
      method.invoke(this, new Class[0]);
    } catch (InvocationTargetException e) {
      e.fillInStackTrace();
      throw e.getTargetException();
    } catch (IllegalAccessException e) {
      e.fillInStackTrace();
      throw e;
    }

  }


  protected void setUp() throws Exception {

  }

  protected void tearDown() throws Exception {

  }

  @Override
  public int countTestCases() {
    return 1;
  }
}
