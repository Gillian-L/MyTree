package datastructure.tree.RBTree;

import datastructure.tree.BSTTree.TreeNode;

/**
 * @Author: Gillian
 * @Date: 2021/1/15-16:44
 * @Description: Gillian_pro:datastructure.tree.RBTree
 * @Version: 1.0
 */
public class TreeNode_RB extends TreeNode {
    enum Color {Red,Black};
    private Color color;
    private TreeNode_RB parent;
    private Object equalNode;

    public TreeNode_RB(Object value, TreeNode left, TreeNode right, Color color, TreeNode_RB parent) {
        super(value, left, right);
        this.color = color;
        this.parent = parent;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setParent(TreeNode_RB parent) {
        this.parent = parent;
    }


    public Color getColor() {
        return color;
    }

    public TreeNode_RB getParent() {
        return parent;
    }

}
