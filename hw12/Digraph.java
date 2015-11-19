import java.util.LinkedList; // a doubly linked list library
import java.util.Arrays;     // an array utility library



/******************************************************************************
 A digraph is an array of linkedlists which shows where each node points to
  * <p>*/

@SuppressWarnings("unchecked")
public class Digraph {
 
  
  int clock;          // global exploration clock
  boolean hasCycle;   // global exploration flag
  //Color[] color;      // table of dfs-colorings
  // private static int[] preNum;    // not used
   int[] postNum;      // table of post-visit numbers
  
  // instance variables
   int verts;                 // number of verts in graph
  

  LinkedList<Integer>[] adj;
  /** the constructor for the Digraph
    * @param n
    *   n is how big the array should be
    **/
  public Digraph(int n)
  {
    verts = n;
    adj = (LinkedList<Integer>[]) new LinkedList[n];
    for (int i = 0; i < verts; i++)
      adj[i] = new LinkedList<Integer>();
  }
  
  
  
  /** adds a vertex to the graph 
   * @param i
   *   the vertex it is adding the number to
   * @param j
   *    the new number the vertex points to 
   **/
  
  public void add(int i, int j)
  {
    if (i<0 || j<0 || i>= verts || j >= verts)
      throw new IllegalArgumentException();
    Integer jj = new Integer(j);
    if (!adj[i].contains(jj))
      adj[i].add(jj);
  }
  
  
  /** removes a vertex to the graph 
   * @param i
   *   the vertex it is removing the number from
   * @param j
   *    the  number the vertex no longer points to 
   **/
  
  public void remove(int i, int j)
  {
    if (i<0 || j<0 || i>= verts || j >= verts)
      throw new IllegalArgumentException();
    Integer jj = new Integer(j);
    if (adj[i].contains(jj))
      adj[i].remove(jj);
  }
  
  /** prints out the whole array of linkedlists **/
  
  public void print()
  {
    for (int i = 0; i < adj.length; i++){
      Object[] arr = adj[i].toArray();
      System.out.print(i + ": ");
      for (int j = 0; j < arr.length; j++){
        System.out.print(arr[j] + " ");
      }
      System.out.println();
    }
  }
  
  
  /** checks if a certain vertex is reachable from a certain vertex
    * @param cur
    *   the vertex to start at
    * @param dest
    *   the vertex it is trying to get to
    **/
  
  public boolean reachable(int cur, int dest)
  {
    boolean ans = false;
    if (cur == dest)
    {
       throw new IllegalArgumentException();
      
    }
    else{
      Integer[] linkedArry = (Integer[]) adj[cur].toArray(new Integer[adj[cur].size()]);
      for (int i = 0; i < linkedArry.length; i++)
      {
       if (linkedArry[i] == dest)
       {       
         ans = true;
       }
       else if (ans == true)
         break;
       else
       {
         ans = (false || (reachable(linkedArry[i], dest))) ;
       }
      }
    }
    return ans;
  }
  /** checks if the digraph has a cycle in it **/
  public boolean isAcyclic()
  {
    
    try {
    return reachable(0,3);
    }
    catch (StackOverflowError e)
    {
      return false;
    }
      
    
  }
  /*
  public Digraph tSort()
  {
    return new Digraph(0); //***********************************
  }
  */



public static void main(String argv[]) {
    Digraph g = new Digraph(14);
    
    g.add(0,4);    g.add(0,5);    g.add(0,11);
    g.add(1,2);    g.add(1,4);    g.add(1,8);
    g.add(2,5);    g.add(2,6);    g.add(2,9);
    g.add(3,2);    g.add(3,6);    g.add(3,13);
    g.add(4,7);
    g.add(5,8);    g.add(5,12);  
    g.add(6,5);  
    g.add(8,7);  
    g.add(9,10);   g.add(9,11);  
    g.add(10,13);  
    g.add(12,9);  
    g.add(13,1);
    
    g.print();
    
    

    g.remove(13,1);
    
    System.out.println("remove" + "\n" + "\n");
    g.print();
    
    System.out.println("g.reachable(0,13) = " + g.reachable(0,13));
    //System.out.println("Starting second search");
    System.out.println("g.reachable(0,3)  = " + g.reachable(0,3));
    
    g.add(13,1);
    System.out.println("g.isAcyclic()     = " + g.isAcyclic());
    g.remove(13,1); // RECHECK!!!
    System.out.println("g.isAcyclic()     = " + g.isAcyclic());    
    
    //int[] ts1 = g.tSort();
    //System.out.println(Arrays.toString(ts1));
    
  }
}

 