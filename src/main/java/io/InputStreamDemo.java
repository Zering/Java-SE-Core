package io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author Zhanghj @ 2016年10月10日
 *
 */
public class InputStreamDemo {
	public static void main(String[] args) {
		
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(
											new FileInputStream("src/io/InputStreamDemo.java"));
			byte[] buff = new byte[1024];
			int read = 0;
			while ( (read = inputStream.read(buff)) != -1) {
				for(int i = 0; i < read; i++)
					System.out.print((char)buff[i]);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
}
