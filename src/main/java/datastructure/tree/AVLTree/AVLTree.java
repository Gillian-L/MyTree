package datastructure.tree.AVLTree;

import datastructure.Queue.Queue_;
import datastructure.Stack.Stack_;
import datastructure.tree.BSTTree.BSTree;
import datastructure.tree.BSTTree.TreeNode;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: Gillian
 * @Date: 2021/1/5-14:49
 * @Description: Gillian_pro:datastructure.tree.AVLTree
 * @Version: 1.0
 */
public class AVLTree<E extends Comparable> extends BSTree<E> {
   // private TreeNode root;

    public AVLTree add(E element){
        if(root==null) root=new TreeNode_(element,null,null,1);
        else add(root,element,null);
        return this;
    }

    public int height(TreeNode node){
        if (node==null) return 0;
        else {
            int lHeight = height(node.getLeft());
            int rHeight = height(node.getRight());
            return (lHeight>rHeight)?lHeight+1:rHeight+1;
        }
    }

    public List<E> search(E element){
        TreeNode node = root;
        // if (root==null) return null;
        List<E> list = new ArrayList<>();
        Queue_<TreeNode> queue_equal = new Queue_<>();
        while (node!=null||!queue_equal.isEmpty()) {
            while (node != null) {
                E element_node = (E) node.getValue();
                if (element.compareTo(element_node) == 0) {
                    list.add(element_node);
                    queue_equal.add(node.getLeft()); //旋转会破坏相同元素左右顺序，无法保证在右边
                    node = node.getRight();
                } else if (element.compareTo(element_node) > 0) {
                    node = node.getRight();
                } else if (element.compareTo(element_node) < 0) {
                    node = node.getLeft();
                }
            }
            node=queue_equal.poll();
        }
        return list;
    }

    public void delete(E element){
        TreeNode node = root;
        TreeNode pre_node = null;
        Stack_<TreeNode> stack_balence = new Stack_<>();
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
                        node=node.getLeft();//avl相等值可能在左子树
                    }
                    else if (pre_node.getLeft()==node){
                        pre_node.setLeft(node.getLeft());
                        node=node.getLeft();
                    }
                    else if(pre_node.getRight()==node){
                        pre_node.setRight(node.getLeft());
                        node=node.getLeft();
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
                stack_balence.push(node);
                stack_balence.push(pre_node);
                pre_node=node;
                node=node.getRight();
            }
            else if(element.compareTo(element_node)<0){
                stack_balence.push(node);
                stack_balence.push(pre_node);
                pre_node=node;
                node=node.getLeft();
            }
        }
        while(!stack_balence.isEmpty()){
            TreeNode p = stack_balence.pop();
            TreeNode n = stack_balence.pop();
            balance(n,p);
        }
    }

    private void add(TreeNode node, E element,TreeNode pre_node) {
       TreeNode p = pre_node;
        E element_comp=(E)node.getValue();
        if(element.compareTo(element_comp)<0){
            if(node.getLeft()==null){
                node.setLeft(new TreeNode_(element,null,null,1));
            }
            else {
               // pre_node = node;
                add(node.getLeft(),element,node);
                int lh = height(node.getLeft());
                int rh = height(node.getRight());
                if(lh-rh>=2){
                    if(element.compareTo((E)node.getLeft().getValue())<0)
                        rightRotate(node,p);//LL
                    else
                       leftRightRotate(node,p);//LR
                }

                }
        }
        else{
            if(node.getRight()==null){
                node.setRight(new TreeNode_(element,null,null,1));
            }
            else {
                add(node.getRight(),element,node);
                int lh = height(node.getLeft());
                int rh = height(node.getRight());
                if(rh-lh>=2){
                    if(element.compareTo((E)node.getRight().getValue())>=0)
                        LeftRotate(node,p);//RR
                    else
                        RightleftRotate(node,p);//RL

            }
            }
        }
    }

    private void RightleftRotate(TreeNode node, TreeNode p) {
        rightRotate(node.getRight(),node);
        LeftRotate(node,p);
    }

    private void LeftRotate(TreeNode a, TreeNode p) {
        TreeNode b = a.getRight();
        a.setRight(b.getLeft());
        b.setLeft(a);
        if(p==null){root=b;}
        else if (p.getLeft()==a){
            p.setLeft(b);
        }
        else {
            p.setRight(b);
        }
    }

    private void leftRightRotate(TreeNode node, TreeNode p) {
            LeftRotate(node.getLeft(),node);
            rightRotate(node,p);
    }

    private void rightRotate(TreeNode a,TreeNode p) {
        TreeNode b = a.getLeft();
        a.setLeft(b.getRight());
        b.setRight(a);
        if(p==null){root=b;}
        else if (p.getLeft()==a){
            p.setLeft(b);
        }
        else {
            p.setRight(b);
        }
        //return b;
    }

    private void balance(TreeNode t,TreeNode p){
        if(t==null) return;
        //左子树比右子树高，左右或左左
        if(height(t.getLeft())-height(t.getRight())>=2){
            //左子树的左子树比左子树的右子树高，左左；
            //这里等号是为了单旋转，双旋转也是可以的，单旋简单
             /*
　　　　　　　　　　等号出现，表示左子树的左子树和左子树的右子树高度相等，但是t的左子树高度大于右子树，所以，这是删除元素造成的。
　　　　　　　　　　插入是不能做成这种情况，因为插入之前就会是不平衡的。这里使用单旋转，简单。
　　　　　　　　*/
            if(height(t.getLeft().getLeft())>=height(t.getLeft().getRight())){
                rightRotate(t,p);
            }else{
                leftRightRotate(t,p);
            }
        }else if(height(t.getRight())-height(t.getLeft())>=2){ //右子树比左子树高，右右或右左
                if(height(t.getRight().getRight())>=height(t.getRight().getLeft()))
                    LeftRotate(t,p);//右右
                else
                    RightleftRotate(t,p);
            }
        //t.height=Math.max(height(t.left), height(t.right))+1;
    }

}
