package study;

/**
 * 是否为平衡二叉树
 *
 * @author sunkangchao
 * @date 2022/7/21 01:58
 */
public class Code17_IsBalanceTree {


    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }


    public boolean isBalanceTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        int lh = treeHeight(root.left);
        int rh = treeHeight(root.right);
        if (Math.abs(lh - rh) > 1) {
            return false;
        }
        return isBalanceTree(root.left) && isBalanceTree(root.right);
    }

    public int treeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(treeHeight(root.left), treeHeight(root.right)) + 1;
    }


    public static class Info {
        public boolean isBalance;
        public int height;

        public Info(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    public Info processInfo(TreeNode root) {
        if (root == null) {
            return new Info(true, 0);
        }
        Info leftInfo = processInfo(root.left);
        Info rightInfo = processInfo(root.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalance = (Math.abs(leftInfo.height - rightInfo.height) <= 1) && leftInfo.isBalance && rightInfo.isBalance;
        return new Info(isBalance, height);
    }

    public boolean isBalance(TreeNode root) {
        return processInfo(root).isBalance;
    }




}
