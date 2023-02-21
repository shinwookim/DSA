/* CS0445 Spring 2022
   Alternative version of People class using "old style" Comparable
   implementation.  This will also generate a warning from the compiler.
*/

import java.util.*;
import java.text.*;

public class People2 implements Comparable			
{
	private String name;   // name and dob are both private
	private Date dob;

	public People2(String s, String d)
	{
		name = new String(s);
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		try 
		{
			dob = df.parse(d);
		}
		catch (ParseException e)
		{
			dob = null;
		}
	}

	public int age()
	{
		long dobtime = dob.getTime();  
		long currtime = System.currentTimeMillis();  
		long difftime = currtime - dobtime;
		difftime = difftime/1000; 
		difftime = difftime/(24 * 3600);
		difftime = difftime/365;
		return (int) difftime;
	}

	public String toString()  // called implicitly as mentioned before
	{
		return ("Name: " + name + " Age: " + age());
	}

	// Note that in this version of compareTo the parameter is Object.  This
	// header allows for generic behavior but does not restrict the type of
	// the parameter.  We must then cast the parameter to the correct type
	// within the method -- which is a run-time rather than a compile-time check.
	public int compareTo(Object r)
	{
		People2 rhs = (People2) r;
		if (age() > rhs.age()) return 1;
		else if (age() < rhs.age()) return -1;
		else return 0;
	}
}
