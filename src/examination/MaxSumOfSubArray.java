package examination;

/**
 * 子数组最大和
 *
 * @author SunKangChao
 * @date 2021/7/13 17:07
 */
public class MaxSumOfSubArray {

    public int maxsumofSubarray(int[] arr) {
        // write code here
        //定义dp[i]为当前最大子序和
        //如果上一个和小于0，就放弃，重新开始算子序和
        //for循环里面定义的变量的生命周期

        int maxSum = 0;
        int preSum = 0;

        for (int num : arr) {
            int curSum = Math.max(preSum, 0) + num;
            if (curSum > maxSum) {
                maxSum = curSum;
            }
            preSum = curSum;
        }

        return maxSum;


    }
}
