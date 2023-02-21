// CS 0445 
// Somewhat generic comparator in Java.  Most of the code in this file was taken
// from an example provided by Gervase Gallant:
// [see http://www.javazoid.com/foj_arrays.html].  

// I modified it a bit to use methods rather than fields for the comparisons, and 
// to add type parameters to the Comparator.

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GenericComparator<T> implements Comparator<T> {
	public static final String ASC="asc";
	public static final String DESC="desc";
	
	private String compareMethod = null;
	private String sortOrder = null;
	
	// Create a new GenericComparator object using the method name and
	// sorting order passed in as arguments
	public GenericComparator(String method, String order) {
		super();
		this.compareMethod = method;
		if (order == null || order.equals(ASC)){
			this.sortOrder=ASC;	
		} else if (order.equals(DESC)){
			this.sortOrder=DESC;	
		}	
		
	}
	
	public GenericComparator(String method){
		this(method,"asc");	
	}	

	public int compare(T o1, T o2) {
		Object value1;
		Object value2;
		try{
			// The Method class in Java allows us to get information about and
			// invoke an arbitrary method within a Java class.  
			Method m = this.getMethod(o1);
					
			if (m == null) return 0;
			
			// The invoke method executes a methed and returns the result.  So
			// in this case we invoke the method for both objects and then
			// compare the results.
			if (this.sortOrder.equals(ASC))
			{
				value1 = m.invoke(o1);
				value2 = m.invoke(o2);
			} 
			else
			{
				value2 = m.invoke(o1);
				value1 = m.invoke(o2);
				
			}	
			return this.compareField( value1, value2);
			
		}catch (Exception nsfe){
			System.out.println(nsfe + " for " + o1 + " or " + o2);
			return 0;	
		}	
		
	}
	
	private int compareField(Object value1, Object value2) throws IllegalAccessException{
			
			if (value1 instanceof Comparable){
				return ((Comparable) value1).compareTo(value2);
					
			} else {		
				return value1.toString().compareTo(value2.toString()); //try String sort.
			}	
	}	
	
	private Method getMethod(Object object){
	
			Method [] methods = object.getClass().getMethods();
			for (int i = 0; i < methods.length; i++){
				if (methods[i].getName().equals(this.compareMethod)){
						return methods[i];		
				}
					
			}
			System.out.println("Sorry...couldn't sort on " + this.compareMethod);
			return null;
	}
}
