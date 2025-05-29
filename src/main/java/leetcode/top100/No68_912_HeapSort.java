package leetcode.top100;

import study.util.PrintArray;

/**
 * No68_912_HeapSort
 *
 * 堆排序
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 29, 2025</pre>
 */
public class No68_912_HeapSort {


    public int[] sortArray(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }

        // 1. 数组堆化
        int length = nums.length;
        for (int i = (length - 1) / 2; i >= 0; i--) {
            maxHeapify(nums, i, length);
        }
        // 2. 排序输出
        int size = length;
        for (int i = length - 1; i >= 0; i--) {
            swap(nums, i, 0);
            size--;
            maxHeapify(nums, 0, size);
        }
        return nums;
    }


    // 堆化-下沉操作
    // 注意： i << 1才是乘以2，i <<2 是乘以4了，不要搞错
    // 其次，注意分清楚，索引和值。
    // len和平时一样，索引位最多只能到len - 1
    // 取左右节点最大值时，第二步要跟large比较了，而不是一直跟i比较
    // 分清楚索引和值，别总是写错这里
    private void maxHeapify(int[] nums, int i, int len) {
        // 每轮循环取当前节点与左右子节点比较 交换 赋值新的当前节点
        while ((i << 1) + 1 < len) { // 左边节点都越界时 它就是叶子节点了
            int large = i;
            int left = (i << 1) + 1;
            int right = (i << 1) + 2;

            if (left < len && nums[left] > nums[i]) {
                large = left;
            }
            if (right < len && nums[right] > nums[large]) { //
                large = right;
            }
            if (large != i) {
                swap(nums, i, large);
                i = large;
            } else {
                break;
            }
        }
    }


    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public static void main(String[] args) {
        No68_912_HeapSort obj = new No68_912_HeapSort();
        int[] arr = {5,1,1,2,0,0};
        int[] ans = obj.sortArray(arr);
        PrintArray.printArray(ans);
    }


}
