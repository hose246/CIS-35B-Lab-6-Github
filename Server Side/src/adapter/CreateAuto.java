package adapter;

import java.util.Properties;

public interface CreateAuto {
	
	public void buildAuto(String fileName);
	
	public void printAuto(String modelName);
	
	public void buildAutoWithProperty(Properties p);
}