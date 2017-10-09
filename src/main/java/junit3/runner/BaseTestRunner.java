package junit3.runner;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

import junit3.core.Test;
import junit3.core.TestSuite;
import junit3.listener.TestListener;

/**
 * Created by zhanghaojie on 2017/10/9.
 */
public abstract class BaseTestRunner implements TestListener {

    // 固定测试方法名
    private static final String SUITE_METHOD_NAME = "suite";

    public Test getTest(String suiteName) {
        if (StringUtils.isBlank(suiteName)) {
            return null;
        }
        Class<?> testClass = null;
        try {
            testClass = loadClass(suiteName);
        } catch (ClassNotFoundException e) {
            String failedMessage = getFilteredTrace(e);
            runFailed(failedMessage);
            return null;
        } catch (Exception e) {
            runFailed("Error: " + e.toString());
        }
        Method method;
        try {
            method = testClass.getMethod(SUITE_METHOD_NAME, new Class[0]);// 固定方法名 无参
        } catch (Exception e) {
            return new TestSuite(testClass);
        }
        Test test = null;
        try {
            test = (Test) method.invoke(null, new Class[0]);
        } catch (IllegalAccessException e) {
            runFailed("Failed to invoke suite():" + e.toString());
        } catch (InvocationTargetException e) {
            runFailed("Failed to invoke suite():" + e.getTargetException().toString());
        }
        return test;
    }

    private Class<?> loadClass(String suitename) throws ClassNotFoundException {
        // getClass().getClassLoader().loadClass(suitename);
        return Class.forName(suitename);
    }

    /**
     * 错误信息
     *
     * @param t
     * @return
     */
    public String getFilteredTrace(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        t.printStackTrace(printWriter);
        return stringWriter.getBuffer().toString();
    }

    public abstract void runFailed(String message);
}
