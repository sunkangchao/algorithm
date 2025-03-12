package leetcode.top100;

import study.util.PrintArray;

/**
 * No9_912_QuickSort
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>03月 12, 2025</pre>
 */
public class No9_912_QuickSort {



    public int[] sortArray(int[] nums) {
        return sortArray(nums, 0, nums.length - 1);
    }

    public int[] sortArray(int[] nums, int l, int r) {
        if (l >= r) {
            return nums;
        }

//        swap(nums, l, (l + r) / 2);
        int m = split2(nums, l, r);

        int m1 = m, m2 = m;
//        while (m1-- >= l && nums[m1] == nums[m1 + 1]);
//        while (m2++ <= r && nums[m2] == nums[m2 - 1])
        while (m1 > l && nums[m1] == nums[m1 - 1]) m1--;
        while (m2 < r && nums[m2] == nums[m2 + 1]) m2++;
        m1--;m2++;

        sortArray(nums, l, m1);
        sortArray(nums, m2, r);
        return nums;
    }


    /**
     * 使用覆盖的方式完成数组划分
     * @param nums
     * @param l
     * @param r
     * @return
     */
    private int split(int[] nums, int m, int l, int r) {
        int num = nums[m];
        while (l < r) {
            while (l < r && nums[r] >= num) r--;
            if (l < r) {
                nums[l] = nums[r];
                l++;
            }

            while (l < r && nums[l] < num) l++;
            if (l < r) {
                nums[r] = nums[l];
                r--;
            }
        }
        nums[m] = num;
        return l;
    }


    /**
     * 交换法
     * @param nums
     * @param l
     * @param r
     * @return
     */
    private int split2(int[] nums, int l, int r) {

        int num = nums[l];
        while (l < r) {
            while (l < r && nums[r] >= num) r--;
            if (l < r) {
                swap(nums, l, r);
            }
            while (l < r && nums[l] < num) l++;
            if (l < r) {
                swap(nums, l, r);
            }
        }
        return l;

    }

    private void swap(int num[], int i, int j) {
//        num[i] = num[i] ^ num[j];
//        num[j] = num[i] ^ num[j];
//        num[i] = num[i] ^ num[j];
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }

    public static void main(String[] args) {
        No9_912_QuickSort instance = new No9_912_QuickSort();
//        int[] nums = {4,2,1,3,4,7,3,5};
        int[] nums = {5,1,1,2,0,0};
        int[] ints = instance.sortArray(nums);
        PrintArray.printArray(ints);

    }



}
