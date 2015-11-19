/** A simple list implementation of a bag.
  */
public class LBag<E> implements Cloneable {
  private Node<E> head;
  private int manyNodes;   
  /** Constructs an empty bag. */
  public LBag() { 
    head = null; 
    manyNodes = 0; 
  }
  /** Adds an element to the bag 
    * @param elm the item to be added to the bag.
    */
  public void add(E elm)   {      
    head = new Node<E>(elm, head);
    manyNodes++;
  }
  /** Sets the head field of the LBag.
    * @param newHead the new value of the head field
    */
  public void setHead(Node<E> newHead) {
    head = newHead;
  }
  /** Gets the head field of the LBag.
    * @return the head field 
    */
  public Node<E> getHead() {
    return head;
  }
  /** Gets the number of elements in the bag.
    * @return the number of elements in the bag
    */
  public int size() { 
    return manyNodes; 
  }
  /** Construct a new iterator for the bag.
    * @return a new iterator for the bag
    */
  @SuppressWarnings("unchecked")
  public LWalker<E> iterator() { 
    return new LWalker(this); 
  }
  
  //////////////////////////////////////////
  /** A test program for the LBag class.
    */
  @SuppressWarnings("unchecked")
  public static void main(String argv[]){
    LBag b = new LBag<String>();
    LWalker lw;
    int i;

    b.add("January");   b.add("February");
    b.add("March");     b.add("April");
    
    System.out.println("====================");    
    // List the elements of b.
    lw = b.iterator();
    i = 1;
    while (lw.hasNext()) {
      System.out.printf("%2d: %-10s\n",i,lw.next());
      i++;
    }

    
    // Remove everyother element from b
    lw = b.iterator();
    i = 1;
    while (lw.hasNext()) {
      lw.next();
      if ((i%2)==1) lw.remove();
      i++;
    }
    System.out.println("====================");

    // List the elements of b.
    lw = b.iterator();
    i = 1;
    while (lw.hasNext()) {
      System.out.printf("%2d: %-10s\n",i,lw.next());
      i++;
    }
    System.out.println("====================");      
  }
}