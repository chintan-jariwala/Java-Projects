package lab2task2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

@SuppressWarnings("serial")
public class SubmitClicked extends HttpServlet {

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if(session !=null)
		{
			 
			String user = session.getAttribute("uname").toString();
			String userLangs[] = session.getAttribute("ulangs").toString().split(" ");
			String userDays[] = session.getAttribute("udays").toString().split(" ");
			String userFavlang = session.getAttribute("ufavlang").toString();
			
			System.out.println(user);
			System.out.println(Arrays.toString(userLangs));
			System.out.println(Arrays.toString(userDays));
			System.out.println(userFavlang);
			
			File file = new File("data.xml");
			boolean problem = false;
			
			if(file.exists()){
					problem = addToXML(user,userLangs,userDays,userFavlang);
			
			}else{
				
					problem = saveToXML(user,userLangs,userDays,userFavlang);
				
			}
			
			if(!problem){
				session.invalidate();
		        response.sendRedirect(request.getContextPath() + "/lab2task2/Form1");
				
			}	
		}
			
	}

	private boolean saveToXML(String user, String[] userLangs, String[] userDays, String userFavlang) {
		Document dom;
		boolean problem = false;

		try{
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			dom = db.newDocument();
			File datafile = new File("data.xml");
			FileChannel fileChannel = new RandomAccessFile(datafile, "rw").getChannel();
			FileLock lock = fileChannel.lock(0L, Long.MAX_VALUE, false);
			
			Element rootele = dom.createElement("Data");
			dom.appendChild(rootele);
			
			Element coder = dom.createElement("coder");
			rootele.appendChild(coder);
			
			Element username = dom.createElement("name");
			username.appendChild(dom.createTextNode(user));
			coder.appendChild(username);
			
			Element langs = dom.createElement("languages");
			coder.appendChild(langs);
			
			for(String s:userLangs){
				
				Element lang = dom.createElement("lang");
				lang.appendChild(dom.createTextNode(s));
				langs.appendChild(lang);
			}
			
			Element days = dom.createElement("days");
			coder.appendChild(days);
			
			for(String s : userDays){
				
				Element day = dom.createElement("day");
				day.appendChild(dom.createTextNode(s));
				days.appendChild(day);
			}
			
			Element favouriteLang = dom.createElement("FavouriteLang");
			favouriteLang.appendChild(dom.createTextNode(userFavlang));
			coder.appendChild(favouriteLang);
			
			
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(dom);
	        
	        StreamResult streamResult = new StreamResult(Channels.newOutputStream(fileChannel));
	        transformer.transform(source, streamResult);
			
			
		}catch (Exception e) {
			
		}
		
		return problem;
	}

	private boolean addToXML(String user, String[] userLangs, String[] userDays, String userFavlang) {
		// TODO Auto-generated method stub
boolean problem = false;
		
		Document dom;
		
		try{
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		File datafile = new File("data.xml");
		dom = db.parse(datafile);
		FileChannel fileChannel = new RandomAccessFile(datafile, "rw").getChannel();
		FileLock lock = fileChannel.lock(0L, Long.MAX_VALUE, false);
		
				
		Element coder = dom.createElement("coder");
		
		
		Element username = dom.createElement("name");
		username.appendChild(dom.createTextNode(user));
		coder.appendChild(username);
		
		Element langs = dom.createElement("languages");
		coder.appendChild(langs);
		
		for(String s:userLangs){
			
			Element lang = dom.createElement("lang");
			lang.appendChild(dom.createTextNode(s));
			langs.appendChild(lang);
		}
		
		Element days = dom.createElement("days");
		coder.appendChild(days);
		
		for(String s : userDays){
			
			Element day = dom.createElement("day");
			day.appendChild(dom.createTextNode(s));
			days.appendChild(day);
		}
		
		Element favouriteLang = dom.createElement("FavouriteLang");
		favouriteLang.appendChild(dom.createTextNode(userFavlang));
		coder.appendChild(favouriteLang);
		
		dom.getDocumentElement().appendChild(coder);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(dom);
        
        StreamResult streamResult = new StreamResult(Channels.newOutputStream(fileChannel));
        transformer.transform(source, streamResult);
		
        lock.release();
		
		}catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
            problem = true;
        }  catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            problem = true;

		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            problem = true;

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            problem = true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            problem = true;

		}
		return problem;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		throw new UnsupportedOperationException("HTTP GET request is not allowed!");
		}

}
