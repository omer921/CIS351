import java.util.Scanner;
import java.util.Stack;
import java.io.*;

public class part1
{
  public static void main(String[] args)
    throws IOException 
  {
    postscan("postfix.dat"); //calling the postscan method with the name of the file
    
  }
  public static void postscan(String fileName) 
    throws FileNotFoundException 
  {
    Scanner infile = new Scanner(new File(fileName));//creating a scanner object to take input from the file
    String token; // token will be the string that is used to identify the type of input
    Stack<Double> stak = new Stack(); //creating a new stack to store the numbers
    String ans = ""; //string that is used to show the answer at the end
    while (infile.hasNext()) { //loops until there is no more input left
      token = infile.next();//stores the input in the string
      double first, second;
      switch (Kind.classify(token.charAt(0))) { //makes a switch statement to choose what type of input
        case NUMBER: //if it is a number it will push it into the stack 
          System.out.printf("Number pushed: %10.4f\n", Double.parseDouble(token));
          stak.push(Double.parseDouble(token));//it also converts it from a string to a double by using parseDouble()
          break;
        case PLUS:
          second = stak.pop();
          first = stak.pop();
          stak.push(first+second); //if its a + then it will pop off the first two numbers add them then store them back in the stack
          break;
        case MINUS:
          second = stak.pop();
          first = stak.pop();
          stak.push(first - second); //if its a - then it will pop off the first two numbers subtract them then store them back in the stack
          break;
        case TIMES:
           second = stak.pop();
          first = stak.pop();
          stak.push(first * second); //if its a * then it will pop off the first two numbers multiply them then store them back in the stack
          break;
        case DIVIDE:
          second = stak.pop();
          first = stak.pop();
          stak.push(first / second); //if its a / then it will pop off the first two numbers divide the "first" variable by "second" 
                                     //then store them back in the stack
          break;
        case ENDEXP:
          System.out.println("   END OF EXPRESSION");
          ans += stak.pop() + "\n"; //program realizes that this is the end of the expression so it adds it 
                                    //to the string ans so it can be printed all at once at the end
          break;
        default:
          System.out.println("\tEnd of input, I quit!");
          System.out.println(ans);// quits the loop as well as prints the ans variable to the screen
          return;
      }
    } 
  }
}