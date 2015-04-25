import java.io.*;
import java.net.*;

public class udp
{
	DatagramSocket socket;
	InetAddress local;
	
	public udp(String server, int port) throws Exception{
		local = InetAddress.getByName(server);
		socket = new DatagramSocket(port, local);
	}
	public byte[] recv() throws Exception{
		byte[] receiveData = new byte[1024];
		
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		socket.receive(receivePacket);
		
		return receiveData;
	}
	
	public void sendByte(byte[] message) throws Exception{
		int msg_length = message.length;
		
		DatagramPacket packet = new DatagramPacket(message, msg_length);
		
		socket.send(packet);
	}
}
