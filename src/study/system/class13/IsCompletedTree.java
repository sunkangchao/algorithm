package study.system.class13;

import study.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断是否为完全二叉树
 * IsCompletedTree
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 15, 2024</pre>
 */
public class IsCompletedTree {


    public boolean isCompletedTree(TreeNode head) {

        if (head == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);

        boolean flag = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if ((flag && (node.left != null || node.right != null)) || (node.left == null && node.right != null)) {
                return false;
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            // 写在最后，省去continue
            if (node.left == null || node.right == null) {
                flag = true;
            }
        }
        return true;
    }


}
