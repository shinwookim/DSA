// Subclass of Worker
import java.util.Scanner;
public class Hourly extends Worker
{
	private double wage;
	private int hours;
		
	public void readData(Scanner inScan)
	{
		super.readData(inScan);
		System.out.print("Hourly Wage? ");
		wage = Double.parseDouble(inScan.nextLine());
		System.out.print("Hours Per Year? ");
		hours = Integer.parseInt(inScan.nextLine());
		salary = wage * hours;
	}

	public String toString()
	{
		String firstpart = super.toString(); 
		return (firstpart + " Hourly Wage: " + wage + " Hours: " + hours);
	}

	public double getWage()
	{
		return wage;
	}
	
	public int getHours()
	{
		return hours;
	}
}
