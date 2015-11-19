// import edu.colorado.nodes.*;

/**********************************************************
  * 
  * <h1>Collection of nodes constructed in a binary tree, if the node has leafs, then the left leaf is smaller
  * that the value of the parent leaf and the right leaf has a greater value the the parent leaf</h1>
  * 
  **********************************************************/
import java.util.*;
@SuppressWarnings("unchecked")

public class TreeBag {
  ////////////////////////////////////////////////////////
  /** 
   * <h1>Creates a new TNode of and it store data of type StringOcc called root</h1>
   **/
  protected TNode<StringOcc> root;
  
  
  ////////////////////////////////////////////////////////
  /** 
   * <h1>Constructor that creates a new TreeBag</h1>
   **/
  public TreeBag() {
    root = null;
  }
  
  
  ////////////////////////////////////////////////////////
  /** 
   * <h1>Adds the new string occurence into the tree bag</h1>
   *
  
  // @param requires a parameter of type StringOcc */
  public void add(StringOcc  str) {
    if (str==null)  return;
    
    if(!exist(root, str))
    {
      root = addHelp(root,str,root);
      //System.out.println("Does not exist: " + str); 
    }
    else
    {
      //System.out.println("exists adding the numbers to " + str);
      
      TNode<StringOcc> tNode = findNode(root, str); 
      Node nNode = str.head;
      tNode.getData().head.addNodeAfter(str.head.getData());
      //System.out.println("this is now " + tNode.getData());
    }
  }
  
   ////////////////////////////////////////////////////////
  /** 
   * <h1>Finds ths current node with the certain type data matching the TNode given in the parameter </h1>
   *
  
  // @param requires a parameter of type StringOcc and Tnode */
  
  public TNode findNode(TNode root, StringOcc str)
  {
    TNode ans = null;
    if (root == null){
      ans = null;
      return ans; 
    }
    
    String r = "" + root.getData();
    String s = "" + str;
    Scanner scanR = new Scanner(r);
    Scanner scanS = new Scanner(s);
    int rS = r.indexOf(':');
    int sS = s.indexOf(':');

    if (r.substring(0,rS).equals(s.substring(0,sS))){
      //System.out.println("found the node returning");
      ans = root;
      return ans;
    }
    
    else
    {
      if (findNode(root.getLeft(), str) != null)
        ans = findNode(root.getLeft(), str);
      else
        ans = findNode(root.getRight(), str);
    }
    
    return ans;
  }
  
  
   ////////////////////////////////////////////////////////
  /** 
   * <h1>checks if a certain node exists in the tree </h1>
   *
  
  // @param requires a parameter of type StringOcc and TNode*/
  
  
  public boolean exist(TNode root, StringOcc str)
  {
    boolean ans = false;
    if (root == null)
      return false;
    else
    {
      String r = "" + root.getData();
      String s = "" + str;
      Scanner scanR = new Scanner(r);
      Scanner scanS = new Scanner(s);
      int rS = r.indexOf(':');
      int sS = s.indexOf(':');
      //System.out.println (root.getData());
      //System.out.println("checking " + r.substring(0,rS) + " vs " + s.substring(0,sS));
      if (r.substring(0,rS).equals(s.substring(0,sS))){
        //System.out.println("this exists " + str);
        return true;
      }
      else
      {
        //System.out.println("recuring again");
        return exist(root.getLeft(), str) || exist(root.getRight(), str);
        
      }
    }
    //return ans;
  }
   ////////////////////////////////////////////////////////
  /** 
   * <h1>Adds the new string occurence into the tree bag</h1>
   *
  
  // @param requires a parameter of type StringOcc */
  private TNode<StringOcc> addHelp(TNode<StringOcc> nd, 
                                   StringOcc        str, 
                                   TNode<StringOcc> mom)  {
    if (nd == null)
      return new TNode<StringOcc>(str,mom,null,null);
    
    int comp = (nd.getData()).compareTo(str);
    
    if (comp < 0)      // nd.data << str
      nd.setRight(addHelp(nd.getRight(),str,nd));
    else if (comp > 0) // nd.data >> str
      nd.setLeft(addHelp(nd.getLeft(),str,nd));
    else               // nd.data ==== str       
      /* do nothing  */;
    
    return nd;
  }
  
  
  ////////////////////////////////////////////////////////
  /** 
   * <h1>Iterates on the treebag class by using the methods in tree walker</h1>
   **/
  public TWalker iterator() {
    /* fill in */
    return new TWalker(this);
  }
}