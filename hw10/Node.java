/******************************************************************************
  * A Node<E> provides a generic node for a linked list. Each node  contains a 
  * piece of data (which is a reference to an E object) and a link (which is a 
  * reference to the next node of the list). The reference stored in a node can 
  * be null. 
  * <p>
  * This is based on
  * <a href="http://www.cs.colorado.edu/~main/edu/colorado/nodes/Node.java">
  * Mike Main's Node class</a>.
  ******************************************************************************/
public class Node<E> {

  // Invariant of the Node class:
  //   1. Each node has one reference to an E Object, stored in the instance
  //      variable data.
  //   2. For the final node of a list, the link part is null.
  //      Otherwise, the  link part is a reference to the
  //      next node of the list.
  private E data;
  private Node<E> link;   
  
  /**
   * Initialize a node with a specified initial data and link to the next node. 
   * @param initData
   *   the initial data of this new node
   * @param initLink
   *   a reference to the node after this new node--this reference may be null
   *   to indicate that there is no node after this new node.
   **/   
  public Node(E initData, Node<E> initLink) {
    data = initData;
    link = initLink;
  }
  
  /**
   * Construct a list with the values in ds. 
   * @param ds the values for the list
   **/   
  @SuppressWarnings("unchecked")
  public Node(E... ds) {
    if (ds==null || ds.length == 0) 
      throw new IllegalArgumentException("Null or length 0 array of values");
    link = null;
    for (int i = ds.length-1; i>0; i--) 
      link = new Node(ds[i],link);
    data = ds[0];
  }
    
  /**
   * Adds a new node after this node.   
   * @param element
   *   the data to place in the new node 
   **/
  public void addNodeAfter(E element) {
    link = new Node<E>(element, link);
  }          
  
  /**
   * Gets the data field from this node.   
   * @return the data from this node
   **/
  public E getData( ) {
    return data;
  }
  
  /**
   * Gets a reference to the next node after this node. 
   * @return
   *   a reference to the node after this node (or the null reference if there
   *   is nothing after this node)
   **/
  public Node<E> getLink( ) {
    return link;                                               
  } 
  
  /**
   * Copy a list.
   * @param source
   *   the head of a linked list that will be copied (which may be an empty 
   *   list in where source is null)
   * @return
   *   A reference to the head of a copy of the list that starts with source.
   **/ 
  public static <E> Node<E> listCopy(Node<E> source) {
    if (source == null) return null;
    else                return new Node<E>(source.data, 
                                           listCopy(source.link));
  }
  
  /**
   * Copy a list, returning both a head and tail reference for the copy.
   * @param source
   *   the head of the linked list to be copied
   * @return
   *   The method has made a copy of the linked list starting at source.  
   *   The return value is an array where 
   *     the [0] element is a head reference for the copy and 
   *     the [1] element is a tail reference for the copy.
   **/
  @SuppressWarnings("unchecked")
  public static <E> Node<E>[ ] listCopyWithTail(Node<E> source) {
    Node<E>[ ] answer;
    
    if (source == null)
      answer = (Node<E>[]) new Object[2]; // The answer has two null references .
    else {
      answer = listCopyWithTail(source.link);
      answer[0] = new Node<E>(source.data,answer[0]);
      if (answer[1]==null) answer[1] = answer[0]; // the one elm list case
    }
    return answer;
  }
  
  /**
   * Compute the number of nodes in a linked list.
   * @param head
   *   the head reference for a linked list 
   * @return
   *   the number of nodes in the list with the given head 
   **/   
  public static <E> int listLength(Node<E> head) {
    if (head == null) return 0;
    else              return 1+listLength(head.link);
  }
  
  /**
   * Copy part of a list, providing a head and tail reference for the new copy. 
   * <p>
   * <b>Precondition:</b> 
   *    We assume <code>start</code> and <code>end</code> are nonempty
   *    and that <code>end</code> is in the list headed by <code>start</code>.
   * @param start reference to the start of the list to copy.
   * @param end   reference to the end of the list to copy.
   * @return
   *   The return value is an array where the [0] component is a head reference 
   *   for the copy and the [1] component is a tail reference for the copy.
   * @exception IllegalArgumentException
   *   Indicates one of start and end is null   
   **/   
  @SuppressWarnings("unchecked")
  public static <E> Node<E>[ ] listPart(Node<E> start, Node<E> end) {
    Node<E>[ ] answer;
    
    if (start == null || end == null)
      throw new IllegalArgumentException("end node was not found on the list");      
    else if (start == end) {
      answer = (Node<E>[]) new Object[2];
      answer[0] = answer[1] = new Node<E>(end.data,null);
    }
    else {
      answer = listPart(start.link,end);
      answer[0] = new Node<E>(start.data,answer[0]);
    }
    return answer;
  }        
  
  /**
   * Find a node at a specified position in a linked list, counting from 1.
   * @param head
   *   the head reference for a linked list (which may be an empty list in
   *   which case the head is null)
   * @param position
   *   a node number
   * @return
   *   The return value is a reference to the node at the specified position in
   *   the list; or null if there is no such position..   
   **/   
  public static <E> Node<E> listPosition(Node<E> head, int position) {      
    if (head == null || position < 1) return null;
    else if (position==1)             return head;
    else                              return listPosition(head.link, position-1);
  }
  
  /**
   * Search for a particular piece of data in a linked list.
   * @param head
   *   the head reference for a linked list (which may be an empty list in
   *   which case the head is null)
   * @param target
   *   a target to search for
   * @return
   *   The return value is a reference to the first node that contains the
   *   specified target. If the target is non-null, then the
   *   target.equals method is used to find such a node.
   *   The target may also be null, in which case the return value is a
   *   reference to the first node that contains a null reference for its
   *   data. If there is no node that contains the target, then the null
   *   reference is returned.     
   **/   
  public static <E> Node<E> listSearch(Node<E> head, E target) {
    if (head==null) return null;
    else if (target == head.data || target.equals(head.data))
                    return head;
    else            return listSearch(head.link,target);
  }
  
  /**
   * Removes the node after this node.   
   * @exception NullPointerException
   *   Indicates that this was the tail node of the list, so there is nothing
   *   after it to remove.
   **/
  public void removeNodeAfter( ) {
    link = link.link;
  }          
  
  /**
   * Set the data field of this node.   
   * @param newData
   *   the new data to place in this node
   **/
  public void setData(E newData)  {
    data = newData;
  }                                                               
  
  /**
   * Set the link field of this node.
   * @param newLink
   *   a reference to the node that should appear after this node in the linked
   *   list (or the null reference if there is no node after this node)
   **/
  public void setLink(Node<E> newLink) {                    
    link = newLink;
  }

}
