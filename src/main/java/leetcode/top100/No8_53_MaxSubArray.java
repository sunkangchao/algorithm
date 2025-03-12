package leetcode.top100;

/**
 * No8_53_MaxSubArray
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>03月 11, 2025</pre>
 */
public class No8_53_MaxSubArray {


    /**
     * 动态规划解法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int maxSubArray1(int[] nums) {

        // 定义dp[i]为以数组元素i结尾的子数组的最大和
//        int[] dp = new int[nums.length];
//
//        dp[0] = nums[0];
//        int maxSum = dp[0];
//
//        for (int i = 1; i < nums.length; i++) {
//            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i];
//            maxSum = Math.max(maxSum, dp[i]);
//        }
//        return maxSum;

        // 上述解法其实可以接续优化空间复杂度 dp[i]只与dp[i-1]有关 可以使用一个变量把它存储起来
        int pre = 0, max = nums[0];

        for (final int num : nums) {
            pre = pre > 0 ? pre + num : num;
            max = Math.max(pre, max);
        }

        return max;
    }

    /**
     * 分治解法
     */
    public int maxSubArray(int[] nums) {
        Info info = maxSubArray(nums, 0, nums.length - 1);
        return info.mSum;
    }

    public Info maxSubArray(int[] nums, int l, int r) {

        if (l == r) {
            return new Info(nums[l], nums[l], nums[l], nums[l]);
        }

        int m = (l + r) / 2;
        Info lInfo = maxSubArray(nums, l, m);
        Info rInfo = maxSubArray(nums, m + 1, r);

        int iSum = lInfo.iSum  + rInfo.iSum;
        int lSum = Math.max(lInfo.lSum, lInfo.iSum + rInfo.lSum);
        int rSum = Math.max(rInfo.rSum, rInfo.iSum + lInfo.rSum);
        int mSum = Math.max(lInfo.mSum, Math.max(rInfo.mSum, lInfo.rSum + rInfo.lSum));
        return new Info(iSum, lSum, rSum, mSum);

    }

    public static class Info {

        int iSum;
        int lSum;
        int rSum;
        int mSum;

        public Info(int iSum, int lSum, int rSum, int mSum) {
            this.iSum = iSum;
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
        }


    }



}
