package junit3.extentions;

import junit3.core.Test;
import junit3.core.TestResult;

/**
 * 在整个测试前后 做一些额外的事情
 * Created by zhanghaojie on 2017/10/10.
 */
public class TestSetup extends TestDecorator {

    public TestSetup(Test test) {
        super(test);
    }

    @Override
    public void run(TestResult testResult) {
        testResult.runProtected(this, () -> {
            this.setup();
            this.basicRun(testResult);
            this.tearDown();
        });
    }

    protected void setup() {
        System.out.println("===== TEST START =====");
    }

    protected void tearDown() {
        System.out.println("===== TEST END =====");
    }

}
