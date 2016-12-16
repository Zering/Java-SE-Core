package simpleServlet;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ServletProcessor {

	public void process(Request request, Response response) {
		String uri = request.getUri();
		String servletName = uri.substring(uri.lastIndexOf("/") + 1);
		URLClassLoader classLoader = null;
		try {
			URL[] urls = new URL[1];
			URLStreamHandler handler = null;
			File classpath = new File(Constants.WEB_ROOT);
			System.out.println("---classpath:"+classpath.getCanonicalPath());
			String repository = (new URL("file", null, classpath.getCanonicalPath() + File.separator)).toString();
			urls[0] = new URL(null, repository, handler);
			System.out.println("url:"+urls[0].toString());
			classLoader = new URLClassLoader(urls);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Class myClass = null;
		try {
			System.out.println(servletName);
			myClass = classLoader.loadClass("PrimitiveServlet");
			System.out.println("class name:"+myClass.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Servlet servlet = null;
		try {
			servlet = (Servlet) myClass.newInstance();
			servlet.service((ServletRequest) request, (ServletResponse) response);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
