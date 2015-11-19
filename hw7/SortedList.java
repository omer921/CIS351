import java.util.NoSuchElementException;

/** A class for representing a list of integers sorted in increasing value.
  * <p>
  * <b>Implementation Notes:</b> The list is represented by a circular 
  * doubly linked list with a single sentinel DNode, called <code>anchor</code>.
  * <p>
  * The invariants of this representation are:
  * <ol type=I>
  *   <li> anchor is a sentinel DNode.
  *  <li> If the SortedList is empty, 
  *       <code>anchor.next = anchor.prev = anchor</code>. 
  *  <li> If the SortedList is nonempty, then 
  *       <ol type=a>
  *         <li> <code>anchor.next</code> = the first element of the list and 
  *         <li> <code>anchor.prev</code> = the last element of the list.
  *       </ol>
  *   <li> The elements of the list are sorted in increasing order. 
  * </ol>
  * 
  */ 
@SuppressWarnings("unchecked") 
public class SortedList {
  
////////////////////////////////////////////////////////////////////////
  protected DNode<Integer> anchor;
  private int num;
  ////////////////////////////////////////////////////////////////////////
  /** Construct an empty SortedList
    */
  public SortedList() {
    anchor = new DNode<Integer>(Integer.MAX_VALUE);
    anchor.setNext(anchor);
    anchor.setPrev(anchor);
    num = 0;
  }
  ////////////////////////////////////////////////////////////////////////
  /** Construct a SortedList with the elements from vs.
    * (Note: This won't work until insert works.)
    */
  public SortedList(int... vs) {
    this();
    insert(vs);
  }
  ////////////////////////////////////////////////////////////////////////
  /** Tests if the SortedList is empty
    * @return true, if the list is empty; false, o/w.
    */
  public boolean isEmpty() {
    return (num==0);
  }
  ////////////////////////////////////////////////////////////////////////
  /** Returns the size of the SortedList
    * @return the size of the SortedList
    */
  public int size() {
    DNode first = anchor.getNext();
    int count = 0;
    while (first.getVal() != anchor.getVal())
    {
      count++;
      first = first.getNext();
    }
    return num;
  }
  //return Integer.MIN_VALUE;  // FIX
  
  ////////////////////////////////////////////////////////////////////////
  /** Get the smallest value in this SortedList
    * @return the smallest value in this SortedList
    * @throws Class NoSuchElementException if this list is empty.
    */
  public int smallest() {
    int n = size();
    DNode<Integer> start = anchor.getNext();
    int small = start.getVal();
    
    for (int i = 0; i < n; i++)
    {
      if (small > start.getVal())
        small = start.getVal();
      start = start.getNext();
    }
    return small;  // FIX
  }
  
  ////////////////////////////////////////////////////////////////////////
  /** Get the largest value in this SortedList
    * @return the largest value in this SortedList
    * @throws Class NoSuchElementException if this list is empty.
    */
  public int largest() {
    int n = 8;
    DNode<Integer> start = anchor.getNext();
    int large = start.getVal();
    
    while(start != anchor)
    {
      if (large < start.getVal())
        large = start.getVal();
      start = start.getNext();
    }
    return large;  // FIX 
  }
  
  
  ////////////////////////////////////////////////////////////////////////
  /** Test if a value v is an element of this SortedList
    * @param v the value to be searched for
    * @return true, if the value is found; false, o/w.
    */  
  public boolean isMember(int v) {
    //int n = size();
    DNode<Integer> start = anchor.getNext();
    int large = v;
    Boolean ans = false;
    while(start.getVal() != Integer.MAX_VALUE)
    {
      if (large == start.getVal())
      {
        ans = true;
        break;
      }
      start = start.getNext();
    }
    return ans;  // FIX
  }
  ////////////////////////////////////////////////////////////////////////
  /** Constructs a String representation of this SortedList.
    * @return a String representation of this SortedList
    */
  public String toString() {
    if (num==0) return "[]";
    String ans = "[" + anchor.getNext().getVal();
    for (DNode cursor = anchor.getNext().getNext();
         cursor != anchor;
         cursor = cursor.getNext()) 
      ans = ans + ","+ cursor.getVal();
    return (ans +"]");
  }
  
