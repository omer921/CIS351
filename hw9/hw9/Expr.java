import java.util.*;

/******************************************************************************
  * This is a class to represent expression trees.
  * 
  * <p><b>Class invariants:</b>
  * <ol type=I>
  * <li>  <!-- Invariant I. -->
  *       An Expr represents the number num when tag = space (i.e., ' '), 
  *       in which case both left and right are null.
  * <li>  <!-- Invariant II. -->
  *       An Expr represents the variable tag when tag is a lowercase letter, 
  *       in which case num = 0.0 and both left and right are null.
  * <li>  <!-- Invariant III. -->
  *       An Expr represents ( e1 op e2 ) (where op is one of +, -, *, /)
  *       when tag = the character op, in which case 
  *       <ul>
  *         <li> num = 0.0,
  *         <li> left is a reference to a representation of e1, and 
  *         <li> right is a reference to a representation of e2.
  *       </ul>
  * </ol>
  * 
  * @author I forgot to add my name.  So, please Mr. Grader, take some points off.
  *
  * @version
  *   Oct 29, 2014
  ****************************************************************************/

public class Expr {
  // Instance variables
  private char   tag;
  private double num;
  private Expr   left;
  private Expr   right;
  
  /////////////////////////////////////////////////////////////////////////////
  // Constructors
  /** Builds an Expr for number <code>n</code>
    *  @param n  the number for the Expr
    **/
  public Expr(double n) {
    tag = ' ';  num = n;  left = right = null;
  }
  
  /** Builds an Expr for variable var
    *  @param var  the variable tag (one of 'a', ... , 'z')
    **/
  public Expr(char var) {
    if (var < 'a' || 'z' < var) 
      throw new IllegalArgumentException(
                                         "A variable expression must a lowercase letter, not "+var);
    tag = var;  num = 0.0;  left = right = null;
  }
  
  /** Builds an Expr with operator <code>op</code>, left child 
    *    <code>lft</code>, and right child <code>rght</code>.
    *  @param op   the operator
    *  @param lft  the left child
    *  @param rght the right child
    **/
  public Expr(Expr lft, char op, Expr rght) {
    if (!isOp(op)) throw new IllegalArgumentException(
                                                      "A operator must +, -, *, or /,  not "+op);
    tag = op;  num = 0.0;  left = lft;  right = rght;
  }
  
  /** Construct an expression tree from an infix expression.
    * @param exp   an infix expression string
    *  <p> <b><i>Important:</i></b> In the String <code>exp</code>, parens, variables, numbers, and operators
    *    should be surrounded with spaces. 
    *    If you omit these spaces, errors will result.
    * <p>
    *    E.g., "( ( 2 + 3 ) *  4 )" is OK, but each of "((2+3)*4)", "(( 2 + 3 ) * 4 )", 
    *    and "( ( 2 +3 ) * 4 )" will cause an error. 
    **/
  public Expr(String exp) {
    Expr ans = readTree(new Scanner(exp));
    tag = ans.tag;  num = ans.num;
    left = ans.left;  right = ans.right;
  }
  
  /** Helper method for the constructor above */
  private static Expr readTree(Scanner inscan) {
    String token = inscan.next();
    char first = token.charAt(0);
    // check for a variable
    if ('a'<= first && first <= 'z') 
      return new Expr(first);
    // check for a number
    else if ('0' <= first && first <= '9') 
      return new Expr(Double.valueOf(token));
    // Check for an expression of the form "( e1 op e2 )"
    else if (first == '(') {
      Expr e1 = readTree(inscan);
      char op = (inscan.next()).charAt(0);
      Expr e2 = readTree(inscan);
      inscan.next(); // reads past the ')'
      return new Expr(e1,op,e2);
    }
    else
      throw new IllegalArgumentException("Strange input: "+token);
  }
  
  /////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////////////
  // Instance Methods (provided)
  
  /** Tests if the node is a leaf.
    * @return true iff the node is a leaf
    */
  public boolean isLeaf()   { return (left==null)&&(right==null); }
  
  /** Tests if the node represents a variable.
    * @return true iff the node represents a variable
    */
  public boolean isVar()    { return ('a'<=tag)&&(tag<='z'); }
  
  /** Tests if the node represents a number.
    * @return true iff the node represents a number.
    */
  public boolean isNum()    { return (tag==' '); }
  
