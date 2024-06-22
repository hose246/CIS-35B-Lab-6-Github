package exception;

public class AutoException extends Exception{
	private int errorno;
	private String errormsg;
	
	public AutoException() {
		super();
		printMyProblem();
	}
	
	public AutoException(String errormsg) {
		super();
		this.errormsg = errormsg;
		printMyProblem();
	}
	
	public AutoException(int errorno) {
		super();
		this.errorno = errorno;
		printMyProblem();
	}
	
	public AutoException(int errorno, String errormsg) {
		super(errormsg);
		this.errorno = errorno;
		this.errormsg = errormsg;
		printMyProblem();
	}
	
	public int getErrorno() {
		return errorno;
	}
	
	public String getErrormsg() {
		return errormsg;
	}
	
	public void setErrorno(int newErrorno) {
		this.errorno = newErrorno;
	}
	
	public void setErrormsg(String newErrormsg) {
		this.errormsg = errormsg;
	}
	
	public void fix(int errno) {
		FixHelper f1 = new FixHelper();
		
		switch(errno) {
		case 1: f1.fix1(errno); break;
		case 2: f1.fix2(errno); break;
		case 3: f1.fix3(errno); break;
		case 4: f1.fix4(errno); break;
		case 5: f1.fix5(errno); break;
		case 6: f1.fix6(errno); break;
		default: break;
		}
	}
	
	public void printMyProblem() {
		System.out.printf("%nAutoException(%d) -- Error message: %s", errorno, errormsg);
	}
}
