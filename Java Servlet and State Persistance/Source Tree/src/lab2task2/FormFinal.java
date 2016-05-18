package lab2task2;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class FormFinal extends HttpServlet {
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		throw new UnsupportedOperationException("HTTP GET request is not allowed!");
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		
		HttpSession session = request.getSession(false);
		
		if(session != null){
			session.invalidate();
			 request.getRequestDispatcher("Form1").forward(request, response);
			 return;
		}else{
			 request.getRequestDispatcher("Form1").forward(request, response);
			 return;
		}
	}

}
