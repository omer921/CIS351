import java.util.*;

public class test {
  public static void main(String[] args)
  {
    Random gen = new Random();
    Treap head = new Treap (7, 1);
    int random = gen.nextInt(20)+1;
    head.addTreap(new Treap(4, gen.nextInt(20)+1));
    head.addTreap(new Treap(12, gen.nextInt(20)+1));
    head.addTreap(new Treap(6, gen.nextInt(20)+1));
    head.addTreap(new Treap(20, gen.nextInt(20)+1));

    head.print(5);
    System.out.println();
    System.out.println();
    System.out.println("SplitAt 5");
    System.out.println();
    System.out.println();
    Treap newH = head.splitAt(5);
    newH.print(6);
    System.out.println();
    System.out.println();
    System.out.println("Rotate Right");
    System.out.println();
    System.out.println();
    head.rotateRight();
    head.print(5);
    //System.out.println(head.isLeaf());
    System.out.println();
    System.out.println();
    head.rotateLeft();
    System.out.println("Rotate Left");
    System.out.println();
    head.print(5);
    
    
  }
}