package junit3.extentions;

import junit3.core.Assert;
import junit3.core.Test;
import junit3.core.TestResult;

/**
 * 测试装饰器
 * Created by zhanghaojie on 2017/10/10.
 */
public class TestDecorator extends Assert implements Test {

    protected Test test;

    public TestDecorator(Test test) {
        this.test = test;
    }

    @Override
    public void run(TestResult testResult) {
        basicRun(testResult);
    }

    public void basicRun(TestResult testResult) {
        this.test.run(testResult);
    }

    @Override
    public int countTestCases() {
        return this.test.countTestCases();
    }

    public Test getTest() {
        return test;
    }
}
