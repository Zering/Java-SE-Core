package junit3.test;

import junit3.core.Test;
import junit3.core.TestSuite;

/**
 * Created by zhanghaojie on 2017/10/9.
 */
public class CalculatorSuiteTest {

    public static Test suite() {
        TestSuite suite = new TestSuite("Calculator All Test");
        suite.addTestSuite(CalculatorTest.class);
        return suite;
    }

}
