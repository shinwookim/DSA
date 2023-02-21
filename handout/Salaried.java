// Subclass of Worker
import java.util.Scanner;
public class Salaried extends Worker
{		
	public void readData(Scanner inScan)
	{
		super.readData(inScan);
		System.out.print("Salary? ");
		salary = Double.parseDouble(inScan.nextLine());
	}

}
