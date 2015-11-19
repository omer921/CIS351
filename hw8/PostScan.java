// import edu.colorado.collections.DoubleLinkedStack; 
// import edu.colorado.collections.CharLinkedStack; 
import java.util.Scanner;
import java.util.Stack;
import java.io.*;

public class PostScan {
  
  ////////////////////////////////////////////////////////////////////////
  public static void main(String argv[]) 
    throws IOException 
  {
    postscan("postfix.dat");
  } // end of main ///////////////////////////////////////////////////////
  
  ////////////////////////////////////////////////////////////////////////
  // This method scans through the file ``postfix.dat'' and reports on
  // what it finds.
  public static void postscan(String fileName) 
    throws FileNotFoundException 
  {
    Scanner infile = new Scanner(new File(fileName));
    String token;
    
    for(;;) {
      token = infile.next();
      switch (Kind.classify(token.charAt(0))) {
        case NUMBER: 
          System.out.printf("Number: %10.4f\n", Double.parseDouble(token));
          break;
        case PLUS:
        case MINUS:
        case TIMES:
        case DIVIDE:
          System.out.println("Operator:   " + token);
          break;
        case ENDEXP:
          System.out.println("   END OF EXPRESSION");
          break;
        default:
          System.out.println("\tEnd of input, I quit!");
          return;
      }
    } 
  } // end postscan //////////////////////////////////////////////////////
  
  ////////////////////////////////////////////////////////////////////////
}