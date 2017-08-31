package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

	public static void main(String[] args) {
		try {
			/**
			 * 发送消息
			 */
			InetAddress inetAddress = InetAddress.getByName("localhost");
			int port = 8000;
			byte[] data = "我是客户端，通过udp发送消息".getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, inetAddress, port);
			DatagramSocket socket = new DatagramSocket();
			socket.send(packet);
			/**
			 * 接收消息
			 */
			byte[] data2 = new byte[1024];
			DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
			socket.receive(packet2);
			String reply = new String(data2, 0, packet2.getLength());
			System.out.println(reply);
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
