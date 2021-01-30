package datastructure.tree.BSTTree;

/**
 * @Author: Gillian
 * @Date: 2020/12/8-14:10
 * @Description: Gillian_pro:datastructure.tree.BSTTree
 * @Version: 1.0
 */
public class BSTTest {

    public static void main(String[] args) {
        BSTree<Integer> bst = new BSTree<>();
        bst.add(7);
        bst.add(5);
        bst.add(9);
        bst.add(4);
        bst.add(4);
        bst.add(2);
        bst.add(10);
        bst.add(20);
        bst.add(20);
        bst.add(6);
        bst.add(16);
        bst.add(5);
        bst.add(6);
        bst.add(20);
      //  bst.add(7).add(9).add(18).add(9).add(20).add(9).add(11).add(10).add(9);
      //  bst.add(7).add(9).add(18).add(20);
        bst.printLeft();
        System.out.println("-----");
      //  bst.printSequence();
        bst.printSequencebyFloor();
        System.out.println("------66666----");
        bst.delete(5);
        bst.printSequencebyFloor();
       // bst.printLeft();
        System.out.println("----------");
        bst.delete(20);
        bst.printSequencebyFloor();
       // bst.printLeft();
       // bst.printRoot_withStack();
      /*  List<Integer> list = bst.search(5);
        Arrays.asList(list).forEach(System.out::println);*/

    }
}
