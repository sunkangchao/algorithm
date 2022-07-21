package study;

import study.base.TreeNode;

/**
 * 是否为平衡二叉搜索树
 *
 * @author sunkangchao
 * @date 2022/7/21 03:39
 */
public class Code19_IsBST {

    public static class Info {
        public boolean isBST;
        public int height;
        public int max;
        public int min;

        public Info(boolean isBST, int height, int max, int min) {
            this.isBST = isBST;
            this.height = height;
            this.max = max;
            this.min = min;
        }
    }

    public boolean isBalanceSearchTree(TreeNode root) {
        return process(root).isBST;
    }

    public Info process(TreeNode root) {
        if (root == null) {
            return null;
        }

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        int height = 0 ;
        if (leftInfo != null) {
            height = leftInfo.height;
        }
        if (rightInfo != null) {
            height = Math.max(height, rightInfo.height);
        }
        height += 1;

        int max = root.val;
        int min = root.val;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
        }

        boolean isBST = true;
        if (leftInfo != null ^ rightInfo != null) {
            if (leftInfo != null) {
                isBST = leftInfo.height < 2 && leftInfo.max < root.val;
            }
            if (rightInfo != null) {
                isBST = rightInfo.height < 2 && rightInfo.min > root.val;
            }
        }
        if (leftInfo != null && rightInfo != null) {
            isBST = Math.abs(leftInfo.height - rightInfo.height) < 2 && leftInfo.max < root.val && rightInfo.min > root.val;
        }

        return new Info(isBST, height, max, min);
    }





}
