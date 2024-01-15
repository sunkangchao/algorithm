package study.system.class13;

import study.base.TreeNode;

/**
 * 100.判断一棵二叉树是否为平衡二叉树
 * No100_IsBalanceTree
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 15, 2024</pre>
 */
public class No100_IsBalanceTree {


    public boolean isBalance(TreeNode head) {
        return process(head).isBalance;
    }

    private Info process(TreeNode node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        boolean isBalance = true;
        int height;

        // 如果返回null, 则自己在上层去处理空元素，赋予空元素含义
        int leftHeight = leftInfo != null ? leftInfo.height : 0;
        int rightHeight = rightInfo != null ? rightInfo.height : 0;
        height = Math.max(leftHeight, rightHeight) + 1;

        boolean isLeftBalance = leftInfo == null || leftInfo.isBalance;
        boolean isRightBalance = rightInfo == null || rightInfo.isBalance;

        if (!isLeftBalance || !isRightBalance) {
            isBalance = false;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            isBalance = false;
        }
        return new Info(isBalance, height);
    }

    private static class Info {

        boolean isBalance;
        int height;

        public Info(boolean _isBalance, int _height) {
            this.isBalance = _isBalance;
            this.height = _height;
        }

    }


}
