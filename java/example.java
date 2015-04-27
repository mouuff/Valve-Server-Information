import java.net.*;
import java.util.*;

public class example {
    public static void main(String[] args){
		try{
			valve server = new valve(args[0],Integer.parseInt(args[1]));
			String[] infos = server.info();
			for (int x = 0; x < infos.length; x++){
				System.out.println(infos[x]);
			}
			server.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
    }
}
