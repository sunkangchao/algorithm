package study.system.class12;

import study.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 先学遍历的方式：序列化和反序列化二叉树
 * No91_BinaryTreeSerialize
 *
 * 使用递归来序列化则需要使用递归来反序列化
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 15, 2024</pre>
 */
public class No91_BinaryTreeSerialize {

    public Queue<String> preSerialize(TreeNode head) {
        Queue<String> queue = new LinkedList<>();
        preSerialize(head, queue);
        return queue;
    }

    private void preSerialize(TreeNode node, Queue<String> queue) {
        if (node == null) {
            queue.add(null);
            return;
        }
        queue.add(String.valueOf(node.val));
        if (node.left != null) {
            preSerialize(node.left, queue);
        }
        if (node.right != null) {
            preSerialize(node.right, queue);
        }
    }

    private TreeNode preDeSerialize(Queue<String> queue) {
        String nodeVal = queue.poll();
        if (nodeVal == null) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(nodeVal));
        node.left = preDeSerialize(queue);
        node.right = preDeSerialize(queue);
        return node;
    }


}
