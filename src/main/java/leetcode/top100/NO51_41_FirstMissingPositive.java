package leetcode.top100;

/**
 * 41. 缺失的第一个正数
 *
 * <p>
*     给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 *
 *    请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 *
 * </p>
 *
 * @author sunkangchao
 * @since 2025/5/15 01:54
 */
public class NO51_41_FirstMissingPositive {


    /**
     * 思考过程：这道题目要求找出第一个缺失的正整数，经过我们分析，对于一个长度为n的数组，
     * 我们只需要从1-n遍历，查看每个数字是否存在nums数组中即可，也就是可以使用Set或者Map
     * 来存储大于0且小于n的数字，然后从1-n遍历，看是否包含和这个数字，返回第一个不包含的数字，就是答案。
     * 但是这样的时间复杂度为O(n)，满足要求，但是空间复杂度也是O(n)，题意要求O(1)的空间复杂度，显然不满足。
     *
     * 要求O(1)的空间复杂度，换言之，也就是我们只能在原数组上面修改，然而直接把出现的数字对应的索引下标更改会导致原数组的值丢失，
     * 为了解决这个问题，我们采用标记为负数的方式来解决，当然，这里使用其它规则也可以，只要能起到标记的作用且不丢失原数组的值即可。
     *
     * 我们发现，如果我们在出现的数字对应的下标位标记负数的话，由于原数组中也存在负数，且等于0的数字我们无法进行取反。
     * 所以，需要先对数组进行一步预处理，即把小于等于0的数字标记成nums.length + 1，然后我们再把数字下标标记为负数，
     * 最后，我们只需要遍历一次，取到第一个为正数的即是结果。
     *
     * 思路看起来没问题，我们写一遍。
     *
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {

        // 1. 先对数组中小于等于0的数字预处理成nums.length + 1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = nums.length + 1;
            }
        }

        // 2. 遍历整个数组，把数字对应下标位处理成负数，需要注意重复出现的数字频繁取反会出现干扰结果，需要先取绝对值
        for (int i = 0; i < nums.length; i++) {
            int abs = Math.abs(nums[i]);
            if (abs <= nums.length) {
                nums[abs - 1] = - Math.abs(nums[abs - 1]);
            }
        }

        // 3. 再次遍历数组，返回第一个为正数的下标 + 1，如果没有正数，就返回 n + 1。
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        // 4. 没有正数就返回数组长度+1
        return nums.length + 1;
    }

    public static void main(String[] args) {
        NO51_41_FirstMissingPositive obj = new NO51_41_FirstMissingPositive();
        int[] arr = {3,4,-1,1};
        int ans = obj.firstMissingPositive(arr);
        System.out.println(ans);
    }



}