  ////////////////////////////////////////////////////////////////////////
  /** Tests whether obj is an SortedList that is equal to this SortedList.
    * <p> <b>Implementation Note:</b> 
    * If one overrides <code>equals</code> (as we are doing here),
    * it is good practice to also override <code>hashCode</code>.  
    * <i>But</i> as we don't know about hash codes yet, we are skipping 
    * this. 
    * @return true, if obj is equal to this list; false, o/w.
    */ 
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass()!=this.getClass()) return false;
    SortedList that = (SortedList) obj;
    DNode<Integer> p1 = this.anchor.getNext();
    DNode<Integer> p2 = that.anchor.getNext();
    while (p1!=this.anchor && p2!=that.anchor) {
      if (p1.getVal() != p2.getVal()) return false;
      p1 = p1.getNext();
      p2 = p2.getNext();
    }
    return (p1==this.anchor && p2==that.anchor);  
  }
  
  ////////////////////////////////////////////////////////////////////////
  /** Remove a value v from this SortedList.
    * @param v the value to be removed
    * @return true, if the value was found and removed; false, o/w.
    */
  public boolean remove(int v) {
    if(isMember(v))
    {
      DNode<Integer> first = anchor.getNext();
      while (first.getVal() != v)
      {
        first = first.getNext();  
      }
      DNode ahead = first.getNext();
      ahead.removeBefore();
      num--;
    }
    
    return (isMember(v)); // FIX
  }
  ////////////////////////////////////////////////////////////////////////
  /** Remove values from this SortedList
    * @param vs the array of values to be removed.
    */
  public void remove(int... vs) {
    for (int v : vs) remove(v);
  }  
  ////////////////////////////////////////////////////////////////////////
  /** Insert  a value v to the SortedList
    * @param v the value to be inserted (in order)
    */
  public void insert(int v) {
    Boolean skip = false;
    DNode<Integer> first = anchor.getNext();
    if (anchor == first)
    {
      DNode<Integer> lead = new DNode(v, anchor, anchor);
      anchor.setNext(lead);
      anchor.setPrev(lead);
      skip = true;
      num++;
    }
    else
    {
      while(first.getVal() != anchor.getVal())
      {
        if (v < first.getVal())
        {
          break;
        }
        else if (first.getVal() == Integer.MAX_VALUE)
        {
          break;
        }
        else
        {
          first = first.getNext();
        }
      }
    }
    if(skip==false)
    {
      DNode<Integer> before = first.getPrev();
      DNode<Integer> after = first.getNext();
      DNode<Integer> nNode = new DNode(v, before, first);
      first.setPrev(nNode);
      before.setNext(nNode);
      num++;
    }
    // FIX
  }
  
  ////////////////////////////////////////////////////////////////////////
  /** Insert values to the SortedList
    * @param vs an array of values to be inserted (in order)
    */
  public void insert(int... vs) {
    for (int v : vs) insert(v);
  }
  ////////////////////////////////////////////////////////////////////////
  /** Merge two SortedLists, s1 and s2, into a new SortedList via a 
    * linear time algorithm.    * 
    * @param s1 the first of the SortedLists to be merged
    * @param s2 the second of the SortedLists to be merged
    * @return a new SortedList that contains the values of s1 and s2.
    * The SortedList's s1 and s2 are unchanged by the merge. 
    */
  public static SortedList merge(SortedList s1, SortedList s2) {
    SortedList s = new SortedList();
    SortedList s2Copy = s2;
    DNode<Integer> fNode = s1.anchor.getNext();
    while(fNode.getVal() != s1.anchor.getVal())
    {
      s2Copy.insert(fNode.getVal());
      //System.out.println("node being put in: " + fNode.getVal());
      fNode = fNode.getNext();
    }
    return s2Copy;    
  }
  
  /**    
   * Create an Iterator containing the elements of this SortedList.     
   * @return    
   *   an Iterator containing the elements of this SortedList.    
   * <dt><b>Note    
   *   If changes are made to this bag before the Iterator    
   *   returns all of its elements, then the subsequent behavior of the    
   *   Iterator is unspecified.    
   **/   
  public Stepper iterator( )  {     
    return new Stepper(anchor);   
  }
  
  ////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////
  // Utility methods
  
  ////////////////////////////////////////////////////////////////////////
  /** Given an int v, this method returns the first DNode in the list 
    * with value >= v, or, if v is not in the list, the method
    * returns the anchor.
    */
  private DNode<Integer> find(int v) {
    DNode<Integer> cursor = anchor.getNext();
    while (cursor != anchor && cursor.getVal() < v) 
      cursor = cursor.getNext();
    return cursor;
  }
  
  ////////////////////////////////////////////////////////////////////////
  /** Checks that the SortedList really is sorted.  Used in tesing. 
    */
  public boolean checkSorted() {
    Stepper it = this.iterator();
    int prevVal = Integer.MIN_VALUE;
    int thisVal;
    while (it.hasNext()) {
      thisVal = it.next();
      if (prevVal > thisVal) return false;
      prevVal = thisVal;
    }
    return true;
  }
  
}