package datastructure.tree.RBTree;

import datastructure.Queue.Queue_;
import datastructure.tree.BSTTree.BSTree;
import datastructure.tree.BSTTree.EqualNode;
import datastructure.tree.BSTTree.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: Gillian
 * @Date: 2021/1/18-13:36
 * @Description: Gillian_pro:datastructure.tree.RBTree
 * @Version: 1.0
 */
public class RBTree<E extends Comparable> extends BSTree<E> {
   // private TreeNode_RB root;
    public RBTree add(E element){
        if(root==null) root=new TreeNode_RB(element,null,null, TreeNode_RB.Color.Black,null);
        else add(root,element);
        return this;
    }

    private void add(TreeNode node,E element){
        E element_comp=(E)node.getValue();
        if(element.compareTo(element_comp)<0){
            if(node.getLeft()==null){
                TreeNode_RB node_rb = new TreeNode_RB(element,null,null, TreeNode_RB.Color.Red,(TreeNode_RB) node);
                node.setLeft(node_rb);
                fixUp(node_rb);
            }
            else {
                add(node.getLeft(),element);
            }
        }
        else if(element.compareTo(element_comp)>0){
            if(node.getRight()==null){
                TreeNode_RB node_rb = new TreeNode_RB(element,null,null, TreeNode_RB.Color.Red,(TreeNode_RB) node);
                node.setRight(node_rb);
                fixUp(node_rb);
            }
            else {
                add(node.getRight(),element);
            }
        }
        else {
            if(node.getEqualNode()==null){
                node.setEqualNode(new EqualNode(element,null));
            }
            else{
                EqualNode tmp = node.getEqualNode();
                while(tmp.getNext()!=null){
                  tmp=tmp.getNext();
                }
                tmp.setNext(new EqualNode(element,null));
            }
        }
    }

    public void printSequencebyFloor(){
        if(root==null) System.out.println("null");
        else {
            Queue_<TreeNode_RB> queue = new Queue_<>();
            ArrayList<Integer> numList = new ArrayList<>();
            queue.add((TreeNode_RB) root);
            int floor =0;int count=0;int tmp_num =0;
            numList.add(floor,1);
            while (!queue.isEmpty()) {
                TreeNode_RB node = queue.poll();
                if(node.getColor()== TreeNode_RB.Color.Black){
                    if(node.getParent()!=null){
                    System.out.print(node.getValue()+"("+node.getParent().getValue()+")"+" ");
                    }
                    else{
                        System.out.print(node.getValue()+" ");
                    }
                }
                else{
                    System.out.print("("+node.getValue()+")"+"("+node.getParent().getValue()+")"+" ");
                }
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

                if(node.getLeft()!=null)queue.add((TreeNode_RB) node.getLeft());
                if(node.getRight()!=null)queue.add((TreeNode_RB) node.getRight());
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
                if(node.getEqualNode()!=null){
                    EqualNode tmp = node.getEqualNode();
                    list.add((E)tmp.getValue());
                    while(tmp.getNext()!=null){
                        tmp=tmp.getNext();
                        list.add((E)tmp.getValue());
                    }
                }
                break;
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

    private void fixUp(TreeNode_RB node){
        TreeNode_RB parent;

        while((parent=node.getParent())!=null&&parent.getColor()== TreeNode_RB.Color.Red){
            TreeNode_RB grand = parent.getParent();
            if (parent == grand.getLeft()) {
                // Case 1条件：叔叔节点是红色
                TreeNode_RB uncle = (TreeNode_RB) grand.getRight();
                if ((uncle != null) && uncle.getColor() == TreeNode_RB.Color.Red) {
                    uncle.setColor(TreeNode_RB.Color.Black);
                    parent.setColor(TreeNode_RB.Color.Black);
                    grand.setColor(TreeNode_RB.Color.Red);
                    node = grand;
                    //continue;
                } else if (parent.getLeft() == node) {
                    parent.setColor(TreeNode_RB.Color.Black);
                    grand.setColor(TreeNode_RB.Color.Red);
                    rightRotate(grand);
                    break;
                }
                else{
                    node.setColor(TreeNode_RB.Color.Black);
                    grand.setColor(TreeNode_RB.Color.Red);
                    leftRightRotate(grand);
                    break;
                }
            }
            else if(parent == grand.getRight()){
                TreeNode_RB uncle = (TreeNode_RB) grand.getLeft();
                if ((uncle != null) && uncle.getColor() == TreeNode_RB.Color.Red) {
                    uncle.setColor(TreeNode_RB.Color.Black);
                    parent.setColor(TreeNode_RB.Color.Black);
                    grand.setColor(TreeNode_RB.Color.Red);
                    node = grand;
                    //continue;
                }else if (parent.getRight() == node) {
                    parent.setColor(TreeNode_RB.Color.Black);
                    grand.setColor(TreeNode_RB.Color.Red);
                    leftRotate(grand);
                    break;
                }
                else{
                    node.setColor(TreeNode_RB.Color.Black);
                    grand.setColor(TreeNode_RB.Color.Red);
                    RightleftRotate(grand);
                    break;
                }
            }
        }
        ((TreeNode_RB)root).setColor(TreeNode_RB.Color.Black);
        ((TreeNode_RB)root).setParent(null);
    }

    private void rightRotate(TreeNode_RB a) {
        TreeNode_RB p = a.getParent();
        TreeNode b = a.getLeft();
        a.setLeft(b.getRight());
        if(b.getRight()!=null){
            ((TreeNode_RB)b.getRight()).setParent(a);
        }
        b.setRight(a);a.setParent((TreeNode_RB) b);
        if(p==null){root=b;}
        else if (p.getLeft()==a){
            p.setLeft(b);
            ((TreeNode_RB) b).setParent(p);
        }
        else {
            p.setRight(b);
            ((TreeNode_RB) b).setParent(p);
        }
    }

    private void leftRotate(TreeNode_RB a) {
        TreeNode_RB p = a.getParent();
        TreeNode b = a.getRight();
        a.setRight(b.getLeft());
        if(b.getLeft()!=null){
            ((TreeNode_RB)b.getLeft()).setParent(a);
        }
        b.setLeft(a);a.setParent((TreeNode_RB) b);
        if(p==null){root=b;}
        else if (p.getLeft()==a){
            p.setLeft(b);
            ((TreeNode_RB) b).setParent(p);
        }
        else {
            p.setRight(b);
            ((TreeNode_RB) b).setParent(p);
        }
    }

    private void RightleftRotate(TreeNode_RB node) {
        TreeNode_RB p = node.getParent();
        rightRotate((TreeNode_RB) node.getRight());
        leftRotate(node);
    }

    private void leftRightRotate(TreeNode_RB node) {
        TreeNode_RB p = node.getParent();
        leftRotate((TreeNode_RB) node.getLeft());
        rightRotate(node);
    }

}
