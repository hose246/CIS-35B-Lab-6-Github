

package server;

import java.io.*;
import java.util.Properties;

import adapter.*;
import model.Automobile;

public class BuildCarModelOptions extends proxyAutomobile implements AutoServable{

	////////// PROPERTIES //////////

	private static final int WAITING = 0;
	private static final int REQUEST_BUILD_AUTO = 1;
	private static final int REQUEST_CONFIGURE_AUTO = 2;

	private int state = WAITING;

	////////// CONSTRUCTORS //////////

	public BuildCarModelOptions() {

	}

	////////// INSTANCE METHODS //////////

	public Object processRequest(Object obj) {
		Object toClient = null;
		
		if (state == REQUEST_BUILD_AUTO) {
		//add code to buildauto
			super.buildAutoWithProperty((Properties)obj);
			
			toClient = "Automobile object successfully added to database\n"
					+ "Press any key to return to main menu";
		}
		else if (state == REQUEST_CONFIGURE_AUTO) {
		//add code for configureauto
			try {
				toClient = super.selectAuto(obj);
				if(toClient == null) {
					System.out.println("Wrong input/Index out of bound...");
					System.out.println("Exiting System...");
					System.exit(1);
				}
			} catch(Exception ex) {
				System.out.println("Problems with input...");
				System.out.println("Exiting system...");
				System.exit(1);
			}
		}
		else {
			
		}

		this.state = WAITING;

		return toClient;
	}

	public String setRequest(int i) {
		String output = null;

		if (i == 1) {
			this.state = REQUEST_BUILD_AUTO;
			output = "Upload a file to create an Automobile";
		}
		else if (i == 2) {
			this.state = REQUEST_CONFIGURE_AUTO;
			output = "Select an Automobile from the following list to configure:\n" + super.displayModels();
		}
		else {
			output = "Invalid request";
		}

		return output;
	}



}
