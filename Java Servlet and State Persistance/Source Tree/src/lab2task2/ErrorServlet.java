package lab2task2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	        // Analyze the servlet exception
	        Exception exception = (Exception)req.getAttribute("javax.servlet.error.exception");
	        Integer statusCode = (Integer)req.getAttribute("javax.servlet.error.status_code");
	        String servletName = (String)req.getAttribute("javax.servlet.error.servlet_name");
	        String requestUri = (String)req.getAttribute("javax.servlet.error.request_uri");

	        // Set response content type
	        resp.setContentType("text/html");

	        // print the output
	        PrintWriter out = resp.getWriter();
	        out.write("<html><head><title>Servlet 3 Exception Handling example</title></head><body>");
	        if (statusCode != 500){
	            out.write("<h3>Servlet Exception Handling</h3>");
	            out.write("<strong>Status Code</strong>:  " + statusCode + "<br>");
	            out.write("<strong>I am sorry, Requested URI:  " + requestUri +"is not available </strong>");
	        } else {
	            out.write("<h3>Servlet Exception Handling</h3>");
	            out.write("<ul><li>Servlet Name:  " + servletName + "</li>");
	            out.write("<li>Exception Name:  " + exception.getClass().getName() + "</li>");
	            out.write("<li>Requested URI:  " + requestUri + "</li>");
	            out.write("<li>Exception Message:  " + exception.getMessage() + "</li>");
	            out.write("</ul>");
	        }
	        out.println("<br><br>");
	        out.write("<a href=\""+  req.getContextPath() + "/"+"\"> Home </a>");
	      
	        out.write("</body></html>");
	    }

	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        doGet(req, resp);
	    }
}
