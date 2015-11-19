import java.util.NoSuchElementException;

public class IList {
  // Class invariants:
  //   1. if the list is empty first = last = null.
  //   2. if the list is not empty, then
  //      a. first = a pointer to the initial element of the list 
  //      b. last = a pointer to the final element of the list 
  //      c. last.getNext() == null
  private INode first;
  private INode last;
  ////////////////////////////////////////////////////////////////////////
  /** Constructs a list from the contents of elms.
    * Note that <code>new IList()</code> constructs an empty list.
    * @param elms the elements of the new list
    */
  public IList(int... elms) {
    switch (elms.length) {
      case 0:
        first = last = null;
        return;
      case 1:
        first = last = new INode(elms[0]);
        return;
      default:
        last  = new INode(elms[elms.length-1]);
        first = new INode(elms[0],last);
        for (int i=elms.length-2; i>0; i--)
          first.addAfter(elms[i]);
        return;
    }
  }
  ////////////////////////////////////////////////////////////////////////
  /** Accesses the first element of this list.
    * @return the first value in this list
    * @throws NoSuchElementException when the list is empty
    */
  public int firstVal() { 
    if (isEmpty()) throw new NoSuchElementException(
                                                    "Attempt to take the first element of an empty list"); 
    return first.getVal();
  }
  ////////////////////////////////////////////////////////////////////////
  /** Accesses the last element of this list.
    * @return the last value in this list
    * @throws NoSuchElementException when the list is empty
    */
  public int lastVal() { 
    if (isEmpty()) throw new NoSuchElementException(
                                                    "Attempt to take the last element of an empty list");  
    return last.getVal();
  }
  ////////////////////////////////////////////////////////////////////////
  /** Tests if the list is empty 
    * @return true, if the list is empty; false, o/w.
    */
  public boolean isEmpty() {
    return (first == null);
  }
  ////////////////////////////////////////////////////////////////////////
  /** Adds an int to the front of the list.
    * @param v the int to be added to the front of the list
    */
  public void addFirst(int v) {
    if (isEmpty()) 
      first = last = new INode(v);
    else
      first = new INode(v,first);
  }
  ////////////////////////////////////////////////////////////////////////
  /** Adds an int to the end of the list
    * @param v the int to be added to the end of the list
    */
  public void addLast(int v) {
    if (isEmpty())
    {
      
      first = last = new INode(v);
    }
    else
    {
      if (length() < 2)
      {
        first.setNext(new INode (v, null));
        last = last.getNext();
      }
      else
      {
        last.setNext(new INode (v, null));
        last = last.getNext();
      }
    }
  }
  ////////////////////////////////////////////////////////////////////////
  /** Removes the first element of the list.
    * @throws NoSuchElementException if the list is empty
    */
  public void removeFirst() {
    if (isEmpty()) 
      throw new NoSuchElementException(
                                       "Attempt to remove an element from an empty list");
    if (first == last) {
      first = last = null;
    }
    else { // scan the list to find the next to first INode
      INode cursor = first;
      //while (cursor.getNext() != last) 
      // cursor   = cursor.getNext();
      //cursor.setNext(null);
      INode second = new INode (first.getNext().getVal() ,first.getNext().getNext());
      first = second;
    }
  }
  ////////////////////////////////////////////////////////////////////////
  /** Removes the last element of the list.
    * @throws NoSuchElementException if the list is empty
    */
  public void removeLast() {
    if (isEmpty()) 
      throw new NoSuchElementException(
                                       "Attempt to remove an element from an empty list");
    if (first == last) {
      first = last = null;
    }
    else { // scan the list to find the next to last INode
      INode cursor = first;
      while (cursor.getNext() != last) 
        cursor   = cursor.getNext();
      cursor.setNext(null);
      last = cursor;
    }
  }
  ////////////////////////////////////////////////////////////////////////
  /** Adds v to each element of the list
    * @param v the value to be added to each element of the list.
    */
  public void bump(int v) {
    for(INode cursor = first; cursor != null; cursor = cursor.getNext()) 
      cursor.setVal(v+cursor.getVal());
  }  
  ////////////////////////////////////////////////////////////////////////
  /** Constructs a copy of this list with <b>no</b> INodes in common with the original.
    * @return a new IList containing a copy of this list with 
    *         <b>no</b> INodes in common with the original.
    */ 
  public IList copy() {
    IList answer; 
    if (isEmpty())
    {
      answer = new IList();
    }
    else
    {
      
      
      //  (Obvious hint, use INode.listPart.)
      INode firstp = new INode (first.getVal(), first.getNext());
      INode ans[] = new INode[length()];
      int a[] = new int[length()];
      
      for (int i = 0; i < length(); i++)
      {
        ans = INode.listPart(firstp, firstp);
        a[i] = ans[0].getVal();
        firstp = firstp.getNext();
      }
      answer = new IList(a);
    }
    return answer;
  }
  ////////////////////////////////////////////////////////////////////////
  /** Tests whether obj is an IList that is equal to this IList.
    * <p> <b>Implementation Note:</b> 
    * If one overrides <code>equals</code> (as we are doing here),
    * it is good practice to also override <code>hashCode</code>.  
    * <i>But</i> as we don't know about hash codes yet, we are skipping 
    * this. 
    * @return true, if obj is equal to this list; false, o/w.
    */ 
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass()!=this.getClass()) return false;
    IList that = (IList) obj;
    INode p1 = this.first, p2 = that.first;
    while (p1!=null && p2!=null) {
      if (p1.getVal() != p2.getVal()) return false;
      p1 = p1.getNext();
      p2 = p2.getNext();
    }
    return (p1==null && p2==null);  
  }
  ////////////////////////////////////////////////////////////////////////
  /** Determines the length of this IList
    * @return the length of this IList
    */
  public int length() {
    int count = 0;
    for(INode cursor = first; cursor!=null; cursor=cursor.getNext(), count++);
    return count;
  }
  ////////////////////////////////////////////////////////////////////////
  /** Produces a new IList that is the reverse of this list.
    * @return a new IList that is the reverse of this list with <b>no</b> 
    *   INodes in common with the original.
    */
  public IList reverse() {
    IList ans = new IList();
    // (Hint: Consider how to use addFirst.)
    /////////////
    // YOUR JOB
    /////////////
    if (isEmpty())
      ans = ans;
    else if (length() == 1)
      ans = new IList(first.getVal());
    else 
    {
      IList nlist = copy();
      for (int i = 0; i < length(); i++)
      {
        
        ans.addFirst(nlist.first.getVal());
        nlist.first = nlist.first.getNext(); 
        
      }
    }
    
    
    return ans;  
  }
  ////////////////////////////////////////////////////////////////////////
  /** Advances p n-many next links.
    * Note that nth(p,0) returns p.
    * @return a reference to the INode n-places down the list from p;
    *   null is returned if the list headed by p is shorter than n-1.
    */
  private static INode nth(INode p,int n) {
    while (p!=null && n>0) {
      p = p.getNext();
      n--;
    }
    return p;
  }
  ////////////////////////////////////////////////////////////////////////
  /** Produces a new IList that is made up of the first n elements
    * of this list.
    * @return a new IList that is made up of the first n elements of
    *   this list with <b>no</b> INodes in common with the original.
    *   If this list has fewer than n elements, a copy of this list
    *   is returned.
    */
  public IList take(int n) {
    IList answer = new IList();
    // Obvious special cases: n=0 and n>the list length.
    if (n>length())
      answer = copy();
    else if (n >= 0)
    {
      INode firstp = new INode (first.getVal(), first.getNext());
      INode ans[] = new INode[n];
      int a[] = new int[n];
      
      for (int i = 0; i < n; i++)
      {
        ans = INode.listPart(firstp, firstp);
        a[i] = ans[0].getVal();
        firstp = firstp.getNext();
      }
      answer = new IList(a);
    }
    return answer;    
  }
  ////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////
  /** Produces a new IList that is made up of the first n elements of this list.
    * @return a new IList that is made up of the first n elements of this 
    *   list with <b>no</b> INodes in common with the original.  If this 
    *   list has fewer than n elements, a copy of this list is returned.
    */
  public IList drop(int n) {
    // (Obvious special case: n > the length of the list)
    IList list = copy();
    // Obvious hints, think how to use nth and INode.listPart.
    IList answer = new IList();
    if (n > list.length())
      answer = answer;
    else 
    {
      list.first = nth(list.first, n);
      if (list.first == null)
      {
        answer = answer;
      }
      else {
        INode firstp = new INode (list.first.getVal(), list.first.getNext());
        INode ans[] = new INode[n];
        int a[] = new int[length()-n];
        
        for (int i = 0; i < length()-n; i++)
        {
          ans = INode.listPart(firstp, firstp);
          if (ans[0] == null)
            break;
          a[i] = ans[0].getVal();
          firstp = firstp.getNext();
        }
        answer = new IList(a);
      }
    }
    /////////////
    // YOUR JOB
    /////////////  
    
    return answer;
  }  
  ////////////////////////////////////////////////////////////////////////
  /** Destructively appends the list lst at the end of this list.
    * However, the revised list and the argument lst should have no INodes 
    * in common. 
    * @param lst the list to append to the end of this list
    */
  public void appendAfter(IList lst) {
    if (lst.isEmpty()) return;
    IList cp = lst.copy();
    if (isEmpty()) 
      first = cp.first;
    else
      last.setNext(cp.first);
    last = cp.last;
    //deleteAll(0);
  }
  ////////////////////////////////////////////////////////////////////////
  /** Tests if an int value v occurs in this list.
    * @return true if v occurs in this list; false, o/w.
    */
  public boolean isElem(int v) {
    INode cursor = first;
    while(cursor!=null && cursor.getVal() != v)
      cursor = cursor.getNext();
    return (cursor != null);
  }
  ////////////////////////////////////////////////////////////////////////
  /** Delete the first occurrence of a int value v from this list.
    * If v does not occur in this list, then the list is unchanged.
    */
  
  public void delete(int v) {
    // Handle the case of an empty list
    if (isEmpty()) return;
    // Handle the case of one element list
    if (first==last) {
      if (first.getVal()==v)
        first = last = null;
      return;
    }
    // Handle the case of a list with at least two elements
    // Check if v is the first element
    if (first.getVal()==v) {
      // since first!=last, no need to update last
      first = first.getNext();
      return;
    }
    // Find the node just before the first occurrence of v (if any)
    INode follower = first;
    INode leader   = first.getNext();
    while (leader !=null && leader.getVal() != v) {
      follower = leader;
      leader   = leader.getNext();
    }
    // See if we found a v and if not return
    if (leader==null) return;
    // We found a v. so chop the leader's INode out of the list
    follower.removeAfter();
    // Update last if need be
    if (leader==last) 
      last = follower;
  }
  ////////////////////////////////////////////////////////////////////////
  /** Delete all occurrences of a int value v from this list.
    */
  public void deleteAll(int v) {
     if (length() == 1)
          if (first.getVal() == v)
          first = last = null;
    while (isElem(v))
    {
      delete(v);
    }
  }


////////////////////////////////////////////////////////////////////////   
/** Produces a String representation of this list.
  * @return a String representation of this list.
  */
public String toString() {
  if (isEmpty()) return "[]";
  INode cursor = first;
  String ans = "[";
  while (cursor != null) {
    ans = ans + Integer.toString(cursor.getVal());
    cursor = cursor.getNext();
    if (cursor != null) ans = ans + ",";
  }
  return (ans+"]");
}
////////////////////////////////////////////////////////////////////////


}


