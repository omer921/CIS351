/******************************************************************************
  * A Treap provides a generic node for a Binary Tree. Each Treap contains a 
  * piece of data, two links which point to the left and right subtrees
  * ans its priority. The reference stored in a node can 
  * be null. 
  * <p>*/


public class Treap {
  
  int key;
  int priority;
  Treap left;
  Treap right;
  
  /**
   * Initialize a TreapNode with a specified initial data and its priority. 
   * @param key
   *   the interger value stored within the node
   * @param priority
   *   its priority in the que
   **/   
  
  
  public Treap(int key, int priority)
  {
   this.key = key; 
   this.priority = priority;
   left = null;
   right = null;
  }
  
  /**
   * Initialize a TreapNode with a specified initial data, priority, and left and right links to the subtrees.
   * @param key
   *   the interger value stored within the node
   * @param priority
   *   its priority in the que
   * @param left
   *   pointer to the left tree
   * @param right
   *   pointer to the right tree
   **/   
  
  
  public Treap(int key, int priority, Treap left, Treap right)
  {
   this.key = key; 
   this.priority = priority;
   this.left = left;
   this.right = right;
  }
  
  
  /** adds a new TreapNode to the tree if it does not match any of the requirements then it goes to the addTreap recurisive method
   * @param nNode
   *   the node that is going to be added to the tree **/
  
  public void addTreap(Treap nNode)
  {

   Boolean skip = false;
   if (nNode.priority < this.priority )
    {
      if (nNode.key < this.key)
        nNode.setLeft(this);
      else
        nNode.setRight(this);
      skip = true;
   }
   if (this.left == null)
    {
     //System.out.println("in the left null with: " + nNode.key);
      if (this.key > nNode.key)
      {
        this.setLeft(nNode);
        //System.out.println("setting left");
        skip = true;
      }
      
    }
   if (this.right == null)
   {
     //System.out.println("in the right null with: " + nNode.key);
     if (this.key < nNode.key)
     {
        this.setRight(nNode);
        //System.out.println("setting right");
        skip = true;
     }
   }
   if (!skip)
   {this.addTreapHelp(nNode); /*System.out.println("getting help with: " + nNode.key);*/}
  }
  /** the recurive method that runs on the whole tree to see where to place the new node
   * @param nNode
   *   the new node being added **/
  public void addTreapHelp(Treap nNode)
  {
    //System.out.println("Adding: " + nNode.key);
    if (this.key < nNode.key)
    {
      if (left != null)
       left.addTreapHelp(nNode);
      else
        setLeft(nNode);
    }
    else if (this.key > nNode.key) 
    {
      if(right != null)
       right.addTreapHelp(nNode);
      else
        setRight(nNode);
    }
    else {
      if (nNode.key < this.key)
      {
        this.setLeft(nNode);
      }
      else
      {
        this.setRight(nNode);
      }
    }
  }
  
  
  /** sets a the link to the current node
   * @param left
   *  the new link to the new node **/
  public void setLeft(Treap left)
  {
   this.left = left;
  }
  
  /** sets a the link to the current node
   * @param right
   *  the new link to the new node **/
  
  public void setRight(Treap right)
  {
   this.right = right; 
  }
  
  /** sets a the key to the current node
**/
  
  public void setKey()
  {
   this.key = key;
  }
  
  /** sets a the priority to the current node
 **/
  
  public void setPri()
  {
   this.priority = priority;
  }

  
  /** prints the tree according to how deep the user wants it to go
   * @praram depth
       integer value of how deep te tree should be **/
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
    System.out.println(key);
    
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
 /** rotates the tree to the right **/
 public void rotateRight()
 {
   Treap p = this.left;
   Treap newN = new Treap(this.key, this.priority, this.left, this.right);
   this.key = p.key;
   this.priority = p.priority;
   this.left = p.left;
   this.right = newN;
 }
 /** rotates the tree to the lef **/
 public void rotateLeft()
 {
   Treap p = this.right;
   Treap newN = new Treap(this.key, this.priority, this.right, this.left);
   this.key = p.key;
   this.priority = p.priority;
   this.right = p.right;
   this.left = newN;
 }
  /** checks if the node is a leaf **/
  public boolean isLeaf()
 { return ((left == null) && (right == null));}
 /** creates a new tree according to the integer value given in the parameter
  @param v
    the interger value the new tree is going to have its root as **/
 public Treap splitAt(int v)
 {
  Treap ans = new Treap (v, 0);
  if (this.isLeaf())
  {
    //System.out.println(this.key + " is a leaf");
    return this;
  }
  else
  {
   Treap nN = new Treap(this.key, this.priority);
   ans.addTreap(nN); 
   if (this.left != null){
     //System.out.println("splitting left " + left.key);
     ans.addTreap(this.left);
     //ans.addTreap(left.splitAt(v));
   }
   if (this.right != null){
     //System.out.println("adding right " + right.key);
     ans.addTreap(this.right); 
     //ans.addTreap(right.splitAt(v));
   }
  }
  return ans; 
 }
 

  
}