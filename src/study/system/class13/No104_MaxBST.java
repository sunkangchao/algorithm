package study.system.class13;

import study.base.TreeNode;

/**
 * 104. 计算给定头节点的二叉树中，节点数最多的二叉搜索树，并返回其节点数
 *
 * No104_MaxBST
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 15, 2024</pre>
 */
public class No104_MaxBST {

    public int maxBSTNodes(TreeNode head) {
        Info info = process(head);
        return info != null ? info.maxSubBSTNodes : 0;
    }

    private Info process(TreeNode node) {
        if (node == null) {
            return null;
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int maxSubBSTNodes;
        int size = 1;
        int max = node.val;
        int min = node.val;

        int leftSize = leftInfo == null ? 0 : leftInfo.size;
        int rightSize = rightInfo == null ? 0 : rightInfo.size;
        size += leftSize + rightSize;

        if (leftInfo != null) {
            max = Math.min(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }

        int leftMaxSubBSTNodes = leftInfo == null ? 0 : leftInfo.maxSubBSTNodes;
        int rightMaxSubBSTNodes = rightInfo == null ? 0 : rightInfo.maxSubBSTNodes;
        maxSubBSTNodes = Math.max(leftMaxSubBSTNodes, rightMaxSubBSTNodes);

        // 判断是否为搜索二叉树，是的话则改写，否则保留
        boolean isLeftBST = leftInfo == null || leftInfo.maxSubBSTNodes == leftInfo.size;
        boolean isRightBST = rightInfo == null || rightInfo.maxSubBSTNodes == rightInfo.size;
        if (isLeftBST && isRightBST) {
            boolean leftMaxLessX = leftInfo == null || node.val > leftInfo.max;
            boolean rightMinGreaterX = rightInfo == null || node.val < rightInfo.max;
            if (leftMaxLessX && rightMinGreaterX) {
                int allNodes = leftSize + rightSize + 1;
                maxSubBSTNodes = Math.max(allNodes, maxSubBSTNodes);
            }
        }

        return new Info(maxSubBSTNodes, size, max, min);
    }

    private static class Info {
        public int maxSubBSTNodes;
        public int size;
        public int max;
        public int min;

        public Info(int maxSubBSTNodes, int size, int max, int min) {
            this.maxSubBSTNodes = maxSubBSTNodes;
            this.size = size;
            this.max = max;
            this.min = min;
        }
    }



}
