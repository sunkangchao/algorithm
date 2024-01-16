package study.system.class12;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import study.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 设计一种编码和解法算法，把N叉树转化为二叉树，并且能够把这颗二叉树转为原本的N叉树
 *
 * Encode算法：把N叉树的children全部放在左侧节点的右边界上
 * Decode算法：把这颗二叉树的左侧右边界上的节点取出来，放回到N叉树的children集合里
 *
 * 当然，你也可以把它们放在右树的左边界上，只要它们能确保唯一地相互转换，那么这个算法就是合理的。
 *
 * No94_EncodeNAryTree
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 15, 2024</pre>
 */
public class No94_EncodeNAryTree {

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // 递归结构一
    public TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }
        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = en(root.children);
        return newRoot;
    }

    // 定义递归结构为孩子集合 转为为一条二叉树右侧链表 返回
    private TreeNode en(List<Node> children) {
        TreeNode head = null;
        TreeNode cur = null;
        for (Node child : children) {
            TreeNode childNode = new TreeNode(child.val);
            childNode.left = en(child.children);
            if (head == null) {
                head = childNode;
            } else {
                cur.right = childNode;
            }
            cur = childNode;
        }
        return head;
    }


    // 递归结构二
//    public TreeNode encode(Node root) {
//        if (root == null) {
//            return null;
//        }
//        return en(root, root.children);
//    }
//
//    private TreeNode en(Node root, List<Node> children) {
//        TreeNode rootNode = new TreeNode(root.val);
//        // todo 左侧的链表缺少了头节点引用 随着循环进行 rightNode指向了最后一个元素 而不是头元素 而我们需要指向头元素
//        // 这里加一个头节点的引用
//        TreeNode head = null;
//        TreeNode rightNode = null;
//        for (Node child : children) {
//            TreeNode childNode = en(child, child.children);
//            if (head == null) {
//                head = childNode;
//            } else {
//                rightNode.right = childNode;
//            }
//            rightNode = childNode;
//        }
//        rootNode.left = rightNode;
//        return rootNode;
//    }

    public Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        return new Node(root.val, de(root.left));
    }

    /**
     * 这是一个递归方法 把左链表 -》children集合
     * 如果n叉树没有子节点，应该表示为null还是空集合？
     * @param node
     * @return
     */
    private List<Node> de(TreeNode node) {
        List<Node> children = new ArrayList<>();
        while (node != null) {
            Node child = new Node(node.val, de(node.left));
            children.add(child);
            node = node.right;
        }
        return children;
    }

//    public Node decode(TreeNode node) {
//        if (node == null) {
//            return null;
//        }
//        Node root = new Node(node.val);
//        List<Node> nodeList = new ArrayList<>();
//        List<TreeNode> children = de(node.left);
//        for (TreeNode child : children) {
//            nodeList.add(decode(child));
//        }
//        root.children = nodeList;
//        return root;
//    }
//
//    private List<TreeNode> de(TreeNode node) {
//        List<TreeNode> children = new ArrayList<>();
//        if (node == null) {
//            return children;
//        }
//        while (node != null) {
//            children.add(node);
//            node = node.right;
//        }
//        return children;
//    }


}
