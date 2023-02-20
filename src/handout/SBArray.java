// CS 0445 Spring 2022
// Simple class to demonstrate shallow vs. deep copies of
// objects.  See also Example2.java.

public class SBArray
{
	private StringBuilder [] A;
	private int size;

	public SBArray(int max)
	{
		A = new StringBuilder[max];
		size = 0;
	}
	
	// Copy constructor.  Based on the value of the flag
	// it will be shallow, "in between", or deep.
	public SBArray(SBArray old, int flag)
	{
		if (flag == 0)  // shallow - just assign refs
		{				// array is shared
			A = old.A;
			size = old.size;
		}
		else if (flag == 1)  // deeper - make new array
		{
			A = new StringBuilder[old.A.length];
			for (int i = 0; i < old.size; i++)
				A[i] = old.A[i];
      		size = old.size;
      	}
      	else // deep - make new objects inside array
      	{
      		A = new StringBuilder[old.A.length];
      		for (int i = 0; i < old.size; i++)
      		{
      			StringBuilder newS = new StringBuilder(old.A[i]);
      			A[i] = newS;
      		}
      		size = old.size;
      	}
	}

	// add to end of array
	public void add(StringBuilder S)
	{
		if (size < A.length)
		{
			A[size] = S;
			size++;
		}
	}
	
	// change value in A[i]
	public void set(int i, StringBuilder S)
	{
		if (0 <= i && i < size)
		{
			A[i] = S;
		}
	}
	
	// return value in A[i]
	public StringBuilder get(int i)
	{
		if (0 <= i && i < size)
			return A[i];
		else
			return null;
	}
	
	// Return a single String containing all data
	public String toString()
	{
		StringBuilder total = new StringBuilder();
		for (int i = 0; i < size; i++)
			total.append(A[i].toString() + "  ");
		return total.toString();
	}
}