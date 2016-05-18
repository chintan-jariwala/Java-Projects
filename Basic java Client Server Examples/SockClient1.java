
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;


public class SockClient1 {
	
	public static void main(String args[]) throws IOException{
		InputStream dis = null;
		OutputStream dos = null;
		Socket socket = null;
		PrintWriter pw = null;
		int port = 8887;
		
	
		
		try{
				
			socket = new Socket("localhost",port);
			
			dis = socket.getInputStream();
			dos = socket.getOutputStream();
			pw = new PrintWriter(dos,true);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));
	
			
			
			pw.println(args[0]);
			String s = br.readLine();
			System.out.println("Running Total : "+s);

			System.out.println("Done");

			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			socket.close();
			dis.close();
			dos.close();
		
		}
        
		
	
	}

}
