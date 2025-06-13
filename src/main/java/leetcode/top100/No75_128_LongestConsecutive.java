package leetcode.top100;

import java.util.HashSet;
import java.util.Set;

/**
 * No75_128_LongestConsecutive
 *
 * 128. 最长连续序列
 *
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>06月 13, 2025</pre>
 */
public class No75_128_LongestConsecutive {



    // 先把元素存储到set当中，遍历每个元素，统计每个元素为起点在集合中的连续序列
    // 重点优化：如果nums[i] - 1的值在set中，先不进行统计，直至最小那个在set的元素才开始统计，避免重复计算（时间复杂度从O(n^2)降低至O(n)）
    // 这步优化不是使用空间换时间实现的，而是使用了剪枝的思想实现的。
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // 1. 添加到set当中
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }

        // 2. 定义结果长度，遍历nums，如果num-1不在当前set当中就开始遍历
        int ans = 1;
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i] - 1)) { // 重点优化步骤 从最小的开始遍历
                int curLen = 1;
                int cur = nums[i];

                // 3. 在循环内层while循环，统计长度
                while (set.contains(cur + 1)) {
                    curLen++;
                    cur = cur + 1;
                }
                ans = Math.max(curLen, ans);
            }
        }

        // 4. 返回最大长度
        return ans;
    }


}
