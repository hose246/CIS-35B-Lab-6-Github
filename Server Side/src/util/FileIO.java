package util;
import java.io.*;
import java.util.Properties;

import exception.AutoException;
import model.Automobile;

public class FileIO{
	String fname;
	
	FileIO() {
		
	}
	
	public FileIO(String fileName) {
		this.fname = fileName;
	}
	
	public Automobile buildAutoObject(String fileName){
		try {
			FileReader file = new FileReader(fileName);
			BufferedReader buff = new BufferedReader(file);
			Automobile car = new Automobile();
			
			car = new Automobile(buff.readLine(), Float.parseFloat(buff.readLine()), Integer.parseInt(buff.readLine()));
			
			try {
				if(car.getName().equals(""))
					throw new AutoException(1);
			} catch(AutoException f) {
				f.fix(f.getErrorno());
			}
			
			for(int i = 0; i < car.countOptionSet(); i++) { //count all the Option Set's
				car.buildOptionSet(i, buff.readLine(), Integer.parseInt(buff.readLine()));
				try {
					if(car.getSetName(i).equals(""))
						throw new AutoException(3);
				} catch (AutoException f) {
					f.fix(f.getErrorno());
				}
				
				for(int j = 0; j < car.countOption(i); j++) { //count the amount of Options in Option Set
					car.buildOption(i, j, buff.readLine(), Float.parseFloat(buff.readLine()));
					try {
						if(car.getOptName(i, j).equals(""))
							throw new AutoException(4);
						if(Float.toString(car.getOptPrice(i, j)).equals(""))
							throw new AutoException(5);
					} catch (AutoException f) {
						
						f.fix(f.getErrorno());
					}
				}
			}
			buff.close();
			file.close();
			
			return car;
			
		} catch (IOException e) {
			System.out.printf("Error: %s", e.toString());
		}
		
		return new Automobile("No car", 0, 0);
	}
	
	public String serializeAuto(Automobile auto) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("serial.txt"));
			out.writeObject(auto);
			out.close();
		} catch(IOException e) {
			System.out.printf("%nError -- %s at serializeAuto()", e.toString());
		}
		
		return "serial.txt";
	}
	
	public Automobile deserializeAuto(String fileName) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
			Automobile car = (Automobile) in.readObject();
			in.close();
			return car;
		} catch(Exception e) {
			System.out.printf("%nError -- %s at deserializeAuto()", e.toString());
		}
		
		return new Automobile("No car", 0, 0);
	}
	
	public Automobile parsePropertyFile(Properties p, String fileType) {
		Automobile car = new Automobile();
			String carName = p.getProperty("CarMake") + " " + p.getProperty("CarModel");
			car = new Automobile(carName, 825, 5);
			//set the car's name

			if(!car.getName().equals(null)) {
				car.setModel(p.getProperty("CarModel"));
				car.setMake(p.getProperty("CarMake"));
				
				//set the car's color
				car.buildOptionSet(0, p.getProperty("Option1"), 10);
				car.buildOption(0, 0, p.getProperty("OptionName1a"), Float.parseFloat(p.getProperty("OptionPrice1a")));
				car.buildOption(0, 1, p.getProperty("OptionName1b"), Float.parseFloat(p.getProperty("OptionPrice1a")));
				car.buildOption(0, 2, p.getProperty("OptionName1c"), Float.parseFloat(p.getProperty("OptionPrice1a")));
				car.buildOption(0, 3, p.getProperty("OptionName1d"), Float.parseFloat(p.getProperty("OptionPrice1a")));
				car.buildOption(0, 4, p.getProperty("OptionName1e"), Float.parseFloat(p.getProperty("OptionPrice1a")));
				car.buildOption(0, 5, p.getProperty("OptionName1f"), Float.parseFloat(p.getProperty("OptionPrice1a")));
				car.buildOption(0, 6, p.getProperty("OptionName1g"), Float.parseFloat(p.getProperty("OptionPrice1a")));
				car.buildOption(0, 7, p.getProperty("OptionName1h"), Float.parseFloat(p.getProperty("OptionPrice1a")));
				car.buildOption(0, 8, p.getProperty("OptionName1i"), Float.parseFloat(p.getProperty("OptionPrice1a")));
				car.buildOption(0, 9, p.getProperty("OptionName1j"), Float.parseFloat(p.getProperty("OptionPrice1a")));
				
				//set the car's trasmission
				car.buildOptionSet(1, p.getProperty("Option2"), 2);
				car.buildOption(1, 0, p.getProperty("OptionName2a"), Float.parseFloat(p.getProperty("OptionPrice2a")));
				car.buildOption(1, 1, p.getProperty("OptionName2b"), Float.parseFloat(p.getProperty("OptionPrice2b")));
				
				//set the car's brakes/traction control
				car.buildOptionSet(2, p.getProperty("Option3"), 3);
				car.buildOption(2, 0, p.getProperty("OptionName3a"), Float.parseFloat(p.getProperty("OptionPrice3a")));
				car.buildOption(2, 1, p.getProperty("OptionName3b"), Float.parseFloat(p.getProperty("OptionPrice3b")));
				car.buildOption(2, 2, p.getProperty("OptionName3c"), Float.parseFloat(p.getProperty("OptionPrice3c")));
				
				//set the car's side impact air bags
				car.buildOptionSet(3, p.getProperty("Option4"), 2);
				car.buildOption(3, 0, p.getProperty("OptionName4a"), Float.parseFloat(p.getProperty("OptionPrice4a")));
				car.buildOption(3, 1, p.getProperty("OptionName4b"), Float.parseFloat(p.getProperty("OptionPrice4b")));
				
				//set the car's power moonroof
				car.buildOptionSet(4, p.getProperty("Option5"), 2);
				car.buildOption(4, 0, p.getProperty("OptionName5a"), Float.parseFloat(p.getProperty("OptionPrice5a")));
				car.buildOption(4, 1, p.getProperty("OptionName5b"), Float.parseFloat(p.getProperty("OptionPrice5b")));
				
				System.out.println("Finished building Automobile Object in FileIO");
			}
		
			System.out.println("Returning Automobile Object from FileIO...");
			return car;
		
	}
}
