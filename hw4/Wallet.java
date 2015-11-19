// File: Wallet.java
/******************************************************************************
  * A <CODE>Wallet</CODE> keeps track of a collection of bills of US currency.
  *
  * @author
  *   I forgot to add my name, so please take off some points Mr. Grader. 
  * @version
  *   3 Vendemiaire CCXXIII
  *****************************************************************************/

public class Wallet {
  // Class Invariants
  //   bills[i] = the number of $denom[i]-bills in the Wallet (i=0,..,6)
  //   E.g. bills[4] = the number of $20-bills in the Wallet (since denom[4]=20).
  /////////////////////////////////////////////////////////////////////////////
  // static variables
  private static int[] denom = {1,2,5,10,20,50,100}; // US bill denominations
  /////////////////////////////////////////////////////////////////////////////
  // instance variables
  private int [] bills;
  
  /////////////////////////////////////////////////////////////////////////////
  // Constructor
  /** Builds a fresh, empty Wallet
    */
  public Wallet() {
    bills = new int[denom.length];
  }
  
  /////////////////////////////////////////////////////////////////////////////
  /** Computes the index of a given denomination.
    * In detail, we have map(1)=0, map(2)=1, map(5)=3, map(10)=4,
    * map(20)=5, map(50)=6, map(100)=7, and map(any thing else)=-1.
    *
    * @param d a denomination
    * @return the index of d, if d is not in denom, -1 is returned.
    */
  private int map(int d) {
    int ans = -1;
    for (int i = 0; i < denom.length; i++)
    {
      if (d == denom[i])
      {
        ans = i;
      }  
    }
    return ans;
    
  }
  
  /////////////////////////////////////////////////////////////////////////////
  /** Computes the total amount of cash in this Wallet.
    * <p>
    * <i>Example:</i> If  Wallet <code>w</code> contains a $5 bill 
    *    and three $20 bills, and nothing else, then <code>w.amount()</code>
    *    returns <code>65</code> (<code>= 1*5 + 3*20</code>).
    * @return the dollar value of the cash in this Wallet.
    */ 
  public int amount() { 
    int amount = 0;
    for (int bill : bills)
      amount = amount + bill;
    return amount;
  }
  
  /////////////////////////////////////////////////////////////////////////////
  /** Returns the number of bills, of any denomination, in this Wallet.
    * <p>
    * <i>Example:</i> If Wallet <code>w</code> contains a $5 bill
    *    and three $20 bills, and nothing else, then
    *    <code>w.countBills()</code> returns <code>4</code>.
    *  @return the number of bills, of any denomination, in this Wallet
    */
  public int countBills() {
    int num=0;
    for (int j = 0; j < denom.length; j++)
    {
      for (int i = 0; i < bills.length; i++)
      {
        if (bills[i] > denom[j])
          num++;
      }
    }
    return num;
  }
  
  
  /////////////////////////////////////////////////////////////////////////////
  /** Returns the number of bills of a given denomination in this Wallet.
    * <p>
    * <i>Example:</i> If Wallet <code>w</code> contains a $5 bill and
    *    three $20 bills, and nothing else, then
    *    <code>w.countBills(5)</code> returns <code>1</code>,
    *    <code>w.countBills(20)</code> returns <code>3</code>, and
    *    <code>w.countBills(17)</code> returns 0.
    *
    * @param d the denomination of bills to count
    * @return the number of bills of denomination <code>d</code> in this Wallet
    */
  public int countBills(int d) {
    int amount=0;
    for (int bill : bills)
    {
      if (d == bill)
        amount++;
    }
    return amount;
  }
  
