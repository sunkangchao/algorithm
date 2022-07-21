package study;

import study.base.TreeNode;

/**
 * 能否组成路径和
 *
 * @author sunkangchao
 * @date 2022/7/21 19:10
 */
public class Code20_PathSum {


    public boolean hasPathSum(TreeNode root, int targetSum) {
        return hasPathSum(root, 0, targetSum);
    }

    public static boolean hasPathSum(TreeNode root, int curSum, int targetSum) {
        if (root == null) {
            return false;
        }
        curSum += root.val;
        if (root.left == null && root.right == null) {
            return curSum == targetSum;
        }
        return hasPathSum(root.left, curSum, targetSum) || hasPathSum(root.right, curSum, targetSum);
    }



}
