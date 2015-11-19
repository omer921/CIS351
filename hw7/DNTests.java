@SuppressWarnings("unchecked")
 
/** Tests of the DNode class, Version 2 (14 Oct 2014)
  */
public class DNTests {
  
  public static void main(String args[]) {
    DNode<Character> cnode = new DNode<Character>('t',null,null);
    checkLinks(cnode,"t");
    
    cnode.addAfter('l'); 
    checkLinks(cnode,"tl");
    
    cnode.addAfter('i');
    checkLinks(cnode,"til");
    
    cnode.addBefore('r');
    checkLinks(cnode,"rtil");
    
    cnode.addAfter('a');
    checkLinks(cnode,"rtail");
    
    cnode.addBefore('e');
    checkLinks(cnode,"retail");
    
    cnode.addBefore('d');
    checkLinks(cnode,"redtail");
    
    cnode.removeBefore();
    checkLinks(cnode,"retail");   
    
    cnode.removeAfter();
    checkLinks(cnode,"retil");
    
    cnode.removeAfter();
    checkLinks(cnode,"retl");
    
    cnode.removeBefore();
    checkLinks(cnode,"rtl");   
    
    cnode.removeBefore();
    checkLinks(cnode,"tl");   
    
    cnode.removeBefore();
    checkLinks(cnode,"tl");   
    
    cnode.removeAfter();
    checkLinks(cnode,"t");
    
    cnode.removeAfter();
    checkLinks(cnode,"t");
    
    cnode.removeBefore();
    checkLinks(cnode,"t");
    
    System.out.println("Tests done.");
  }
  public static void checkLinks(DNode<Character> q, String expected) {
    checkInvars(q);
    checkInvars(q.getPrev());
    checkInvars(q.getNext());
    
    String actual = stringify(q);
    if (!expected.equals(actual)) 
      System.out.println("Mismatch! Exected: " + expected
                         +      "    Actual: " + actual);
  }
  public static void checkInvars(DNode<Character> q) {
    if (q==null) return;
    
    if (q.getPrev() != null && q.getPrev().getNext() != q)
      System.out.println("DNode invariant I fails for the " + q.getVal() + "-node");
    
    if (q.getNext() != null && q.getNext().getPrev() != q)
      System.out.println("DNode invariant II fails for the " + q.getVal() + "-node"); 
  }
  
  public static String stringify(DNode<Character> q) {
    String ans ="";
    if (q==null) return ans;
    
    for (; q.getPrev()!=null;q = q.getPrev());
    
    for(; q != null; q = q.getNext()) {
      char c = (char) q.getVal();
      ans = ans + c;
    }
    return ans;
  }
}