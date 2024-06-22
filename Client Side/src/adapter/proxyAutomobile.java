package adapter;
import model.*;
import scale.EditOptions;
import exception.AutoException;
import util.FileIO;
import java.util.Collection;
import java.util.Set;
import java.util.Iterator;

public abstract class proxyAutomobile {
	private static Automobile a1;
	private static Gen<String, Automobile> autoMap = new Gen<String, Automobile>();
	
	public void buildAuto(String fileName) {
		FileIO file = new FileIO(fileName);
		a1 = file.buildAutoObject(fileName);
		autoMap.put(a1.getMake() + a1.getModel(), a1);
	}
	
	public void printAuto(String modelName) {
		a1.printAll();
	}
	
	public void updateOptionSetName(String modelName, String optionSetName, String newName) {
		Collection c = autoMap.values();
		Iterator it = c.iterator();
		while(it.hasNext()) {
			Automobile temp = (Automobile)it.next();
			if(temp.getName().equals(modelName)) {
				a1 = temp;
			}
		}
		
		if(a1.findOptionSetIndex(optionSetName) == -1)
			System.out.printf("%nCannot find the Option Set.");
		else
			a1.setSetName(a1.findOptionSetIndex(optionSetName), newName);
	}
	
	public void updateOptionName(String modelName, String optionSetName, String initialOptionName, String finalOptionName) {
		Collection c = autoMap.values();
		Iterator it = c.iterator();
		while(it.hasNext()) {
			Automobile temp = (Automobile)it.next();
			if(temp.getName().equals(modelName)) {
				a1 = temp;
			}
		}
		
		for(int i=0;i<a1.countOptionSet();i++)
			for(int j=0;j<a1.countOption(i);j++)
				if(a1.getOptName(i, j).equals(initialOptionName))
					a1.setOptName(i, j, finalOptionName);
	}
	
	public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice) {
		Collection c = autoMap.values();
		Iterator it = c.iterator();
		while(it.hasNext()) {
			Automobile temp = (Automobile)it.next();
			if(temp.getName().equals(modelName)) {
				a1 = temp;
			}
		}
		
		for(int i=0;i<a1.countOptionSet();i++)
			for(int j=0;j<a1.countOption(i);j++)
				if(a1.getOptName(i, j).equals(optionName))
					a1.setOptPrice(i, j, newPrice);
	}
	
	public void fix(int errorno) {
		AutoException e = new AutoException();
		e.fix(errorno);
	}
	
	public Gen<String, Automobile> getAutoMap() {
		return autoMap;
	}
	
	public void editThread(String modelName, int operation, String [] args) {
		new EditOptions(modelName, operation, args).run();
	}
	
	public Automobile getAutoWithModelName(String modelName) {
		Collection c = autoMap.values();
		Iterator it = c.iterator();
		while(it.hasNext()) {
			Automobile temp = (Automobile)it.next();
			if(temp.getName().equals(modelName)) {
				return temp;
			}
		}
		
		return null;
	}
	
	/*public void selectAuto(String model) {
		a1 = autoMap.getValue(model);
	}*/
}
