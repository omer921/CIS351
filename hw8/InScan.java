// import edu.colorado.collections.DoubleLinkedStack; 
// import edu.colorado.collections.CharLinkedStack; 
import java.util.Scanner;
import java.util.Stack;
import java.io.*;

public class InScan {
  
  ////////////////////////////////////////////////////////////////////////
  /** Scan through the file infix.dat line by line and report on what is
    *   found.
    */
  public static void main(String argv[]) 
    throws IOException 
  {
    Scanner infile = new Scanner(new File("infix.dat")); 
    
    while (inscanLine(infile));
    
  } // end of main ///////////////////////////////////////////////////////
  
  
  ////////////////////////////////////////////////////////////////////////
  // This method scans through a single line of infile and prints a report 
  //  on what it finds.  It returns true if there is more input to read
  //  after the line it just reported on.
  public static boolean inscanLine(Scanner infile) {
    String token;
    int num;
    
    while (infile.hasNext()) {
      token = infile.next();
      switch(Kind.classify(token.charAt(0))) {
        case NUMBER:
          num = Integer.valueOf(token);
          System.out.println("Number:      " + num);
          break;
        case PLUS:     
        case MINUS:     
        case TIMES:     
        case DIVIDE:     
          System.out.println("Operator:    " + token );
          break;
        case SYMBOL:
          System.out.println("Variable:    " + token );
          break;     
        case OPENPAR:
          System.out.println("Open paren:  " + token );
          break;
        case CLOSEPAR:
          System.out.println("Close paren: " + token );
          break;     
        case SPACE:
          System.out.println("\t \t ignoring space" );
          break;
        case ENDEXP:
          System.out.println("    END OF EXPRESSION" );
          infile.nextLine();
          return true;
        case ENDINPUT:
          System.out.println("\tEnd of input, I quit!" );
          return false;
        default:
          System.out.println("\t ignoring strange input: " + token );
          break;
      }
    } // end of while
    
    return false;
  }
  ////////////////////////////////////////////////////////////////////////
}