package datastructure.tree.BSTTree;

import datastructure.Queue.Queue_;
import datastructure.Stack.LinkedStack_;
import datastructure.Stack.Stack_;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: Gillian
 * @Date: 2020/12/7-15:58
 * @Description: Gillian_pro:datastructure.tree.BSTTree
 * @Version: 1.0
 */
public class BSTree<E extends Comparable> {
    protected TreeNode root;

    public BSTree add(E element){
        if(root==null){
            root=new TreeNode(element,null,null);
        }
        else{
            add(root,element);
        }
        return this;
    }

    public void printLeft(){
        if(root==null){
            System.out.println("null");
        }
        else{
            printLeft(root);
        }
    }

    public void printRight_withStack(){
        if(root==null){
            System.out.println("null");
        }
        else{
            printRight_withStack(root);
        }
    }

    public void printRoot(){
        if(root==null){
            System.out.println("null");
        }
        else{
            printRoot(root);
        }
    }

    public void printRoot_withStack(){
        if(root==null){
            System.out.println("null");
        }
        else{
            printRoot_withStack(root);
        }
    }

    public void printSequence(){
        if(root==null) System.out.println("null");
        else {
            Queue_<TreeNode> queue = new Queue_<>();
            queue.add(root);
            while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
                System.out.println(node.getValue());
                if(node.getLeft()!=null)queue.add(node.getLeft());
                if(node.getRight()!=null)queue.add(node.getRight());
            }
        }
    }

    public void printSequencebyFloor(){
        if(root==null) System.out.println("null");
        else {
            Queue_<TreeNode> queue = new Queue_<>();
            ArrayList<Integer> numList = new ArrayList<>();
            queue.add(root);
            int floor =0;int count=0;int tmp_num =0;
            numList.add(floor,1);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                System.out.print(node.getValue()+" ");
                int num = childrenNum(node);
                tmp_num=tmp_num+num;
                count++;
                if(count==numList.get(floor)){
                    if(tmp_num==0){
                        System.out.println("\n");
                    }
                    else {
                        floor++;
                        numList.add(floor, tmp_num);
                        count = 0;
                        tmp_num = 0;
                        System.out.println("\n");
                    }
                }

                if(node.getLeft()!=null)queue.add(node.getLeft());
                if(node.getRight()!=null)queue.add(node.getRight());
            }
            Iterator iterator = numList.iterator();
            while(iterator.hasNext()){
                System.out.print(iterator.next()+" ");
            }
            System.out.println("over");
        }
    }

    public List<E> search(E element){
        TreeNode node = root;
       // if (root==null) return null;
        List<E> list = new ArrayList<>();
        while (node!=null) {
            E element_node = (E) node.getValue();
            if (element.compareTo(element_node) == 0) {
                list.add(element_node);
                node = node.getRight();
               // if(node!=null&&element.compareTo(node.getValue())!=0) break;//右分支下可能有相等值，需要继续下去
            }
            else if (element.compareTo(element_node) > 0){
                node=node.getRight();
            }
            else if(element.compareTo(element_node)<0){
                node=node.getLeft();
            }
        }
        return list;
    }

    public void delete(E element){
        TreeNode node = root;
        TreeNode pre_node = null;
        while (node!=null){
            E element_node = (E) node.getValue();
            if (element.compareTo(element_node) == 0) {
                if (node.getLeft()==null&&node.getRight()==null){ //叶子节点
                    if(pre_node==null){
                        root=null;
                  //      node=null; //The value null assigned to 'node' is never used
                        break;
                    }
                    else if (pre_node.getLeft()==node){
                        pre_node.setLeft(null);
                        break;
                    }
                    else if(pre_node.getRight()==node){
                        pre_node.setRight(null);
                        break;
                    }
                }
                else if(node.getRight()==null){ //只有左分支
                    if(pre_node==null){
                        root=node.getLeft();
                        break;
                    }
                   /* else{
                        node=node.getLeft();
                        break;
                    }*/
                    else if (pre_node.getLeft()==node){
                        pre_node.setLeft(node.getLeft());
                        break;
                    }
                    else if(pre_node.getRight()==node){
                        pre_node.setRight(node.getLeft());
                        break;
                    }
                }
                else if (node.getLeft()==null){  //只有右分支
                    if(pre_node==null){
                        root=node.getRight();
                        node=node.getRight();
                    }
                    else if(node.getRight().getLeft()==null){ //右孩子没有左子树，直接接右孩子
                        if (pre_node.getLeft()==node) {
                            pre_node.setLeft(node.getRight());
                            node=node.getRight();
                        }
                        if(pre_node.getRight()==node){
                            pre_node.setRight(node.getRight());
                            node=node.getRight();
                        }
                    }
                    else{  //右孩子有左子树，取最左的叶子节点
                        TreeNode node_left = node.getRight().getLeft();
                        TreeNode node_left_pre = node.getRight();
                        while (node_left.getLeft()!=null){
                            node_left_pre=node_left;
                            node_left=node_left.getLeft();
                        }
                        if (pre_node.getLeft()==node) {
                            if(node_left.getRight()!=null){  //最左的叶子节点有右分支
                                node_left_pre.setLeft(node_left.getRight());
                            }
                            else {
                                node_left_pre.setLeft(null);
                            }
                            node_left.setRight(node.getRight());
                            pre_node.setLeft(node_left);
                            node=node_left;
                        }
                        else if(pre_node.getRight()==node){
                            if(node_left.getRight()!=null){  //最左的叶子节点有右分支
                                node_left_pre.setLeft(node_left.getRight());
                            }
                            else {
                                node_left_pre.setLeft(null);
                            }
                            node_left.setRight(node.getRight());
                            pre_node.setRight(node_left);
                            node=node_left;
                        }
                    }
                }
                else{ //左右分支都有
                    if(node.getRight().getLeft()==null){  //右孩子没左子树
                        if(pre_node==null){
                            node.getRight().setLeft(node.getLeft());
                            root = node.getRight();
                            node = node.getRight();
                        }
                        else if (pre_node.getLeft()==node){
                            node.getRight().setLeft(node.getLeft());
                            pre_node.setLeft(node.getRight());
                            node = node.getRight();
                        }
                        else if (pre_node.getRight()==node){
                            node.getRight().setLeft(node.getLeft());
                            pre_node.setRight(node.getRight());
                            node = node.getRight();
                        }
                    }
                    else{ //右孩子有左子树
                        TreeNode node_left = node.getRight().getLeft();
                        TreeNode node_left_pre = node.getRight();
                        while (node_left.getLeft()!=null){
                            node_left_pre=node_left;
                            node_left=node_left.getLeft();
                        }
                        if (pre_node ==null){
                            if(node_left.getRight()!=null){  //最左的叶子节点有右分支
                                node_left_pre.setLeft(node_left.getRight());
                            }
                            else {
                                node_left_pre.setLeft(null);
                            }
                            node_left.setLeft(node.getLeft());
                            node_left.setRight(node.getRight());
                            root = node_left;
                        }
                        else if (pre_node.getLeft()==node) {
                            if(node_left.getRight()!=null){  //最左的叶子节点有右分支
                                node_left_pre.setLeft(node_left.getRight());
                            }
                            else {
                                node_left_pre.setLeft(null);
                            }
                            node_left.setLeft(node.getLeft());
                            node_left.setRight(node.getRight());
                            pre_node.setLeft(node_left);
                        }
                        else if (pre_node.getRight()==node) {
                            if(node_left.getRight()!=null){  //最左的叶子节点有右分支
                                node_left_pre.setLeft(node_left.getRight());
                            }
                            else {
                                node_left_pre.setLeft(null);
                            }
                            node_left.setLeft(node.getLeft());
                            node_left.setRight(node.getRight());
                            pre_node.setRight(node_left);
                        }
                       // node_left_pre.setLeft(null);
                        node=node_left; //有可能相当值接上，不能直接跳到右孩子，从该节点将循环继续
                    }
                }
            }
            else if (element.compareTo(element_node) > 0){
                pre_node=node;
                node=node.getRight();
            }
            else if(element.compareTo(element_node)<0){
                pre_node=node;
                node=node.getLeft();
            }
        }
    }
   /* public void printbyHeight(){
        if(root==null){
            System.out.println("null");
        }
        else{
            System.out.print(root.getValue());
            printbyHeight(root);
        }
    }

    private void printbyHeight(TreeNode node){
        TreeNode left = node.getLeft();
        TreeNode right = node.getRight();
        if (left==null&&right==null) return;
        System.out.print("\n");
        if (left!=null) System.out.print(left.getValue());
        if(right!=null) System.out.print(right.getValue());
        if(left!=null) printbyHeight(left);
        if(right!=null) printbyHeight(right);
    }*/
    protected int childrenNum(TreeNode node){
        int i = 0;
        if (node.getLeft()!=null)i++;
        if(node.getRight()!=null)i++;
        return i;
    }

    private void printRoot(TreeNode node){
        if (node==null) return;
        System.out.println(node.getValue());
        printRoot(node.getLeft());
        printRoot(node.getRight());
    }

    private void printRoot_withStack(TreeNode node) {
        if (node==null) return;
        Stack_<TreeNode> stack = new Stack_<>();
        /*while (node!=null){
            System.out.println(node.getValue());
            stack.push(node);
            node=node.getLeft();
        }
        while (!stack.isEmpty()){
            node = stack.pop();
            node = node.getRight();
            while (node!=null){
                System.out.println(node.getValue());
                stack.push(node);
                node=node.getLeft();
            }
        }*/
      /*  while(node!=null||!stack.isEmpty()) {
            while (node != null) {
                System.out.println(node.getValue());
                stack.push(node);
                node=node.getLeft();
            }
            node = stack.pop();
            node = node.getRight();
        }*/

        while(node!=null){
            System.out.println(node.getValue());
           if(node.getRight()!=null) stack.push(node.getRight());
           if(node.getLeft()!=null) stack.push(node.getLeft());
            node=stack.pop();
        }


    }

    private void printLeft(TreeNode node){
            if (node==null) return;
            printLeft(node.getLeft());
            System.out.println(node.getValue());
            printLeft(node.getRight());
    }

    private void printRight_withStack(TreeNode node){
        if (node==null) return;
        LinkedStack_<TreeNode> stack = new LinkedStack_<>();
        while(node!=null||!stack.isEmpty()){
            while(node!=null) {
                stack.push(node);
                node = node.getRight();
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.println(node.getValue());
                node=node.getLeft();
            }
        }
    }

    private void add(TreeNode node,E element){
        E element_comp=(E)node.getValue();
        if(element.compareTo(element_comp)<0){
            if(node.getLeft()==null){
                node.setLeft(new TreeNode(element,null,null));
            }
            else {
                add(node.getLeft(),element);
            }
        }
        else{
            if(node.getRight()==null){
                node.setRight(new TreeNode(element,null,null));
            }
            else {
                add(node.getRight(),element);
            }
        }
    }

}
