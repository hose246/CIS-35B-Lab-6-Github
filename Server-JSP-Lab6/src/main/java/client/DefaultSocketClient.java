
package client;

import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class DefaultSocketClient extends Thread {

	////////// PROPERTIES //////////

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private BufferedReader stdIn;
	private Socket sock;
	private String strHost;
	private int iPort;
	private CarModelOptionsIO clientFTP;
	private SelectCarOptions clientProtocol;
	
	private ArrayList<String> displayModels; //save the models from server

	////////// CONSTRUCTORS //////////

	public DefaultSocketClient(String strHost, int iPort) {
		this.strHost = strHost;
		this.iPort = iPort;
	}

	////////// INSTANCE METHODS //////////

	public void establishConnection() {
		try {
			//if (DEBUG)
				System.out.println("Connecting to host ... ");
			this.sock = new Socket(strHost, iPort);

			//if (DEBUG)
				System.out.println("Connected to host, creating object streams ... ");
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
			stdIn = new BufferedReader(new InputStreamReader (System.in));

			clientFTP = new CarModelOptionsIO();
			clientProtocol = new SelectCarOptions();
		}
		catch (IOException e) {
			System.err.println("Error obtaining I/O for connection to host ... ");
			System.exit(1);
		}
	}

	public void handleConnection() {
		Object fromServer, toServer = null;

		try {
			//if (DEBUG)
				System.out.println("Communicating with host ... ");

			while ((fromServer = in.readObject()) != null) {
				//if (DEBUG)
					System.out.println("Received server response ... ");
				System.out.println(fromServer.toString());
				
				if(fromServer.toString().contains("Select")) { 
					displayModels = getModels(fromServer.toString());
				}
				
				if (clientProtocol.isAutomobile(fromServer))
					clientProtocol.configureAuto(fromServer);

				System.out.println("Response to server: ");
				toServer = stdIn.readLine();
				if (toServer.toString().contains(".prop")) {
					toServer = clientFTP.loadPropsFile(toServer.toString());
				}
				if (toServer.toString().contains(".txt")) {
					toServer = clientFTP.loadTextFile(toServer.toString());
				}
				//if (DEBUG)
					System.out.println("Sending " + toServer + " to server ... ");
				sendOutput(toServer);
				System.out.println("");

				if (toServer.equals("0")) {
					break;
				}
			}

			//if (DEBUG)
				System.out.println("Closing client input stream ... ");
			in.close();

		}
		catch (ClassNotFoundException e) {
			System.err.println("Error in downloaded object, object may be corrupted ... ");
			System.exit(1);
		}
		catch (IOException e) {
			System.err.println("Error in I/O stream ... ");
			e.printStackTrace();
			System.exit(1);
		}

	}

	public void sendOutput(Object obj) {
		try {
			out.writeObject(obj);
		}
		catch (IOException e) {
			System.err.println("Error in I/O stream while sending object to host ... ");
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void run() {
		establishConnection();
		handleConnection();
		try {
			//if (DEBUG)
				System.out.println("Closing client output stream ... ");
			out.close();

			//if (DEBUG)
				System.out.println("Closing client console input stream ... ");
			stdIn.close();

			//if (DEBUG)
				System.out.println("Closing client socket ... ");
			sock.close();
		}
		catch (IOException e) {
			System.err.println("Error ending client connection ... ");
			System.exit(1);
		}
	}
	
	public ArrayList<String> getModels(String str) {
		ArrayList<String> result = new ArrayList();
		int index = str.indexOf(":");
		String subString = str.substring(index + 1);
		ArrayList<String> strArray = new ArrayList(Arrays.asList(subString.split("\n")));
		

		for(int i = 0; i < strArray.size(); i++) {
			if(strArray.get(i).equals("")) {
				strArray.remove(i);
			} 
			if(str.contains(".")){
				int index2 = strArray.get(i).indexOf(".");
				strArray.set(i, strArray.get(i).substring(index2 + 1));
			}
		}
		for(int i = 0; i < strArray.size(); i++) {
			System.out.println(strArray.get(i));
		}
		return result;
	}
	
	public ArrayList<String> getDisplayModels() {
		return displayModels;
	}
}
