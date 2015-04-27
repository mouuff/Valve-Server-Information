import java.net.*;
import java.util.*;
import java.nio.*;

public class valve {
	byte[] INFO = valve.hexStringToByteArray("ffffffff54536f7572636520456e67696e6520517565727900");
	udp Udp;
	
	public valve(String server, int port) throws Exception{
		Udp = new udp(server, port);
	}
	
	public void info() throws Exception{
		Udp.sendByte(INFO);
		byte[] raw = Udp.recv();
		
		char[] packet = "xxxxcbsssshbbb".toCharArray();
		
		int pos = 0;
		
		for (int x = 0; x < packet.length; x++){
			
			char type = packet[x];
			if (type == 'x'){
				pos += 1;
				continue;
			}
			else if (type == 'c'){
				char c = (char) (raw[pos] & 0xFF);
				System.out.println(c);
				pos += 1;
			}
			else if (type == 'h'){
				ByteBuffer bb = ByteBuffer.allocate(2);
				bb.order(ByteOrder.LITTLE_ENDIAN);
				bb.put(raw[pos]);
				bb.put(raw[pos+1]);
				short shortVal = bb.getShort(0);
				System.out.println(shortVal);
				pos += 2;
			}
			else if (type == 's'){
				String s = "";
				
				while (raw[pos] != (byte)0x00){
					char c = (char) (raw[pos] & 0xFF);
					s += Character.toString(c);
					pos += 1;
				}
				System.out.println(s);
				pos += 1;
			}
			else if (type == 'b'){
				Byte p = raw[pos];
				System.out.println(Integer.toString(p.intValue()));
				pos += 1;
			}
			
			//System.out.println(packet[x]);
		}
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
