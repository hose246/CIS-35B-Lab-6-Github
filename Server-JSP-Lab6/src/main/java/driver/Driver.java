package driver;
import java.util.ArrayList;

import adapter.*;
import client.DefaultSocketClient;
import model.*;
import util.*;

public class Driver {

	public static void main(String[] args) {
		
		DefaultSocketClient client = new DefaultSocketClient("127.0.0.1", 6666);
		
		client.run();
		/*
		CreateAuto a1 = new BuildAuto();
		a1.buildAuto("a.txt");
		a1.printAuto("Ford ZTW");
		
		UpdateAuto a2 = new BuildAuto();
		a2.updateOptionSetName("Ford ZTW", "Color", "Colors");
		a2.updateOptionPrice("Ford ZTW", "Color", "Fort Knox Gold Clearcoat Metallic", 9999);
		
		a1.printAuto("Ford ZTW");
		*/
		
		/*
		FileIO file = new FileIO("a.txt");
		Automobile car = file.buildAutoObject("a.txt");
		
		car.setOptionChoice("Color", "Liquid Grey Clearcoat Metallic");
		car.setOptionChoice("Transmission", "automatic");
		car.setOptionChoice("Brakes/Traction Control", "ABS");
		car.setOptionChoice("Side Impact Air Bags", "selected");
		car.setOptionChoice("Power Moonroof", "selected");
		
		
		System.out.println("Color choice " + car.getOptionChoice("Color"));
		System.out.println("Color price: " + car.getOptionChoicePrice("Color"));
		System.out.println("Transmission choice " + car.getOptionChoice("Transmission"));
		System.out.println("Transmission price: " + car.getOptionChoicePrice("Transmission"));
		System.out.println("Brakes/Traction Control choice " + car.getOptionChoice("Brakes/Traction Control"));
		System.out.println("Brakes/Traction Control price: " + car.getOptionChoicePrice("Brakes/Traction Control"));
		System.out.println("Side Impact Air Bags choice " + car.getOptionChoice("Side Impact Air Bags"));
		System.out.println("Side Impact Air Bags price: " + car.getOptionChoicePrice("Side Impact Air Bags"));
		System.out.println("Power Moonroof choice " + car.getOptionChoice("Power Moonroof"));
		System.out.println("Power Moonroof price: " + car.getOptionChoicePrice("Power Moonroof"));
		System.out.println("Total Price " + car.getTotalPrice());
		*/
		

	}

}
