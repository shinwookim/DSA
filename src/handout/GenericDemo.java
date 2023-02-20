// CS 0445 Spring 2022
// More detailed example demonstrating Java generics and how the "old style"
// and parameterized generics differ.  All four of the method calls below
// work.  However, warnings are generated for all but the original version.
// To see the warnings, compile this program as follows:

// javac -Xlint:unchecked GenericDemo.java

// Generics are quite complex in Java and you should not feel that you need
// to completely understand all aspects of them right away.  We will revisit
// generics later in the term (and the text does as well).  The important thing
// to take from this is that as currently defined, Java generics allow methods
// to work for arbitrary, specific types.

public class GenericDemo
{
      public static void main(String [] args)
      {
             People [] P, orig; 
             People2 [] P2, orig2; 
             orig = new People[6];    P = new People[6];
             orig2 = new People2[6];    P2 = new People2[6];

             orig[0] = new People("Herb", "1/1/1972");
             orig[1] = new Worker("Bart", "3/18/1959", 85000);
             orig[2] = new Worker("Zeke", "6/9/1961", 45000);
             orig[3] = new Student("Bill", "4/3/1985", "CS", 3.5);
             orig[4] = new Student("Mary", "5/8/1983", "English", 3.8);
             orig[5] = new People("Alice", "11/21/1976");
             
             // Class People2 implements generics in the "old" way.  See more
             // info in People2.java
             orig2[0] = new People2("Herb", "1/1/1972");
             orig2[1] = new Worker2("Bart", "3/18/1959", 85000);
             orig2[2] = new Worker2("Zeke", "6/9/1961", 45000);
             orig2[3] = new Student2("Bill", "4/3/1985", "CS", 3.5);
             orig2[4] = new Student2("Mary", "5/8/1983", "English", 3.8);
             orig2[5] = new People2("Alice", "11/21/1976");
						
             for (int i = 0; i < 6; i++)
             {
                 System.out.println(orig[i]);
                 P[i] = orig[i];
                 P2[i] = orig2[i];
             }

             System.out.println();

			// First we will call the parameterized sort method on both the "new style"
			// Comparable<T> array and also the old style Comparable array.  The second
			// call below will generate a warning, saying the call is "unsafe".
             SortArrayVersions.selectionSort(P, P.length);
             SortArrayVersions.selectionSort(P2, P2.length);
             	
			 System.out.println("Sorted Data: Parameterized in class and method");
             for (int i = 0; i < 6; i++)  { 
                 System.out.println(P[i]);  // Print out sorted item
                 P[i] = orig[i];	// Set back to original for next case
             }
             System.out.println();
             
             System.out.println("Sorted Data: Parameterized in method but not class");
             for (int i = 0; i < 6; i++)  { 
                 System.out.println(P2[i]);  // Print out sorted item
                 P2[i] = orig2[i];	// Set back to original for next case
             }
             System.out.println();
                 
             // Calling the "old style" generic method with both arrays.  No warning
             // is generated here, but a warning is generated in the selectionSortB code
             // for the sort method itself, specifically at the call of the compareTo()
             // method.
             SortArrayVersions.selectionSortB(P, P.length); 
        	 SortArrayVersions.selectionSortB(P2, P2.length);

			 System.out.println("Sorted Data: Parameterized in class but not method");
   			 for (int i = 0; i < 6; i++)  { 
                 System.out.println(P[i]);
             }      
             System.out.println();
             
             System.out.println("Sorted Data: Not parameterized");
             for (int i = 0; i < 6; i++)  {
                 System.out.println(P2[i]);
             }      
             System.out.println();
             
      }
}