  /** An equality test for Expr's.
    * @param obj   the reference object with which to compare.
    * @return true iff <code>this</code> object is the same as the <code>obj</code> argument
    */
  public boolean equals(Object obj) {
    if ( (obj == null) || !(obj instanceof Expr) ) return false;
    if ( this == obj ) return true;
    Expr that = (Expr)obj;
    if (this.isLeaf()) 
      return (     that.isLeaf()
                && that.tag == this.tag 
                && that.num == this.num  );
    else 
      return (     !that.isLeaf()
                && that.tag == this.tag 
                && left.equals(that.left) 
                && right.equals(that.right)  );    
  }
  
  /** Constructs a string representation of this Expr.
    * @return a string representation of this Expr
    */ 
  public String toString() {
    return this.toInfix(); // This won't work until toFix does.
  }
  /** Construct an expression tree from a postfix expression.
    * @param exp   a postfix expression string
    *  <p> <b><i>Important:</i></b> In the String <code>exp</code>, parens, variables, numbers, and operators
    *    should be surrounded with spaces. 
    *    If you omit these spaces, errors will result.
    * <p>
    *    E.g., "2 3 + 4 *" is OK, but both of "2 3+ 4 +" and "2 3 + 4+"
    *    will cause an error. 
    **/
  public static Expr fromPostfix(String exp) {
    Scanner inscan = new Scanner(exp);
    Stack<Expr> stk = new Stack<Expr>();
    while (inscan.hasNext()) {
      String token = inscan.next();
      char first = token.charAt(0);
      if ('0'<=first && first <= '9') 
        stk.push(new Expr(Double.valueOf(token)));
      else if ('a'<= first && first <= 'z') 
        stk.push(new Expr(first));
      else if (isOp(first)) {
        Expr e2 = stk.pop();
        Expr e1  = stk.pop();
        stk.push(new Expr(e1,first,e2));
      }
      else throw new IllegalArgumentException("Strange input: "+token);
    }
    return stk.pop();
  }
  
  /////////////////////////////////////////////////////////////////////////////  
  /////////////////////////////////////////////////////////////////////////////
  // Instance Methods (for the homework)
  
  /** Evaluates an expression tree.
    * <p> &nbsp; &nbsp; &nbsp; <strong><i><font size=4, color="red">Part of the homework!</font></i></strong>
    * @param env  a table of variable values
    * @return     the value of the expression
    **/
  public double eval(double[] env) {
    //System.out.println("eval: Please fix me"); // FIX ME
    double ans = 0;
    if (this.isLeaf())
    {
      if(this.isVar())
        return env[letterOrd(tag)];
      else
        return num;
    }
    else if (isOp(this.tag))
    {
      switch (tag){
        case '+':
          ans = (left.eval(env) + right.eval(env));
          break;
        case '-':
          ans = (left.eval(env) - right.eval(env));
          break;
        case '/':
          ans = (left.eval(env) / right.eval(env));
          break;
        case '*':
          ans = (left.eval(env) * right.eval(env));
          break;
      }
    }
    return ans; 
  }
  
  
  /** Constructs a simplification of the expression.
    * <p> &nbsp; &nbsp; &nbsp; <strong><i><font size=4,  color="red">Part of the homework!</font></i></strong>
    * @return the simplified Expr
    **/
  public Expr simplify() {
    //System.out.println("simplify: Please fix me"); // FIX ME ************************************************************
    if (this.isLeaf())
      return this;
    else if (this.isOp(tag))
    {
      left = left.simplify();
      right = right.simplify();
      if (right.isNum() && left.isNum())
      {
        if (right.isNum() && left.isNum())
        {
          switch (tag)
          {
            case '+': 
              return new Expr(left.num + right.num);
            case '-':
              return new Expr(left.num - right.num);
            case '/':
              return new Expr(left.num / right.num);
            case '*':
              return new Expr(left.num * right.num);
          }
        }
        else
        {
          return new Expr(left.simplify() + " " + tag + " " + right.simplify()); 
        }
      }
    }
    return (this); 
  }
  
