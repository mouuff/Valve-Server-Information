import java.net.*;
import java.util.*;

public class valve {
	byte[] INFO = valve.hexStringToByteArray("ffffffff54536f7572636520456e67696e6520517565727900");
	udp Udp;
	
	public valve(String server, int port) throws Exception{
		Udp = new udp(server, port);
	}
	
	public String[] info() throws Exception{
		Udp.sendByte(INFO);
		byte[] raw = Udp.recv();
		
		
		String name, map, folder, game;
		
		
		//byte[] data = java.util.Arrays.copyOfRange(raw, 2, raw.length);
		
		int end;
		byte[] raw1 = Arrays.copyOfRange(raw, 6, raw.length);
		
		end = valve.find(raw1,(byte)0x00);
		name = new String(Arrays.copyOfRange(raw1,0,end),"UTF-8");
		
		end = valve.find(raw1,(byte)0x00);
		byte[] raw2 = Arrays.copyOfRange(raw1, end+1, raw1.length);
		map = new String(Arrays.copyOfRange(raw2,0,valve.find(raw2,(byte)0x00)),"UTF-8");
		
		end = valve.find(raw2,(byte)0x00);
		byte[] raw3 = Arrays.copyOfRange(raw2, end+1, raw2.length);
		folder = new String(Arrays.copyOfRange(raw3,0,valve.find(raw3,(byte)0x00)),"UTF-8");
		
		end = valve.find(raw3,(byte)0x00);
		byte[] raw4 = Arrays.copyOfRange(raw3, end+1, raw3.length);
		game = new String(Arrays.copyOfRange(raw4,0,valve.find(raw4,(byte)0x00)),"UTF-8");
		
		byte[] raw5 = Arrays.copyOfRange(raw4, end, raw4.length);
		byte[] pack = Arrays.copyOfRange(raw5, 0, 7);
		
		Byte p, mp, b;
		
		/*
		if (pack[7] == (byte)0x01){
			p = pack[1];
			mp = pack[2];
			b = pack[3];
		}
		else if (pack[7] == (byte)0x31){
			p = pack[0];
			mp = pack[1];
			b = pack[2];
		}
		else{
			p = pack[5];
			mp = pack[6];
			b = pack[7];
		}
		* */
		p = pack[0];
		mp = pack[1];
		b = pack[2];
		
		String players = Integer.toString(p.intValue());
		String maxplayers = Integer.toString(mp.intValue());
		String bots = Integer.toString(b.intValue());
		
		String[] result = {name, map, game, players, maxplayers, bots};
		
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
