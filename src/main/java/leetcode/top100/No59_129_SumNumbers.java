package leetcode.top100;

import leetcode.top100.base.TreeNode;

/**
 * No59_129_SumNumbers
 *
 * <p/>
 * 129. 求根节点到叶节点数字之和
 *
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 *
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 *
 * 叶节点 是指没有子节点的节点。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 26, 2025</pre>
 */
public class No59_129_SumNumbers {

    private int sum;

    /**
     * 思路：dfs
     *
     * 每一层判断左右节点是否为空，如果不为空则按照 curSum * 10 + root.val的方式继续往下传递当前总和
     *
     * 一步步地往下传递当前值，当它的左右节点都会null时，说明此时达到了当前分支的叶子节点，此时把当前分支总和加到成员变量sum上面。
     *
     * @param root
     * @return
     */
    public int sumNumbers1(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    private void dfs(TreeNode root, int curSum) {
        if (root.left != null) {
            dfs(root.left, curSum * 10 + root.val);
        }
        if (root.right != null) {
            dfs(root.right, curSum * 10 + root.val);
        }
        if (root.left == null && root.right == null) {
            sum += curSum * 10 + root.val;
        }
    }


    /**
     * 思路二：带返回值dfs
     *
     * 把dp[i]定义经过当前节点所有子树的和。
     *
     * dp[i] = dp[i.left] + dp[i.right]
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return sumNumbers(root, 0);
    }

    private int sumNumbers(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        int t = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return t;
        } else {
            return sumNumbers(root.left, t) + sumNumbers(root.right, t);
        }
    }

    public static void main(String[] args) {
        No59_129_SumNumbers obj = new No59_129_SumNumbers();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        int sum = obj.sumNumbers(root);
        System.out.println(sum);
    }


}
