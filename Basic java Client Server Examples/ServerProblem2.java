
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProblem2 {

	
	
	public static void main(String args[]) throws IOException{
	
		
		ServerSocket ss = null;
		InputStream dis = null;
		OutputStream dos = null;
		Socket socket = null;
		
		int port = 8887;
		int sum = 0;
		int temp = 0;
		
		ss = new ServerSocket(port);
		
		while(ss.isBound() && !ss.isClosed()){
        
		System.out.println("Ready...");

		try{
				
			socket = ss.accept();
			
			dis = socket.getInputStream();
			dos = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));
	
	        System.out.println("Running total is now " + sum);
	        
		        while(true)
		        {
		        	String s = br.readLine();
					
		        	if(s.equalsIgnoreCase("reset")){
		        		sum = 0;
		        		}
		        	else {
							try{
							temp = Integer.parseInt(s);
							
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
			socket.close();
			ss.close();
			dis.close();
			dos.close();
			
		}
        
		}
		
		System.out.println("Server is now closed");
		
	}
}
