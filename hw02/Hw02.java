import java.io.*;                      
import java.util.*;
import java.lang.Math;

public class Hw02 {
  //////////////////////////////////////////////////////////////////////
  // for Problem 1
  public static double payMonthly(double prin, double apr, int yrs) {
    
    if (apr > 0)
    {
      double n = prin*((apr/12)+((apr/12)/((Math.pow((1+(apr/12)), (yrs*12))) - 1)));
      return n;
    }
    else
    {
      double n = (prin/(yrs*12));
      return n;
    }
    
  }
  public static void testPay(double prin,double apr,int yrs) {
    System.out.println("For a loan of "+prin+" at interest rate "+apr+
                       " (APR) for "+yrs+ " years, ");
    System.out.println("  the monthy payment is: "
         + payMonthly(prin,apr,yrs) + "\n");
  }
  
  //////////////////////////////////////////////////////////////////////
  // for Problem 2
  public static int factIter(int n) {
    int fact = 1;
    while (n>0)
    {
      fact *= n;
      n--;
    }
    return fact;
    
  }
  //////////////////////////////////////////////////////////////////////
  // for Problem 3
  public static int factRec(int n) {
    if (n==0)
    {
     return 1;
    }
    else
    {
      int fact = n * factRec(n-1);
      return fact;
    }
  }
  //////////////////////////////////////////////////////////////////////
  // for Problem 4
  public static int days(int year, int month) {
    if ( (year % 4) == 0)
    switch (month) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        return 31;
      case 9:
      case 11:
      case 4:
      case 6:
        return 30;
      default:
        return 29;
    }
    else
      switch (month) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        return 31;
      case 9:
      case 11:
      case 4:
      case 6:
        return 30;
      default:
        return 28;
    }

  }
  public static void testDays(int year, int month) {
    System.out.println(month+"/"+year+" has " + days(year,month)+" days\n");
  }
    
  //////////////////////////////////////////////////////////////////////
  public static void main(String argv[]) {
    Scanner sc = new Scanner(System.in);
    

    // Problem 1
    System.out.println("Problem 1 output");
    // Tests for problem 1.   E.g.:
     testPay(10000.0,0.08,3);
     testPay(10000.0,0.00,3);
     testPay(10000.0,0,5);
     testPay(10000.0,0,1);
     testPay(10000.0,1,6);
     testPay(10000.0,.05, 10);
    // Add more
     
    // Problem 2
    System.out.println("\nProblem 2 output"); 
    for (int i = 1; i <= 4; i++)
    {
      System.out.println(factIter(i));
    }
    // Tests for problem 2
    
    // Problem 3
    System.out.println("\nProblem 3 output"); 
    for (int i = 1; i <= 4; i++)
    {
      System.out.println(factRec(i));
    }
    // Tests for problem 3
    
    // Problem 4
    System.out.println("Problem 4 output");
    // Tests for problem 4

    while (true) {
      System.out.print("year = ");
      int year = sc.nextInt();
      System.out.print("month = ");
      int month = sc.nextInt();
      if (year < 1753 || month < 1 || month > 12) {
        System.out.println("Silly input, terminating");
        break;
      }
      else
        testDays(year,month);
    }
  }
  //////////////////////////////////////////////////////////////////////
}