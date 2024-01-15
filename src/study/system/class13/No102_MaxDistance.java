package study.system.class13;

import study.base.TreeNode;

/**
 * No102_MaxDistance
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 15, 2024</pre>
 */
public class No102_MaxDistance {


    public int maxDistance(TreeNode node) {
        Info process = process(node);
        // 节点数 - 1才是距离，在leetcode上还需要减1
        return process == null ? 0 : process.max - 1;
    }

    private Info process(TreeNode node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int max;
        int height;

        int leftHeight = leftInfo != null ? leftInfo.height : 0;
        int rightHeight = rightInfo != null ? rightInfo.height : 0;

        height = Math.max(leftHeight, rightHeight) + 1;

        int leftMax = leftInfo != null ? leftInfo.max : 0;
        int rightMax = rightInfo != null ? rightInfo.max : 0;
        max = leftHeight + rightHeight + 1;

        max = Math.max(leftMax, Math.max(rightMax, max));

        return new Info(max, height);
    }


    private static class Info {
        public int max;
        public int height;

        public Info(int max, int height) {
            this.max = max;
            this.height = height;
        }

    }




}
