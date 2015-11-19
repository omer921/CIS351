import java.util.Iterator;
import java.util.*;
/**********************************************************
 * 
 * <h1>Add documentation</h1>
 * 
 **********************************************************/

public class TWalker implements Iterator {
  ////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////
  //
  // Add documentation
  //

  // Instance variables
  /** <h1>Adds a tree bag variable to the class</h1> */
   TreeBag bag; // the bag
   /** <h1>Adds a TNode variable to the class</h1> */
   TNode root; // the main node
   /** <h1>Adds a TNode variable to the class</h1> */
   TNode left;
   /** <h1>Adds a TNode variable to the class</h1> */
   TNode right;
   //E data;
   /** <h1>Adds a TNode variable to the class</h1> */
   TNode parent;
  /** <h1>Creates a new TWalker object that takes in a TreeBag to iterate upon</h1> */


  public TWalker(TreeBag bag){
    /* fill in */
    bag = bag;
    root = bag.root;
    left = bag.root.getLeft();
    right = bag.root.getRight();
    parent = null;
    
  }

  ////////////////////////////////////////////////////////
  /** 
   * <h1>Checks if the head node has children so it can continue ot iterate</h1>
   **/
    
  public boolean hasNext() {
    return ((root.getRight() != null)||(root.getLeft() != null)); // FIX
  }
    
  ////////////////////////////////////////////////////////
  /** 
   * <h1>Iterates on the the next node in the tree</h1>
   **/
    
  public Object next() {
    if (!hasNext()) throw new NoSuchElementException();
    Object ans = root.getSuccessor();
    if (parent!=null) parent = root.getPredecessor();
    
    return ans; // FIX **********
  }
    
  ////////////////////////////////////////////////////////
  /** 
   * <h1>removes teh current node in the tree</h1>
   **/
  
  public void remove() {
    if (parent!=null){
      if(parent != null)
      bag.root = parent.getRight();
      else
        parent.removeRightmost();
    }
    //return; 
  }
}