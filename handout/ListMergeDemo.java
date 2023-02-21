// CS 0445 Spring 2022
// Simple demo of linked list MergeSort
import java.util.*;
public class ListMergeDemo
{
	public static void main(String [] args)
	{
		LListWithSort<Integer> L1 = new LListWithSort<Integer>(); 
		Random R = new Random();
		
		for (int i = 0; i < 250; i++)
		{
			int x = R.nextInt(10000);
			L1.add(x);
		}
		printData(L1);  System.out.println();
		L1.sort();
		printData(L1);
	}

	public static void printData(ListInterface<Integer> L)
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
