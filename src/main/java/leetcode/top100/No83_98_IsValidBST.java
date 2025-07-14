package leetcode.top100;

import leetcode.top100.base.TreeNode;

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
        return dfs(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private boolean dfs(TreeNode root, int max, int min) {
        if (root == null) {
            return true;
        }
        if (root.val >= max || root.val <= min) {
            return false;
        }
        return dfs(root.left, root.val, min) && dfs(root.right, max, root.val);
    }


    // 中序遍历，迭代解法
    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        long lastVal = Long.MIN_VALUE;

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (root.val <= lastVal) {
                return false;
            }
            lastVal = root.val;
            root = root.right;
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
