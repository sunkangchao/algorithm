package leetcode.top100;

import study.util.PrintArray;

/**
 * No62_34_SearchRange
 *
 * <p/>
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 26, 2025</pre>
 */
public class No62_34_SearchRange {


    /**
     * 二分法一般都是找到就停止，现在需要找的不是目标值，而是找到出现目标值的最小索引位和最大索引位，
     * 所以，需要在相等时再加上一个条件，就是继续二分，就是继续往左找，更新目标值出现的最小索引位，直至left > right。
     * 然而，我们需要找的是最小值和最大值，但是又不想写两套代码，那么你就需要一个变量来控制方向，只能这么干了。
     *
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }

        int leftMin = binarySearch(nums, target, true);
        int rightMax = binarySearch(nums, target, false);
        return new int[]{leftMin, rightMax};
    }

    // 因为一套代码无法控制两个方向 所以题解其实是用了一个变量来复用代码的
    private int binarySearch(int[] nums, int target, boolean lower) {
        int rs = -1;

        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 如果相等
            if (nums[mid] == target) {
                rs = mid;
                // 继续往下寻找
                if (lower) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
                continue;
            }

            // 如果不等
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return rs;
    }


    public static void main(String[] args) {
        No62_34_SearchRange obj = new No62_34_SearchRange();
        int[] arr = {5,7,7,8,8,10};
        int[] ans = obj.searchRange(arr, 8);
        PrintArray.printArray(ans);
    }


}
