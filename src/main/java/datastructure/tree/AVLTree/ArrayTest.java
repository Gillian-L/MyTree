package datastructure.tree.AVLTree;

import datastructure.tree.BSTTree.BSTree;

import java.util.Random;

/**
 * @Author: Gillian
 * @Date: 2021/1/7-16:49
 * @Description: Gillian_pro:datastructure.tree.AVLTree
 * @Version: 1.0
 */
public class ArrayTest {
    public static void main(String[] args) {
        int num = 50;
        int[] array = new int[num];
        Random random = new Random();
        BSTree<Integer> bst = new BSTree<>();
        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < num; i++) {
            array[i] = random.nextInt(1000);
        }
        for (int a : array) {
            System.out.println(a);
        }
        System.out.println("------");
        for (int i = 0; i < num; i++) {
            bst.add(array[i]);
        }
        for (int i = 0; i < num; i++) {
            avl.add(array[i]);
        }
        bst.printSequencebyFloor();
        System.out.println("------");
        avl.printSequencebyFloor();
        System.out.println("------");
        bst.printLeft();
        System.out.println("------");
        avl.printLeft();
        System.out.println(bst.search(array[3]));
        System.out.println(avl.search(array[3]));
    }
}
