package study.system.class13;

import study.base.TreeNode;

import java.util.HashMap;
import java.util.Map;

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


    public boolean isBalance2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(depth(root.left) - depth(root.right)) <= 1;
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public boolean isBalance3(TreeNode root) {
        if (root == null) {
            return true;
        }
        Map<TreeNode, Integer> depthMap = new HashMap<>();
        depthMap.put(null, 0);
        return Math.abs(depth3(root.left, depthMap) - depth3(root.right, depthMap)) <= 1;
    }

    private int depth3(TreeNode node, Map<TreeNode, Integer> depthMap) {
        if (depthMap.containsKey(node)) {
            return depthMap.get(node);
        }
        int leftDepth = depth3(node.left, depthMap);
        int rightDepth = depth3(node.right, depthMap);
        int depth = Math.max(leftDepth, rightDepth) + 1;
        depthMap.put(node, depth);
        return depth;
    }


}
