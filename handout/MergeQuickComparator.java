// CS 0445 
// QuickSort and MergeSort modified to take a comparator as an argument.  The basic code
// for these was taken from the author's handout (See TextMergeQuick.java).  I have
// removed most of the author's original comments here and added Comparators as the
// means to compare rather than utilizing the Comparable interface.
import java.util.*;
public class MergeQuickComparator
{
	public static final int MIN_SIZE = 5; // for quick sort
	
	// MERGE SORT
	public static <T> void mergeSort(T[] a, int n, Comparator<? super T> c)
	{
		mergeSort(a, 0, n - 1, c);
	} // end mergeSort

	public static <T> void mergeSort(T[] a, int first, int last, Comparator<? super T> c)
	{
	  T[] tempArray = (T[])new Object[a.length];
	  mergeSort(a, tempArray, first, last, c);
	} // end mergeSort
	
	private static <T> void mergeSort(T[] a, T[] tempArray, int first, int last,
	                                         Comparator<? super T> c)
	{
	   if (first < last)
	   {  // sort each half
	      int mid = (first + last)/2;// index of midpoint
	      mergeSort(a, tempArray, first, mid, c);  // sort left half array[first..mid]
	      mergeSort(a, tempArray, mid + 1, last, c); // sort right half array[mid+1..last]

			if (c.compare(a[mid],a[mid + 1]) > 0)      // Question 2, Chapter 9
	     	 	merge(a, tempArray, first, mid, last, c); // merge the two halves
	   //	else skip merge step
	   }  // end if
	}  // end mergeSort
	
	private static <T> void merge(T[] a, T[] tempArray, int first, int mid, int last,
	                                     Comparator<? super T> c)
	{
		// Two adjacent subarrays are a[beginHalf1..endHalf1] and a[beginHalf2..endHalf2].
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid + 1;
		int endHalf2 = last;

		// while both subarrays are not empty, copy the
	    // smaller item into the temporary array
		int index = beginHalf1; // next available location in
								            // tempArray
		for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++)
	   {  // Invariant: tempArray[beginHalf1..index-1] is in order
	   
	      if (c.compare(a[beginHalf1],a[beginHalf2]) <= 0)  // Make sure it is 
	      {  									// <= 0 to be stable
	      	tempArray[index] = a[beginHalf1];
	        beginHalf1++;
	      }
	      else
	      {  
	      	tempArray[index] = a[beginHalf2];
	        beginHalf2++;
	      }  // end if
	   }  // end for

	   // finish off the nonempty subarray

	   // finish off the first subarray, if necessary
	   for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
	      // Invariant: tempArray[beginHalf1..index-1] is in order
	      tempArray[index] = a[beginHalf1];

	   // finish off the second subarray, if necessary
		for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
	      // Invariant: tempa[beginHalf1..index-1] is in order
	      tempArray[index] = a[beginHalf2];
		
	   // copy the result back into the original array
	   for (index = first; index <= last; index++)
	      a[index] = tempArray[index];
	}  // end merge
// -------------------------------------------------------------------------------

