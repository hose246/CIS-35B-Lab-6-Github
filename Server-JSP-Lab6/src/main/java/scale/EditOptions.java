package scale;
import model.Automobile;
import adapter.proxyAutomobile;

public class EditOptions extends proxyAutomobile implements Runnable{
	private Thread t;
	private Automobile auto;
	private boolean DEBUG = true;
	private String modelName;
	private String [] args;
	private int operation;
	
	public EditOptions() {
		
	}
	
	public EditOptions(String modelName, int operation, String [] args ) {
		this.modelName = modelName;
		this.operation = operation;
		this.args = args;
		this.t = new Thread(this);
		this.auto = getAutoWithModelName(modelName);
	}
	
	public void run() {
		ops();
	}
	
	public void start() {
		t.start();
	}
	
	public void ops() {
		Helper h = new Helper();
		switch(operation) {
		case 0:
			h.unsyncChange(this.auto, args[0], args[1], args[2]);
			break;
		case 1:
			h.unsyncChange(this.auto, args[0], args[1], args[2]);
			break;
		case 2:
			h.syncChange(this.auto, args[0], args[1], args[2]);
			break;
		case 3:
			h.syncChange(this.auto, args[0], args[1], args[2]);
			break;
		}
	}
}
