package leetcode.top100;

/**
 * No64_42_Trap
 *
 * 42. 接雨水
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 28, 2025</pre>
 */
public class No64_42_Trap {


    // 方法一：动态规划
    // 先对数组做预处理，求出每个位置左右最高的柱子，然后遍历每个柱子，把它们能接的雨水量相加。
    // 本质上都是单独求解每个柱子能接的雨水量，然后把它们相加。
    // 如果要求一个柱子能接的雨水量，必须要知道它的左边和右边的最高柱子，只有这样才能确定当前柱子能接多少雨水。这两种解法本质都是这个思路。
    public int trap0(int[] height) {

        int len = height.length;
        // 求出左侧最高的柱子
        int[] left = new int[len];
        int maxLeft = height[0];
        for (int i = 0; i < len; i++) {
            left[i] = maxLeft;
            maxLeft = Math.max(maxLeft, height[i]);
        }

        // 求出右侧最高的柱子
        int[] right = new int[len];
        int maxRight = height[len - 1];
        for (int i = len - 1; i >= 0; i--) {
            right[i] = maxRight;
            maxRight = Math.max(maxRight, height[i]);
        }

        // 遍历求和
        int ans = 0;
        for (int i = 1; i < len - 1; i++) {
            int volumn = Math.max(0, Math.min(left[i], right[i]) - height[i]);
            ans += volumn;
        }

        return ans;
    }


    // 方法二：双指针
    // 定义Lmax, Rmax值，以及left和right两个指针
    // Lmax表示左侧的最高柱子的值，Rmax表示右侧的最高柱子的值
    // left和right分别表示左右指针
    // 1）当Lmax < Rmax时，left处的高度取决于Lmax的高度
    // 2）当Lmax >= Rmax时，right处的高度取决于Rmax的高度
    // 当left > right时终止循环
    //
    // 注意：Lmax和Rmax严格来说，应该定义成left指针左边的最大值，right指针右边的最大值。它是跟着left和right指针同步变更的。
    public int trap(int[] height) {
        // 基础校验
        if (height.length <= 2) {
            return 0;
        }

        // 定义左右侧最高值，以及左右双指针
        int len = height.length;
        int leftMax = height[0], rightMax = height[len - 1];
        int left = 1, right = len - 2;
        int ans = 0;

        // 循环遍历 把结果叠加
        while (left <= right) {
            System.out.println("left: " + left + ", right: " + right);
            if (leftMax < rightMax) {
                ans += Math.max(0, leftMax - height[left]);
                leftMax = Math.max(leftMax, height[left]);
                left++;
            } else {
                ans += Math.max(0, rightMax - height[right]);
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }
        }

        // 返回结果
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        No64_42_Trap obj = new No64_42_Trap();
        int ans = obj.trap(arr);
        System.out.println(ans);
    }


}