  /** Substitute Expr <code>e2</code> for variable <code>v</code> in <code>e1</code>.
    * <p> &nbsp; &nbsp; &nbsp; <strong><i><font size=4,  color="red">Part of the homework!</font></i></strong>
    * @param e1 the expression to be substituted into
    * @param v the variable to be substituted for
    * @param e2 the expression to be substituted for v
    * @return the result of doing the substitution
    */
  public static Expr subst(Expr e1, char v, Expr e2) {
    //System.out.println("subst: Please fix me");  // FIX ME  ***************************************************************************
    if (e1.isNum())
    {
      //System.out.println("in if");
      if (e1.left != null)
        e1.left = subst(e1.left,v,e2);
      if (e1.right != null)
        e1.right = subst(e1.right,v,e2);
      return e1;
    }
    else if (e1.tag == v)
    {
      //System.out.println("in if else");
      if (e1.left != null)
        e1.left = subst(e1.left,v,e2);
      if (e1.right != null)
        e1.right = subst(e1.right,v,e2);
      return e2; 
    }
    else{
      //System.out.println("in else");
      if (e1.left != null)
        e1.left = subst(e1.left,v,e2);
      if (e1.right != null)
        e1.right = subst(e1.right,v,e2);
      return e1; 
    }
  }
  
  /** Converts the expression tree to the infix form of the expression.
    * <p> &nbsp; &nbsp; &nbsp; <strong><i><font size=4,  color="red">Part of the homework!</font></i></strong>
    * @return a String consisting of the infix form of the expression
    **/
  
  public String toInfix() {
    //System.out.println("toInfix: Please fix me");
    String ans = "";
    if (this.isLeaf())
    {
      if (this.isVar())
        return (tag + " ");
      else
        return (num + " ");
    }
    else if (this.isVar())
      return (this.tag + " (" + left.toInfix() + " (" + right.toInfix() +"");
    else if (this.isNum())
      return (this.num + " (" + left.toInfix() + " (" + right.toInfix() +"");
    else
    {
      //System.out.println("here with outside" + ans);
      ans = "( " + left.toInfix() + " " + this.tag + " " + right.toInfix() + " )"; 
    }
    //System.out.println(ans);
    return ans;
  }
  
  /** Converts the expression tree to a postfix form of the expression.
    * <p> &nbsp; &nbsp; &nbsp; <strong><i><font size=4,  color="red">Part of the homework!</font></i></strong>
    *  @return  a String consisting of the postfix form of the expression
    **/
  public String toPostfix() {
    //System.out.println("toInfix: Please fix me");
    String ans2 = "";
    if (this.isLeaf())
    {
      if (this.isVar())
        return (tag + " ");
      else
        return (num + " ");
    }
    else if (this.isVar())
      return (this.tag + " " + left.toPostfix() + " " + right.toPostfix());
    else if (this.isNum())
      return (this.num + " " + left.toPostfix() + " " + right.toPostfix());
    else
    {
      //System.out.println("here with outside" + ans);
      ans2 = left.toPostfix() + " " + right.toPostfix() + " " + this.tag ; 
    }
    //System.out.println(ans2);
    return ans2; 
  }  
  
  
  /////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////////////
  // Utilities (provided)
  /** Tests if a character <code>c</code> is one of '+', '-', '*', '/'
    * @param c  the char to be tested
    * @return true iff c is one of +,-,*,/
    */ 
  public static boolean isOp(char c) {
    return ("+-*/").indexOf(c) >=0;
  }  
  /** Converts 'a' &rarr; 0, 'b' &rarr; 1, &hellip; , 'y' &rarr; 24, 'z' &rarr; 25
    * @param c  the char to be converted
    * @return the character code
    */ 
  public static int letterOrd(char c) {
    return ( (int)c - (int)'a');
  }  
  
  /** Checks that the Expr satisfies the class invariants.
    * For debugging.
    */
  public void checkTreeProps() {
    if (isLeaf()) {
      if (!isVar() && !isNum())      
        System.out.println("Problem: Bad leaf node with tag "+tag);
      return; 
    }
    if (!isOp(tag))
      System.out.println("Problem: Interior node with bad label: " + tag);
    
    if (left==null) 
      System.out.println("Problem: Interior node with label " + 
                         tag + " has a null left child");
    else
      left.checkTreeProps();
    
    if (right==null) 
      System.out.println("Problem: Interior node with label " + 
                         tag + " has a null right child");
    else
      right.checkTreeProps();
  }
  
}