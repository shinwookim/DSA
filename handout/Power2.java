// CS 0445 Spring 2022
// Another demonstration of the recursive Power function.  This time we compare
// a version with 2 recursive calls to the original (with only 1 call).  Note the
// extreme difference.  This is because we lose the power of "divide and conquer" if
// we do both recursive calls rather than just one.

import java.math.BigInteger;
import java.io.*;

public class Power2
{
    private BigInteger pow3mults, pow3BadMults;
    public static final BigInteger zero = BigInteger.ZERO;
    public static final BigInteger one = BigInteger.ONE;
    public static final BigInteger two = new BigInteger("2");

    public Power2() throws IOException
    {
          pow3mults = zero;
          pow3BadMults = zero;
    
          BufferedReader indata = new BufferedReader(
                         new InputStreamReader(System.in));
          System.out.println("Enter the integer base: ");
          BigInteger base = new BigInteger(indata.readLine());
          System.out.println("Enter the integer exponent: ");
          BigInteger exp = new BigInteger(indata.readLine());
          BigInteger ans;
       
          ans = Pow3(base, exp);
          System.out.println(base + "^" + exp + " div/conq: " + ans);
          System.out.println("Requires " + pow3mults + " mults ");

          ans = Pow3Bad(base, exp);
          System.out.println(base + "^" + exp + " 2 calls: " + ans);
          System.out.println("Requires " + pow3BadMults + " mults ");
    }

    public static void main (String [] args) throws IOException
    {
          Power2 P = new Power2();
    }

	// Divide and conquer function to calculate X^N
    public BigInteger Pow3(BigInteger X, BigInteger N)
    {
          if (N.compareTo(zero) == 0)
                return new BigInteger("1");
          else
          {
                BigInteger NN = N.divide(two);
                BigInteger temp = Pow3(X, NN);
                pow3mults = pow3mults.add(one);
                if ((N.mod(two)).compareTo(zero) == 0) // N is even
                {
                    return (temp.multiply(temp));      // temp * temp
                }
                else                                   // N is odd
                {
                    pow3mults = pow3mults.add(one);
                    BigInteger MM = temp.multiply(temp);
                    return (MM.multiply(X));           // X * temp * temp
                }
          }
    }

	// Attempt at Divide and conquer but with 2 recursive calls.  This version
	// is very bad, as its name suggests!  To envision why this is bad, think of
	// how the calls propagate as the recursion progresses.  With a single call the
	// progression is linear but with two recursive calls in each method the
	// calls grow exponentially, doubling at each level.
    public BigInteger Pow3Bad(BigInteger X, BigInteger N)
    {
          if (N.compareTo(zero) == 0)
                return new BigInteger("1");
          else
          {
                BigInteger NN = N.divide(two);
                BigInteger temp1 = Pow3Bad(X, NN);  // Make one call
                BigInteger temp2 = Pow3Bad(X, NN);	// Make second, identical call
                pow3BadMults = pow3BadMults.add(one);
                if ((N.mod(two)).compareTo(zero) == 0) // N is even
                {
                    return (temp1.multiply(temp2));      // temp1 * temp2
                }
                else                                   // N is odd
                {
                    pow3BadMults = pow3BadMults.add(one);
                    BigInteger MM = temp1.multiply(temp2);
                    return (MM.multiply(X));           // X * temp1 * temp2
                }
          }
    }

}
