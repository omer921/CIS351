 @SuppressWarnings("unchecked") 
 
/** Tests of the SortedList class. 
 */ 
class SLTests {
  ///////////////////////////////////////////////////////
  public static void main(String[ ] args) {
    SortedList lst0 = new SortedList( );
    SortedList lst1 = new SortedList( );
    SortedList lst2 = new SortedList( );
    SortedList lst3 = new SortedList( );
    SortedList lst4 = new SortedList( );
    //SortedList lst6 = new SortedList(1,2,3,5);
    
    System.out.println("This: " + lst1 + " should be the empty list ");
    // should be []
    
    lst1.insert(12,2,29,0);  
    //lst1.insert(12);
    System.out.println("This: " + lst1 + " should be [0,2,12,29]");
    //System.out.println(lst1.size());
    
    //lst6.insert();
    //System.out.println("This: " + lst6 + " should be [1,2,3,4]");
    
    check("insert test 1",new SortedList(29,12,2,0),lst1);
    
    lst2.insert(1,3,5,15,20,30); 
    check("insert test 2",new SortedList(30,20,15,5,3,1),lst2);
    
    lst3 = SortedList.merge(lst1,lst2);
    check("merge test 1",new SortedList(0,1,2,3,5,12,15,20,29,30),lst3);

    lst3.remove(0,15,30);
    check("remove test 1a",new SortedList(0,2,12,29),lst1);
    check("remove test 1b",new SortedList(1,3,5,15,20,30),lst2);
    check("remove test 1c",new SortedList(1,2,3,5,12,20,29),lst3);
    System.out.println("size of list3: " + lst3.size());
    Stepper it = lst3.iterator();
    System.out.print("This: ");
    while (it.hasNext()) System.out.print(it.next() + " ");
    System.out.println(" should be: 1 2 3 5 12 20 29");
     
    SortedList lsts[] = { new SortedList(), 
                          new SortedList(5), 
                          new SortedList(3,5), 
                          new SortedList(3,5,12), 
                          new SortedList(2,3,5,12), 
                          new SortedList(2,3,5,12,20), 
                          new SortedList(1,2,3,5,12,20)}; 
    while (!lst3.isEmpty()) {
      if (lst3.size()%2==0) 
        lst3.remove(lst3.smallest());
      else
        lst3.remove(lst3.largest());
      check("remove test ",lsts[lst3.size()],lst3);
    }
   
    SortedList lst5 = new SortedList( );
    
    lst5.insert(1,3,5,15,20,30); 
    System.out.println("smallest in list5: " + lst5.smallest());
    System.out.println("largest in list5: " + lst5.largest());
    System.out.println("is 3 a member: " + lst5.isMember(3));
    System.out.println("is 70 a member: " + lst5.isMember(70));
    System.out.println("size of list5: " + lst5.size());
    // ADD MORE TESTS!!

  } // end main
  /** Check that SortedLists expected and actual are equal and fuss if they are not.
    * @param tag       a String to identify the test
    * @param expected  the expected SortedList
    * @param actual    the actual SortedList
   */
  public static void check(String tag, SortedList expected, SortedList actual) {
    if (!expected.equals(actual))
      System.out.println(tag + " Mismatch: " + expected + " and " + actual);
  
  }
    
} // end SLTests