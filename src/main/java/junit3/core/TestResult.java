package junit3.core;

import java.util.Vector;

/**
 * Created by zhanghaojie on 2017/8/14.
 */
public class TestResult {

  // 测试结果错误
  protected Vector<TestFailure> failures;
  // 程序异常
  protected Vector<TestFailure> errors;

  protected int testCount;
  protected boolean stop;

  public TestResult() {
    failures = new Vector<>();
    errors = new Vector<>();
    testCount = 0;
    stop = false;
  }

  public void addFailure(Test test, AssertionFailedError t) {
    failures.addElement(new TestFailure(test, t));
  }

  public void addError(Test test, Throwable t) {
    errors.addElement(new TestFailure(test, t));
  }

  protected void startTest(final Test testCase) {
    testCount += testCase.countTestCases();
  }

  protected void run(final TestCase testCase) {
    startTest(testCase);
    try {
      testCase.runBare();
    } catch (AssertionFailedError failedError) {
      addFailure(testCase, failedError);
    } catch (Throwable throwable) {
      addError(testCase, throwable);
    }
    endTest(testCase);
  }

  protected void endTest(TestCase testCase) {

  }


  public Vector<TestFailure> getFailures() {
    return failures;
  }

  public Vector<TestFailure> getErrors() {
    return errors;
  }

  public int getTestCount() {
    return testCount;
  }

  public int getFailureCount() {
    return failures.size();
  }

  public int getErrorCount() {
    return errors.size();
  }

  public boolean shouldStop() {
    return stop;
  }

  public void stop() {
    stop = true;
  }

  public boolean wasAllSuccess() {
    return failures.isEmpty() && errors.isEmpty();
  }

}
