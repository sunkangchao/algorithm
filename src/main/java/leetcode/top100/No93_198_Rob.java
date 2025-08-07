package leetcode.top100;

/**
 * No93_198_Rob
 *
 * 198. 打家劫舍
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>08月 07, 2025</pre>
 */
public class No93_198_Rob {


    // 基础版的打家劫舍
    // 定义dp[i]为前i间房间能够盗取的最大金额，而不是以第i间为结尾
    // 本质是就是把每个房间偷和不偷的情况考虑过了 求其最大值 就相当于考虑了所有情况
    // dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1])
    // 也就是存在这样的关系，把dp[i]定义为前i间房间能够盗取的最大金额，它就可以由之前的解来推导出来
    public int rob(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length < 2) {
            return nums[0];
        }

        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[len - 1];
    }



}
