// CS0445 Spring 2022
// Another subclass of People2.  

public class Student2 extends People2
{
       private String major;
       private double gpa;

       public Student2(String s, String d, String major, double gpa)
       {
              super(s, d);
              this.major = new String(major);
              this.gpa = gpa;
       }

       public String toString()
       {
              String firstpart = super.toString();
              return (firstpart + " Major: " + major
                                + " GPA: " + gpa);
       }
}
