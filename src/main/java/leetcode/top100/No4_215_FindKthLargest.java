package leetcode.top100;

import study.util.PrintArray;

/**
 * @author sunkangchao
 * @since 2025/3/5 23:48
 */
public class No4_215_FindKthLargest {



    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return findKthLargest(nums, n - k, 0, n - 1);
    }


    /**
     * 第一种解法
     * 平均时间复杂度 nlogn 空间复杂度取决于递归栈的深度 logn
     * @param nums
     * @param k
     * @param l
     * @param r
     * @return
     */
    // 返回值为第K大的数值
    public int findKthLargest(int[] nums, int k, int l, int r) {
        // 递归出口 找不到就返回-1？出口条件完善了吗 还有哪些情况没有考虑？
        if (l == r) {
            // 一直二分法查找 此时只排除剩余最后一个 那这一个必然是答案
            return nums[k];
        }

        // 每次取中间的元素作为基准值 使其复杂度更偏向于理想
        swap(nums, l, (l + r) / 2);

        int j = splitArrByBaseValue(l, nums, l, r);
        // 找到找到基准值恰好位于N-K位置的值 返回该第K大的值
        if (j == k) {
            return nums[j];
        }
        // 否则继续往下递归 递归一边就可以了 不需要两边都递归
        if (k >= j) {
            return findKthLargest(nums, k, j + 1, r);
        } else {
            return findKthLargest(nums, k, l, j - 1);
        }
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


    private int splitArrByBaseValue2(int index, int[] nums, int i, int j) {
        int base = nums[index];
        while(i<j){
            while(i<j&&nums[j] >= base){
                j--;
            }
            if(i<j){
                swap(nums, i, j);
                // i++;
            }
            while(i<j&&nums[i] <= base){
                i++;
            }
            if(i<j){
                swap(nums, i, j);
            }
        }
        return i;
    }


    private void swap(int[] nums, int t1, int t2) {
        int tmp = nums[t1];
        nums[t1] = nums[t2];
        nums[t2] = tmp;
    }



    public static void main(String[] args) {
        int[] nums = {4,1,5,2,6,3,7};
        No4_215_FindKthLargest instance = new No4_215_FindKthLargest();
        int kthLargest = instance.findKthLargest(nums, 2);
        System.out.println(kthLargest);
        PrintArray.printArray(nums);
    }





}
