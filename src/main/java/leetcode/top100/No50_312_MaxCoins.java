package leetcode.top100;

/**
 * 312. 戳气球
 *
 * <p>
 *     有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 *
 * 求所能获得硬币的最大数量。
 * </p>
 *
 * @author sunkangchao
 * @since 2025/5/14 01:43
 */
public class No50_312_MaxCoins {

    /**
     * 思路：动态规划
     * 1）气球扎破的顺序不一致 会导致最终得到的最大数量不同 且这个顺序没有规律 因此你要尝试扎破不同的气球 求得最大值
     * 2）寻找转移关系，一般来说以题目给定的结果来定义，即先尝试定义dp[i][j]为以i，j为结尾的数组能够获得硬币的最大数量，且为开区间
     * 3）设置开区间的原因是，存在需要参与计算但不能扎破的边界值，如题意中两侧的1
     * <p/>
     * 假如最后扎破的元素是nums[k]，左边界i，右边界j，那么此时dp[i][j] = dp[i][k] + dp[k][j] + nums[k] * nums[i] * nums[j]
     * 找到这样的转移公式是本题的难点，在于深刻理解题意，结合经验不断尝试，找到转移公式。
     *<p/>
     * 显然，dp[i][j]需要遍历最后一个被扎破的气球可以是数组中任意值，求它的最大值。
     * 且求出dp[i][j]，可以发现，dp[i][j]的计算需要先计算其它的区间长度，也就是从区间长度为2开始，直至len;
     *<p/>
     * 求解步骤：
     * 1）第一步，先在原数组基础上添加左、右边界的1。
     * 2）第二步，从区间长度为2开始，逐步求解到区间长度为新数组length - 1，因为扎最后一个气球，左侧的区间长度最大是length -1。
     * 3）第三步，确定初始值，区间长度为2时，由于是左闭右开区间，因此它们的值都是0。
     * 4）第四步，返回值即dp[0][length - 1].
     *
     * 注意：
     * 1）对于确定动态规划数组dp的长度，可以单独去考虑每个维度的值的取值范围而定。
     * 2）结果有问题，先扫一遍把低级错误排除，一般来说思路没问题，直接瞪会比你Debug更快找到问题所在
     * 3）dp[i][j] = dp[i][k] + dp[k][j] + nums[k] * nums[i] * nums[j]， 注意后面相乘是i和j，而不是k-1和k+1。
     */
    public int maxCoins(int[] nums) {

        // 1. 先拼接左1、右1
        int[] coins = new int[nums.length + 2];
        int len = coins.length;
        coins[0] = 1;
        coins[coins.length - 1] = 1;
        for (int i = 1; i < coins.length - 1; i++) {
            coins[i] = nums[i - 1];
        }

        // 2. 定义dp数组，从长度为2开始遍历
        int[][] dp = new int[len][len];

        for (int l = 2; l <= len; l++) {
            for (int i = 0; i + l - 1 < len; i++) {
                int j = i + l - 1;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + coins[k] * coins[i] * coins[j]);
                }
            }
        }

        // 3. 返回dp[0][length - 1]
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        No50_312_MaxCoins obj = new No50_312_MaxCoins();
        int[] arr = {3,1,5,8};
        int ans = obj.maxCoins(arr);
        System.out.println(ans);
    }


}
