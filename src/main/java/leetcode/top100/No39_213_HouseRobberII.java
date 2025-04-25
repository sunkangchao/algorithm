package leetcode.top100;

/**
 * No39_213_HouseRobberII
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 25, 2025</pre>
 */
public class No39_213_HouseRobberII {


    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }

    private int rob(int[] nums, int start, int end) {
        int f0 = nums[start], f1 = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i < end; i++) {
            int tmp = f1;
            f1 = Math.max(nums[i] + f0, f1);
            f0 = tmp;
        }
        return f1;
    }


}
