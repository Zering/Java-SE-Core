package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ByteBufferDemo {

	public static void main(String[] args) {
		RandomAccessFile randomAccessFile = null;
		
		try {
			randomAccessFile = new RandomAccessFile("src/nio/ByteBufferDemo.java", "rw");
			FileChannel fileChannel = randomAccessFile.getChannel();
			ByteBuffer buff = ByteBuffer.allocate(1024);
			while((fileChannel.read(buff)) != -1){
				buff.flip();
				while (buff.hasRemaining()) {
					System.out.print((char)buff.get());
				}
				buff.compact();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (randomAccessFile != null) {
				try {
					randomAccessFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
