package junit3.listener;

import junit3.core.AssertionFailedError;
import junit3.core.Test;

/**
 * Created by zhanghaojie on 2017/10/9.
 */
public interface TestListener {

    void startTest(Test test);

    void endTest(Test test);

    void addFailure(Test test, AssertionFailedError failedError);

    void addError(Test test, Throwable t);
}
