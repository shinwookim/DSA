// CS 0445 
// Demonstration of the instability of QuickSort vs. the stability of MergeSort.
// To demonstrate this I am sorting the data on more than one field -- this can be
// done (in a really cool way) in Java using Comparators and reflection.  The
// reflection is beyond the scope of this course, but if you want to investigate it
// feel free.  I have included some comments to get you started.

// Note that the People class is listed here but since we are using reflection the
// other class names that we might want to use (Worker, Salaried, Hourly, Student)
// are not in here and will not be automatically compiled when this program is
// compiled. Thus, it is not sufficient to just compile this program -- we must compile
// the individual class files separately.

import java.lang.reflect.*;
import java.util.*;
public class Stability
{
	public static void main(String [] args)
	{
		People [] P1 = new People[10];
		People [] P2 = new People[10];  // make two arrays to test the sorts
		Scanner inScan = new Scanner(System.in);
		System.out.print("Enter another object? (y/n) ");
		String ans = inScan.nextLine();
		int count = 0;   
		while (ans.equals("y") && count < P1.length)
		{
			System.out.print("Class of new object? ");
			String newClass = inScan.nextLine();
			try
			{
				// Using the Class class to create a new instance of
				// the class entered by the user.  Note the exceptions
				// that could occur, as shown below.  This way, any legal
				// class can be created, even ones that were not defined
				// when this code was written.
				Class C = Class.forName(newClass);
				People temp = (People) C.newInstance();  // must be a People to put
										// into this array
				temp.readData(inScan);  // This method must exist for all classes 
										// that we are using
				P1[count] = temp;
				P2[count] = temp;
					// Note that we are putting the same object into both arrays, rather
					// than making two equivalent objects.  If we are planning to mutate
					// these objects we definitely want separate copies, but for the
					// purposes of this handout we are ok with one copy that is
					// stored in both arrays.
				count++;
				System.out.println(temp);
			}
			catch (ClassNotFoundException e)
			{
				System.out.println("Invalid class -- please try again");
			}
			catch (InstantiationException e2)
			{
				System.out.println("Cannot be instantiated -- please try again");
			}
			catch (IllegalAccessException e3)
			{
				System.out.println("Access is forbidden");
			}
			
			System.out.print("Enter another object? (y/n) ");
			ans = inScan.nextLine();
		}
		
		System.out.println("Here is the data: ");
		for (int i = 0; i < count; i++)
			System.out.println(P1[i]);
		
		// Since People is our reference type, we will look at all of the methods
		// in the People class to see which can be used for a Comparator.  This includes
		// all methods that return primitive values and all the return values that are
		// Comparable.  With some more effort we could actually choose the superclass
		// that is "closest" to the objects in the array -- this would allow us to
		// potentially have more methods to choose from if, for example, all of the
		// objects were subclasses of Worker.
		Class sup = null;
		try 
		{
			sup = Class.forName("People");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Problem with People class -- Oh no!");
		}
	
		if (sup != null)
		{
			String methodName = null;
			do
			{
				System.out.println("\nPlease select the method to be used for sorting:");
				Method [] methods = sup.getMethods();
				System.out.print("(");
				for (int i = 0; i < methods.length; i++)
				{
					// Show the user the methods that can be used for comparisons.
					// These include non-void primitive types and any types that
					// are Comparable.  Note that there are methods here that don't seem
					// to exist in People -- this is because the getMethods() method will
					// return all of the methods for a class -- including those that are
					// inherited.  In this case People inherits several methods from class
					// Object.
					Class retClass = methods[i].getReturnType();
					if (retClass.isPrimitive() && (!retClass.getName().equals("void")))
					{
						System.out.print(methods[i].getName() + " ");
					}
					else // See if the method's return type implements Comparable
					{
						boolean comp = false;
						Class [] interf = retClass.getInterfaces();
						for (Class c: interf)
						{
							if (c.getName().equals("java.lang.Comparable"))
								comp = true;
						}
						if (comp)
							System.out.print(methods[i].getName() + " ");
					}
				}
				System.out.println(")");
				methodName = inScan.nextLine();
			
				// As long as the method chosen is valid, sort using that method via
				// a Comparator.  See more details about the comparator in the 
				// GenericComparator class.
				if (!methodName.equals(""))
				{
					MergeQuickComparator.quickSort(P1, 0, count-1, new GenericComparator<People>(methodName,GenericComparator.ASC));
					MergeQuickComparator.mergeSort(P2, 0, count-1, new GenericComparator<People>(methodName,GenericComparator.ASC));
					System.out.println("Here is the QuickSorted data in P1: ");
					for (int i = 0; i < count; i++)
						System.out.println(P1[i].toString());
					System.out.println();
					System.out.println("Here is the MergeSorted data in P2: ");
					for (int i = 0; i < count; i++)
						System.out.println(P2[i].toString());
					System.out.println();
				}
			} while (!methodName.equals(""));
		}
	}

}
