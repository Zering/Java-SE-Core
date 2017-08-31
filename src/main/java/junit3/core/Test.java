package junit3.core;

/**
 * Created by zhanghaojie on 2017/8/14.
 */
public interface Test {

  /**
   * 运行测试
   */
  void run(TestResult testResult);

  /**
   * 计数
   *
   * @return 该测试类包含的测试数
   */
  int countTestCases();
}
