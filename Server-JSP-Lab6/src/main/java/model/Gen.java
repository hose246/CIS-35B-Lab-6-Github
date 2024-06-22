package model;
import java.util.LinkedHashMap;
import java.util.Collection;
import java.util.Set;
import java.util.Iterator;

public class Gen<T, V> {
	T ob1;
	V ob2;
	LinkedHashMap<T, V> lhm = new LinkedHashMap<>();
	
	public Gen() {
		
	}
	
	public Gen(T o1, V o2) {
		ob1 = o1;
		ob2 = o2;
		lhm.put(o1, o2);
	}
	
	public T getLastKey() {
		return ob1;
	}
	
	public V getLastValue() {
		return ob2;
	}
	
	public V getValue(T o1) {
		return lhm.get(o1);
	}
	
	public boolean containsKey(T o1) {
		return lhm.containsKey(o1);
	}
	
	public boolean containsValue(V o2) {
		return lhm.containsValue(o2);
	}
	
	public void put(T o1, V o2) {
		ob1 = o1;
		ob2 =  o2;
		lhm.put(o1, o2);
	}
	
	public void delete(T o1) {
		lhm.remove(o1);
	}
	
	public void printAll() {
		System.out.print(lhm);
	}
	
	public Set<T> keySet() {
		return lhm.keySet();
	}
	
	public Collection<V> values() {
		return lhm.values();
	}
	
	
	/*T ob;
	
	Gen(T ob) {
		this.ob = ob;
	}
	
	public T getOb() {
		return ob;
	}
	*/
}
