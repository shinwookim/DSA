// CS 0445 Spring 2022
// Demo of why an iterator may be useful.  This example is using that standard
// Java List, LinkedList and ArrayList in a demonstration that finds the mode
// [most common value] in a list of Integers.
import java.util.*;

public class Example13
{
	public static void main(String [] args)
	{
		List<Integer> L1 = new LinkedList<Integer>(); 
		List<Integer> L2 = new ArrayList<Integer>();

		Random R = new Random();
		
		for (int i = 0; i < 100; i++)
		{
			Integer newVal = R.nextInt(100);
			L1.add(newVal);
			L2.add(newVal);
		}

		System.out.println("Calculating mode with nested iterators: ");
		getModeIterator1(L1);
		getModeIterator1(L2);

		System.out.println("\nCalculating mode with for loop iterators: ");
		getModeIterator2(L1);
		getModeIterator2(L2);

		System.out.println("\nCalculating mode without iterator: ");
		getModeNoIterator(L1);
		getModeNoIterator(L2);

		System.out.println("\nIterator with destructive calculation: ");
		getModeDestructive(L1);
		getModeDestructive(L2);

		System.out.println("The size of List 1 is " + L1.size());
		System.out.println("The size of List 2 is " + L2.size());
	}

	// Using nested iterators to calculate the mode of a list of
	// integers.  Note that this works fine for either an array or
	// a linked implementation of the List, and that at this level
	// we do not need to know or care how the List is stored or how
	// the iterator is implemented.  The idea is that the iterator will
	// be implemented in an efficient way for the underlying data
	// structure of the list.
	public static void getModeIterator1(List<Integer> L)
	{
		Iterator<Integer> outer = L.iterator();
		Iterator<Integer> inner;
		Integer theMode = null, currOuter = null, currInner = null;
		int modeCount = 0, currCount = 0;
		while (outer.hasNext())
		{
			currOuter = outer.next();
			currCount = 0;
			inner = L.iterator();
			while (inner.hasNext())
			{
				currInner = inner.next();
				if (currInner.equals(currOuter))
					currCount++;
			}
			if (currCount > modeCount)
			{
				theMode = currOuter;
				modeCount = currCount; 
			}
		}
		System.out.println("The mode is " + theMode + " with " + modeCount +
						   " occurrences ");
	}

	// Logically, this code is equivalent to the version above.  However, with
	// JDK 1.5 the Iterable interface was introduced, which allows the standard
	// Java for loop to use the iterator of the underlying class in the loop.
	// This version of the for loop can also be used to iterate through regular
	// arrays (if so desired).
	public static void getModeIterator2(List<Integer> L)
	{
		Integer theMode = null;
		int modeCount = 0, currCount = 0;
		for (Integer currOuter : L)
		{
			currCount = 0;
			for (Integer currInner : L)
			{
				if (currInner.equals(currOuter))
					currCount++;
			}
			if (currCount > modeCount)
			{
				theMode = currOuter;
				modeCount = currCount;
			}
		}
		System.out.println("The mode is " + theMode + " with " + modeCount +
						   " occurrences ");
	}

	// In this version we are using the get() method rather than using an
	// iterator.  For the array version this works fine, but it is very
	// inefficient for the linked list, since each call to get() for the
	// linked list requires a traversal of the list.  It is situations like
	// this where it is prudent for the programmer to be aware of the
	// underlying implementation of an ADT.
	public static void getModeNoIterator(List<Integer> L)
	{
		Integer theMode = null, currOuter = null, currInner = null;
		int modeCount = 0, currCount = 0;
		for (int i = 0; i < L.size(); i++)
		{
			currOuter = L.get(i);
			currCount = 0;
			for (int j = i; j < L.size(); j++)
			{
				currInner = L.get(j);
				if (currInner.equals(currOuter))
					currCount++;
			}
			if (currCount > modeCount)
			{
				theMode = currOuter;
				modeCount = currCount;
			}
		}
		System.out.println("The mode is " + theMode + " with " + modeCount +
						   " occurrences ");
	}

	// A drawback of the methods above is that they have to consider all of 
	// elements in the list many times. The method below improves the efficiency
	// somewhat by removing elements as they are counted.  This way, each
	// iteration through the list considers fewer elements than the previous.
	// One drawback of this method, however, is that it destroys the list
	// in the process of finding the mode.  We could get around this problem
	// by copying the list before finding the mode, or by building a new list
	// as we remove items from the old list.  Another drawback of this method
	// is that it relies on an efficient implementation of the remove()
	// method.  If you think about it, you will realize that, in the course
	// of an iteration, remove() can be implemented in a fairly efficient way
	// for a linked list but not so for an array (due to shifting).  Thus,
	// this destructive method is only really better for the linked list
	// implementation.
	public static void getModeDestructive(List<Integer> L)
	{
		Integer theMode = null, currOuter = null, currInner = null;
		int modeCount = 0, currCount = 0;
		while (L.size() > 0)
		{
			Iterator<Integer> iter = L.iterator();
			currOuter = iter.next();
			iter.remove();
			currCount = 1;
			while (iter.hasNext())
			{
				currInner = iter.next();
				if (currInner.equals(currOuter))
				{
					currCount++;
					iter.remove();
				}
			}
			if (currCount > modeCount)
			{
				theMode = currOuter;
				modeCount = currCount;
			}
		}
		System.out.println("The mode is " + theMode + " with " + modeCount +
						   " occurrences ");
	}
}
