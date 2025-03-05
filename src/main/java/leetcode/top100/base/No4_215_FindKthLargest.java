package leetcode.top100.base;

import study.util.PrintArray;

/**
 * @author sunkangchao
 * @since 2025/3/5 23:48
 */
public class No4_215_FindKthLargest {



    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, k, 0, nums.length - 1);
    }

    // 返回值为第K大的数值
    public int findKthLargest(int[] nums, int k, int l, int r) {
        // 递归出口 找不到就返回-1？出口条件完善了吗 还有哪些情况没有考虑？
        if (l > r) {
            return -1;
        }

        // 使用快排方法
        int len = nums.length;

        int baseValueIndex = splitArrByBaseValue(l, nums, l, r);
        // 找到找到基准值恰好位于N-K位置的值 返回该第K大的值
        if (baseValueIndex == len - k) {
            return nums[baseValueIndex];
        }
        // 否则继续往下递归
        int leftValue = findKthLargest(nums, k, l, baseValueIndex - 1);
        int rightValue = findKthLargest(nums, k, baseValueIndex + 1, r);

        // 左右必然有一边能找到？是的，因为如果能找到早就返回了 不会继续往下递归 错的 如果递归走的是同一边 这里根本就不会有结果
        if (leftValue == len - k) {
            return leftValue;

        }
        if (rightValue == len - k) {
            return rightValue;
        }
        return -1;
    }


    // 写一个方法 根据基准值把数组分成小于基准值和大于基准值的两组 且返回基准值的索引位
    private int splitArrByBaseValue(int index, int[] nums, int l, int r) {
        int baseValue = nums[index];
        // 以下将会使用到快排的思想 划分成两个数组 回忆一下
        // 操作左边
        while (l < r) {
            // 操作右边
            while (l < r) {
                if (nums[r] >= baseValue) {
                    r--;
                } else {
//                    swap(nums, l, r); // 其实这里并不是交换，而应该是覆盖
                    nums[l] = nums[r];
                    l++;
                    break;
                }
            }
            while (l < r) {
                if (nums[l] < baseValue) {
                    l++;
                } else {
                    // 继续憋代码 此时和右边的交换位置
//                    swap(nums, l, r);
                    nums[r] = nums[l];
                    r--;
                    break;
                }
            }
        }
        // 此时l = r 然后把index位置和l交换
        nums[l] = baseValue;
        return l;
    }

    public static void main(String[] args) {
        int[] nums = {4,1,5,2,6,3,7};
        No4_215_FindKthLargest instance = new No4_215_FindKthLargest();
        int kthLargest = instance.findKthLargest(nums, 2);
        System.out.println(kthLargest);
        PrintArray.printArray(nums);
    }





}
