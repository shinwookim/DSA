// CS 0445 Spring 2022
// I have taken the author's LList class and added a sort() method to it, utilizing
// Mergesort.  See a LOT of additional comments below.

/**
   A class that implements the ADT list by using a chain of
   linked nodes that has a head reference.
 
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/
public class LListWithSort<T> implements ListInterface<T>
{
	private Node firstNode;            // Reference to first node of chain
	private int  numberOfEntries; 

	public LListWithSort()
	{
		initializeDataFields();
	} // end default constructor
   
	public void clear()
	{
		initializeDataFields();
	} // end clear
   
	public void add(T newEntry) 	      // OutOfMemoryError possible
	{
		Node newNode = new Node(newEntry);

		if (isEmpty())
			firstNode = newNode;
		else                              // Add to end of non-empty list
		{
			Node lastNode = getNodeAt(numberOfEntries);
			lastNode.setNextNode(newNode); // Make last node reference new node
		} // end if	
		
		numberOfEntries++;
	}  // end add

   public void add(int newPosition, T newEntry)
	{
 		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
		{
			Node newNode = new Node(newEntry);
         
			if (newPosition == 1)                  // Case 1
			{
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}
			else									         // Case 2: list is not empty
			{                                      // and newPosition > 1
            Node nodeBefore = getNodeAt(newPosition - 1);
            Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			} // end if
         
			numberOfEntries++;
		}
      else
         throw new IndexOutOfBoundsException("Illegal position given to add operation.");
   } // end add

	public T remove(int givenPosition)
	{
      T result = null;                           // Return value

      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
         assert !isEmpty();

         if (givenPosition == 1)                 // Case 1: Remove first entry
         {
            result = firstNode.getData();        // Save entry to be removed
            firstNode = firstNode.getNextNode(); // Remove entry
         }
         else                                    // Case 2: Not first entry
         {
            Node nodeBefore = getNodeAt(givenPosition - 1);
            Node nodeToRemove = nodeBefore.getNextNode();
            result = nodeToRemove.getData();     // Save entry to be removed
            Node nodeAfter = nodeToRemove.getNextNode();
            nodeBefore.setNextNode(nodeAfter);   // Remove entry
         } // end if

         numberOfEntries--;                      // Update count
         return result;                          // Return removed entry
      }
      else
         throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
	} // end remove

	public T replace(int givenPosition, T newEntry)
	{
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {   
      	assert !isEmpty();

			Node desiredNode = getNodeAt(givenPosition);
         T originalEntry = desiredNode.getData();
			desiredNode.setData(newEntry);
         return originalEntry;
      }
		else
         throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
   } // end replace

   public T getEntry(int givenPosition)
   {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			assert !isEmpty();
         return getNodeAt(givenPosition).getData();
     	}
      else
         throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
   } // end getEntry

   public T[] toArray()
   {
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries];
      
      int index = 0;
      Node currentNode = firstNode;
      while ((index < numberOfEntries) && (currentNode != null))
      {
         result[index] = currentNode.getData();
         currentNode = currentNode.getNextNode();
         index++;
      } // end while
      
      return result;
   } // end toArray
                                             
	public boolean contains(T anEntry)
	{
		boolean found = false;
		Node currentNode = firstNode;
		
		while (!found && (currentNode != null))
		{
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		} // end while
		
		return found;
	} // end contains

   public int getLength()
   {
      return numberOfEntries;
   } // end getLength

   public boolean isEmpty()
   {
      boolean result;

      if (numberOfEntries == 0) // Or getLength() == 0
      {
         assert firstNode == null;
         result = true;
      }
      else
      {
         assert firstNode != null;
         result = false;
      } // end if

      return result;
   } // end isEmpty
	
   // Initializes the class's data fields to indicate an empty list.
   private void initializeDataFields()
   {
		firstNode = null;
		numberOfEntries = 0;
   } // end initializeDataFields
	
   // Returns a reference to the node at a given position.
   // Precondition: The chain is not empty;
   //               1 <= givenPosition <= numberOfEntries.	
	private Node getNodeAt(int givenPosition)
	{
		assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;
		
      // Traverse the chain to locate the desired node
      // (skipped if givenPosition is 1)
		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.getNextNode();
		
		assert currentNode != null;
      
		return currentNode;
	} // end getNodeAt

  	// This mutator will sort the list using the compareTo() method
  	public void sort()
  	{
  		int size = getLength();
  		// Note that the recursive mergeSort method is returning a sorted list.
  		// This is necessary because Java does not have reference parameters.  Since the
  		// front of the list may change we have to allow for a reassignment of the
  		// firstNode variable.  If we were implementing this in C++ or another language
  		// with reference parameters, we would not need to return the list in this way.
  		// This is not necessary for the array sorts since the array itself does not change
  		// in the sorts -- only the data inside the array changes.
  		firstNode = mergeSort(firstNode, size);
  	}
  	
  	// Linked List mergeSort method.  See more comments below.  This method can be efficient
  	// if implemented correctly, or much less efficient if implemented incorrectly.  In
  	// particular, note that this sort does not create or destroy any nodes -- it simply moves
  	// them to the appropriate places based on the comparisons.  If nodes were created and
  	// destroyed there would be a lot more overhead in this method (ex: if it was not an
  	// instance method and we did not have access to the Nodes themselves).
  	// However, due to the inherent overhead required for traversing and modifying a linked
  	// list versus that of accessing and swapping data in an array, this method will likely
  	// not be as efficient as a good array-based sort such as Quicksort.
  	private Node mergeSort(Node list, int size)
  	{
  		if (size > 1)	// check for base case
  		{
  			int mid = size/2;		// cut list in half
  			Node leftList = list;	// Start left half at beginning
  			Node rightList = null;
  			Node temp = list;
  			for (int i = 0; i < mid-1; i++)	// Traverse to starting point of 
  				temp = temp.next;			// right half.  This is some extra
  											// overhead not needed for the array.
  			rightList = temp.next;		// Assign and disconnect right half
  			temp.next = null;
  			
  			// make recursive calls on sublists
  			leftList = mergeSort(leftList, mid);
  			rightList = mergeSort(rightList, size - mid);
  			
  			// Now we merge the lists back together.  The code here looks
  			// complicated but the run-time is what matters more.  The code
  			// complexity is due (mostly) to special cases.  Basically this code
  			// is moving nodes out of the subprogram return lists into the single
  			// sorted list.  The idea is the same as for an array, but, unlike the
  			// array, we do not need to recopy the data.  Thus, in at least this
  			// aspect of the MergeSort algorithm the linked-list version is more
  			// efficient than the array version.
  			temp = null;
  			Comparable leftFront = null;
  			Comparable rightFront = null;
  			boolean done = false;
  			// if either list is null we do not have to merge anything
  			if (leftList == null || rightList == null)
  				done = true;
  			else	// Both lists have at least one item.  Get front of
  			{		// each one.
  				leftFront = (Comparable) leftList.data;
  				rightFront = (Comparable) rightList.data;
  			}
  			Node curr = null;
  			list = null;	// This will be the list we return
  			while (!done)
  			{
  		 		if (leftList == null || rightList == null)
  					done = true;
  				else
  				{
  					// Should next item come from left or right list?
  					int cmp = leftFront.compareTo(rightFront);
  					if (cmp <= 0)
  					{
  						curr = leftList;
  						leftList = leftList.next;
  						curr.next = null;
  						if (leftList != null)
  							leftFront = (Comparable) leftList.data;
  					}
  					else
  					{
    					curr = rightList;
  						rightList = rightList.next;
  						curr.next = null;
  						if (rightList != null)
  							rightFront = (Comparable) rightList.data;
  					}
  					if (list == null)  	// special case for first node -- assign
  					{					// the list variable
  						list = curr;
  						temp = list;
  					}
  					else
  					{
  						temp.next = curr;	// general case -- connect the curr
  						temp = curr;		// node to the list
  					}
  				}
  			}
  			if (leftList != null)		// After loop one of the sides still has
  			{							// at least one node in it.  Append those
  				if (list == null)		// nodes to the end of the list.  Note we
  					list = leftList;    // again do this without making any new
  				else				    // nodes.
  					temp.next = leftList;
  			}
  			else if (rightList != null)
  			{
  				if (list == null)
  					list = rightList;
  				else
  					temp.next = rightList;
  			}	
  			return list;
  		}
  		else return list;
  	}

	private class Node
	{
      private T    data; // Entry in list
      private Node next; // Link to next node

      private Node(T dataPortion)
      {
         data = dataPortion;
         next = null;	
      } // end constructor

      private Node(T dataPortion, Node nextNode)
      {
         data = dataPortion;
         next = nextNode;	
      } // end constructor

      private T getData()
      {
         return data;
      } // end getData

      private void setData(T newData)
      {
         data = newData;
      } // end setData

      private Node getNextNode()
      {
         return next;
      } // end getNextNode

      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
	} // end Node
} // end LListWithSort
