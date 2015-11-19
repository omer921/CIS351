import java.util.Stack;
import java.util.Scanner;
import java.io.*;

public class Rev {
  // Reads in a sequence of doubles and prints them in reverse order.
  // Uses a Scanner for input and a Stack<Double> for the reversal.
  // Input file: doubles.dat
  public static void main(String argv[]) 
    throws FileNotFoundException 
  {
    Scanner infile = new Scanner(new File("doubles.dat"));
    Stack<Double> stk = new Stack<Double>();
    double val;
    
    System.out.println("Read in:");
    while (infile.hasNext()) {
      val = Double.valueOf(infile.next());
      System.out.print(val + "  ");
      stk.push(val);
    }

    System.out.println("\nThe reverse:"); 
    while (!stk.empty()) 
      System.out.print(stk.pop() + "  ");
    System.out.println();
  }
    
} 
    
    
    