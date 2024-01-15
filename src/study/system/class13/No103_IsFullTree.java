package study.system.class13;

import study.base.TreeNode;

/**
 * 判断给定的一棵二叉树，是否为满二叉树
 * No103_IsFullTree
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 15, 2024</pre>
 */
public class No103_IsFullTree {

    public boolean isFullTree(TreeNode node) {
        Info info = process(node);
        return (1 << info.height) - 1 == info.allNodes;
    }

    private Info process(TreeNode node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int height;
        int allNodes;

        height = Math.max(leftInfo.height, rightInfo.height) + 1;
        allNodes = leftInfo.allNodes + rightInfo.allNodes + 1;
        return new Info(height, allNodes);
    }

    private static class Info {
        public int height;
        public int allNodes;

        public Info(int height, int allNodes) {
            this.height = height;
            this.allNodes = allNodes;
        }
    }


}
