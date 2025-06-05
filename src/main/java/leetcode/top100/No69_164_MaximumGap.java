package leetcode.top100;

import java.util.Arrays;

/**
 * No69_164_MaximumGap
 *
 * 164. 最大间距
 *
 * 给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。如果数组元素个数小于 2，则返回 0 。
 *
 * 您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>06月 04, 2025</pre>
 */
public class No69_164_MaximumGap {


    /**
     * 思路：桶排序
     *
     * 先求解出nums数组中的最小值和最大值，显然，数组中元素间隔的最大值的最小值为平均值，即
     * (maxValue - minValue) / (n - 1)，其中n为元素个数，n-1为间隔数。平均下来则元素间的间隔就是最小的。
     *
     * 显然，我们把每个元素分配到桶当中，每个桶都记录最大值和最小值，那么此时最大间隔一定位于不同的桶当中，
     * 即后一个桶的最大值和前一个桶的最小值的差值的最大值，就是整个数组的最大间距。
     *
     *
     * 最大疑问：为什么分成桶以后，最大差值一定位于不同的桶当中，
     * 我们求解的间隔是最大间隔的最小值，理论上来说间隔可能刚好等于同一个桶里的最大值和最小值的差值？
     * A: 因为假设桶的间距恰好是数组的最大间距，这个间距也可以由相邻的桶的最小值和最大值之差来等值求解。
     * 这里是比较巧妙的，这就是桶排序。把元素分配到每个桶当中。
     *
     * 时间复杂度：O(n)，求解最大值、最小值、初始化、桶赋值都是O(n)，遍历桶数组最坏情况下间距为1，此时也是复杂度最高，也是O(n)。
     * 空间复杂度：O(n)，需要一个额外的桶数据来存储最大值和最小值。
     *
     *
     * @param nums
     * @return
     */
    public int maximumGap0(int[] nums) {

        // 0. 基础校验
        int len = nums.length;
        if (len < 2) {
            return 0;
        }

        // 1. 求解nums数组的最大值和最小值
        int minVal = Arrays.stream(nums).min().getAsInt();
        int maxVal = Arrays.stream(nums).max().getAsInt();

        // 2. 定义桶长度、数量、数组
        int d = Math.max(1, (maxVal - minVal) / (len - 1));
        int bucketNum = (maxVal - minVal) / d + 1;
        int[][] buckets = new int[bucketNum][2];

        // 补：初始化桶数组
        for (int i = 0; i < buckets.length; i++) {
            Arrays.fill(buckets[i], -1);
        }

        // 3. 遍历nums数组，放置到桶中
        for (int i : nums) {
            int bi = (i - minVal) / d;
            if (buckets[bi][0] == -1) {
                buckets[bi][0] = buckets[bi][1] = i;
            } else {
                buckets[bi][0] = Math.min(buckets[bi][0], i);
                buckets[bi][1] = Math.max(buckets[bi][1], i);
            }
        }

        // 4. 遍历每个桶 取后一个桶与前一个桶差值的最大值
        int preBi = -1;
        int ans = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i][0] == -1) { // 先剔除无效桶
                continue;
            }
            if (preBi != -1) {
                ans = Math.max(ans, buckets[i][0] - buckets[preBi][1]);
            }
            preBi = i;
        }
        return ans;
    }

    /**
     * 思路二：基数排序
     *
     * 先按照个位，十位，百位...，直至nums出现的最大值的位数
     * 对每个位数进行排序，然后重新赋值回原数组，这样整个数组就整体基于个位、十位、百位进行排序，整体就是有序的。
     *
     * 其实是类似于多级排序，比如先把数组按照个位排序好，然后在统计每个十位数字的出现个数，最后通过十位出现个数来重组数组时，其实
     * 是依赖于按照个位排好序的数组，这样十位重组完后，整个数组就是按照个位、十位都排好序的。如果我们遍历完最大的位数，那整个数组就是有序的了。
     *
     * 基数排序和桶排序的异同：
     * 本质也是桶排序，只是桶的个数是固定的，且是固定的。基数排序可以理解为桶排序的一种特殊情况，或者是一种扩展。所以两者广义上都可以称之为桶排序。
     *
     * 注意：cnt数组需要每次重新赋值，避免上轮循环产生的数据干扰。
     *
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {

        if (nums.length < 2) {
            return 0;
        }
        radixSort(nums);
        int ret = 0;
        for (int i = 1; i < nums.length; i++) {
            ret = Math.max(ret, nums[i] - nums[i - 1]);
        }
        return ret;
    }

    // 基数排序
    private void radixSort(int[] nums) {

        // 1. 统计nums中的最大值maxVal
        int maxVal = Arrays.stream(nums).max().getAsInt();

        // 2. 定义buf、cnt、位数k
        int n = nums.length;
        int[] buf = new int[n];
        int k = 1;

        // 3. 循环，直至maxVal < k
        while (maxVal >= k) {

            // cnt数组需要重新赋值 相当于是清空数组
            int[] cnt = new int[10];

            // 4. 每次循环 统计cnt数组 累加cnt数组 然后赋值buf数组 最后交换nums数组 位数*10
            for (int i = 0; i < nums.length; i++) {
                int digit = (nums[i] / k) % 10;
                cnt[digit]++;
            }

            for (int i = 1; i < cnt.length; i++) {
                cnt[i] += cnt[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int digit = (nums[i] / k) % 10;
                buf[cnt[digit] - 1] = nums[i];
                cnt[digit]--;
            }

            System.arraycopy(buf, 0, nums, 0, n);
            k *= 10;
        }

    }




    public static void main(String[] args) {
        No69_164_MaximumGap obj = new No69_164_MaximumGap();
        int[] arr = {1,10000000};
        int ans = obj.maximumGap(arr);
        System.out.println(ans);
    }

}