  /////////////////////////////////////////////////////////////////////////////
  /** Adds a bill of a given denomination to this Wallet.
    * If d is in {1,2,5,10,20,50,100}, a denomination-d bill is added
    * to this Wallet and true is returned; otherwise, this Wallet is 
    * unchanged and false is returned.
    * <p>
    *   <i>Example:</i> <code>w.add(20)</code> would add a 20-dollar bill to 
    *   <code>w</code> and return <code>true</code>.
    *   <br>
    *   <i>Example:</i>
    *   <code>w.add(3)</code> would leave <code>w</code> unchanged and 
    *   return <code>false</code>.
    * 
    * @param d a denomination
    * @return a boolean flag indicating if the bill was successfully added to 
    * this Wallet. 
    */
  public boolean add(int d) {
    boolean finals = false;
    for (int i = 0; i < denom.length; i++)
    {
      if (d == denom[i])
      {
        for (int j = 0; j < bills.length; j++)
        {
          if (0 == bills[j])
          {
            bills[j] = d;
            finals = true;
            break; 
          }
        }
      }
    }
    return finals;
  }
  
  
/////////////////////////////////////////////////////////////////////////////
  /** Removes a bill of a given denomination to this Wallet.
    * If d is in {1,2,5,10,20,50,100} <b>and</b> there is a $d bill in this
    * Wallet, then a $d bill is removed from this Wallet and true is returned; 
    * otherwise,  the Wallet is unchanged and false is returned.
    * <p>
    * <i>Example:</i> If Wallet <code>w</code> contains a $5 bill
    *    and three $20 bills, and nothing else, then
    *    <code>w.remove(5)</code> removes the $5 bill and returns
    *    true, whereas <code>w.remove(10)</code> leaves
    *    <code>w</code> unchanged and returns <code>false</code>.
    * 
    * @param d a denomination
    * 
    * @return a boolean flag indicating if the bill was successfully
    *         removed from this Wallet. 
    */
  public boolean remove(int d) {
    boolean finals = false;
    for (int i = 0; i < denom.length; i++)
    {
      if (d == denom[i])
      {
        for (int j = 0; j < bills.length; j++)
        {
          if (d == bills[j])
          {
            bills[j] = 0;
            finals = true;
            break; 
          }
        }
      }
    }
    return finals;
  }
  
  
  /////////////////////////////////////////////////////////////////////////////
  /** Transfers the contents of a Wallet w to this Wallet.
    *   The source Wallet w is left empty.
    * <p>
    *   <i>Example:</i> If Wallet <code>wTo</code> contains a $5 bill
    *   and three $20 bills and Wallet <code>wFrom</code> contains
    *   four $1 bills and a $10 bill, then after
    *   <code>wTo.transfer(wFrom)</code>, Wallet <code>wTo</code>
    *   contains four $1 bills, one $5 bill, one $10 bill, and three
    *   $20 bills, and Wallet <code>wFrom</code> contains no bills.
    * @param w the source Wallet 
    */
  public void transfer(Wallet w) {
    for (int i = 0 ; i < bills.length; i++)
      w.add(bills[i]);
  }
  
  
  /////////////////////////////////////////////////////////////////////////////
  /** Removes an amount a from this Wallet, if possible.
    * <p>
    * <i>Examples:</i> Suppose Wallet <code>w</code> contains a $5 bill and 
    *   three $20 bills.  Then both <code>w.payout(345)</code> and 
    *   <code>w.payout(10)</code> would return false and leave <code>w</code>
    *   unchanged.  Whereas, <code>w.payout(25)</code> would remove
    *   the $5 bill and a $20 bill from <code>w</code> and return true.
    * @param a the amount of money to attempt to remove.
    * @return a boolean flag indicating if the amount a was successfully 
    *   added removed from this Wallet.
    */
  public boolean payout(int a) {
    boolean ans = true;
    if (amount() < a)
      ans = false;
    else 
    {
     if (a/100 > 0) 
     {
       while (a/100 > 0)
       {
       remove(100);
       a-=100;
       }
     }
     if (a/50 > 0) 
     {
       while (a/50 > 0)
       {
       remove(50);
       a-=50;
       }
     }
     if (a/20 > 0) 
     {
       while (a/20 > 0)
       {
       remove(20);
       a-=20;
       }
     }
     if (a/10 > 0) 
     {
       while (a/10 > 0)
       {
       remove(10);
       a-=10;
       }
     }
     if (a/5 > 0) 
     {
       while (a/5 > 0)
       {
       remove(5);
       a-=5;
       }
     }
     if (a/2 > 0) 
     {
       while (a/2 > 0)
       {
       remove(2);
       a-=2;
       }
     }
     if (a/1 > 0) 
     {
       while (a/1 > 0)
       {
       remove(1);
       a-=1;
       }
     }
    }
    
    return ans;
  }
  
  /////////////////////////////////////////////////////////////////////////////
  /** Constructs a String representation of this Wallet.
    * Each bill is listed by its denomination.
    * <p>
    * <i>Example:</i>  If Wallet <code>w</code> contains a $5 bill and 
    *   three $20 bills,  then <code>w.toString()</code> would return
    *   <code>"5 20 20 20"</code>.
    * <br>
    * <i>Example:</i>  If Wallet <code>w</code> contains six $1 bills,
    *   a $10, and four $50 bills,  then <code>w.toString()</code> would return
    *   <code>"1 1 1 1 1 1 10 50 50 50 50"</code>.
    * @return String listing the bills in this Wallet
    */ 
  public String toString() {
    StringBuilder sb = new StringBuilder();
    boolean empty = true;
    
    for (int i = 0; i<bills.length; i++) {
      for (int j=1;j<=bills[i];j++) 
        sb.append(denom[i]+" ");
      if (bills[i]>0) empty = false;
    }
    if (empty) return "Empty"; else return sb.toString();
  }
  
  /////////////////////////////////////////////////////////////////////////////
  /** Tests if an object, obj, equals this Wallet.
    * That is, return true exactly when obj is a Wallet 
    * and the number of bills of each denomination 
    * matches in this Wallet and obj.
    * @return the result of the equality test
    */
  public boolean equals(Object obj) {
   if (obj == null || obj.getClass()!=this.getClass()) return false;
    Wallet w = (Wallet) obj;
    boolean result = true;
    // FIX: See if the bills in w match the bills in this Wallet
    for (int j = 0; j < bills.length; j++)
    {
      
        if (w.map(j) == map(j))
          result = true;
        else
        {
          result = false;
          break;
        }
      
    }
         
      
    return result;
  } 
  
  /////////////////////////////////////////////////////////////////////////////
  /** Clones this Wallet.
    * <p>
    * Note that if we were dealing with real money, then
    * this would be counterfeiting! 
    * @return a clone of this Wallet.
    */
  // BIG HINT: Look at the clone method in 
  // http://www.cis.syr.edu/courses/cis351/Diary/java-classes2/IntArrayBag.java
  public Object clone() {
    
    return bills;
  }
  
  
}