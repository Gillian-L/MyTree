package datastructure.tree.RBTree;


/**
 * @Author: Gillian
 * @Date: 2021/1/19-9:41
 * @Description: Gillian_pro:datastructure.tree.RBTree
 * @Version: 1.0
 */
public class RBTest {

    public static void main(String[] args) {
        RBTree<Integer> rbTree = new RBTree<>();
       // rbTree.add(20).add(10).add(30).add(40).add(10).add(20).add(6).add(50).add(60).add(25).add(28).add(10);
        rbTree.add(30).add(20).add(10);
        rbTree.printSequencebyFloor();
        rbTree.printLeft();
        System.out.println(rbTree.search(10));
    }

}
