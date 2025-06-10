package leetcode.top100;

import java.util.Arrays;

/**
 * No63_354_MaxEnvelopes
 *
 * <p/>
 * 354. 俄罗斯套娃信封问题
 *
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 *
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 *
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 *
 * 注意：不允许旋转信封。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 27, 2025</pre>
 */
public class No63_354_MaxEnvelopes {


    /**
     * 先对二维数组进行排序预处理：按照宽度升序排列，且宽度相等按照高度降序排列
     *
     * 疑问：为什么宽度相等时按照高度降序排列？
     * 由于宽度相等时，无法形成套娃，需要剔除这个结果，如果想按照高度来找到最多的信封数量，
     * 就需要保证高度始终有效，所以需要把宽度相等时高度最大的放在前面，这样就可以按照高度数组，
     * 问题转化成求解高度数组的最长递增子序列问题。
     *
     * 最长递增子序列问题有两种解法：
     * 1）O(n^2)，每次往前遍历，找到长度最大的子序列，然后+1
     * 2）O(nlogn)，二分法，维护一个长度数组，且数组中的每个元素为以数组下标+1为长度的最小元素。
     *
     * 注意：
     * 1）二分法的特点，在达到left > right条件前，不一定会经过left == right的。
     * 如果是往右半边区间缩减，那么会经过left==right，此时元素个数为2。
     * 如果是往左半边区间缩减，那么不会经过left==right，此时元素个数为2。本质是因为除法是向下取整的。
     *
     * 2）二分法大有学问，循环终止条件，边界收敛方式，都与查找目标场景 和 数组维护逻辑 密切相关。
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {

        // 1. 排序
        Arrays.sort(envelopes, (a1, a2) -> {
            if (a1[0] == a2[0]) {
                return a2[1] - a1[1];
            }
            return a1[0] - a2[0];
        });

        // 2. 按照高度组织新数组
        int n = envelopes.length;
        int[] height = new int[envelopes.length];
        for (int i = 0; i < n; i++) {
            height[i] = envelopes[i][1];
        }

        // 3. 求解最长公共子序列（二分法求解）
        int[] ret = new int[n];
        ret[0] = height[0];
        int size = 1;
        for (int i = 1; i < n; i++) {
            // 4. 大于size直接添加、小于size找到其索引位，然后交换 最后返回最大长度
            if (height[i] > ret[size - 1]) {
                ret[size++] = height[i];
            } else {
                int k = binarySearch(ret, size, height[i]);
                ret[k] = height[i];
            }
        }
        return size;
    }

    // 设计一个方法，查询数组中第一个数ret[i]，使的 ret[j] < target <= ret[i]
    private int binarySearch(int[] ret, int len, int target) {
        int left = 0, right = len - 1;

        // 想想本来是查询什么功能的 就知道这里的边界是left < right 有没有等号
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (ret[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }


    // 查找小于target的最大i，即是小于target的最右边的i
//    private int binarySearchMaxI(int[] arr, int len, int target) {
//        int left = 0, right = len - 1;
//        while (left < right) {
//            int mid = left + (right - left) / 2;
//            if (target > arr[mid]) {
//                left = mid + 1;
//            } else if (target <= arr[mid]) {
//                right = mid; // 又是这里，right必须是mid，而不能是right - 1 这是因为当
//            }
//        }
//        return left;
//    }


//    private int binarySearchLIS(int[] height) {
//
//        // 定义长度数组 定义长度
//        int[] arr = new int[height.length];
//        int len = 0;
//
//        // 循环二分法 在数组中找寻元素的位置 如果left = len 则赋值该位置且更新最长长度 否则只更新该位置
//        for (int i = 0; i < height.length; i++) {
//            int val = height[i];
//            int left = 0, right = len;
//            while (left < right) { // 和一般二分法不同 这里left==right时 此时最大了 无需在循环
//                int mid = left + (right - left) / 2;
//                if (val > arr[mid]) { // 当val > height[mid] 往右寻找 left = mid + 1
//                    left = mid + 1;
//                } else if (val < arr[mid]) { // 当val < height[mid] 往左循环 right = mid - 1
//                    right = mid; // 如果right = mid - 1，结果就错了
//                } else { // 当val == height[mid] 递增子序列为严格递增 当相等时剔除 需要严格递增 right = mid - 1
//                    right = mid;
//                }
//            }
//            if (left == len) {
//                len++;
//            }
//            arr[left] = val;
//        }
//
//        return len;
//    }

    // [[5,4],[6,4],[6,7],[2,3]]
    public static void main(String[] args) {
        No63_354_MaxEnvelopes obj = new No63_354_MaxEnvelopes();
        // new int[][] {{5,4}, {6,4}, {6,7}, {2,3}};
        int[][] arr = new int[][] {{4,5},{4,6},{6,7},{2,3},{1,1},{1,1}};
        int ans = obj.maxEnvelopes(arr);
        System.out.println(ans);
    }


}
