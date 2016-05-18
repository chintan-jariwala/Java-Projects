
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.io.PrintWriter;


public class ClientProblem2 {
	
	public static void main(String args[]) throws IOException{
		InputStream dis = null;
		OutputStream dos = null;
		Socket socket = null;
		Scanner scanner = new Scanner(System.in);
		PrintWriter pw = null;
		int port = 8888;
		
	
		
		try{
				
			socket = new Socket("localhost",8887);
			
			dis = socket.getInputStream();
			dos = socket.getOutputStream();
			pw = new PrintWriter(dos,true);
		
	
			int sm = 0;
			
			System.out.println(sm);
			
			
			while(true){
			
			String x = scanner.nextLine();
			
			pw.println(x);
						

			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			socket.close();
			dis.close();
			dos.close();
			scanner.close();
		
		}
        
		
	
	}

}
