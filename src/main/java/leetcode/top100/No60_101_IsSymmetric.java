package leetcode.top100;

import leetcode.top100.base.TreeNode;

/**
 * No60_101_IsSymmetric
 *
 * <p/>
 * 101. 对称二叉树
 *
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 26, 2025</pre>
 */
public class No60_101_IsSymmetric {


    /**
     * 思路：经过画图分析，发现想要判断一棵树是否为基于中轴线对称，
     * 需要一层一层去对比，即左节点的和右节点比较，一旦相等，则它们继续往一层递归，
     * 左节点的左节点与右节点的右节点比较 && 左节点的右节点与右节点的左节点比较
     * 当发现不等时立即返回false，相等的话继续往下比较。
     *
     * 出口条件：通过避免空指针得出，left和right不同时为空返回false，同时为空则返回true.
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right != null || left != null && right == null) {
            return false;
        }

        if (left == null) {
            return true;
        }

        if (left.val != right.val) {
            return false;
        }

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

}
