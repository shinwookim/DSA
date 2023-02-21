// CS 0445 Spring 2022
// Demonstration of shallow vs. deep copies of objects.
// See also SBArray.java

public class Example2
{
	public static void main(String [] args)
	{
		SBArray A = new SBArray(10);
		A.add(new StringBuilder("Princess"));
		A.add(new StringBuilder("Roberts"));
		A.add(new StringBuilder("Vizzini"));
		A.add(new StringBuilder("Fezzik"));
   
   		// Make three copies of the SBArray, a shallow copy, a
   		// deeper copy and a deep copy.  See class SBArray to see
   		// the details of how these copies are made.          
		SBArray shallow = new SBArray(A, 0);
		SBArray deeper = new SBArray(A, 1);
		SBArray deep = new SBArray(A, 2);
            
        System.out.println("Initial Data:"); 
		System.out.println("Orig. object:\t" + A.toString());
		System.out.println("Shallow copy:\t" + shallow.toString());
		System.out.println("Deeper copy:\t" + deeper.toString());
		System.out.println("Deep copy:\t" + deep.toString());
             
		// Set a new value in the shallow copy.
		// Since array is shared, this will affect
		// the original object
		shallow.set(1, new StringBuilder("Count"));
			
		// With the deeper and deep copies, the arrays are
		// separate, so these sets will only affect the 
		// specific mutated object
		deeper.set(2, new StringBuilder("Inigo"));
		deep.set(3, new StringBuilder("Prince"));

		System.out.println("\nAfter some sets:"); 	
		System.out.println("Orig. object:\t" + A.toString());
		System.out.println("Shallow copy:\t" + shallow.toString());
		System.out.println("Deeper copy:\t" + deeper.toString());
		System.out.println("Deep copy:\t" + deep.toString());		

		// Mutate some StringBuilders
		
		// This append will affect A, shallow and deeper, since
		// they share the same StringBuilder in that location.
		// However, deep has a separate copy of "Princess" which
		// will not be mutated.
		A.get(0).append("_Buttercup");
		
		// This append will affect A and shallow, but deeper has
		// changed the StringBuilder at that location so it no longer
		// shares the original StringBuilder
		shallow.get(1).append("_Rugen");
		
		// These appends will only affect one object each, since
		// new StringBuilders have been put into those locations
		deeper.get(2).append("_Montoya");
		deep.get(3).append("_Humperdinck");
		
		System.out.println("\nAfter mutating some StringBuilders:"); 	 
		System.out.println("Orig. object:\t" + A.toString());
		System.out.println("Shallow copy:\t" + shallow.toString());
		System.out.println("Deeper copy:\t" + deeper.toString());
		System.out.println("Deep copy:\t" + deep.toString());
	}             
}