// CS 0445 Spring 2022
// Demonstration of some of the abilities of an ADT List.  The interface
// we are using is ListInterface from the text.  Remember that we are the
// "client" in this case -- using the ADT without being overly concerned
// with how it was implemented.  We will look at implementation details
// soon.  Note also that some of the functionality here is the same as for
// our Bag ADT, but some is not.

import java.util.*;
import javax.swing.*;
public class Example8
{
	public static void addItems(ListInterface<Integer> L, int N)
	{
		System.out.print("Adding: ");
		for (int i = 1; i <= N; i++)
		{
			Integer newI = Integer.valueOf(i);
			System.out.print(newI + " "); 
			L.add(newI);
		}
		System.out.println();
	}

	public static void main(String [] args)
	{
		// We will look at the implementation of the list later.  For now
		// we are just considering its abilities
		ListInterface<Integer> L = new LList<Integer>();

		// Last In First Out behavior
		addItems(L, 5);
		System.out.print("Removing: ");
		while (L.getLength() > 0)
		{
			Integer oldI = L.remove(L.getLength());
			System.out.print(oldI + " ");
		}
		System.out.println();
		System.out.println();

		// First in First Out behavior
		// Note: This works but it is NOT a good way to get FIFO behavior
		// due to efficiency concerns.  We will discuss this more later.
		addItems(L, 6);
		System.out.print("Removing: ");
		while (L.getLength() > 0)
		{
			Integer oldI = L.remove(1);
			System.out.print(oldI + " ");
		}
		System.out.println();

		// Check for inclusion.  If searching is our primary goal, we
		// should use a more specialized ADT
		addItems(L, 10);
		Random R = new Random();
		for (int i = 0; i < 5; i++)
		{
			Integer findI = Integer.valueOf(R.nextInt(20));
			if (L.contains(findI))
				System.out.println(findI + " is in the list");
			else
				System.out.println(findI + " is not in the list");
		}

		// Insert and update data at a position
		// To see how invalid indexes are handled, enter a bogus index when
		// testing below.  A user of an ADT must know how unusual cases
		// are handled.  Note that the "index" here is a logical index within
		// and abstract List.  It may or may not translate into an actual
		// array index -- the user does not need to know this.  Note also
		// that in the author's ListInterface the indexing starts at 1 --
		// so index 0 is not actually valid.
		L.clear();
		addItems(L, 8);
		int pos = Integer.parseInt(JOptionPane.showInputDialog(null,
			                       "Enter index of new item"));
		Integer newI = Integer.valueOf(Integer.parseInt(
						   JOptionPane.showInputDialog(null,
						   "Enter new item")));
		try
		{
			L.add(pos, newI);
			System.out.println(newI + " added at pos " + pos);
		}
		catch (IndexOutOfBoundsException e)
		{
			System.out.println("Invalid index: " + newI + " not added");
		}		
		
		pos = Integer.parseInt(JOptionPane.showInputDialog(null,
			"Enter index of item to update"));
		newI = Integer.valueOf(Integer.parseInt(
			JOptionPane.showInputDialog(null,
			"Enter new value")));
		
		try
		{
			L.replace(pos, newI);
			System.out.println("Position " + pos + " changed to " + newI);
		}
		catch (IndexOutOfBoundsException e)
		{
			System.out.println("Invalid Index: List not changed");
		}
		System.out.println();
		// We will iterate through the list in two ways:
		// 1) Using the getEntry() method and
		// 2) Converting to an array and iterating through that
		// Although these operations seem similar their run-times are not
		// necessarily the same.  We will talk about these and other
		// ways of iterating through a list soon.
		System.out.println("Updated List (using getEntry()):");
		for (int i = 1; i <= L.getLength(); i++)
			System.out.print(L.getEntry(i) + " ");
		System.out.println("\n");
			
		System.out.println("Updated List (using toArray()):");
		Object [] items = L.toArray();
		for (Object x: items)
			System.out.print(x + " ");
		System.out.println();
		System.exit(0);
	}
}