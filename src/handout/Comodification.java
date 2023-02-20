// CS 0445 Spring 2022
// Demonstration of concurrent modification exception in Java.
import java.util.*;

public class Comodification
{
	public static void main(String [] args)
	{
		List<Integer> L1 = new LinkedList<Integer>(); 

		Random R = new Random();
		
		for (int i = 0; i < 10; i++)
		{
			Integer newVal = R.nextInt(100);
			L1.add(newVal);
		}

		// In this block we are modifying the underlying data via the inner iterator
		// Within the inner loop that is fine because the same iterator is accessing
		// and removing the data.  However, when we loop back to the outer iterator
		// and call next() again, we will get a ConcurrentModificationException, 
		// since some other entity (the inner iterator) has changed the underlying
		// data that we are using for our outer iterator.
		
		Iterator<Integer> iter1 = L1.iterator();
		Iterator<Integer> iter2 = L1.iterator();
		while (iter1.hasNext())
		{
			System.out.println("Outer iterator has items remaining...");
			Integer X = iter1.next();
			System.out.println("Next value in outer iterator is : " + X);
			while (iter2.hasNext())
			{
				Integer Y = iter2.next();
				System.out.println("\tNext value in inner iterator is: " + Y);
				if (Y % 2 == 0)
				{
					System.out.println("\t\tRemoving even number " + Y + " in inner iterator");
					iter2.remove();
				}
			}
		}
		System.out.println();
	}
}
