package lab2task2;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Form1 extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			PrintWriter out = response.getWriter();
			
			out.println("<html><body>");
			
			HttpSession session = request.getSession(true);
			
			out.println("<form action=\"Form2\" method=\"post\">");
			
			if(session.isNew()){
				
				out.println("Name : <input type=\"text\" name =\"uname\" > <br><br>");
				
			}else{
				if(session.getAttribute("uname") != null)
					out.println("Name :<input type=\"text\" name =\"uname\" value=\""+session.getAttribute("uname").toString()+"\"> <br><br>");
				else
					out.println("Name : <input type=\"text\" name =\"uname\" > <br><br>");
			}
			
				out.println("<input type=\"Submit\" value=\"Next\">");
				
				
				out.println("</form >");
				
				out.println("<br> Enter name normally. Like Eg. <br> Name : Chintan");
			
				out.println("</body></html>");
		
		}catch(Exception e){
			response.sendError(500,"Oppppps, It looks likeS the server messed up. :( ");
			return;
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
	}

}
