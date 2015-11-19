@SuppressWarnings("unchecked")
/********************************************************************
 * A TNode provides a node for a binary tree. Each node contains a
 * piece of data (which is a reference to an StringOcc) and
 * references to a left and right child. The references to children
 * may be null to indicate that there is no child. The reference
 * stored in a node can also be null.
 *
 * LIMITATIONS: 
 *   Beyond Int.MAX_VALUE elements, treeSize, is wrong.
 *
 * AUTHOR: Michael Main 
 *
 * VERSION
 *   Jun 12, 1998
 *
 ********************************************************************/
public class TNode<E> {
  //////////////////////////////////////////////////////////////////
  // Invariant of the TNode class:
  //   1. Each node has one reference to an E, stored in the
  //      instance variable data.
  //   2. The instance variables left and right are references to
  //      the node's left and right children.
  //   3. The instance variable parent is a reference to
  //      the node's parent.
  private E     data;
  private TNode parent;
  private TNode left;
  private TNode right;   
  
  //////////////////////////////////////////////////////////////////
  /**
   * Initialize a TNode with a specified initial data and links children. 
   * @param initData
   *   the initial data of this new node
   * @param initLeft
   *   a reference to the left child of this new node (null if 
   *   there is no left child)
   * @param initRight
   *   a reference to the right child of this new node (null if 
   *   there is no right child)
   * <DT><B>POSTCONDITION:</B><DD>:
   *   This node is initialized as required.
   **/   
  public TNode(E initData, TNode initParent, 
               TNode initLeft, TNode initRight)  {
    data   = initData;
    parent = initParent;
    left   = initLeft;
    right  = initRight;
  }       
  
  
  //////////////////////////////////////////////////////////////////
  /**
   * Gets the data field from this node.   
   * @return
   *   the data from this node
   **/
  public E getData( ) {
    return data;
  }
  
  
  //////////////////////////////////////////////////////////////////
  /**
   * Gets the parent field of this node.
   * @return
   *   a reference to the parent of this node (or the null
   *   reference if there is no parent child)
   **/
  public TNode<E> getParent( ) {
    return parent;                                       
  } 
  
  //////////////////////////////////////////////////////////////////
  /**
   * Gets the left child field of this node.
   * @return
   *   a reference to the left child of this node (or the null
   *   reference if there is no left child)
   **/
  public TNode<E> getLeft( ) {
    return left;                                        
  } 
  
  //////////////////////////////////////////////////////////////////
  /**
   * Gets the right child field of this node.
   * @return
   *   a reference to the right child of this node (or the null
   *   reference if there is no right child)
   **/
  public TNode<E> getRight( ) {
    return right;                                        
  } 
  
  
  //////////////////////////////////////////////////////////////////
  /**
   * Test if the node is a leaf. 
   * @return true iff this is a leaf
   **/
  public boolean isLeaf( ) {
    return (left == null) && (right == null);
  } 
  
  
  //////////////////////////////////////////////////////////////////
  /**
   * Set the data field of this node.   
   * @param newData 
   *   the new value for the data field
   * <DT><B>POSTCONDITION:</B><DD>:
   *   The data of this node has been set to newData.
   **/
  public void setData(E newData) {
    data = newData;
  }   
  
  
  //////////////////////////////////////////////////////////////////
  /**
   * Set the parent field of this node.
   * @param newParent
   *   the new value for the parent field
   * <DT><B>POSTCONDITION:</B><DD>:
   *   The parent field has been set to newParent.
   **/
  public void setParent(TNode<E> newParent)   {
    parent = newParent;
  }
  
  
  //////////////////////////////////////////////////////////////////
  /**
   * Set the left child field of this node.
   * @param newLeft
   *   the new value for the left child field
   * <DT><B>POSTCONDITION:</B><DD>:
   *   The left field has been set to newLeft.
   **/
  public void setLeft(TNode<E> newLeft)   {
    left = newLeft;
  }
  
