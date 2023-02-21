// CS 0401 Fall 2021
// Demonstration of Iterable interface in Java.  Compare to Example4.java.
// See also MyArrayIterable.java

import java.util.*;  // Need java.util for Iterator interface (note: we do not
						// need this if we are just using the "for each" loop.
						// It is needed herebecause I access an Iterator
						// directly later in the code.)
public class Example4b
{
	public static String [] stringData = {"PA", "OH", "NY", "WV", "NH", "MD", "NJ"}; 
	public static void main(String [] args)
	{
		// Note the three variable declarations below.  Each is the same
		// fundamental type (MyArray) but with different generic parameters.
		MyArrayIterable<String> S = new MyArrayIterable<String>(5);
		MyArrayIterable<Integer> I = new MyArrayIterable<Integer>(10);
		MyArrayIterable<MyRectangle> R;
		R = new MyArrayIterable<MyRectangle>(3);

		for (int i = 0; i < S.length(); i++)
			S.set(i, stringData[i]);

		for (int i = 0; i < I.length(); i++)
			I.set(i, Integer.valueOf(i));
	
		R.set(0, new MyRectangle(0, 0, 20, 40));
		R.set(1, new MyRectangle(0, 0, 40, 20));
		R.set(2, new MyRectangle(40, 40, 25, 25));

		// Note below that showData is using for "foreach" loop -- this loop
		// requires the underlying collection to be Iterable
		showData(S);
		showData(I);
		showData(R);
		
		// We can also use the Iterator directly
		Iterator<String> it = S.iterator();
		while (it.hasNext())
		{
			System.out.println("Next state is " + it.next());
		}
		
		// Now we will go past the end of the iterator
		try
		{
			System.out.println("This will print " + it.next());
		}
		catch (NoSuchElementException e)
		{
			System.out.println(e);
		}
	}

	// Since the MyArrayIterable type implements the Iterable<T> interface,
	// we can use the "for each" version of the for loop to output the data.
	public static <T> void showData(MyArrayIterable<T> A)
	{
		System.out.println("Here is the data: ");
		for (T x : A) 
			System.out.println(x);
		System.out.println();
	}
}