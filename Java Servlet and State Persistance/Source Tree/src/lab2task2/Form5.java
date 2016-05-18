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

public class Form5 extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		PrintWriter out = response.getWriter();
		
		out.println("<html><body>");
		String name = null;
		String languages = null;
		String days = null;
		String favLang = null;
		
		try{
		HttpSession session = request.getSession(false);

		if(session != null){
			
			if(session.getAttribute("uname") != null){
				out.print("Name =  "+session.getAttribute("uname").toString()+ "<br><br>");
				name = session.getAttribute("uname").toString();

			}
			if(session.getAttribute("ulangs") != null){
				out.print("Languages =  "+session.getAttribute("ulangs").toString()+ "<br><br>");
				languages = session.getAttribute("ulangs").toString();
			}
			if(session.getAttribute("udays") != null){
				out.print("Days =  "+session.getAttribute("udays").toString()+ "<br><br>");
				days = session.getAttribute("udays").toString();
			}
			
			if(request.getParameter("ufavlang") != null){
				if(session.getAttribute("ufavlang") != null){
					if(request.getParameter("ufavlang").equalsIgnoreCase(session.getAttribute("ufavlang").toString())){
						out.print("Favourite Language =  "+session.getAttribute("ufavlang").toString()+ "<br><br>");	
						favLang = session.getAttribute("ufavlang").toString();
					}else{
						session.setAttribute("ufavlang", request.getParameter("ufavlang"));
						favLang = request.getParameter("ufavlang");
						out.print("Favourite Language =  "+session.getAttribute("ufavlang").toString()+ "<br><br>");
					}
				}else{
					session.setAttribute("ufavlang", request.getParameter("ufavlang"));
					favLang = request.getParameter("ufavlang");
					out.print("Favourite Language =  "+session.getAttribute("ufavlang").toString()+ "<br><br>");
				}
			}
			
			
			out.println("<form action=\"FormFinal\" method=\"post\">");
			out.println("<input type=\"Submit\" value=\"Cancel\">");
			out.println("</form >");
			
			out.println("<br><br>");
			
			out.println("<form action=\"SubmitClicked\" method=\"post\">");
			out.println("<input type=\"Submit\" value=\"Submit\">");
			out.println("</form >");
			
			out.println("<br><br>");
	
			out.println("<form action=\"Form4\" method=\"post\">");
			out.println("<input type=\"Submit\" value=\"Previous\">");
			out.println("</form >"); 
			
			
		}else{
			response.sendError(403,"I am sorry but you cannot be here....");
			return;
		}
		}catch (Exception e) {
			response.sendError(500,"Oppppps, It looks likeS the server messed up. :( ");
			return;
		}	
			
		out.println("</body></html>");
		
		

	}
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	throw new UnsupportedOperationException("HTTP GET request is not allowed!");
	}
	

}
