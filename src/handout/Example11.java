// CS 0445 Example 11
// Simple demo of Mergesort
// This program accepts 2 command line arguments:
// args[0] is the size of the array to sort
// args[1] is the range for the random numbers that will be
//         inserted into the array.

import java.util.*;
public class Example11
{
	public static Random R = new Random();
	// Fill array with random data
	public static void fillArray(Integer [] A, int range)
	{
		for (int i = 0; i < A.length; i++)
		{
			A[i] = Integer.valueOf(R.nextInt(range));
		}
	}

	public static void showArray(Integer [] A)
	{
		for (int i = 0; i < A.length; i++)
		{
			System.out.print(A[i] + " ");
		}
		System.out.println("\n");
	}

	public static void main(String [] args)
	{
		int size = Integer.parseInt(args[0]);
		int range = Integer.parseInt(args[1]);
		Integer [] A = new Integer[size];
		fillArray(A, range);
		showArray(A);
		TextMergeQuick.mergeSort(A, A.length);
		showArray(A);
	}
}


