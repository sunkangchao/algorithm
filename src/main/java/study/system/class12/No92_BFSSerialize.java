package study.system.class12;

import study.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 重点：广度优先方式实现二叉树的序列化和反序列化
 * No92_BFSSerialize
 *
 * 使用循环来序列化则需要使用循环来反序列化
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 15, 2024</pre>
 */
public class No92_BFSSerialize {

    public Queue<String> bfsSerialize(TreeNode head) {
        Queue<String> queue = new LinkedList<>();
        bfsSerialize(head, queue);
        return queue;
    }

    private void bfsSerialize(TreeNode node, Queue<String> queue) {
        if (node == null) {
            queue.add(null);
            return;
        }
        Queue<TreeNode> children = new LinkedList<>();
        queue.add(String.valueOf(node.val));
        children.add(node);
        while (!children.isEmpty()) {
            TreeNode treeNode = children.poll();
            if (treeNode.left != null) {
                queue.add(String.valueOf(treeNode.left.val));
                children.add(treeNode.left);
            } else {
                queue.add(null);
            }
            if (treeNode.right != null) {
                queue.add(String.valueOf(treeNode.right.val));
                children.add(treeNode.right);
            } else {
                queue.add(null);
            }
        }
    }

    public TreeNode bfsDeserialize(Queue<String> queue) {
        String nodeVal = queue.poll();
        if (nodeVal == null) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(nodeVal));
        Queue<TreeNode> children = new LinkedList<>();
        children.add(node);

        while (!children.isEmpty()) {
            TreeNode treeNode = children.poll();
            treeNode.left = generateTreeNode(queue.poll());
            treeNode.right = generateTreeNode(queue.poll());
            if (treeNode.left != null) {
                children.add(treeNode.left);
            }
            if (treeNode.right != null) {
                children.add(treeNode.right);
            }
        }
        return node;
    }

    private TreeNode generateTreeNode(String nodeVal) {
        if (nodeVal == null) {
            return null;
        }
        return new TreeNode(Integer.parseInt(nodeVal));
    }


}
