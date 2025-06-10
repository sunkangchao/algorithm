package leetcode.top100;

import java.util.Arrays;

/**
 * No71_369_MaxRotateFunction
 *
 * 396. 旋转函数
 *
 * 给定一个长度为 n 的整数数组 nums 。
 *
 * 假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数  F 为：
 *
 * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
 * 返回 F(0), F(1), ..., F(n-1)中的最大值 。
 *
 * 生成的测试用例让答案符合 32 位 整数
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>06月 10, 2025</pre>
 */
public class No71_369_MaxRotateFunction {


    /**
     * 思路：动态规划
     *
     * 尝试寻找规律：
     *
     * F(0) = 0*nums[0] + 1*nums[1] + 2*nums[2] + ... + (n-2)*nums[n-2) + (n-1)*nums[n-1]
     * F(1) = 0*nums[n-1] + 1*nums[0] + 2*nums[1] + ... + (n-2)*nums[n-3] + (n-1)*nums[n-2]
     *
     * F(1)-F(0) = nums[0] + nums[1] + ... + nums[n-2] - (n-1)*nums[n-1]    拼凑一个nums[n-1]
     *           = numsSum - n*nums[n-1]
     *
     * 可知：
     * F(i)-F(i-1) = numsSum - n*nums[n-i]
     *
     * 只要已知i和i-1的运算公式，也就是可知i和i-1的关系，把i-1移动至右侧就知道结果，所以：
     * F(i) = F(i-1) + numsSum - n*nums[n-i]
     *
     * 注意：
     * 1）只需要上一个变量便可求解下一个变量，因此使用同一个变量来记录上一个变量即可，只需要使用一个变量，而不是两个。
     * 2）只有通用代码无法兼顾边界情况时，才加边界判断。比如这道题的开头长度判断是否为1。
     * 3)  nums[(n - i) % n]，这种表示是很合理的。
     * 4）max变量赋值错误刚看不出来问题
      * @param nums
     * @return
     */
    public int maxRotateFunction(int[] nums) {

        if (nums.length == 1) {
            return 0;
        }

        // 1. 求得numsSum n
        int n = nums.length;
        int numsSum = Arrays.stream(nums).sum();

        // 2. 初始化F(0) 定义最大值
        int cur = 0;
        for (int i = 0; i < n; i++) {
            cur += i * nums[i];
        }
        int max = cur;

        // 3. 递推求解 循环次数为n-1次 由F(0) 至 F(n-1)
        for (int i = 1; i < n; i++) {
            cur = cur + numsSum - n * nums[n - i];
            max = Math.max(max, cur);
        }

        return max;
    }


    public static void main(String[] args) {
        No71_369_MaxRotateFunction obj = new No71_369_MaxRotateFunction();
        int[] arr = {4,3,2,6};
        int ret = obj.maxRotateFunction(arr);
        System.out.println(ret);
    }



}
