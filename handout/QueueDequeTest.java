// CS 0445 Spring 2022
//
// Testing of simple queue and deque operations.
// In this handout we don't care how our queue and deque are implemented.
// However, we need some classes to use so in this case we will use:
// 		LinkedQueue (in LinkedQueue.java) for the Queue
//		LinkedDeque (in LinkedDeque.java) for the Deque
// Don't worry about the code in those classes right now --
// we will discuss these implementations later in the term.
// For this handout you will need the following files:
//		QueueInterface.java
//		DequeInterface.java
//		LinkedQueue.java
//		LinkedDeque.java
//		EmptyQueueException.java
// Note: The included files were written by the text authors, and they do not
// want them to be publicly available.  Because of this, to access them from the
// CS 0445 Web site, you will need to be on a Pitt network -- either physically or
// via a VPN.  If you would prefer not to connect to Pitt with a VPN, you can also
// obtain these files from the course Canvas site.

import java.util.*;

public class QueueDequeTest
{
	public static void main(String [] args)
	{
		QueueInterface<Integer> Q1 = new LinkedQueue<Integer>();
		DequeInterface<Integer> D1 = new LinkedDeque<Integer>();

		// Testing addToBack
		for (int i = 0; i < 10; i++)
		{
			Integer newItem = Integer.valueOf(2 * i);
			System.out.println("Trying to add " + newItem + " added to Deques");
			Q1.enqueue(newItem);
			D1.addToBack(newItem);
			
		}
		
		Integer X = Q1.getFront();
		System.out.println("Front of Q1: " + X);
		X = D1.getFront();
		System.out.println("Front of D1: " + X);
		System.out.println();
		
		// Testing removeFront for Queue
		while (!(Q1.isEmpty()))
		{
			Integer F = Q1.dequeue();
			System.out.println("Front of Q1: " + F);
		}
		
		// dequeue() will throw an exception if the queue is empty
		try
		{
			Integer noItem = Q1.dequeue();
			System.out.println("Front of Q1 is " + noItem);
		}
		catch (EmptyQueueException e)
		{
			System.out.println("Cannot get front of empty queue");
		}
		System.out.println();
		
		// Testing removeFront and removeBack for Deque
		while (!(D1.isEmpty()))
		{
			Integer F = D1.removeFront();
			System.out.println("Front of D1: " + F);
			if (!D1.isEmpty())
			{
				Integer B = D1.removeBack();
				System.out.println("Back of D1: " + B);
			}
		}
		
		// removeFront() and removeBack() will throw an exception if
		// the deque is empty
		try
		{
			Integer noItem = D1.removeBack();
			System.out.println("Back of D1 is " + noItem);
		}
		catch (EmptyQueueException e)
		{
			System.out.println("Cannot get back of empty deque");
		}
	}
}
		