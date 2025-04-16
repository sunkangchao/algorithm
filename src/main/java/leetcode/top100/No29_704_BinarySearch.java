package leetcode.top100;

/**
 * 704. 二分查找
 *
 * @author sunkangchao
 * @since 2025/4/15 23:22
 */
public class No29_704_BinarySearch {


    /**
     * 第一种：迭代法
     *
     * 注意二分法的终止条件为L<=R
     * 2) 注意返回值是索引位
     * 脑子不清晰，一到简单题都写半天。你先把思路捋清楚吧。
     * @param nums
     * @param target
     * @return
     */
    public int search1(int[] nums, int target) {
        int L = 0, R = nums.length - 1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            if (target > nums[mid]) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 第二种：递归法
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        // 一般都取左闭右闭区间
        return search(nums, target, 0, nums.length - 1);
    }

    private int search(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] > target) {
            return search(nums, target, left, right - 1);
        } else {
            return search(nums, target, left + 1, right);
        }
    }

}
