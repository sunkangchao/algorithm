package leetcode.top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * No14_46_Permute
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>03月 21, 2025</pre>
 */
public class No14_46_Permute {


    private List<List<Integer>> result = new ArrayList<>();


    /**
     * 给定一个数组，对这个数组中的元素进行全排序，结果输出为集合
     * 每个索引位都遍历所有的可能性，每次在数组中剔除一个元素往下遍历，
     * 因为结果是一个集合，并且使用集合时回溯比较方便，有现成的api方便你去删除某一个下标元素，
     * 且可以根据某个下标插入元素，所以使用集合作为参数来定义一个回溯方法
     * remaining数组为空时，把当前集合添加到结果集当中，结果存在属性当中即可
     *
     */
    public List<List<Integer>> permute(int[] nums) {
        // 2024-07-04 回溯法
        if (nums.length == 0) {
            return result;
        }
        List<Integer> numsList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        backtrack(numsList, new ArrayList<>());
        return result;
    }


    private void backtrack(List<Integer> remaining, List<Integer> current) {
        if (remaining.isEmpty()) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < remaining.size(); i++) {
            Integer removed = remaining.remove(i);
            current.add(removed);
            backtrack(remaining, current);
            // 回溯 每次执行完恢复原样
            remaining.add(i, removed);
            // 移除哪一个元素
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        No14_46_Permute solution = new No14_46_Permute();
        int[] nums = {1,2,3};
        List<List<Integer>> result = solution.permute(nums);
        System.out.println(result);
    }






}
