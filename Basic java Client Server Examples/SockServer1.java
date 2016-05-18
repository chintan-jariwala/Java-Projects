
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SockServer1 {

	
	
	public static void main(String args[]) throws IOException{
	
		
		ServerSocket ss = null;
		InputStream dis = null;
		OutputStream dos = null;
		Socket socket = null;
		PrintWriter pw = null;

				
		int port = 8887;
		int sum = 0,temp = 0;
		
		ss = new ServerSocket(port);
		
		
        
		System.out.println("Ready...");

		try{
				
			
	
	       while(true){
			   
					socket = ss.accept();
					dis = socket.getInputStream();
					dos = socket.getOutputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(dis));
					pw = new PrintWriter(dos);
					String s=null;
					System.out.println("Connected to "+	socket.getLocalAddress().getHostName());
		        	s = br.readLine();
						try{
							
							temp = Integer.parseInt(s);
							sum = sum + temp;
							pw.print(sum);
							System.out.println("Total "+sum);

						}
						catch(NumberFormatException e){
							System.out.println("Please Enter a valid Integer");
						}

					
					
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
        
		
		
		System.out.println("Server is now closed");
		
	}
}
