// CS 0445 Fall 2021
// Same as the MyArray<T> type, but with the addition of an
// Iterator, making the class Iterable.  Note how the Iterator
// is implemented.  See comments below and Example3b.java

import java.util.*;
public class MyArrayIterable<T> implements Iterable<T>
{
	private T [] theArray;  // Parameter used for array data

	public MyArrayIterable(int size)
	{
		// Note the way this array is created here.  It would
		// seem more straightforward to have the following
		// assignment:
		//  theArray = new T[size];
		// However, this is not allowed in Java
		theArray = (T[]) new Object[size];
	}

	// We make length() a method rather than a public instance
	// variable.
	public int length()
	{
		return theArray.length;
	}

	// Standard "get and set" methods to retrieve a value and to
	// assign a value.
	public T get(int i)
	{
		return theArray[i];
	}

	public void set(int i, T data)
	{
		theArray[i] = data;
	}
	
	public String toString()
	{
		StringBuilder S = new StringBuilder();
		for (int i = 0; i < theArray.length; i++)
		{
			S.append(theArray[i].getClass());
		}
		return S.toString();
	}
	
	// Simple method to return a new Iterator object on this
	// MyArrayIterable object.  This method satisfies the Iterable<T>
	// interface.
	public Iterator<T> iterator()
	{
		return new MyArrayIterator();
	}
	
	// Simple Iterator for an array.  The remove() method is not
	// implemented in this Iterator.
	private class MyArrayIterator implements Iterator<T>
	{
		private int loc;
		
		public MyArrayIterator()
		{
			loc = 0;
		}
		
		public boolean hasNext()
		{
			return loc < theArray.length;
		}
		
		public T next()
		{
			if (hasNext())
				return theArray[loc++];
			else
				throw new NoSuchElementException("Iterating past end of list");
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException("remove() not implemented");
		}
	}	
	
}