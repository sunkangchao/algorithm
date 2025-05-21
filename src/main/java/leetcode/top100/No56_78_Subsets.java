package leetcode.top100;

import java.util.ArrayList;
import java.util.List;

/**
 * No56_78_Subsets
 *
 * <p/>
 * 78. 子集
 *
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 21, 2025</pre>
 */
public class No56_78_Subsets {

    private List<List<Integer>> result = new ArrayList<>();

    /**
     * 思路：回溯、迭代法（直接从0-2^n，通过索引掩码对应的bit位来选择取不取元素）
     *
     * 已知一个数组的全部子集个数为2^n，其中n为元素个数，由于每一位都存在选择/不选两种可能，因此2^n。
     *
     * 原问题等于最后一个元素的可选与不选时，再从[0, len - 2]选取的全部可能
     *
     * 复杂度：
     * 时间复杂度：O(2^n * n)，一共需要遍历2^n种状态，每种状态需要o(n)的复杂度来构造子集。
     * 空间复杂度：O(n)，临时数组的长度。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, new ArrayList<>(), 0);
        return result;
    }

    private void backtrack(int[] nums, List<Integer> cur, int index) {
        if (index >= nums.length) {
            result.add(new ArrayList<>(cur));
            return;
        }

        cur.add(nums[index]);
        backtrack(nums, cur, index + 1);

        cur.remove(cur.size() - 1);
        backtrack(nums, cur, index + 1);

    }

    public static void main(String[] args) {
        No56_78_Subsets obj = new No56_78_Subsets();
        List<List<Integer>> ans = obj.subsets(new int[]{1, 2, 3});
        System.out.println(ans);
    }

}
