
package client;

import model.*;
import java.util.*;

public class SelectCarOptions {

	////////// PROPERTIES //////////
	private Scanner in = new Scanner(System.in);

	////////// CONSTRUCTORS //////////

	public SelectCarOptions() {

	}

	////////// INSTANCE METHODS //////////

	public void configureAuto(Object obj) {
		Automobile car = (Automobile) obj;
		System.out.println("Starting car configuration for client...");
		for(int i = 0; i < car.countOptionSet(); i++) {
			System.out.printf("%nSelect one of these options for %s", car.getSetName(i));
			car.printAllOption(i);
			String input = in.nextLine();
			if(car.optionExist(i, input)) {
				car.setOptionChoice(i, input);
				System.out.printf("Successfully set %s to %s%n", car.getSetName(i), input);
				continue;
			}
			System.out.printf("%nOption is not set %s with %s. Either Option not found, or input error.", car.getSetName(i), in);
			System.out.println("Exiting program...");
			System.exit(1);
		}
		System.out.println("Car configuration finished.");
		System.out.println("Press any key to return to menu: ");
	}

	public boolean isAutomobile(Object obj) {
		boolean isAutomobile = false;

		try {
			Automobile a1 = (Automobile) obj;
			isAutomobile = true;
		}
		catch (ClassCastException e) {
			isAutomobile = false;
		}

		return isAutomobile;
	}

}
