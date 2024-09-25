package study.base;

/**
 * @author sunkangchao
 * @since 2024/1/14 16:00
 */
public class ParentTreeNode {


    public int val;
    public ParentTreeNode left;
    public ParentTreeNode right;
    /**
     * 相比二叉树，多一个指向父节点的指针
     */
    public ParentTreeNode parent;

    public ParentTreeNode(int val) {
        this.val = val;
    }

}