// QUICK SORT
	public static <T> void quickSort(T[] array, int n, Comparator<? super T> c)
	{
		quickSort(array, 0, n-1, c);
	} // end quickSort
	
	/** Sorts an array into ascending order. Uses quick sort with
	 *  median-of-three pivot selection for arrays of at least 
	 *  MIN_SIZE elements, and uses insertion sort for other arrays. */
	public static <T> void quickSort(T[] a, int first, int last, Comparator<? super T> c)
	{
	  if (last - first + 1 < MIN_SIZE)
	  {
	    insertionSort(a, first, last, c);
	  }
	  else
	  {
	    // create the partition: Smaller | Pivot | Larger
	    int pivotIndex = partition(a, first, last, c);
	    
	    // sort subarrays Smaller and Larger
	    quickSort(a, first, pivotIndex - 1, c);
	    quickSort(a, pivotIndex + 1, last, c);
	  } // end if
	} // end quickSort

	// 12.17
	/** Task: Partitions an array as part of quick sort into two subarrays
	 *        called Smaller and Larger that are separated by a single
	 *        element called the pivot. 
	 *        Elements in Smaller are <= pivot and appear before the 
	 *        pivot in the array.
	 *        Elements in Larger are >= pivot and appear after the 
	 *        pivot in the array.
	 *  @param a      an array of Comparable objects
	 *  @param first  the integer index of the first array element; 
	 *                first >= 0 and < a.length 
	 *  @param last   the integer index of the last array element; 
	 *                last - first >= 3; last < a.length
	 *  @return the index of the pivot */
	private static <T> int partition(T[] a, int first, int last, Comparator<? super T> c)
	{
	  int mid = (first + last)/2;
	  sortFirstMiddleLast(a, first, mid, last, c);
	  
	  // Assertion: The pivot is a[mid]; a[first] <= pivot and 
	  // a[last] >= pivot, so do not compare these two array elements
	  // with pivot.
	  
	  // move pivot to next-to-last position in array
	  swap(a, mid, last - 1);
	  int pivotIndex = last - 1;
	  T pivot = a[pivotIndex];
	  
	  // determine subarrays Smaller = a[first..endSmaller]
	  // and                 Larger  = a[endSmaller+1..last-1]
	  // such that elements in Smaller are <= pivot and 
	  // elements in Larger are >= pivot; initially, these subarrays are empty

	  int indexFromLeft = first + 1; 
	  int indexFromRight = last - 2; 
	  boolean done = false;
	  while (!done)
	  {
	    // starting at beginning of array, leave elements that are < pivot;
	    // locate first element that is >= pivot; you will find one,
	    // since last element is >= pivot
	    while (c.compare(a[indexFromLeft],pivot) < 0)
	      indexFromLeft++;
	      
	    // starting at end of array, leave elements that are > pivot; 
	    // locate first element that is <= pivot; you will find one, 
	    // since first element is <= pivot
	    while (c.compare(a[indexFromRight],pivot) > 0)
	      indexFromRight--;
	      
	    assert c.compare(a[indexFromLeft],pivot) >= 0 && 
	           c.compare(a[indexFromRight],pivot) <= 0;
	           
	    if (indexFromLeft < indexFromRight)
	    {
	      swap(a, indexFromLeft, indexFromRight);
	      indexFromLeft++;
	      indexFromRight--;
	    }
	    else 
	      done = true;
	  } // end while
	  
	  // place pivot between Smaller and Larger subarrays
	  swap(a, pivotIndex, indexFromLeft);
	  pivotIndex = indexFromLeft;
	  
	  // Assertion:
	  //   Smaller = a[first..pivotIndex-1]
	  //   Pivot = a[pivotIndex]
	  //   Larger = a[pivotIndex+1..last]
	  
	  return pivotIndex; 
	} // end partition

	
	private static <T> void sortFirstMiddleLast(T[] a, int first, int mid, int last,
	                                            Comparator<? super T> c)
	{
	  order(a, first, mid,c); // make a[first] <= a[mid]
	  order(a, mid, last,c);  // make a[mid] <= a[last]
	  order(a, first, mid,c); // make a[first] <= a[mid]
	} // end sortFirstMiddleLast

	/** Task: Orders two given array elements into ascending order
	 *        so that a[i] <= a[j].
	 *  @param a  an array of Comparable objects
	 *  @param i  an integer >= 0 and < array.length
	 *  @param j  an integer >= 0 and < array.length */
	private static <T> void order(T[] a, int i, int j, Comparator<? super T> c)
	{
	  if (c.compare(a[i],a[j]) > 0)
	    swap(a, i, j);
	} // end order

  /** Task: Swaps the array elements a[i] and a[j].
   *  @param a  an array of objects
   *  @param i  an integer >= 0 and < a.length
   *  @param j  an integer >= 0 and < a.length */
  private static void swap(Object[] a, int i, int j)
  {
    Object temp = a[i];
    a[i] = a[j];
    a[j] = temp; 
  } // end swap

  public static <T> void insertionSort(T[] a, int n, Comparator<? super T> c)
	{
		insertionSort(a, 0, n - 1, c);
	} // end insertionSort

  public static <T> void insertionSort(T[] a, int first, int last, Comparator<? super T> c)
	{
		int unsorted, index;
		
		for (unsorted = first + 1; unsorted <= last; unsorted++)
		{   // Assertion: a[first] <= a[first + 1] <= ... <= a[unsorted - 1]
		
			T firstUnsorted = a[unsorted];
			
			insertInOrder(firstUnsorted, a, first, unsorted - 1, c);
		} // end for
	} // end insertionSort

  private static <T> void insertInOrder(T element, T[] a, int begin, int end,
                                        Comparator<? super T> c)
	{
		int index;
		
		for (index = end; (index >= begin) && (c.compare(element,a[index]) < 0); index--)
		{
			a[index + 1] = a[index]; // make room
		} // end for
		
		// Assertion: a[index + 1] is available
		a[index + 1] = element;  // insert
	} // end insertInOrder

}
