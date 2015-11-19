import java.io.*;
import java.util.Stack;
import java.util.Scanner;

@SuppressWarnings("unchecked")
public class problems
{
  public static void main(String[] args) throws IOException
  {
    final String inFile = "fox.txt";
    final int MINSIZE   = 5; 
    Stack<String> stak = new Stack();
    TreeBag bag = new TreeBag();
    
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
      inLine.useDelimiter("[\\s,.!?'-]");
      
      // Scan the line for tokens & print the ones of length >= MINSIZE
      while (inLine.hasNext()) {
        str = inLine.next();
        if (str.length()>=MINSIZE) {
          // convert the string to lower case
          String strLower = str.toLowerCase();
          // Print the linenumber and strLower
          
          //stak.push(lineNumber + ": " + strLower);
          StringOcc newS = new StringOcc(strLower, new Node(lineNumber, null));
          
          bag.add(newS);
        }
       
      } // end of the inner while
      lineNumber++;
    }
    /*while(!stak.isEmpty())
        System.out.println(stak.pop());*/
    
     bag.root.printChart(bag.root.depth());
     TWalker it = bag.iterator();
     System.out.println(it.hasNext());
     it.remove();
     System.out.println("after remove "+ it.hasNext());
     
     
     //System.out.println(bag.root.depth());
    //bag.root.preorderPrint( );
  }
 }
