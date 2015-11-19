public class INode {
  private int   val;
  private INode next;
  ////////////////////////////////////////////////////////////////////////
  /** Construct an INode with value v and next n.
    * @param v the value for the new INode
    * @param n the next value for the new INode
    *
    */
  public INode(int v,INode n) {
    val = v;
    next = n;
  }
  ////////////////////////////////////////////////////////////////////////
  /** Construct an INode with value=v and next=null.
    * @param v the value for the new INode
    */
  public INode(int v) {
    val = v;
    next = null;
  }
  ////////////////////////////////////////////////////////////////////////
  /** Extract the value field of the INode
    * @return the value field of the INode
    */
  public int   getVal()         { return val; }
  ////////////////////////////////////////////////////////////////////////
  /** Extract the next field of the INode
    * @return the next field of the INode
    */
  public INode getNext()        { return next; }
  ////////////////////////////////////////////////////////////////////////
  /** Set the value field of the INode to v.
    */
  public void  setVal(int v)    { val = v; }
  ////////////////////////////////////////////////////////////////////////
  /** Set the next field of the INode to n.
    */
  public void  setNext(INode n) { next = n; }
  ////////////////////////////////////////////////////////////////////////
  /** Splice in a new INode with value v after this INode
    * @param v the value of the new INode
    */
  public void  addAfter(int v)  { next = new INode(v,next); }
  ////////////////////////////////////////////////////////////////////////
  /** Delete the INode following this one from the list.
    * If there is no INode following this one, nothing is changed.
    */
  public void  removeAfter()  { 
    if (next != null) next = next.next;
  }
  ////////////////////////////////////////////////////////////////////////
  /** Construct a copy of the part of the list from start to end
    * @return a copy of the part of the list from start to end
    * @throws IllegalArgumentException when end is not contained in the 
    *     list headed by start
    */
  public static INode[] listPart(INode start, INode end) {
    INode ans[] = { null, null };
    if (start==null) return ans;
    ans[0]     = new INode(start.val);
    INode to   = ans[0];
    INode from = start;
    
    while (from != end) {
      from = from.next;
      if (from == null) 
        throw new IllegalArgumentException("The end node was not found on this list");
      to.addAfter(from.val);
      to = to.next;
    }
    ans[1] = to;
    return ans;
  }
  
  public String toString()
  {
    return ""+val;
  }
}