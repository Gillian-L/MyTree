package datastructure.tree.AVLTree;

/**
 * @Author: Gillian
 * @Date: 2021/1/7-14:01
 * @Description: Gillian_pro:datastructure.tree.AVLTree
 * @Version: 1.0
 */
public class AVLTest {

    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<>();
        avlTree.add(20).add(10).add(30).add(40).add(20).add(10).add(30).add(40).add(20).add(10).add(30).add(40)
                .add(10).add(30).add(40).add(10).add(30).add(40).add(20).add(20);
        avlTree.printSequencebyFloor();
        avlTree.delete(40);
        avlTree.printSequencebyFloor();
        System.out.println(avlTree.search(20));
//avlTree.printLeft();

        /*BSTree<Integer> bsTree = new BSTree<>();
        bsTree.add(1);
        bsTree.printSequencebyFloor();*/
    }
}
