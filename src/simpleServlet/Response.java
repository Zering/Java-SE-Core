package simpleServlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

public class Response implements ServletResponse {
	
	private final static int BUFFER_SIZE = 1024;
	Request request;
	OutputStream output;
	PrintWriter writer;
	
	public Response(OutputStream output) {
		this.output = output;
	}
	public void setRequest(Request request) {
		this.request = request;
	}

	public void sendStaticResource() throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		
		try {
			File file = new File(Constants.WEB_ROOT, request.getUri());
			fis = new FileInputStream(file);
			
			int ch = fis.read(buffer, 0, BUFFER_SIZE);
			while (ch != -1) {
				output.write(buffer, 0, BUFFER_SIZE);
				ch = fis.read(buffer, 0, BUFFER_SIZE);
			}
		} catch (FileNotFoundException e) {
			String errorMessage = "HTTP/1.1 404 File Not Found\r\n" + 
							"Content-Type: text/html\r\n" + 
							"Content-Length: 23\r\n" + 
							"\r\n" + 
							"<h1>File Not Found</h1>";
			output.write(errorMessage.getBytes());
		} finally {
			if (fis != null) fis.close();
		}
	}
	



	@Override
	public void flushBuffer() throws IOException {

	}

	@Override
	public int getBufferSize() {
		return 0;
	}

	@Override
	public String getCharacterEncoding() {
		return null;
	}

	@Override
	public String getContentType() {
		return null;
	}

	@Override
	public Locale getLocale() {
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		writer = new PrintWriter(output, true);
		return writer;
	}

	@Override
	public boolean isCommitted() {
		return false;
	}

	@Override
	public void reset() {

	}

	@Override
	public void resetBuffer() {

	}

	@Override
	public void setBufferSize(int arg0) {

	}

	@Override
	public void setCharacterEncoding(String arg0) {

	}

	@Override
	public void setContentLength(int arg0) {

	}

	@Override
	public void setContentType(String arg0) {

	}

	@Override
	public void setLocale(Locale arg0) {

	}

}
