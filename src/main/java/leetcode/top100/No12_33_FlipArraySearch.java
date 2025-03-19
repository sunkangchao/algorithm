package leetcode.top100;

/**
 * No12_33_FlipArraySearch
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>03月 19, 2025</pre>
 */
public class No12_33_FlipArraySearch {


    /**
     * 二分法：题目给了一个旋转数组，所谓旋转数组是在给定的某个未知下标处对后续元素进行翻转，把后续元素提到数组的前面
     * 数组本身有序，翻转过后的数据会呈现两段递增，要求找到给定的某个数值，且时间复杂度O(logn)，很显然就是翻转过后的数组仍然
     * 要求你使用二分法，只要能结合这个翻转特性，每次能判断目标值坐落在哪个半区间，这道题就解决了。
     * 接下来分情况讨论：假设给定数组nums = [4,5,6,7,0,1,2], target = 0
     * 1）先判断目标值target位于哪一个半区，是第一段上升区间，还是第二段上升区间；这个判断拿target和数组中最后一个元素来比较
     * 2）再判断当前中值位于哪个上升区间，如果和target位于同一个上升区间，那么就要再判断中值和目标值的大小，从而确定往左半区还是右半区
     * 3）如果中值和目标值位于不同的上升区间，那么只需要往目标上升区间的半区继续查找即可
     *
     *
     * 注意：翻转的下标取自数组的下标 如果是最后一个元素 翻转前后的结果是一样的 你要根据翻转后的第一个元素和最后一个元素做对比
     * 来判断是否发生过翻转 如果没有发生过翻转 使用普通的二分查找来解决
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {

        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int L = 0, R = nums.length - 1;
        int lastNum = nums[nums.length - 1];
        // 判断数组是否真正发生过
        boolean flip = nums[0] > nums[nums.length -1];

        if (flip) {
            while (L <= R) {

                int m = (L + R) / 2;

                if (nums[m] == target) {
                    return m;
                }

                // 判断目标值在哪个半区
                if (lastNum >= target) {
                    // 在第二段上升区间
                    if (nums[m] > lastNum) {
                        // 当前中间索引在第一段上升区间 此时目标值在右侧
                        L = m + 1;
                    } else {
                        // 当前中间索引在第二段上升区间 继续判断中间值与目标值
                        if (nums[m] > target) {
                            // 此时说明目标值可能在左边区间
                            R = m - 1;
                        } else {
                            // 否则在中间值右边
                            L= m + 1;
                        }
                    }
                } else {
                    // 目标值在第一段上升区间
                    if (nums[m] > lastNum) {
                        // 当前索引在第一段上升区间 需要再次判断中间值与目标值
                        if (nums[m] > target) {
                            // 说明在中间值的左侧
                            R = m - 1;
                        } else {
                            // 否则在中间值右侧
                            L = m + 1;
                        }
                    } else {
                        // 当前索引在第二段上升区间 已知目标值在第一段上升区间 那么必然是往左边找
                        R = m - 1;
                    }
                }

            }

        } else {
            // 按照正常的二分法查找

            while (L <= R) {
                int m = (L + R) / 2;

                if (nums[m] == target) {
                    return m;
                }

                if (nums[m] > target) {
                    R = m - 1;
                } else {
                    L = m + 1;
                }

            }
    }

        return - 1;
    }

    public static void main(String[] args) {
        No12_33_FlipArraySearch instance = new No12_33_FlipArraySearch();

        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;

        int result = instance.search(nums, target);
        System.out.println(result);

    }



}
