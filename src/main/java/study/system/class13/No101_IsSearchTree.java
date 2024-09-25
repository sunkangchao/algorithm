package study.system.class13;

import study.base.TreeNode;

/**
 * 判断一棵树是否是二叉搜索树
 * No101_IsSearchTree
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 15, 2024</pre>
 */
public class No101_IsSearchTree {


    public boolean isSearchTree(TreeNode node) {
        Info info = process(node);
        return info == null || info.isSearchTree;
    }

    private Info process(TreeNode node) {
        if (node == null) {
            return null;
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        boolean isSearchTree = true;
        int maxValue = node.val;
        int minValue = node.val;

        if (leftInfo != null) {
            maxValue = Math.max(maxValue, leftInfo.maxValue);
            minValue = Math.min(minValue, leftInfo.minValue);
        }
        if (rightInfo != null) {
            maxValue = Math.max(maxValue, rightInfo.maxValue);
            minValue = Math.min(minValue, rightInfo.minValue);
        }

        boolean isLeftSearchTree = leftInfo == null || leftInfo.isSearchTree;
        boolean isRightSearchTree = rightInfo == null || rightInfo.isSearchTree;

        if (!isLeftSearchTree && !isRightSearchTree) {
            isSearchTree = false;
        }
        if (leftInfo != null && leftInfo.maxValue >= node.val) {
            isSearchTree = false;
        }
        if (rightInfo != null && rightInfo.minValue <= node.val) {
            isSearchTree = false;
        }
        return new Info(isSearchTree, maxValue, minValue);
    }

    private static class Info {
        public boolean isSearchTree;
        public int maxValue;
        public int minValue;

        public Info(boolean isSearchTree, int maxValue, int minValue) {
            this.isSearchTree = isSearchTree;
            this.maxValue = maxValue;
            this.minValue =minValue;
        }
    }


}
