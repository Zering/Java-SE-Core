package junit3.textui;

import junit3.core.AssertionFailedError;
import junit3.core.Test;
import junit3.core.TestFailure;
import junit3.core.TestResult;
import junit3.runner.BaseTestRunner;

import java.io.PrintStream;
import java.util.Vector;

/**
 * Created by zhanghaojie on 2017/10/9.
 */
public class TestRunner extends BaseTestRunner {

    private PrintStream writer = System.out;

    private int column = 0;

    public TestRunner() {
    }

    @Override
    public synchronized void startTest(Test test) {
        writer.print(".");
        if (column++ > 40) {
            column = 0;
            System.out.println();
        }
    }

    @Override
    public synchronized void endTest(Test test) {

    }

    @Override
    public synchronized void addFailure(Test test, AssertionFailedError failedError) {
        writer.print("F");
    }

    @Override
    public synchronized void addError(Test test, Throwable t) {
        writer.print("E");
    }

    @Override
    public void runFailed(String message) {
        System.err.println(message);
        System.exit(-1);
    }

    public static void main(String[] args) {
        TestRunner runner = new TestRunner();
        try {
            TestResult result = runner.start(args);
            if (!result.wasAllSuccess()) {
                System.exit(-1);
            }
            System.exit(0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(-2);
        }
    }

    private TestResult start(String[] args) throws Exception {
        if (args.length == 0) {
            throw new Exception("Usage: TestRunner  testCaseName"); // 类的全系命名
        }

        String testName = args[0];

        try {
            Test test = getTest(testName);
            return doRun(test);
        } catch (Exception e) {
            throw new Exception("Could not create and run test suite: " + e);
        }

    }

    private TestResult doRun(Test test) {
        TestResult result = new TestResult();
        result.addListener(this);
        long startTime = System.currentTimeMillis();
        test.run(result);
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        writer.println();
        writer.println("Time: " + Long.toString(runTime));
        print(result);
        writer.println();
        return result;
    }

    private synchronized void print(TestResult result) {
        printErrors(result);
        printFailures(result);
        printHeader(result);
    }

    private void printErrors(TestResult result) {
        if (result.getErrorCount() != 0) {
            if (result.getErrorCount() == 1) {
                writer.println("There was 1 error!");
            } else {
                writer.println("There were " + result.getErrorCount() + " errors!");
            }
            Vector<TestFailure> errors = result.getErrors();
            for (int i = 0; i < errors.size();) {
                TestFailure testFailure = errors.get(i);
                writer.println(++i + " )" + testFailure.getFailedTest());
                writer.println(getFilteredTrace(testFailure.getThrownException()));
            }
        }
    }

    private void printFailures(TestResult result) {
        if (result.getFailureCount() != 0) {
            if (result.getFailureCount() == 1) {
                writer.println("There was 1 failure!");
            } else {
                writer.println("There were " + result.getFailureCount() + " failures!");
            }
            Vector<TestFailure> failures = result.getFailures();
            for (int i = 0; i < failures.size();) {
                TestFailure testFailure = failures.get(i);
                writer.println(++i + " )" + testFailure.getFailedTest());
                writer.println(getFilteredTrace(testFailure.getThrownException()));
            }
        }
    }

    private void printHeader(TestResult result) {
        if (result.wasAllSuccess()) {
            writer.println();
            writer.println("OK!");
            writer.println("( " + result.getTestCount() + " ) tests");
        } else {
            writer.println();
            writer.println("Failures");
            writer.println("(All: " + result.getTestCount() + " ) tests," + " failures:" + result.getFailureCount() + "," + " Errors: "
                    + result.getErrorCount());
        }
    }

}
