/** A generic doubly linked list node class.
  * <p>
  * <b>Class invariants:</b>
  * <ol type=I>
  * <li> either prev == null or prev.next == this
  * <li> either next == null or next.prev == this 
  * </ol>
  */ 

@SuppressWarnings("unchecked") 
public class DNode<T> {
  
  /** the value stored by the DNode */
  private T val;
  /** a reference to the previous DNode in the list 
    *   (null if there is no previous element) 
    */
  private DNode<T> prev;
  /** a reference to the next DNode in the list
    *   (null if there is no next element) 
    */
  private DNode<T> next;
  
  ////////////////////////////////////////////////////////////////////////
  /** Constructs a DNode with value v and null values for the prev and 
    *   next pointers.
    * @param v the value of the val field
    */
  public DNode(T v) {
    val = v; 
    prev = next = null;
  }
  ////////////////////////////////////////////////////////////////////////
  /** Constructs a DNode with value v and previous pointer p and next 
    *   pointer n.
    * @param v the value of the val field
    * @param p the value for the previous field
    * @param n the value for the next field
    */
  public DNode(T v, DNode<T> p, DNode<T> n) {
    val = v;
    prev = p;
    next = n;
  }
  ////////////////////////////////////////////////////////////////////////
  /** Gets the value of the val field.
    * @return the value of the val field
    */
  public T getVal() { 
    return val;  
  }
  ////////////////////////////////////////////////////////////////////////
  /** Gets the value of the prev field.
    * @return the value of the prev field
    */
  public DNode<T> getPrev() { 
    return prev; 
  }
  ////////////////////////////////////////////////////////////////////////
  /** Gets the value of the next field.
    * @return the value of the next field
    */
  public DNode<T> getNext() { 
    return next; 
  }
  ////////////////////////////////////////////////////////////////////////
  /** Sets the value of the val field
    * @param v the new value of the val field
    */
  public void setVal(T v) { 
    val = v;     
  }
  ////////////////////////////////////////////////////////////////////////
  /** Sets the value of the prev field
    * @param p the new value of the prev field
    */
  public void setPrev(DNode<T> p) { 
    prev = p;    
  }
  ////////////////////////////////////////////////////////////////////////
  /** Sets the value of the next field
    * @param n the new value of the next field
    */
  public void setNext(DNode<T> n) { 
    next = n;    
  }
  ////////////////////////////////////////////////////////////////////////
  /** Inserts a new NDode in to the list before this DNode
    * @param v the value of the val field of the new DNode
    */
  public void addBefore(T v) { 
    if (getPrev() == null)
    {
      prev = this;
      DNode n = new DNode(v, null, this);
      this.setPrev(n);
    }
    else { 
      //System.out.println("in else");
      DNode pre = this.prev;
      DNode n = new DNode(v, pre, this);
      pre.setNext(n);
      this.setPrev(n);
    }
  }
  ////////////////////////////////////////////////////////////////////////
  /** Inserts a new NDode in to the list after this DNode
    * @param v the value of the val field of the new DNode
    */
  public void addAfter(T v) { 
    if (getNext() == null)
    {
      DNode n = new DNode(v, this, null);
      this.setNext(n);
    }
    else {
      //System.out.println("in else");
      DNode n = new DNode(v, this, this.getNext());
      this.setNext(n);
    }
  }
  ////////////////////////////////////////////////////////////////////////
  /** If there is a DNode in the list before this DNode, that DNode is 
    * removed from the list.
    */
  public void removeBefore() {
    DNode befores = this.getPrev();
    //DNode b2 = befores.getPrev();
    
    if (befores == null)
      this.setPrev(null);
    
    else if (befores.prev == null)
      this.setPrev(null);
    
    else 
    {
      DNode before = this.getPrev();
      DNode bBefore = before.getPrev();
      this.setPrev(bBefore);
      bBefore.setNext(this);
    }
    
    
  }  
  ////////////////////////////////////////////////////////////////////////
  /** If there is a DNode in the list after this DNode, that DNode is 
    * removed from the list.
    */
  public void removeAfter() {
    DNode afters = this.getNext();
    
    if (afters == null)
      this.setNext(null);
    
    else if (afters.next == null)
      this.setNext(null);
    
    else // (getNext() != null || this != null)
    {
      DNode after = this.getNext();
      DNode aAfter = after.getNext();
      this.setNext(aAfter);
      aAfter.setPrev(this);
    }
    //else
      
  }
  
}