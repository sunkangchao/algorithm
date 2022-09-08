package study;

import study.base.TreeNode;

/**
 * 两棵树是否为相同结构的树
 *
 * @author sunkangchao
 * @date 2022/7/22 15:21
 */
public class Code24_SameTree {

    public static boolean isSameTree(TreeNode root1, TreeNode root2) {
        if (root1 == null ^ root2 == null) {
            return false;
        }
        if (root1 == null) {
            return true;
        }
        return root1.val == root2.val && isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
    }

}
