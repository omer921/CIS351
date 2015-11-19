public class TNode {
  private int data;
  private TNode left, right;
  // Constructor
  public TNode(int d, TNode lft, TNode rght) {
   data = d;  left = lft;  right = rght;
  }
  public int     getData()         { return data;  }
  public void    setData(int d)    { data = d;     }
  public TNode   getLeft()         { return left;  }
  public void    setLeft(TNode n)  { left = n;     }
  public TNode   getRight()        { return right; }
  public void    setRight(TNode n) { right = n;    }
  public boolean isLeaf()          {
    return (left==null) && (right==null);
  }
  public static int depth(TNode r) 
  { 
    if (r == null)
      return -1;
    
    if (r.isLeaf())
      return 0;
    else
    {
      int lAns = depth(r.getLeft()) +1;
      int rAns = depth(r.getRight()) +1;
      if (lAns > rAns)
        return lAns;
      else
        return rAns;
    }
  }
} // end of the TNode class