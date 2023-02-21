// CS 0445 Spring 2022
// Subclass of People2 (which uses "old" Comparable) rather than People
// -- otherwise identical to Worker

public class Worker2 extends People2
{
       private double salary;

       public Worker2(String s, String d, double salary)
       {
              super(s, d);    
              this.salary = salary;  
       }

       public String toString() 
       {
              String firstpart = super.toString();  
              return (firstpart + " Salary: " + salary);
       }

       public void setSalary(double newsal)  
       {                           
              salary = newsal;     
       }                            
}
