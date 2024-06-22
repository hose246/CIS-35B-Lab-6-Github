package model;
import java.io.Serializable;
import java.util.ArrayList;

public class Automobile implements Serializable{
	private static final long seriaVersionUID = 5160058749708002387L;
	private String make;
	private String model;
	private String name;
	private float basePrice;
	private ArrayList<OptionSet> opset;
	private ArrayList<OptionSet.Option> choice;
	
	public Automobile() {
		
	}
	
	public Automobile(String name, float basePrice, int OptionSetSize) {
		this.name = name;
		this.basePrice = basePrice;
		this.opset = new ArrayList<>(OptionSetSize);
		this.make = ".";
		
		this.choice = new ArrayList<>(OptionSetSize);
		
		for(int i=0; i<OptionSetSize;i++) {
			opset.add(new OptionSet());
			choice.add(new OptionSet().new Option());
			
		}
	}
	
	public String getMake() {
		return make;
	}
	
	public String getModel() {
		return model;
	}
	
	public String getName() {
		return name;
	}
	
	public float getBasePrice() {
		return basePrice;
	}
	
	public OptionSet getOptionSet(int index) {
		return opset.get(index);
	}
	
	public ArrayList<OptionSet> getAllOptionSet() {
		return opset;
	}
	
	public String getSetName(int index) { //get OptionSet's name at index
		return opset.get(index).getSetName();
	}
	
	public String getOptName(int setIndex, int optIndex) { //get name of Option at an index in OptionSet
		return opset.get(setIndex).getOptName(optIndex);
	}
	
	public float getOptPrice(int setIndex, int optIndex) { //get Option price at an index in OptionSet
		return opset.get(setIndex).getOptPrice(optIndex);
	}
	
	public OptionSet.Option getOption(int setIndex, int optIndex) {
		return opset.get(setIndex).getOption(optIndex);
	}
	
	public String getOptionChoice(String setName) {
		return opset.get(findOptionSetIndex(setName)).getOptionChoice().getOptName();
	}
	
	public float getOptionChoicePrice(String setName) {
		return findOptionSet(setName).getOptionChoice().getOptPrice();
	}
	
	public float getTotalPrice() {
		float sum = 0;
		for(int i=0; i<choice.size(); i++) {
			sum += choice.get(i).getOptPrice();
		}
		return sum;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}
	
	public void setOptionSet(int index, OptionSet opSetValue) {
		opset.set(index, opSetValue);
	}
	
	public void setSetName(int index, String setName) { //set OptionSet's name at index
		opset.get(index).setSetName(setName);
	}
	
	public void setOptName(int setIndex, int optIndex, String optName) { //set name of Option at an index in OptionSet
		opset.get(setIndex).setOptName(optIndex, optName);
	}
	
	public void setOptNameWithOptionName(String opsetName, String initialOptionName, String finalOptionName) {
		findOptionSet(opsetName).setOptNameWithOptionName(initialOptionName, finalOptionName);
	}
	
	public void setOptPrice(int setIndex, int optIndex, float optPrice) { //set Option price at an index in OptionSet
		opset.get(setIndex).setOptPrice(optIndex, optPrice);
	}
	
	public void setOptionChoice(String setName, String optionName) {
		int setIndex = findOptionSetIndex(setName);
		opset.get(setIndex).setOptionChoice(optionName);
		choice.set(setIndex, findOptionWithSetIndex(setIndex, optionName));
	}
	
	public void setOptionChoice(int setIndex, String optionName) {
		opset.get(setIndex).setOptionChoice(optionName);
		choice.set(setIndex, findOptionWithSetIndex(setIndex, optionName));
	}
	
	public boolean optionExist(int setIndex, String optionName) {
		return opset.get(setIndex).optionExist(optionName);
	}
	
	public OptionSet findOptionSet(String opSetName) {
		for(OptionSet i : opset)
			if(i.getSetName().equals(opSetName))
				return i;
		
		return new OptionSet();
	}
	
	public int findOptionSetIndex(String opSetName) {
		for(int i=0;i<opset.size();i++)
			if(opset.get(i).getSetName().equals(opSetName))
				return i;
		
		return -1;
	}
	
	public OptionSet.Option findOption(String opSetName, String optionName) {
		for(int i=0;i<opset.size();i++)
			for(int j=0;j<opset.get(i).countOption();j++)
				if(getOptName(i, j).equals(optionName))
					return opset.get(i).getOption(j);
		
		return new OptionSet().getOption(-1);
 	}
	
	public OptionSet.Option findOptionWithSetIndex(int setIndex, String optionName) {
		return opset.get(setIndex).findOption(optionName);
 	}
	
	public int findOptionIndex(int setIndex, String optionName) {
		for(int i=0;i<opset.get(setIndex).countOption();i++) {
			if(opset.get(setIndex).getOptName(i).equals(optionName))
				return i;
		}
		
		return -1;
	}
	
	public int countOptionSet() { //count Option Set's length
		return opset.size();
	}
	
	public int countOption(int index) { //count the amount of Options at an index in OptionSet
		return opset.get(index).getAllOption().size();
	}
	
	public void buildOptionSet(int index, String setName, int setSize) {
		opset.set(index, new OptionSet(setName, setSize));
		//opset[index] = new OptionSet(setName, setSize);
	}
	
	public void buildOption(int setIndex, int optIndex, String optName, float optPrice) { //build Option at an index in OptionSet
		opset.get(setIndex).buildOption(optIndex, optName, optPrice);
	}
	
	public String toStringOneOption(int setIndex, int optIndex) {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(opset.get(setIndex).toStringOneOption(optIndex));
		return strBuild.toString();
	}
	
	public String toStringName() {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(name);
		return strBuild.toString();
	}
	
	public String toStringSetName(int setIndex) {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(opset.get(setIndex).toStringSetName());
		return strBuild.toString();
	}
	
	public String toStringOptionName(int setIndex, int optIndex) {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(opset.get(setIndex).toStringOptionName(optIndex));
		return strBuild.toString();
	}
	
	public String toStringOptionPrice(int setIndex, int optIndex) {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(opset.get(setIndex).toStringOptionPrice(optIndex));
		return strBuild.toString();
	}
	
	public String toStringAllOption(int setIndex) {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(opset.get(setIndex).toStringAllOption());
		return strBuild.toString();
	}
	
	public void printOneOption(int setIndex, int optIndex) {
		System.out.printf("%n%s", toStringOneOption(setIndex, optIndex));
	}
	
	public void printName() {
		System.out.printf("%s", toStringName());
	}
	
	public void printSetName(int setIndex) {
		System.out.printf("%s", toStringSetName(setIndex));
		}
	
	public void printOptionName(int setIndex, int optIndex) {
		System.out.printf("%s", toStringOptionName(setIndex, optIndex));
	}
	
	public void printOptionPrice(int setIndex, int optIndex) {
		System.out.printf("%s", toStringOptionPrice(setIndex, optIndex));
	}
	
	public void printAllOption(int setIndex) {
		System.out.printf("%n%s", toStringAllOption(setIndex));
	}
	
	public void printAll() {
		System.out.printf("%nModel: %s", name);
	
		for(int i = 0; i < opset.size(); i++) {
			System.out.printf("%n%s: ", toStringSetName(i));
			
			for(int j = 0; j < countOption(i); j++) {
				System.out.printf("%s, %s%n", toStringOptionName(i, j), toStringOptionPrice(i, j));
			}
		}
	}
}
