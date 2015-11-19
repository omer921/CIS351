import java.io.*;
import java.util.Stack;
import java.util.Scanner;

/** The program  reads through the file fox.txt and prints out 
  * the words (five or more letters long) together with the 
  * line number of the word occurence.
  */ 

public class Words {
  public static void main (String argv[]) throws IOException {
    final String inFile = "fox.txt";
    final int MINSIZE   = 5; 
    // final String inFile = "long.txt";
    //final int MINSIZE   = 7;
    
    Scanner inp = new Scanner(new File(inFile));
    int lineNumber = 1;
    Scanner inLine;
    String str;
    
    // Scan the input and print all words of length >= MINSIZE.
    while (inp.hasNextLine()) {
      // Get the next input line and build a Scanner out of it.
      inLine = new Scanner(inp.nextLine());
      
      // Delimit tokens by whitespace (that is the \\s) 
      // and any of: ',' '.' '!' '?' '-'.
      inLine.useDelimiter("[\\s,.!?-]");
      
      // Scan the line for tokens & print the ones of length >= MINSIZE
      while (inLine.hasNext()) {
        str = inLine.next();
        if (str.length()>=MINSIZE) {
          // convert the string to lower case
          String strLower = str.toLowerCase();
          // Print the linenumber and strLower
          System.out.printf("%5d: %s\n", lineNumber, strLower);
        }
      } // end of the inner while
      lineNumber++;
    } // end of the outer while
  }
}