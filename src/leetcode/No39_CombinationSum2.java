package leetcode;


import java.util.*;

/**
 * No39_CombinationSum
 * 组合总和（可重复选择）
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>07月 05, 2024</pre>
 */
public class No39_CombinationSum2 {


    private final List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new ArrayList<>();
        }
        backtrack(candidates, target, 0, 0, new ArrayList<>());
        return result;
    }

    private void backtrack(int[] candidate, int target, int n, int sum, List<Integer> pickedList) {
        if (sum == target) {
            // 有什么办法可以去重 目前结果：[[2, 3, 2], [2, 2, 3], [3, 2, 2], [7]]

            result.add(new ArrayList<>(pickedList));
            return;
        }
        // 如果已经超过target 那么可以直接放弃往下递归了 这条分支就不应该继续了
        if (sum > target) {
            return;
        }

        // 重新开始选择
        for (int i = n; i < candidate.length; i++) {
            // 选择第n个元素
            pickedList.add(candidate[i]);
            // 第三个参数 传的是n，那么结果为[[2, 3, 2], [2, 2, 3], [3, 2, 2], [7]]
            // 如果传的是i，结果为[[2, 2, 3], [7]]  就这么保证了唯一组合 6
            backtrack(candidate, target, i, sum + candidate[i], pickedList);

            // 回溯 移除最后一个元素
            pickedList.remove(pickedList.size() - 1);
        }

    }


    public static void main(String[] args) {
        No39_CombinationSum2 instance = new No39_CombinationSum2();
        int[] array = {2,3,6,7};
        List<List<Integer>> lists = instance.combinationSum(array, 7);
        System.out.println(lists);;
    }

}
