package datastructure.tree.BSTTree;

import java.util.Random;

/**
 * @Author: Gillian
 * @Date: 2020/12/8-15:37
 * @Description: Gillian_pro:datastructure.tree.BSTTree
 * @Version: 1.0
 */
public class ArrayTest {
    public static void main(String[] args) {
        int num = 10;
        int[] array = new int[num];
        Random random = new Random();
        BSTree<Integer> bst = new BSTree<>();
        for (int i = 0; i <num ; i++) {
            array[i]= random.nextInt(1000);
        }
        for(int a:array){
            System.out.println(a);
        }
        System.out.println("------");
        for (int i = 0; i <num ; i++) {
            bst.add(array[i]);
        }
        bst.printLeft();
        System.out.println("------------");
        bst.printRoot();
        System.out.println("------------");
        bst.printRoot_withStack();
        System.out.println("------------");
       // bst.printSequencebyFloor();
    }

}
