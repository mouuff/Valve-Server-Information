import java.net.*;
import java.util.*;
import java.nio.*;

public class valve {
	byte[] INFO = valve.hexStringToByteArray("ffffffff54536f7572636520456e67696e6520517565727900");
	
	public int V_HEADER = 0;
	public int V_PROTOCOL = 1;
	public int V_NAME = 2;
	public int V_MAP = 3;
	public int V_FOLDER = 4;
	public int V_GAME = 5;
	public int V_ID = 6;
	public int V_PLAYERS = 7;
	public int V_MAX_PLAYERS = 8;
	public int V_BOTS = 9;
	
	
	udp Udp;
	
	public valve(String server, int port) throws Exception{
		Udp = new udp(server, port);
	}

	
	public String[] info() throws Exception{
		Udp.sendByte(INFO);
		byte[] raw = Udp.recv();
		
		char[] packet = "xxxxcbsssshbbb".toCharArray();
		String[] result = struct.unpack(packet, raw);
		
		return result;
	}
	public static int find(byte[] bytes, byte b){
		for (int x = 0; x < bytes.length; x++){
			if (bytes[x] == b){
				return x;
			}
		}
		return bytes.length;
	}
	public void close() throws Exception{
		Udp.close();
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
