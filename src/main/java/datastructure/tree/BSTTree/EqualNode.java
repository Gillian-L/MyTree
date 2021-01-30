package datastructure.tree.BSTTree;

/**
 * @Author: Gillian
 * @Date: 2021/1/18-14:54
 * @Description: Gillian_pro:datastructure.tree.BSTTree
 * @Version: 1.0
 */
public class EqualNode {
    private Object value;
    private EqualNode next;

    public EqualNode(Object value, EqualNode next) {
        this.value = value;
        this.next = next;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setNext(EqualNode next) {
        this.next = next;
    }

    public Object getValue() {
        return value;
    }

    public EqualNode getNext() {
        return next;
    }
}
