package study.system.class12;

import study.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 广度优先遍历
 * No90_DFS
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 15, 2024</pre>
 */
public class No90_DFS {

    public void dfs(TreeNode head) {
        if (head == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }


}
