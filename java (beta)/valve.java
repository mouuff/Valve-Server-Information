import java.net.*;
import java.util.*;

public class valve {
	byte[] INFO = valve.hexStringToByteArray("ffffffff54536f7572636520456e67696e6520517565727900");
	udp Udp;
	
	public valve(String server, int port) throws Exception{
		Udp = new udp(server, port);
	}
	
	public byte[] info() throws Exception{
		Udp.sendByte(INFO);
		byte[] rep = Udp.recv();
		return rep;
	}
	
    public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
								 + Character.digit(s.charAt(i+1), 16));
		}
		return data;
	}
}
