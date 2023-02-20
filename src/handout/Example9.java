// CS 0445 Spring 2022
// Simple demo of Linked List vs. Array List in Java

public class Example9
{
	public static void main(String [] args)
	{
		ListInterface<Integer> L1 = new LList<Integer>(); 
							// Note that either an LList or an
		ListInterface<Integer> A1 = new AList<Integer>();	
							// AList can be stored in a ListInterface variable
							// since they both implement ListInterface
		for (int i = 1; i <= 10; i++)
		{
			L1.add(new Integer(i));
			A1.add(new Integer(i));
		}

		printData1(L1);
		printData1(A1);

		L1.remove(new Integer(6));
		A1.add(4, new Integer(21));

		System.out.println("\nPrinting using getEntry()");
		printData1(L1);
		printData1(A1);
		
		System.out.println("\nPrinting using toArray()");
		printData2(L1);
		printData2(A1);
	}

	public static void printData1(ListInterface<Integer> L)	 // Print out the data
	{											// Note that this works for both
		if (L != null)							// the AList and the LList since
		{										// both implement ListInterface 
			for (int i = 1; i <= L.getLength(); i++)  // However, the efficiency is VERY
			{									// different for the two different
				System.out.print(L.getEntry(i) + " ");	// lists.  See class notes.
			}									
			System.out.println();
		}
	}
	
	// Print the data using toArray().  The effect of this is identical to that of
	// printData1 above, but for the LList the run-time is dramatically different.
	// For the AList the run-times are comparable for both methods.  We will soon
	// discuss Iterators, which allow efficient sequential access to data without
	// having to first copy it into an array.
	public static void printData2(ListInterface<Integer> L)
	{
		if (L != null)
		{
			Object [] items = L.toArray();
			for (Object x: items)
			{
				System.out.print(x + " ");
			}
			System.out.println();
		}
	}
}
