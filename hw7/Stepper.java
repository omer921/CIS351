import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;


/**
 * An Iterator class for SortedLists.
 * <p>
 * (I was going to have you implement the remove method, but
 * thought better of it.)
 * <p>
 * <b>Class Invariants:</b> 
 * <ol type=I>
 *   <li> sentinel = the anchor of a SortedList
 *   <li> cursor = the next element of the SortedList
 *   <li> removeOK = a flag that indicates if doing
 *                   a remove is OK.
 * </ol>
 */
@SuppressWarnings("unchecked") 
public class Stepper implements Iterator {
  private DNode<Integer> sentinel;
  private DNode<Integer> cursor;
  private boolean removeOK;
  /** Constructor for Stepper.
    * @param p the anchor a SortedList
    */
  public Stepper(DNode<Integer> p) {
    sentinel = p;
    cursor    = sentinel.getNext();
    removeOK  = false;
  }
  /** Returns true if the iteration has more elements. 
    */ 
  public boolean hasNext() {
    return (cursor!=sentinel);
  }
  /** Returns the next element in the iteration.
    * @return the next element in the iteration
    * @throws NoSuchElementException if the iteration has no more elements
   */ 
  public Integer next() {
    Integer answer;
    if (!hasNext())  new NoSuchElementException("The Stepper is empty.");
    answer = cursor.getVal();
    cursor = cursor.getNext();
    removeOK = true;
    return answer;
  }
  /** Removes from the underlying collection the last element returned by this iterator.
    * This method can be called only once per call to next(). 
    * The behavior of an iterator is unspecified if the underlying collection 
    * is modified while the iteration is in progress in any way other than 
    * by calling this method.
    * @throws IllegalStateException 
    *   if (i) the next method has not yet been called, or 
    *   (ii) the remove method has already been called after 
    *   the last call to the next method
    */
  public void remove( ) {
    if (!removeOK) 
      throw new IllegalStateException();
    cursor.removeBefore();
    removeOK = false;
  }
  
}