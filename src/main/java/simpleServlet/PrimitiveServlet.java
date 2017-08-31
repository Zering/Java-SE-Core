package simpleServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 简单Servlet用于测试
 * 需要导入sevlet-api.jar
 * @author Zhanghj @ 2016年11月29日
 *
 */
public class PrimitiveServlet implements Servlet {

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("init");
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("from service");
		PrintWriter writer = response.getWriter();
		writer.println("Roses are red");
		writer.print("Voilet are blue");
	}
	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

}
