import java.util.*; 

/**********************************************************
  * An external iterator for lists build of Nodes.
  **********************************************************/

public class LWalker<E> implements Iterator {
  ////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////
  
  // Instance variables
  
  LBag<E> bag;       /** the bag we are iterating over */
  Node<E> nextNode;  /** a pointer to the next node to visit */
  Node<E> lastRet;   /** a pointer to the last node returned by next() 
                         (or null if the last node returned was deleted */ 
  Node<E> prior;     /** a pointer to the node before lastRet */
  // In general, this is the picture
  // 
  //          prior   lastRet   nextNode
  //            |       |         |
  //            V       V         V
  //       --->[ ]---->[ ]------>[ ]---> .....  --->[ ]
  //
  public LWalker(LBag<E> b){
    bag      = b;
    nextNode = bag.getHead();
    lastRet  = null;
    prior    = null;
  }
  
  //////////////////////////////////////////////////////////////////
  /** 
   * Tests if there is a next node to be visited.
   **/
  public boolean hasNext() {
    return (nextNode != null);
  }
  
  //////////////////////////////////////////////////////////////////
  /** 
   * If there is a next node to be visited, then
   *    return the data value and also advance nextNode to the 
   *    next node to be visited.
   * If there is no next node to be visited, then ...
   **/
  public E next() {
    if (!hasNext()) throw new NoSuchElementException();
    
    E answer = nextNode.getData();
    if (lastRet!=null) prior = lastRet;
    lastRet  = nextNode;
    nextNode = nextNode.getLink();
    return answer;
  }
  
  //////////////////////////////////////////////////////////////////
  /** 
   * Removes from the bag the last element returned by this iterator. 
   * This method can be called only once per call to next(). 
   **/
  public void remove() {
    if (lastRet!=null) { // there is something to delete
      if (prior  ==null) // we last returned the head
        bag.setHead(lastRet.getLink());
      else  // we last returned something past the head
        prior.removeNodeAfter();
    }
    lastRet = null;
  }
}