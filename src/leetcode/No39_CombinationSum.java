package leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * No39_CombinationSum
 * 组合总和（不可重复选择）
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>07月 05, 2024</pre>
 */
public class No39_CombinationSum {


    private final List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 递归出口，相加大于target
        backtrack(candidates, target, 0, new ArrayList<>(), 0);
        return result;
    }


    private void backtrack(int[] candidates, int target, int n, List<Integer> currentList, int currentSum) {
        // 达到遍历的边界
        // 当前和超过target 放到下一层递归处理
        if (currentSum > target) {
            return;
        }
        // 当前和等于target，可以作为目标解
        if (currentSum == target) {
            result.add(new ArrayList<>(currentList));
            return;
        }
        if (n == candidates.length) {
            return;
        }

        // 选择当前节点
        currentList.add(candidates[n]);
        currentSum += candidates[n];
        backtrack(candidates, target, n + 1, currentList, currentSum);

        // 撤回当前节点 因为数据不重复
        currentList.remove(new Integer(candidates[n]));
        currentSum -= candidates[n];

        // 不选当前节点
        backtrack(candidates, target, n + 1, currentList, currentSum);

    }

    public static void main(String[] args) {
        No39_CombinationSum instance = new No39_CombinationSum();
        int[] array = {2,3,6,7};
        List<List<Integer>> lists = instance.combinationSum(array, 7);
        System.out.println(lists);;
    }

}
