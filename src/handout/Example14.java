// CS 0445 Spring 2022
// Demonstration of an Iterator implemented within a linked list (as defined
// by author of the text).  For this handout I use a slightly modified version
// of the author's LinkedListWithIterator class.  I added an instance variable
// and two methods in order to count how many actual "moves" are required to
// iterate through the list using the getEntry() method and using the iterator.
import java.util.*;
public class Example14
{
	// Method to iterate through the list, using the getEntry()
	// method.  Note that from the user's point of view, getEntry()
	// is a single operation, but the getCount() method reveals that
	// it actually is not.
	public static void LoopWithGet(LinkedListWithIterator<Integer> L)
	{
		System.out.println("Iterating using get() method");
		L.clearCount();
		for (int i = 1; i <= L.getLength(); i++)
			System.out.print(L.getEntry(i) + " ");
		System.out.println();
		System.out.println("Count: " + L.getCount() + "\n");
	}

	// Method to iterate through the list, using an iterator as
	// shown.  To the user it does not seem much different from the
	// method above, but the getCount() method reveals the difference
	public static void LoopWithIterator(LinkedListWithIterator<Integer> L)
	{
		System.out.println("Iterating using iterator");
		L.clearCount();
		Iterator<Integer> I = L.getIterator();
		while (I.hasNext())
		{
			System.out.print(I.next() + " ");
		}
		System.out.println();
		System.out.println("Count: " + L.getCount() + "\n");
	}

	public static void main(String [] args)
	{
		LinkedListWithIterator<Integer> L = new LinkedListWithIterator<Integer>();
		for (int i = 0; i < 1000; i++)
			L.add(Integer.valueOf(i));
		LoopWithGet(L);
		LoopWithIterator(L);
	}
}




