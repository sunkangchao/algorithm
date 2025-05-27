package leetcode.top100;

import leetcode.top100.base.TreeNode;

/**
 * No61_104_MaxDepth
 *
 * <p/>
 * 104. 二叉树的最大深度
 * 给定一个二叉树 root ，返回其最大深度。
 *
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 26, 2025</pre>
 */
public class No61_104_MaxDepth {


    /**
     * 这道题使用递归的思想比较好理解。
     *
     * 因为一棵树的最大深度等于根节点的左子树的最大深度和右子树的最大深度的最大值 + 1
     *
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }



}
