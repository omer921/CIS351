// File: WalletTest.java
/******************************************************************************              
  * A <CODE>WalletTest</CODE> is a test program for the Wallet class
  * @author
  *   I forgot to add my name, so please take off some points Mr. Grader. 
  * @version
  *   3 Vendemiaire CCXXIII
  ******************************************************************************/

public class WalletTest {
  
  /** A Test program for the Wallet Class.
    */
  public static void main(String [] argv) {
    Wallet w = new Wallet();
    int cash1[] = {1,1,20,2,10,50,3,100};
    for (int b : cash1) w.add(b);
    System.out.println(w);
    System.out.println("The wallet contains $" + w.amount());
    
    int cash2[] = {9,11,45,10,2,1000};
    for (int a : cash2) {  
      if (w.payout(a))
        System.out.println("After paying out $"+a + " we have:\n\t"+w);
      else 
        System.out.println("Do not know how to make $" + a + " change");
    }
    // ADD TESTS OF OTHER METHODS
    
    System.out.println("amount of bills: " + w.countBills());
    System.out.println("amount of fifty dollar bills: " + w.countBills(50));
    w.remove(10);
    System.out.println("amount of bills after removing 10: " + w.amount());
    w.add(20);
    System.out.println("amount of bills after adding 20: " + w.amount());
    Wallet z = new Wallet();
    w.transfer(z);
    System.out.println("amount of bills in wallet z: " + w.countBills());
    System.out.println("are z and w have the same wallet value: " + w.equals(z));
    
    
  }
}