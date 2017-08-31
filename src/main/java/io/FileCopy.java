package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy {

	public static void main(String[] args) {
		InputStream in = null;
		OutputStream out = null;
				
		try {
			in = new BufferedInputStream(new FileInputStream("src/io/FileCopy.java"));
			out = new BufferedOutputStream(new FileOutputStream("src/io/CopyOfFileCopy.txt"));
			byte[] buff = new byte[1024];
			int read;
			while((read = in.read(buff)) != -1){
				out.write(buff, 0, read);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (in != null) 
					in.close();
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
