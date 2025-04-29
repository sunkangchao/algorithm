package leetcode.top100;
/**
 *
 * 31. 下一个排列
 *
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列
 * 更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 *
 * @author sunkangchao
 * @since 2025/4/23 00:51
 */
public class No33_31_NextPermutation {


    /**
     * 找到下个排列
     * 思路：就是找最后侧的递升序列，然后把它们交换位置，注意是最右侧的，那么这就是下一个序列
     * @param nums
     */
    public void nextPermutation(int[] nums) {

        int i = nums.length - 2;
        // 此时一个等号都是无比的重要 需要劲射边界条件
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            // 说明没有到边界
            int j = nums.length - 1;
            while (j >= i && nums[i] >= nums[j]) {
                j--;
            }
            // 此时nums[i] < nums[j] 抽一个函数出来
            swap(nums, i, j);
        }

        // 此时整个数组降序排列 则逆序整个数组
        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start) {
        // 这里写错了 j应该取索引值 而不是数组值
//        int i = start, j = nums[nums.length - 1];
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

}
