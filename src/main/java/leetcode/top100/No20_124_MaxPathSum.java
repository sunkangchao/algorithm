package leetcode.top100;

import baseclass.base.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 *
 * @author sunkangchao
 * @since 2025/4/7 02:09
 */
public class No20_124_MaxPathSum {


    /**
     *
     * 遇到的问题：
     * 1）因为node == null时，发挥的是info(0,0),但如果存在节点时算出来时负数，会被这个默认值0覆盖
     * 你需要一个特别的状态，由于题目给出-1000 <= Node.val <= 1000
     * a：可以返回一个不可能的值
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        // 深搜 每个节点需要返回几个信息
        // 1. 经过根节点的左侧最大路径
        // 2. 经过根节点的右侧最大路径
        // 3. 不经过根节点的左侧最大路径
        // 4. 不经过根节点的右侧最大路径

        // 难在哪里 难在捋清楚需要考虑哪几种情况 并且保证这些情况囊括所有可能
        // 难在想得太深 思路容易断 即想到后面忘了前面

        // 1. 经过根节点 求最大路径
        // 其最大路径等于左侧的最大路径 + 根节点 + 右侧的最大路径
        // 这里的左侧最大路径是经过左侧节点但不经过右侧节点的最大路径 那么其子节点也满足这个特性吗？
        // 关键在于这个”经过左侧节点但不经过右侧节点“的求解过程是怎样的
        // 既然它需要经过当前的左根节点，那么它一定不能同时经过

        // 1）经过当前根节点的一侧最大路径 需求出Math.max(经过左子节点一侧的最大路径+根节点，经过右侧节点一侧的最大路径)
        // 有了上面这个值，左右节点相加，就能求出经过根节点的最大路径


        // 2. 不经过根节点的最大路径
        // 对于每个节点，不经过根节点，那么等于它左侧的最大路径 + 右侧的最大路径的最大值
        // 左侧最大路径

        Info info = dfs(root);
        return info.maxNotPathRootPath;

    }

    private Info dfs(TreeNode node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info leftInfo = dfs(node.left);
        Info rightInfo = dfs(node.right);

        int maxPassRootPath, maxNotPathRootPath;
        maxPassRootPath = Math.max(leftInfo.maxPassRootPath + node.val, rightInfo.maxPassRootPath + node.val);

        // 经过当前根节点的最大路径,即当前已经形成确定路径的最大路径
        int maxPathAllRootPath = leftInfo.maxPassRootPath + node.val + rightInfo.maxPassRootPath;
        maxNotPathRootPath = Math.max(leftInfo.maxNotPathRootPath, Math.max(rightInfo.maxNotPathRootPath, maxPathAllRootPath));

        return new Info(maxPassRootPath, maxNotPathRootPath);
    }


    private static class Info {
        // 最大的经过根节点一侧的最大路径
        int maxPassRootPath;
        // 不经过根节点的最大路径
        int maxNotPathRootPath;

        Info(int maxPassRootPath, int maxNotPathRootPath) {
            this.maxPassRootPath = maxPassRootPath;
            this.maxNotPathRootPath = maxNotPathRootPath;
        }
    }

}
