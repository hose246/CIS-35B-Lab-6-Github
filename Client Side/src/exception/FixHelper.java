package exception;

public class FixHelper {
	
	public void fix1(int errorno) {
		System.out.printf("%nMissing Model name.");
	}
	
	public void fix2(int errorno) {
		System.out.printf("%nMissing base price for car model.");
	}
	
	public void fix3(int errorno) {
		System.out.printf("%nMissing Option Set name.");
	}
	
	public void fix4(int errorno) {
		System.out.printf("%nMissing Option name.");
	}
	
	public void fix5(int errorno) {
		System.out.printf("Missing Option price.");
	}
	
	public void fix6(int errorno) {
		System.out.printf("Missing file.");
	}
	
}

//Error 1 - missing model name
//Error 2 - missing base price
//Error 3 - missing OptionSet name
//Error 4 - missing Option name
//Error 5 - missing Option price
//Error 6 - missing file