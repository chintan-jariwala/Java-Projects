package lab2task2;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@SuppressWarnings("serial")
public class Home extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
				
		PrintWriter out = response.getWriter();
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		BufferedReader br;
		out.print("<html><body>");
		ArrayList<String> preferancebylangs = new ArrayList<String>();
		ArrayList<String> preferancebydays = new ArrayList<String>();
		HttpSession session =  request.getSession(true);
	
		String firstName = null;
		String lastName =  null;
		boolean problem = false;
		boolean userExists = false;
		String userPerfedLanguages[] = null;
		String userPerfedDays[] = null;

		if(session.isNew()){
			//redirect
			Cookie ck = new Cookie("uservisited","true");
			response.addCookie(ck);
			request.getRequestDispatcher("Register.html").forward(request, response);
			
		}else{
			//check if parameters are there
			
			if(request.getParameter("uFName") != null && request.getParameter("uLName") != null){
				
				firstName = request.getParameter("uFName");
				lastName = request.getParameter("uLName");
				
				Cookie fname = new Cookie("uFName", firstName);
				response.addCookie(fname);
				Cookie lname = new Cookie("uLName", lastName);
				response.addCookie(lname);
								
			}else{
				Cookie ckCookie[] = request.getCookies();
				
				for(Cookie x: ckCookie){
					if(x.getName().equals("uFName"))
						firstName = x.getValue();
				
					if(x.getName().equals("uLName"))
						lastName = x.getValue();
				}
				
			}
		}
		
		File data = new File("data.xml");
		if(data.exists()){
			br = new BufferedReader(new FileReader(data));     
			if (br.readLine() == null) {
			    problem = true;
			
			}else
				problem = false;
		}else{
			problem = false;
		}
			
			if(problem){
				out.print("Hello, "+firstName + " " + lastName + "<br>");
				out.println("Welcome..... ");
			}else{
				out.print("Hello, "+firstName + " " + lastName + "<br>");
				out.println("Welcome back ... ");
				
				try{
					Document dom;
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = dbf.newDocumentBuilder();
					dom = docBuilder.parse(new File("data.xml"));
					dom.getDocumentElement().normalize();
					
					
					NodeList list = dom.getElementsByTagName("coder");
					
					for(int i = 0; i < list.getLength(); i++){
						
						Node nameNode = list.item(i);
						Element e = (Element)nameNode;
						String name = e.getElementsByTagName("name").item(0).getTextContent();
						
						
						if(name.equalsIgnoreCase(firstName)){
							
							userExists = true;
							NodeList laguages = dom.getElementsByTagName("languages");
							NodeList langlist = laguages.item(i).getChildNodes();
							userPerfedLanguages = new String[langlist.getLength()];
							
							for(int k=0;k<langlist.getLength();k++){
								userPerfedLanguages[k] = langlist.item(k).getTextContent();
							}
							
							NodeList days = dom.getElementsByTagName("days");
							NodeList dayList = days.item(i).getChildNodes();
							userPerfedDays = new String[dayList.getLength()];
							for(int k=0;k<dayList.getLength();k++){
								userPerfedDays[k] = dayList.item(k).getTextContent();
							}
							
						}
					}
					if(userExists){
						System.out.println(Arrays.toString(userPerfedDays));
						System.out.println(Arrays.toString(userPerfedLanguages));
						
						for(int i = 0; i < list.getLength(); i++){
							
							NodeList laguages = dom.getElementsByTagName("languages");
							NodeList langlist = laguages.item(i).getChildNodes();
							
							for(int k=0;k<langlist.getLength();k++){
								if(Arrays.asList(userPerfedLanguages).contains(langlist.item(k).getTextContent())){
									
									Node nameNode = list.item(i);
									Element e = (Element)nameNode;
									String name = e.getElementsByTagName("name").item(0).getTextContent();
									preferancebylangs.add(name);
									break;
								}
							}
							
							
						}
						
						for(int i = 0; i < list.getLength(); i++){
							
							NodeList days = dom.getElementsByTagName("days");
							NodeList dayList = days.item(i).getChildNodes();
							
							for(int k=0;k<dayList.getLength();k++){
								if(Arrays.asList(userPerfedDays).contains(dayList.item(k).getTextContent())){
									
									Node nameNode = list.item(i);
									Element e = (Element)nameNode;
									String name = e.getElementsByTagName("name").item(0).getTextContent();
									preferancebydays.add(name);
									break;
								}
							}
							
							
						}
						
						Collections.sort(preferancebydays,String.CASE_INSENSITIVE_ORDER);
						Collections.sort(preferancebylangs,String.CASE_INSENSITIVE_ORDER);
						
						out.println("Your preferd days matched with <br>");
						if(preferancebylangs.size()>3){
							out.println("Your preferd Languages matched with <br>");
							for(int m=0;m<3;m++){
								out.println("<br>");
								out.println(preferancebylangs.get(m));
							}
							
						}else{
							out.println("Your preferd Languages matched with <br>");

							for(String s : preferancebylangs){
								out.println(s + "<br>");
							}
						}

						out.println("<br><br>");

						if(preferancebydays.size()>3){
							out.println("Your preferd days matched with <br>");
							for(int m=0;m<3;m++){
								out.println("<br>");

								out.println(preferancebydays.get(m));
							}
							
						}else{
							out.println("Your preferd days matched with <br>");

							for(String s : preferancebydays){
								out.println(s + "<br>");
							}
						}
						
						
						
					}else{
						//user is not there... cannot determine his preferances.
					}
					
					
					
				}catch(Exception e){
					response.sendError(500,"Oppppps, It looks likeS the server messed up. :( ");
					return;
					
				}
			}
			
	out.print("</body></html>");
			
			
		}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		throw new UnsupportedOperationException("HTTP GET request is not allowed!");
	}
	
	

}
