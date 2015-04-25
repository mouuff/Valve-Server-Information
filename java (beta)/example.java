import java.net.*;
import java.util.*;

public class example {
    public static void main(String[] args){
		try{
			valve server = new valve("192.223.29.10", 27015);
			byte[] bytes = server.info();
			String str = new String(bytes, "UTF-8");
			System.out.print(str);
		}
		catch (Exception e){
			e.printStackTrace();
		}
    }
}
