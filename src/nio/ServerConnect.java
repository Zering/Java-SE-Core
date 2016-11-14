package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ServerConnect {

	private static final int BUF_SIZE = 1024;
	private static final int PORT = 8080;
	private static final int TIMEOUT = 3000;
	public static void main(String[] args) {
		selector();
		System.out.println();
	}
	
	public static void selector() {
		Selector selector = null;
		ServerSocketChannel serverSocketChannel = null;
		
		try {
			selector = Selector.open();
			serverSocketChannel = ServerSocketChannel.open();
			
			serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			
			while(true){
				if (selector.select(TIMEOUT) == 0) {
					System.out.println("========END=========");
					continue;
				}
				
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					if(key.isAcceptable()){
						handleAccept(key);
					}
					if (key.isReadable()) {
						handleRead(key);
					}
					if (key.isWritable() && key.isValid()) {
						handleWrite(key);
					}
					if (key.isConnectable()) {
						System.out.println("isConnectable is true");
					}
					iterator.remove();
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (selector != null) {
					selector.close();
				}
				if (serverSocketChannel != null) {
					serverSocketChannel.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static void handleAccept(SelectionKey key) throws IOException{
		ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
		SocketChannel channel = ssChannel.accept();
		channel.configureBlocking(false);
		channel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(BUF_SIZE));
	}
	
	private static void handleRead(SelectionKey key) throws IOException{
		SocketChannel sChannel = (SocketChannel) key.channel();
		ByteBuffer buffer = (ByteBuffer) key.attachment();
		long bytesRead;
		while((bytesRead = sChannel.read(buffer)) > 0){
			buffer.flip();
			while (buffer.hasRemaining()) {
				System.out.print((char)buffer.get());
			}
			System.out.println();
			buffer.clear();
		}
		
		if (bytesRead == -1) {
			sChannel.close();
		}
	}
	
	private static void handleWrite(SelectionKey key) throws IOException{
		ByteBuffer buffer = (ByteBuffer) key.attachment();
		buffer.flip();
		SocketChannel sChannel = (SocketChannel) key.channel();
		while (buffer.hasRemaining()) {
			sChannel.write(buffer);
		}
		buffer.compact();
	}
}
