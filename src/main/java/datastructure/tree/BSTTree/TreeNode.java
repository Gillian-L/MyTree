package datastructure.tree.BSTTree;

/**
 * @Author: Gillian
 * @Date: 2020/12/7-15:51
 * @Description: Gillian_pro:datastructure.tree.BSTTree
 * @Version: 1.0
 */
public class TreeNode {



    private Object value;
    private TreeNode left;
    private TreeNode right;
    private EqualNode equalNode;

    public TreeNode(Object value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public void setEqualNode(EqualNode equalNode) {
        this.equalNode = equalNode;
    }

    public Object getValue() {
        return value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public EqualNode getEqualNode() {
        return equalNode;
    }
}
