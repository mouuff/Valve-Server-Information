import java.io.*;
import java.net.*;

public class udp
{
	DatagramSocket socket;
	InetAddress IPAddress;
	int port;
	
	public udp(String server, int server_port) throws Exception{
		port = server_port;
		IPAddress = InetAddress.getByName(server);
		socket = new DatagramSocket(new InetSocketAddress(port));
		socket.setSoTimeout(3000);
	}
	public byte[] recv() throws Exception{
		byte[] receiveData = new byte[2048];
		
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		
		socket.receive(receivePacket);
		
		return receiveData;
	}
	
	public void sendByte(byte[] message) throws Exception{
		int msg_length = message.length;
		
		DatagramPacket packet = new DatagramPacket(message, msg_length, IPAddress, port);
		
		socket.send(packet);
	}
	public void close() throws Exception{
		socket.close();
	}
}
