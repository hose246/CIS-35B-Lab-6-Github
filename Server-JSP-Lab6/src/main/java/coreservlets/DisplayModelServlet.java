package coreservlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.CarModelOptionsIO;
import client.DefaultSocketClient;
import client.SelectCarOptions;
import model.Automobile;

/**
 * Servlet implementation class DisplayModelServlet
 */
@WebServlet("/DisplayModelServlet")
public class DisplayModelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private BufferedReader stdIn;
	private Socket client;
	private CarModelOptionsIO clientFTP;
	private SelectCarOptions clientProtocol;
	ArrayList<ArrayList<String>> models;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayModelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Object fromServer, toServer = null;
	    
	    Socket client = new Socket("127.0.0.1", 6666);
	    output = new ObjectOutputStream(client.getOutputStream());
		input = new ObjectInputStream(client.getInputStream());
		Object obj = 2;

		
		try {
			fromServer = input.readObject();
			output.writeObject(obj);
			fromServer = input.readObject();
			
			models = getModels(fromServer.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Class not found oopsie");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IOException oopsie");
			e.printStackTrace();
		}
		
		
		//webpage content
		response.setContentType("text/html");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
	    String title = "Display Models";
	    
	    out.println(ServletUtilities1.headWithTitle(title) +
	    		 "<BODY>\n" +
	    		 "<H2>" + title + "</H2>\n" + 
	    		 "</BODY></HTML>");
	    
	    out.println("<form action=\"SelectOptionServlet\">" +
	    		"<BODY>\n");
	    
	    out.println("<select name=\"models\">"); //start drop down box to choose models
	    for(int i = 0; i < models.size(); i++) {
	    	ArrayList<String> temp = models.get(i);
		    
	    	String num = temp.get(0);
	    	String model = temp.get(1);
	    	String modelModified = "\"" + model + "\"";
	    	out.println("<option value=" + modelModified + ">" + num + ". " + model + "</option>");
	    }
	    out.println("</select>"); //end drop down box
	    out.println("<br>");
	    
	    out.println("<input type=\"submit\" value=\"Done\">" +
	    		"</BODY>" + 
	    		"</form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public ArrayList<ArrayList<String>> getModels(String str) {
		System.out.println("Recieved input from server: " + str);
		
		int index = str.indexOf(":");
		
		String strnew = str.substring(index + 1);
		// TODO Auto-generated method stub
		ArrayList<ArrayList<String>> result = new ArrayList();
		ArrayList<String> strArray = new ArrayList(Arrays.asList(strnew.split("\n")));
		System.out.println(strArray.size());
		
		for(int i = 0; i < strArray.size(); i++) {
			System.out.println("Removing empty strings in array...");
			String temp = strArray.get(i);
			if(temp.equals("")) {
				strArray.remove(i);
			}
		}
		
		
		
		for(int i = 0; i < strArray.size(); i++) {
			System.out.println("Adding num and model to result...");
			result.add(new ArrayList<String>());
			
			String temp = strArray.get(i);
			
			int index1 = temp.indexOf(".");
			
			String num = temp.substring(0, index1);
			String models = temp.substring(index1 + 1);
			
			ArrayList<String> tempArray = new ArrayList();
			tempArray.add(num);
			tempArray.add(models.trim());
			
			
			result.set(i, tempArray);
			
		}
		
		for(int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i).get(0));
			System.out.print(". ");
			System.out.println(result.get(i).get(1));
		}
		
		return result;
	}
}
