public class IntList { /////////////////////////////
  public IntNode head;
  // Constructor
  public IntList()               { head = null; }
  // Other methods
  public void compress()      
  {  
  IntNode lead = head.getLink();
  IntNode trail = head;
  while (lead != null)
  {
   if  (lead.getData() == trail.getData())
   {
    trail.setLink(lead.getLink());
    IntNode aLead = lead.getLink();
    lead = aLead.getLink();
    trail = trail.getLink();
   }
   else {
   lead = lead.getLink();
   trail = trail.getLink();
   }
  }
  }
} // end of the IntList class //////////////////////
