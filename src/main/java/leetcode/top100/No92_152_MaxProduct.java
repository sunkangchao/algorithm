package leetcode.top100;

/**
 * No92_152_MaxProduct
 *
 * 152. 乘积最大子数组
 *
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * 测试用例的答案是一个 32-位 整数。
 *
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>08月 05, 2025</pre>
 */
public class No92_152_MaxProduct {

    // 动态规划解法
    // 定义dp[i]为以i为结尾的连续子数组的最大值
    // 你会发现仅仅以dp[i-1]是无法推导dp[i]的，因此还需要另一个最小值dp[i-1][0]，需要维护两个及以上状态值，属于二类动态规划问题
    // 如果只维护一个最大值，是无法求解它最终的最大值的，因为可能负负得正
    // 注意：
    // 1）你遇到一个最大值和最小值可能会交换的问题，因此使用了三者取最小值/最大值的方式来更新
    // 2）为什么一个最大值不可以？因为如果当前是负数的话，乘以当前值并不能求解它的最大值，你需要再维护一个最小值，把负数的结果也保存下来
    //  这样子，下一个数如果是也是负数，那么两者负负得正，它能够形成比自身更大的连续子数组。
    public int maxProduct1(int[] nums) {

        // 定义dp数组 以及最大值 因为需要维护两个变量 因此需要多加一个维度
        int n = nums.length;
        int[][] dp = new int[n][2];
        int rs = nums[0];

        // 初始化
        dp[0][0] = nums[0]; // 最小值
        dp[0][1] = nums[0]; // 最大值

        // 遍历
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(nums[i], Math.min(dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i]));
            dp[i][1] = Math.max(nums[i], Math.max(dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i]));
            rs = Math.max(rs, dp[i][1]);
        }

        // 返回值
        return rs;
    }

    // 解法二：动态规划之空间压缩
    // 其实只需要保存上一个状态的值即可，因此空间复杂度可以优化成常数级别
    public int maxProduct(int[] nums) {

        // 维护两个变量作为上一个状态的两个变量
        int n = nums.length;
        int preMin = nums[0];
        int preMax = nums[0];
        int rs = nums[0];

        // 遍历
        for (int i = 1; i < n; i++) {
            int curMin = Math.min(nums[i], Math.min(preMin * nums[i], preMax * nums[i]));
            int curMax = Math.max(nums[i], Math.max(preMin * nums[i], preMax * nums[i]));
            rs = Math.max(rs, curMax);
            preMin = curMin; // 这里多新增两个变量的原因是 避免preMin重新复制后 影响到preMax的计算
            preMax = curMax;
        }

        // 返回值
        return rs;
    }


}
