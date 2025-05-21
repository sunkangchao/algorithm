package leetcode.top100;

import leetcode.top100.base.TreeNode;

/**
 * No55_337_RobIII
 *
 * <p/>
 * 337. 打家劫舍 III
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 *
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 *
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 21, 2025</pre>
 */
public class No55_337_RobIII {


    /**
     * 思路：树形dp
     * 已知直接相连的房子不能同时盗取，也就意味着相邻的层级不能同时盗取，此时需要获取最大值金额
     * 就需要遍历每种情况，获取其最大值。
     *
     * 这种问题的思路就是把原问题分解，目前要求root节点的树能获取的最大金额，可以转化成：
     * 
     * 求解它的左子树、右子树的盗取和不盗取的情况下的最大金额。root节点能拿到这个信息，就能判断如何最大：
     *
     * 定义f(root)为盗取当前根节点时能获取的最大金额，g(root)为不盗取当前根节点能获取的最大金额，可知
     *
     * f(root) = root.val + g(root.left) + g(root.right)，即等于左子树不盗取 + 右子树不盗取的和
     * g(root) = Math.max(f(root.left), g(root.left) + Math.max(f(root.right), g(root.right))，
     *      根节点root不盗取，对于它的左子树就可以选择盗取和不盗取两种情况，取其最大值。
     *
     * 原问题被分解成了同类型的子问题，因此只需要再确定递归的出口即可求解答案。
     *
     * 注意：
     * 1）每次递归需要返回两个信息，一个是盗取的最大金额，一个是不盗取的最大金额，因此返回结果需要存储两个值，使用什么数据结构？
     * 定义一个对象，或者使用数组。
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {

        int[] ans = dfs(root);
        return Math.max(ans[0], ans[1]);
    }


    // 定义返回值的第一个元素为盗取，第二个元素为不盗取
    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[] {0, 0};
        }

        int[] leftInfo = dfs(root.left);
        int[] rightInfo = dfs(root.right);

        int rob = root.val + leftInfo[1] + rightInfo[1];
        int noRob = Math.max(leftInfo[0], leftInfo[1]) + Math.max(rightInfo[0], rightInfo[1]);

        return new int[] {rob, noRob};
    }


    public static void main(String[] args) {
        // 创建测试二叉树: [3,2,3,null,3,null,1]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);

        No55_337_RobIII solution = new No55_337_RobIII();
        int result = solution.rob(root);
        System.out.println("Maximum amount that can be robbed: " + result);
    }
}
