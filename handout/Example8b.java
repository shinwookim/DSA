// CS 0445 Spring 2022
// Compare to Example8.java
// Demonstration of some of the abilities of an ADT List, this time using
// the standard Java List interface.  The List is part of the Java Collections
// Framework.  For more details on the Collections Framework, look up
// Collection in the Java API.

import java.util.*;
import javax.swing.*;

public class Example8b
{
	public static void addItems(List<Integer> L, int N)
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
		// Note the difference in method names shown below from those in
		// Example8.java.  Also note that the standard list is indexed from
		// 0..size()-1 (the same way normal arrays are indexed)
		List<Integer> L = new LinkedList<Integer>();

		// Last In First Out behavior
		addItems(L, 5);
		System.out.print("Removing: ");
		while (L.size() > 0)
		{
			Integer oldI = L.remove(L.size()-1);
			System.out.print(oldI + " ");
		}
		System.out.println();
		System.out.println();

		// First in First Out behavior
		// Note: This works but it is NOT a good way to get FIFO behavior
		// due to efficiency concerns.  We will discuss this more later.
		addItems(L, 6);
		System.out.print("Removing: ");
		while (L.size() > 0)
		{
			Integer oldI = L.remove(0);
			System.out.print(oldI + " ");
		}
		System.out.println();

		// Check for inclusion
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
		// are handled.
		L.clear();
		addItems(L, 8);
		int pos = Integer.parseInt(JOptionPane.showInputDialog(null,
			                       "Enter index of new item"));
		Integer newI = Integer.valueOf(Integer.parseInt(
						   JOptionPane.showInputDialog(null,
						   "Enter new item")));

		// Note that in Example8.java, the add() below was in an if
		// condition, since it returns a boolean.  However, in the
		// standard List interface, add() is void and throws an
		// exception if the index is invalid.
		try
		{
			L.add(pos, newI);
			System.out.println(newI + " added at pos " + pos);
		}
		catch (IndexOutOfBoundsException e)
		{
			System.out.println(newI + " not added");
		}

		pos = Integer.parseInt(JOptionPane.showInputDialog(null,
			"Enter index of item to update"));
		newI = Integer.valueOf(Integer.parseInt(
			JOptionPane.showInputDialog(null,
			"Enter new value")));
		try
		{
			L.set(pos, newI);
			System.out.println("Position " + pos + " changed to " + newI);
		}
		catch (IndexOutOfBoundsException e)
		{
			System.out.println("List not changed");
		}
		System.out.println();
		System.out.println("Updated List (using get()):");
		for (int i = 0; i < L.size(); i++)
			System.out.print(L.get(i) + " ");
		System.out.println("\n");
		
		System.out.println("Updated List (using toArray()):");
		Object [] items = L.toArray();
		for (Object x: items)
			System.out.print(x + " ");
		System.out.println();
			
		System.exit(0);
	}
}
