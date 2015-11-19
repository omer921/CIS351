public class IntNode { /////////////////////////////
  private int data;
  private IntNode link;
  // Constructor
public IntNode(int d, IntNode n) { data=d; link=n;
  }
public int getData() {return data;}
    
public IntNode getLink() {return link;}
    
public void setData(int d) {data = d;}
    
public void setLink(IntNode n) { link = n; }
} // end of the IntNode class //////////////////////