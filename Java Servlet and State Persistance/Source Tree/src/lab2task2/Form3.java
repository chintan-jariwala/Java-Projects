package lab2task2;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Form3 extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	throw new UnsupportedOperationException("HTTP GET request is not allowed!");

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		PrintWriter out = response.getWriter();
		
		out.println("<html><body>");
		
		HttpSession session = request.getSession(false);
		
		out.println("<form action=\"Form4\" method=\"post\">");
		try{
		
		if(session != null){
			
			if(session.getAttribute("udays") != null){
				
				out.println("Days : <input type=\"text\" name =\"udays\" value=\""+session.getAttribute("udays").toString()+"\"> <br><br>");

			}else{
				
				
				
				out.println("Days : <input type=\"text\" name =\"udays\"> <br><br>");

			}
			if(request.getParameter("ulangs") != null)
				session.setAttribute("ulangs",request.getParameter("ulangs"));
			
			out.println("<input type=\"Submit\" value=\"Next\">");

			
			out.println("</form >");
				
			out.println("<form action=\"Form2\" method=\"post\">");
			out.println("<input type=\"Submit\" value=\"Previous\">");
			out.println("</form >"); 
			
			
	
		}else{
			response.sendError(403,"I am sorry but you cannot be here....");
			return;
		}
		
				
		out.println("<br> Make sure your Days have space in them.  Eg. <br> Days : Monday Tuesday Wednesday");

			
		
		out.println("</body></html>");
		
		}catch(Exception e){
			response.sendError(500,"Oppppps, It looks likeS the server messed up. :( ");
			return;
		}
	}

}