  //////////////////////////////////////////////////////////////////
  /**
   * Set the right child field of this node.
   * @param newRight
   *   the new value for the right child field
   * <DT><B>POSTCONDITION:</B><DD>:
   *   The right field has been set to newRight.
   **/
  public void setRight(TNode<E> newRight) {
    right = newRight;
  }  
  
  
  //////////////////////////////////////////////////////////////////
  /**
   * Gets the inorder predecessor node of the current node.
   * @return
   *   a reference to the inorder predecessor of the current node.
   *   If there is no predecessor node, then null is returned.
   **/
  public TNode<E> getPredecessor() {
    if (left != null) 
      return left.getRightmost();
    else {
      TNode<E> child = this;
      while (child.parent != null && child.parent.left==child) {
        child = child.parent;
      }
      return child.parent;
    }
  }
  
  public int depth()
  {
    int num = 1;
   if (this.isLeaf())
     return num;
   else
   {
     int ansL = 1;
     if (this.getLeft() != null)
        ansL = this.getLeft().depth() + 1;
     int ansR = 1;
     if (this.getRight() != null)
      ansR = this.getRight().depth() + 1;
     if (ansL > ansR)
       return ansL;
     else
       return ansR;
   }
  }
    
  //////////////////////////////////////////////////////////////////
  /**
   * Gets the inorder successor node of the current node.
   * @return
   *   a reference to the inorder successor of the current node.  If
   *   there is no successor node, then null is returned.
   **/
  public TNode<E> getSuccessor() {
    //System.out.println("getSuccessor: Fix me!!!"); /**************************************************************************************/
    if (left != null) 
      return left.getRightmost();
    else {
      TNode<E> child = this;
      while (child.getLeftmost() != null /*&& child.parent.left==child*/) {
        child = child.getLeftmost();
      }
      return child;
    }
  }
  
  
  //////////////////////////////////////////////////////////////////
  /**
   * Gets the rightmost node of the tree below this node.
   * @return
   *   a reference to the rightmost node in the tree below this node.
   **/
  public TNode<E> getRightmost( )  {
    if (right == null)
      return this;
    else
      return right.getRightmost( );
  }
  
  
  //////////////////////////////////////////////////////////////////
  /**
   * Gets the leftmost node of the tree below this node.
   * @return
   *   a reference to the leftmost node in the tree below this node.
   **/
  public TNode<E> getLeftmost( )  {
    if (left == null)
      return this;
    else
      return left.getLeftmost( );
  }
  
  //////////////////////////////////////////////////////////////////
  /**
   * Count the number of nodes in a binary tree.
   * @param root
   *   a reference to the root of a binary tree (null if the tree is empty)
   * @return
   *   the number of nodes in the binary tree rooted at root.
   **/ 
  public static <E> long treeSize(TNode<E> root) {
    if (root == null)
      return 0;
    else
      return 1 + treeSize(root.left) + treeSize(root.right);
  }   
  
  //////////////////////////////////////////////////////////////////
  /**
   * Uses an inorder traversal to print the data from each node at
   * or below this node of the binary tree.
   * <DT><B>POSTCONDITION:</B><DD>:
   *   The data of this node and all its descendants have been
   *   written by System.out.println( ) using an inorder traversal.
   **/
  public void inorderPrint( )  {
    if (left != null)
      left.inorderPrint( );
    System.out.println(data);
    if (right != null)
      right.inorderPrint( );
  }  
  
  
  //////////////////////////////////////////////////////////////////
  /**
   * Uses a preorder traversal to print the data from each node at
   * or below this node of the binary tree.
   * <DT><B>POSTCONDITION:</B><DD>:
   *   The data of this node and all its descendants have been
   *   writeen by System.out.println( ) using a preorder traversal.
   **/
  public void preorderPrint( )  {
    System.out.println(data);
    if (left != null)
      left.preorderPrint( );
    if (right != null)
      right.preorderPrint( );
  } 
  
