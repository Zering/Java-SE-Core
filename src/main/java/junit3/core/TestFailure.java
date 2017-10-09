package junit3.core;

/**
 * Created by zhanghaojie on 2017/8/14.
 */
public class TestFailure {

  protected Test failedTest;
  protected Throwable thrownException;


  public TestFailure(Test failedTest, Throwable thrownException) {
    this.failedTest = failedTest;
    this.thrownException = thrownException;
  }

  public Test getFailedTest() {
    return failedTest;
  }

  public Throwable getThrownException() {
    return thrownException;
  }

}
