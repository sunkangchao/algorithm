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
     * 思路：面对这种题目，第一反应就是树形dp，深度优先搜索遍历每一个节点，分析需要从每个节点得到哪些信息
     * 由于最终的最大路径可能是经过根节点，也可能不经过根节点，那么分两种情况考虑
     * 1）经过根节点的最大路径
     * 想要求得这个路径，需要得到经过左/右节点，且只有其子节点一侧的路径最大值，那么最终合并就能得到经过根节点的最大值
     * 注意：需要注意如果某一侧的最大路径和是小于0的，那么此时根节点就不应该再与其合并，所有有一个Math.max(maxSubSum, 0)的判断
     * 其次，求解这个值是，我们会本能的担忧子节点给出的这个路径同时经过了它的左右节点，其实，只需要在每个节点上都保证这个值只取一侧，
     * 那么最终你在根节点拿到的值也是只会包含一侧
     *
     * 2）不经过根节点的最大路径
     * 有两种方式，你可以在每个节点递归返回时更新最大的路径，最后返回这个最大路径，维护一个全局的变量即可。
     * 第二种方式是你每个节点都返回当前的最大路径，然后每次取经过当前节点的最大路径，与左右子树的最大路径做比较，取最大值
     * 注意：第二种方式你需要注意，对于空节点你不能返回默认值0，因为节点可能是负数，给的默认值0会导致最终结果为负数的值被0覆盖
     * 所以在求最大值，为了不出现值覆盖，你需要把默认的最大路径和设置为Integer.MIN_VALUE
     *
     * 遇到的问题：
     * 1）因为node == null时，发挥的是info(0,0),但如果存在节点时算出来时负数，会被这个默认值0覆盖
     * 你需要一个特别的状态，给Integer.MIN_VALUE，将不会影响到最大值的判断。
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        Info info = dfs(root);
        // 最终返回最大路径和
        return info.maxPathSum;

    }

    private Info dfs(TreeNode node) {
        if (node == null) {
            return new Info(0, Integer.MIN_VALUE);
        }
        Info leftInfo = dfs(node.left);
        Info rightInfo = dfs(node.right);

        // 非常关键，只有大于0时，这个贡献值才应该被采纳，否则就应该丢弃这个分支 不然只会使得路径更小
        int leftMaxGain = Math.max(leftInfo.maxGain, 0);
        int rightMaxGain = Math.max(rightInfo.maxGain, 0);
        // 1. 求解当前节点的最大贡献值（至多经过一侧子节点）
        int maxGain = Math.max(leftMaxGain + node.val, rightMaxGain + node.val);

        // 经过当前根节点的最大路径,即当前已经形成确定路径的最大路径
        int curNodeMaxPathSum = leftMaxGain + node.val + rightMaxGain;
        // 2. 更新当前的最大路径和
        int maxPathSum = Math.max(leftInfo.maxPathSum, Math.max(rightInfo.maxPathSum, curNodeMaxPathSum));

        return new Info(maxGain, maxPathSum);
    }


    private static class Info {
        // 最大的经过根节点一侧的最大路径，即官方题解中的最大贡献值
        int maxGain;
        // 当前存储的最大路径和，显然，这里使用全局变量来存储也可以
        int maxPathSum;

        Info(int maxGain, int maxPathSum) {
            this.maxGain = maxGain;
            this.maxPathSum = maxPathSum;
        }
    }


    public static void main(String[] args) {
        No20_124_MaxPathSum obj = new No20_124_MaxPathSum();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(-1);
        int maxPathSum = obj.maxPathSum(root);
        System.out.printf("maxPathSum: %d", maxPathSum);
    }
}
