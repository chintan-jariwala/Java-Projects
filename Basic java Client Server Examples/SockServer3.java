
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SockServer3 {

	
	
	public static void main(String args[]) throws IOException{
	
		
		ServerSocket ss = null;
		
		int port = 8887;
		
		ss = new ServerSocket(port);
		
		while(true){
			Socket client = ss.accept();
			ServerHelper sh = new ServerHelper(client);
			sh.start();
			
		}
		
		//System.out.println("Server is now closed");
		
	}
}

class ServerHelper extends Thread{
	
	Socket s = null;
	InputStream dis = null;
	OutputStream dos = null;
		
	public ServerHelper(Socket s){
		this.s = s;
		
	}
	
	public void run(){
		
		while(true){
        
		int sum = 0;
		int temp = 0;
		
		

		try{
				
			
			System.out.println("Connected to " + s.getLocalAddress().getHostName());
			
			dis = s.getInputStream();
			dos = s.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));
	
	        System.out.println("Running total is now " + sum);
	        
		        while(true)
		        {
		        	String str = br.readLine();
					
		        	if(str.equalsIgnoreCase("reset")){
		        		sum = 0;
		        		}
		        	else {
							try{
							temp = Integer.parseInt(str);
							
							if(temp != 0){
								sum = sum + temp;				        
							}
							else
								break;
							}
							catch(NumberFormatException e){
								System.out.println("Please Enter a valid Integer");
							}
						}
		        	
		        	System.out.println("Running total is now " + sum);
		        }

		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
        
		}
		
	}
	
}
