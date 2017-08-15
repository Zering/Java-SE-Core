package junit3.core;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Vector;

/**
 * Created by zhanghaojie on 2017/8/14.
 */
public class TestSuite implements Test {

  private Vector<Test> tests = new Vector<>(10);
  private String tname;

  public TestSuite(final Class<?> theClass) {
    this.tname = theClass.getName();
    Constructor<?> constructor = null;
    try {
      constructor = theClass.getConstructor(String.class);
    } catch (NoSuchMethodException e) {
      addTest(warning("Class " + tname + " has't public TestCase(String name)"));
      return;
    }

    if (!Modifier.isPublic(theClass.getModifiers())) {
      addTest(warning("Class " + tname + " isn't public"));
    }

    Vector<String> names = new Vector<>();
    Method[] methods = theClass.getMethods();
    for (Method method : methods) {
      addMethod(method, names, constructor);
    }

    if (tests.size() == 0) {
      addTest(warning("No tests found in " + tname));
    }

  }

  private void addMethod(Method method, Vector<String> names, Constructor<?> constructor) {
    String name = method.getName();
    if (names.contains(name)) {
      return;
    }
    if (isPublicTestMethod(method)) {
      names.addElement(name);
      Object[] args = new Object[]{name};
      try {
        addTest((Test) constructor.newInstance(args));
      } catch (InstantiationException e) {
        addTest(
            warning("Can not instantiate test case " + name + " (" + exceptionToString(e) + ")"));
      } catch (IllegalAccessException e) {
        addTest(warning("Can not access test case " + name + " (" + exceptionToString(e) + ")"));
      } catch (InvocationTargetException e) {
        addTest(warning("Can not invoke test case " + name + " (" + exceptionToString(e) + ")"));
      }
    } else {
      if (isTestMethod(method)) {
        addTest(warning("It's not public : " + method.getName()));
      }
    }


  }

  private String exceptionToString(Throwable t) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    t.printStackTrace(pw);
    return sw.toString();
  }

  private boolean isPublicTestMethod(Method method) {
    return Modifier.isPublic(method.getModifiers()) && isTestMethod(method);
  }

  private boolean isTestMethod(Method method) {
    String name = method.getName();
    Parameter[] parameters = method.getParameters();
    Class<?> returnType = method.getReturnType();
    return name.startsWith("test") && parameters.length == 0 && returnType.equals(Void.TYPE);
  }

  private Test warning(String message) {
    return new TestCase("warning") {
      public void runBare() {
        fail(message);
      }
    };
  }


  @Override
  public void run(TestResult testResult) {
    for (Test test : tests) {
      test.run(testResult);
    }
  }

  public void addTest(Test test) {
    tests.addElement(test);
  }

  @Override
  public int countTestCases() {
    int count = 0;
    for (Test test : tests) {
      count += test.countTestCases();
    }
    return count;
  }
}
