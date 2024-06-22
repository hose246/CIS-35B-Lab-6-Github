package util;
import java.io.*;
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
}
