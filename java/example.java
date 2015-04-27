import java.net.*;
import java.util.*;

public class example {
    public static void main(String[] args){
		try{
			valve server = new valve(args[0],Integer.parseInt(args[1]));
			server.info();
			server.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
    }
}
