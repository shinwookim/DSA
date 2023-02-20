// CS 0445 Spring 2022
// Adding a LinkedBag to the Bag demonstration.  Note that we do not have to change
// any of this code other than the addition of a new variable, since we use BagInterface
// for all of our functionality.

// The first part of this handout is similar to Example6. I have added a section
// to demonstrate removal.  Note the difference in output between the dynamic array
// Bag and the linked list Bag.  Even though the data is in a different order, it
// does not matter as far as our BagInterface is concerned.  As we discussed in lecture,
// a user of the BagInterface should not care about this order.

import java.util.*;
public class Example7
{
	public static void addItems(BagInterface<Integer> B, int N)
	{
		System.out.println("Adding: ");
		for (int i = 1; i <= N; i++)
		{
			Integer newI = Integer.valueOf(i);
			if (B.add(newI))
				System.out.println("Added " + newI);
			else
				System.out.println("No room for " + newI);
		}
		System.out.println();
	}
	
	public static <T> void showItems(BagInterface<T> B)
	{
		T [] items = B.toArray();
		for (T x: items)
			System.out.print(x + " ");
		System.out.println();
	}
		
	public static void main(String [] args)
	{
		BagInterface<Integer> fixBag = new ArrayBag<Integer>(6);
		BagInterface<Integer> dynBag = new ResizableArrayBag<Integer>(6);
		BagInterface<Integer> linkBag = new LinkedBag<Integer>();

		addItems(fixBag, 10);
		addItems(dynBag, 10);
		addItems(linkBag, 10);
		System.out.println("Fixed Bag contains:");
		showItems(fixBag);
		System.out.println();
		System.out.println("Dynamic Bag contains:");
		showItems(dynBag);
		System.out.println();
		System.out.println("Linked Bag contains:");
		showItems(linkBag);	
		System.out.println();
		
		Integer toRemove = Integer.valueOf(5);
		System.out.println("Removing " + toRemove + " from each Bag");
		System.out.println();
		fixBag.remove(toRemove);
		dynBag.remove(toRemove);
		linkBag.remove(toRemove);
		
		System.out.println("Fixed Bag contains:");
		showItems(fixBag);
		System.out.println();
		System.out.println("Dynamic Bag contains:");
		showItems(dynBag);
		System.out.println();
		System.out.println("Linked Bag contains:");
		showItems(linkBag);
	}
}