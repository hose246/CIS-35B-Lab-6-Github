package model;
import java.io.Serializable;
import java.util.ArrayList;

public class OptionSet implements Serializable{
	private String setName;
	private ArrayList<Option> opt;
	private Option optChoice;
	
	OptionSet() {
		setName = " ";
		opt = new ArrayList<>();
	}
	
	public OptionSet(String setName, int optSize) {
		this.setName = setName;
		opt = new ArrayList<>(optSize);
		
		for(int i=0; i<optSize; i++)
			opt.add(new Option());
			//opt[i] = new Option();
	}
	
	protected String getSetName() {
		return setName;
	}
	
	protected Option getOption(int index) {
		return opt.get(index);
	}
	
	protected ArrayList<Option> getAllOption() {
		return opt;
	}
	
	protected String getOptName(int optIndex) { //get name of Option at index
		return opt.get(optIndex).getOptName();
	}
	
	protected float getOptPrice(int optIndex) { //get price of Option at index
		return opt.get(optIndex).getOptPrice();
	}
	
	protected Option getOptionChoice() {
		return optChoice;
	}
	
	protected void setSetName(String setName) {
		this.setName = setName;
	}
	
	protected void setOptName(int optIndex, String optName) { //set name of Option at index
		opt.get(optIndex).setOptName(optName);
	}
	
	protected void setOptNameWithOptionName(String initialOptionName, String finalOptionName) {
		for(int i = 0; i<opt.size();i++) {
			if(opt.get(i).getOptName().equals(initialOptionName)) {
				opt.get(i).setOptName(finalOptionName);
				System.out.printf("%nName successfully changed from %s to %s", initialOptionName, finalOptionName);
				return;
			}
		}
		System.out.printf("%nCannot find Option with the name %s", initialOptionName);
	}
	
	protected void setOptPrice(int optIndex, float optPrice) { //set price of Option at index
		opt.get(optIndex).setOptPrice(optPrice);
	}
	
	protected void setOptionChoice(String optionName) {
		this.optChoice = findOption(optionName);
	}
	
	protected boolean optionExist(String optionName) {
		boolean exist = false;
		for(int i = 0; i < opt.size(); i++) {
			if(optionName.equals(opt.get(i).getOptName())) {
				exist = true;
				return exist;
			}
		}
		return exist;
	}
	
	protected Option findOption(String optionName) {
		for(Option i: opt)
			if(i.getOptName().equals(optionName))
				return i;
		
		return null;
	}
	
	protected int countOption() {
		return opt.size();
	}
	
	protected void buildOption(int index, String optName, float optPrice) {
		opt.set(index, new Option(optName, optPrice));
		//opt[index] = new Option(optName, optPrice);
	}
	
	protected String toStringOneOption(int index) {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(opt.get(index).toStringOptionData());
		return strBuild.toString();
	}
	
	protected String toStringSetData() {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(setName).append(": \n");
		
		for(int i = 0; i < opt.size(); i++) {
			strBuild.append(opt.get(i).toStringOptionData()).append("\n");
		}
		
		return strBuild.toString();
	}
	
	protected String toStringSetName() {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(setName);
		return strBuild.toString();
	}
	
	protected String toStringOptionName(int optIndex) {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(opt.get(optIndex).getOptName());
		return strBuild.toString();
	}
	
	protected String toStringOptionPrice(int optIndex) {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(opt.get(optIndex).getOptPrice());
		return strBuild.toString();
	}
	
	protected String toStringAllOption() {
		StringBuilder strBuild = new StringBuilder();
		for(int i = 0; i < opt.size(); i++) {
			strBuild.append(opt.get(i).getOptName() + "\n");
		}
		return strBuild.toString();
	}
	
	
	
	public class Option implements Serializable{
		private String optName;
		private float price;
		
		Option() {
			optName = " ";
			price = 0;
		}
		
		public Option(String optName, float price) {
			this.optName = optName;
			this.price = price;
		}
		
		protected String getOptName() {
			return optName;
		}
		
		protected float getOptPrice() {
			return price;
		}
		
		protected void setOptName(String optName) {
			this.optName = optName;
		}
		
		protected void setOptPrice(float price) {
			this.price = price;
		}
		
		protected String toStringOptionData() {
			StringBuilder strBuild = new StringBuilder();
			strBuild.append(optName).append(", ").append(price);
			return strBuild.toString();
		}
		
		protected void printData() {
			System.out.printf("%s", toStringOptionData());
		}
	}
}