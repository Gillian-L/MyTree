package datastructure.tree.AVLTree;

import datastructure.tree.BSTTree.TreeNode;

/**
 * @Author: Gillian
 * @Date: 2021/1/5-14:46
 * @Description: Gillian_pro:datastructure.tree.AVLTree
 * @Version: 1.0
 */
public class TreeNode_ extends TreeNode {
    private int height;//没用上

    public TreeNode_(Object value, TreeNode left, TreeNode right, int height) {
        super(value,left,right);
        this.height = height;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
