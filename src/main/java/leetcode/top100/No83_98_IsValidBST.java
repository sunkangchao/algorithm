package leetcode.top100;

import leetcode.top100.base.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * No83_98_IsValidBST
 *
 * 98. 验证二叉搜索树
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>07月 14, 2025</pre>
 */
public class No83_98_IsValidBST {


    // 递归解法，从上至下遍历
    public boolean isValidBST0(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE); // 不是
    }

    private boolean isValidBST(TreeNode node, long left, long right) {
        if (node == null) {
            return true;
        }

        if (node.val <= left || node.val >= right) { // 需要严格大于左子树的全部节点，严格小于右子树的全部节点
            return false;
        }

        return isValidBST(node.left, left, node.val) && isValidBST(node.right, node.val, right);
    }


    // 中序遍历，迭代解法
    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return false;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root; // 重命名root，使其更符合当前语义
        long lastVal = Long.MIN_VALUE;

        while (node != null || !stack.isEmpty()) {
            // 先把左边压到底
            while (node != null) { // todo while写成了if 导致bug
                stack.push(node); //
                node = node.left;
            }

            TreeNode curNode = stack.pop(); // 可以认为此时是每个节点的根节点 后续打印就是中序遍历
            if (curNode.val <= lastVal) {
                return false;
            }
            lastVal = curNode.val;
            node = curNode.right;
        }

        return true;
    }


    // 解法三：树形dp，严格来说从底至上（这里区别于解法一），通用解法，见语雀7.14
    public boolean isValidBST(TreeNode root) {
        Info info = dfs(root);
        return info.isBSTree;
    }

    private Info dfs(TreeNode root) {
        if (root == null) {
            return new Info(true, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        Info leftInfo = dfs(root.left);
        Info rightInfo = dfs(root.right);
        boolean isBSTree = leftInfo.isBSTree && rightInfo.isBSTree
                && root.val > leftInfo.maxVal && root.val < rightInfo.minVal;
        long maxVal = Math.max((long) root.val, rightInfo.maxVal);
        long minVal = Math.min((long) root.val, leftInfo.minVal);
        return new Info(isBSTree, maxVal, minVal);
    }

    static class Info {
        boolean isBSTree;
        long maxVal;
        long minVal;

        Info(boolean isBSTree, long maxVal, long minVal) {
            this.isBSTree = isBSTree;
            this.maxVal = maxVal;
            this.minVal = minVal;
        }
    }

}
