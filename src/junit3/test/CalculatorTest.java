package junit3.test;

import junit3.core.TestCase;
import junit3.core.TestFailure;
import junit3.core.TestResult;
import junit3.core.TestSuite;

/**
 * Created by zhanghaojie on 2017/8/15.
 */
public class CalculatorTest extends TestCase {

  public CalculatorTest(String tname) {
    super(tname);
  }

  private Calculator calculator = null;

  @Override
  public void setUp() {
    calculator = new Calculator();
  }

  @Override
  public void tearDown() {
    calculator = null;
  }


  public void testAdd1() {
    System.out.println(
        "测试1开始：" + "线程[" + Thread.currentThread().getName() + "], 所处类信息 " + toString());
    calculator.add(10);
    assertTrue(calculator.getResult() == 10);
    System.out.println("测试1结束！");
  }

  public void testAdd2() {
    System.out.println(
        "测试2开始：" + "线程[" + Thread.currentThread().getName() + "], 所处类信息 " + toString());
    calculator.add(15);
    calculator.add(20);
    assertTrue(calculator.getResult() == 30); // failure
    System.out.println("测试2结束！");
  }

  public static void main(String[] args) {
    TestSuite ts = new TestSuite(CalculatorTest.class);
    TestResult tr = new TestResult();
    ts.run(tr);
    System.out.println(tr.getTestCount());
    System.out.println(tr.wasAllSuccess());
    System.out.println(tr.getFailureCount());
    for (TestFailure failure : tr.getFailures()) {
      System.out
          .println(failure.getFailedTest() + " : " + failure.getThrownException().getMessage());
    }
    System.out.println(tr.getErrorCount());
    for (TestFailure failure : tr.getErrors()) {
      System.out
          .println(failure.getFailedTest() + " : " + failure.getThrownException().getMessage());
    }

  }

}
