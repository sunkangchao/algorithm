package study.newhand;

import study.base.TreeNode;

/**
 * 是否为二叉搜索树
 *
 * @author sunkangchao
 * @date 2022/7/21 02:35
 */
public class Code18_IsSearchTree {


    public boolean isSearchTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        int val = root.val;
        if (root.left != null && val <= root.left.val) {
            return false;
        }
        if (root.right != null && val >= root.right.val) {
            return false;
        }
        return isSearchTree(root.left) && isSearchTree(root.right);
    }

    public static class Info {
        public boolean isBalance;
        public int height;

        public Info(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    public boolean isBalanceTree(TreeNode root) {
        return processInfo(root).isBalance;
    }

    public Info processInfo(TreeNode root) {
        if (root == null) {
            return new Info(true, 0);
        }
        Info leftInfo = processInfo(root.left);
        Info rightInfo = processInfo(root.right);
        int height = Math.abs(leftInfo.height - rightInfo.height) + 1;
        boolean isBalance = (Math.abs(leftInfo.height - rightInfo.height) < 2) && leftInfo.isBalance && rightInfo.isBalance;
        return new Info(isBalance, height);
    }

    public static class TreeInfo {
        public int height;
        public boolean isSearchTree;
        public boolean isBalanceTree;

        public TreeInfo(int height, boolean isSearchTree, boolean isBalanceTree) {
            this.height = height;
            this.isSearchTree = isSearchTree;
            this.isBalanceTree = isBalanceTree;
        }

    }
    public boolean isBalanceSearchTree(TreeNode root) {
        TreeInfo info =  process(root);
        return info.isSearchTree && info.isBalanceTree;
    }

    public TreeInfo process(TreeNode root) {
        if (root == null) {
            return new TreeInfo(0, true, true);
        }
        TreeInfo leftInfo = process(root.left);
        TreeInfo rightInfo = process(root.right);

        int height = Math.abs(leftInfo.height - rightInfo.height) + 1;
        boolean isSearchTree = (root.left == null || root.val > root.left.val) && (root.right == null || root.val < root.right.val);
        boolean isBalanceTree = Math.abs(leftInfo.height - rightInfo.height) < 2 && leftInfo.isBalanceTree && rightInfo.isSearchTree;
        return new TreeInfo(height, isSearchTree, isBalanceTree);
    }



}
