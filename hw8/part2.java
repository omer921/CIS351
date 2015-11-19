import java.util.Scanner;
import java.util.Stack;
import java.io.*;

@SuppressWarnings("unchecked")
public class part2
{
  public static void main(String[] args) throws IOException 
  {
    {
      Scanner infile = new Scanner(new File("infix.dat")); //infile is the scanner object that reads from infix.dat
      
      System.out.println(inscanLine(infile)); //prints the output of inscanLine
    }
  }
    
    public static String inscanLine(Scanner infile) {
      String ans = "";
      String token;
      int num;
      Stack stak = new Stack(); 
      while (infile.hasNext()) { //while it still has something to read in it will loop
        token = infile.next(); //sets whatever was read in to token
      switch(Kind.classify(token.charAt(0))) { //makes a switchstatement according to the type
        case NUMBER:
          num = Integer.valueOf(token); //if it is a number then it will add it to the string ans
          ans += num + " ";
          break;
        case PLUS:     
        case MINUS:     
        case TIMES:     
        case DIVIDE:     
          stak.push(token); //if it is + - / * then is will be pushed into stak
          break;
        case SYMBOL:    
        case OPENPAR: //if it is an ( it will do nothing
          break;
        case CLOSEPAR: // but if it is ) then it will pop a + / - * off the stak and will add it to ans
            ans +=stak.pop() + " ";
          break;     
        case SPACE:
          break;
        case ENDEXP: //if it is an end of an expression it will add a new line character to ans to show that there is a new expression
          infile.nextLine();
          ans += "\n";
          break;
        case ENDINPUT:
          break;
        default:
          break;
      }
      }
      return ans; //returns the expression
    }
  }
    