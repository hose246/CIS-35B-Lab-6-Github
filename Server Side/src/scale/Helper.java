package scale;
import model.Automobile;

public class Helper {
	
	//I used synchronization on this method because I feel like it can synchronize all the methods used from here
	public synchronized void syncChange(Automobile auto, String opsetName, String initialOption, String finalOption) {
		auto.setOptNameWithOptionName(opsetName, initialOption, finalOption);
	}
	
	public void unsyncChange(Automobile auto, String opsetName, String initialOption, String finalOption) {
		auto.setOptNameWithOptionName(opsetName, initialOption, finalOption);
	}
}