  //////////////////////////////////////////////////////////////////
  /**
   * Uses a postorder traversal to print the data from each node at
   * or below this node of the binary tree.
   * <DT><B>POSTCONDITION:</B><DD>:
   *   The data of this node and all its descendants have been
   *   writeen by System.out.println( ) using a postorder traversal.
   **/
  public void postorderPrint( ) {
    if (left != null)
      left.postorderPrint( );
    if (right != null)
      right.postorderPrint( );
    System.out.println(data);
  }   
  
  //////////////////////////////////////////////////////////////////
  /**
   * Uses an inorder traversal to print the data from each node at
   * or below this node of the binary tree, with indentations to
   * indicate the depth of each node.
   * @param depth
   *   the depth of this node (with 0 for root, 1 for the root's
   *   children, and so on)
   * <DT><B>PRECONDITION</B><DD>:
   *   depth is the depth of this node.
   * <DT><B>POSTCONDITION:</B><DD>:
   *   The data of this node and all its descendants have been
   *   writeen by System.out.println( ) using an inorder traversal.
   *   The indentation of each line of data is four times its depth
   *   in the tree. A dash "--" is printed at any place where a
   *   child has no sibling.
   **/
  public void print(int depth)  {
    int i;

    
    // Print the left subtree (or a dash if there is a right child
    // and no left child)
    if (left != null)
      left.print(depth+1);
    else if (right != null) {
      for (i = 1; i <= depth+1; i++)
        System.out.print("    ");
      System.out.println("--");
    }
    
    
    // Print the indentation and the data from the current node:
    for (i = 1; i <= depth; i++)
      System.out.print("    ");
    System.out.println(data);
    
    // Print the left subtree (or a dash if there is a left child
    // and no left child)
    if (right != null)
      right.print(depth+1);
    else if (left != null)  {
      for (i = 1; i <= depth+1; i++)
        System.out.print("    ");
      System.out.println("--");
    }
  }
  
  public void printChart(int depth)  {
    int i;

    
    // Print the left subtree (or a dash if there is a right child
    // and no left child)
    if (left != null)
      left.printChart(depth+1);
    else if (right != null) {
      for (i = 1; i <= depth+1; i++)
        System.out.print("");
     // System.out.println("");
    }
    
    
    // Print the indentation and the data from the current node:
    
    System.out.println(data);
    
    // Print the left subtree (or a dash if there is a left child
    // and no left child)
    if (right != null)
      right.printChart(depth+1);
    else if (left != null)  {
      for (i = 1; i <= depth+1; i++)
        System.out.print("");
      //System.out.println("");
    }
  }
  
  
  //////////////////////////////////////////////////////////////////
  /**
   * Remove the leftmost most node of the tree with this node as its
   * root.

   * <DT><B>POSTCONDITION:</B><DD>:
   *   The tree starting at this node has had its leftmost node
   *   removed (i.e., the deepest node that can be reached by
   *   following left links). The return value is a reference to the
   *   root of the new (smaller) tree. This return value could be
   *   null if the original tree had only one node (since that one
   *   node has now been removed).
   **/
  public TNode<E> removeLeftmost( )  {
    if (left == null)  return right;
    
    left = left.removeLeftmost( );
    if (left != null)  left.setParent(this);
    
    return this;
  }
  
  
  //////////////////////////////////////////////////////////////////
  /**
   * Remove the rightmost most node of the tree with this node as
   * its root.

   * <DT><B>POSTCONDITION:</B><DD>:
   *   The tree starting at this node has had its rightmost node
   *   removed (i.e., the deepest node that can be reached by
   *   following right links). The return value is a reference to
   *   the root of the new (smaller) tree.  This return value could
   *   be null if the original tree had only one node (since that
   *   one node has now been removed).  
   **/
  public TNode<E> removeRightmost( ) {
    if (right == null)  return left;
    
    right = right.removeRightmost( );
    if (right != null)  right.setParent(this);

    return this;
  }
  //////////////////////////////////////////////////////////////////
}
