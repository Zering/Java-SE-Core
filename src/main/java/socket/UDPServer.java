package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

	public static void main(String[] args) {
		try {
			DatagramSocket datagramSocket = new DatagramSocket(8000);
			byte[] data = new byte[1024];
			DatagramPacket p = new DatagramPacket(data,data.length);
			System.out.println("*** 我是服务器，等待客户端 ***");
			
			/**
			 * 接收消息
			 */
			datagramSocket.receive(p);
			String info = new String(data, 0, p.getLength());
			System.out.println("服务器收到消息："+info);
			
			/**
			 * 响应消息
			 */
			InetAddress address = p.getAddress();
			int port = p.getPort();
			byte[] bs = "来自服务器的消息：欢迎您！".getBytes();
			DatagramPacket datagramPacket = new DatagramPacket(bs, bs.length, address, port);
			datagramSocket.send(datagramPacket);
			
			datagramSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
