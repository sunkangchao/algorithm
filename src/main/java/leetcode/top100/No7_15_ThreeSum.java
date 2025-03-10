package leetcode.top100;

import study.util.PrintArray;

import java.util.*;

/**
 * No7_15_ThreeSum
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>03月 10, 2025</pre>
 */
public class No7_15_ThreeSum {


    /**
     * 核心思路就是三数之和，拆分成1+2的方式，结合两数之和来解答这个问题，时间复杂度O(n2)
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {

        // 要求不能出现重复的三元组
        // a + b + c = 0，而不是其它数值

        Arrays.sort(nums); // 先排序 nums = [-4,-1,-1,0,1,2]

        List<List<Integer>> result= new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > 0) {
                // 因为已经排好序 第一个元素大于0 再和后面的元素相加不可能等于0
                break;
            }
            if (i == 0 || nums[i] != nums[i - 1]) {
                // 此时才取出第一个元素
                int first = nums[i];

                // 还差两个元素 要保证不重复 性能？ 此时可以按照两数之和去解 由于不需要返回索引位 HashSet足够
                Set<Integer> set = new HashSet<>();
                int j = i + 1;
                while (j < nums.length) {
                        // 第二个元素也不重复 因为每个元素都会全部遍历到 出现重复就可能出现重复的答案
                        int k = - (first + nums[j]);
                        if (set.contains(k)) {
                            result.add(new ArrayList<>(Arrays.asList(first, nums[j], k)));
                            // HashMap解法的核心步骤 这里要避免出现重复解 跳过
                            // 注意：循环结束时nums[j + 1] != nums[j] 也就是出现不一样的是j+1索引位 下一层循环j还需要额外加1
                            while (j < nums.length - 1 && nums[j + 1] == nums[j]) j++;
                        } else {
                            set.add(nums[j]);
                        }
                        j++;
                }
            }
        }
        return result;
    }

    /**
     * 排序 + 双指针，此题目的最优解法
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break; // 因为已经排好序 第一个元素大于0 再和后面的元素相加不可能等于0 直接结束

            if (i == 0 || nums[i] != nums[i - 1]) {
                int first = nums[i];

                int n = nums.length;

                // 双指针题目使用L,R命名变量
                int j = i + 1;
                int k = n - 1;

                while (j < k) {

                    int tmp = first + nums[j] + nums[k];
                    if (tmp == 0) {
                        result.add(new ArrayList<>(Arrays.asList(first, nums[j], nums[k])));
                        while (j < k && nums[j] == nums[++j]);
                        while (j < k && nums[k] == nums[--k]);
                    } else if (tmp > 0) {
                        while (j < k && nums[k] == nums[--k]);
                    } else {
                        while (j < k && nums[j] == nums[++j]);
                    }
                }
            }
        }
        return result;
    }

        public static void main(String[] args) {

        No7_15_ThreeSum instance = new No7_15_ThreeSum();
//        List<List<Integer>> lists = instance.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
//        List<List<Integer>> lists = instance.threeSum2(new int[]{0,0,0,0});
        List<List<Integer>> lists = instance.threeSum2(new int[]{1,-1,-1,0});

        System.out.println(lists);

    }



}
