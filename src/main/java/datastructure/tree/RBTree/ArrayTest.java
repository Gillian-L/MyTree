package datastructure.tree.RBTree;


import java.util.Random;

/**
 * @Author: Gillian
 * @Date: 2021/1/20-9:40
 * @Description: Gillian_pro:datastructure.tree.RBTree
 * @Version: 1.0
 */
public class ArrayTest {

    public static void main(String[] args) {
        int num = 20;
        int[] array = new int[num];
        Random random = new Random();
        RBTree<Integer> bst = new RBTree<>();
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
        bst.printSequencebyFloor();
        System.out.println("------------");
    }


}
