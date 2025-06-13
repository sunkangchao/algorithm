package leetcode.top100;

import study.util.PrintArray;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * No74_39_CombinationSum
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>06月 13, 2025</pre>
 */
public class No74_39_CombinationSum {



    // 1. 记忆化搜索 不要直接写 先写暴力递归再改 否则不好写
    // 如何去重复？ 现在这个方法能够列举全部结果 但是是没有去重复的 如何去重复？ 先排序然后去重复
    // 集合的equals方法判定标准：两个集合包含相同的元素且顺序相同
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        return combinationSum(candidates, target, new HashMap<>());
    }

    private List<List<Integer>> combinationSum(int[] candidates, int target, Map<Integer, List<List<Integer>>> map) {
        if (map.containsKey(target)) {
            return map.get(target);
        }

        // 等于0时，可以直接知道结果，因此不用再继续往下递归，直接返回
        if (target == 0) {
            List<List<Integer>> list = new ArrayList<>();
            list.add(new ArrayList<>());
            return list;
        }

        // 定义返回结果
        List<List<Integer>> ret = new ArrayList<>();
        for (int i : candidates) {
            if (target - i < 0) {
                continue;
            }
            List<List<Integer>> subRet = combinationSum1(candidates, target - i);
            // 组合它的结果 并添加到结果集当中
            for (List<Integer> list : subRet) {
                list.add(0, i);
                list.sort(Comparator.comparingInt(t -> t));
                ret.add(list);
            }
        }

        List<List<Integer>> ans = ret.stream().distinct().collect(Collectors.toList());
        map.put(target, ans);
        return ans;
    }


    // 回溯法
    // 针对完全背包问题，回溯时需要注意，选择了一个元素，应该保持其索引位不变。不选择元素则往下跳一位。
    // 这样选择就能实现完全背包。
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        combinationSum(candidates, target, new ArrayList<>(), ret, 0);
        return ret;
    }

    private void combinationSum(int[] candidates, int target, List<Integer> cur, List<List<Integer>> ret, int index) {

        // 那么在这里index等于candidates.length时，应该提前结束
        if (index == candidates.length) {
            return;
        }

        // 0. 当target == 0时添加到结果当中
        if (target == 0) {
            ret.add(new ArrayList<>(cur));
            return;
        }

        // 1. 选择index位
        if (target - candidates[index] >= 0) { // 这里相当于小于0时不选择
            cur.add(candidates[index]);
            combinationSum(candidates, target - candidates[index], cur, ret, index); // 完全背包问题，这里保持索引位不变
            cur.remove(cur.size() - 1);
        }

        // 2. 不选择index位
        combinationSum(candidates, target, cur, ret, index + 1); // 不选择时+1处理
    }

    public static void main(String[] args) {
        No74_39_CombinationSum obj = new No74_39_CombinationSum();
        int[] arr = {2,3,6,7};
        List<List<Integer>> ans = obj.combinationSum(arr, 7);
        System.out.println(ans);
    }


}
