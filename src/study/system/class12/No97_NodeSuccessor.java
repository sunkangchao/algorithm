package study.system.class12;


import study.base.ParentTreeNode;

/**
 * 97. 一颗二叉树，给你一个节点，找它的后继节点（基于中序遍历的结果）
 *
 * @author sunkangchao
 * @since 2024/1/14 15:57
 */
public class No97_NodeSuccessor {



    public ParentTreeNode successor(ParentTreeNode node) {

        if (node == null) {
            return null;
        }
        if (node.right != null) {
            // 找到最左的节点
            return findLeftestNode(node.right);
        }
        // 否则有两种可能 一种是直接找到根节点 一种是找到结果
        ParentTreeNode parent = node.parent;
        while (parent != null && parent.right == node) {
            node = parent;
            parent = node.parent;
        }
        return parent;

    }

    private ParentTreeNode findLeftestNode(ParentTreeNode node) {

        while (node.left != null) {
            node = node.left;
        }
        return node;

    }




}
