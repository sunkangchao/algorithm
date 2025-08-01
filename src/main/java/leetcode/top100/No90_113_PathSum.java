package leetcode.top100;

import leetcode.top100.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * No90_113_PathSum
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>08月 01, 2025</pre>
 */
public class No90_113_PathSum {


    private List<List<Integer>> result = new ArrayList<>();;

    // dfs + 回溯
    // 踩坑：生成了重复的数据 因为node==null时你把当前列表添加至结果集 对于每个叶子节点 会出现两次node==null的情况
    // 所以，应该是在它是叶子节点时添加至结果集，即左节点和右节点同时为空
    // 其实不算回溯，最多也就是深度优先搜索
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return new ArrayList<>();
        }
        backtrack(root, targetSum, new ArrayList<>(), 0);
        return result;
    }

    private void backtrack(TreeNode node, int targetSum, List<Integer> curList, int curSum) {
        if (node == null) {
            return;
        }

        // 添加当前元素
        curList.add(node.val);

        if (node.left == null && node.right == null) {
            if ((curSum + node.val) == targetSum) {
                ArrayList<Integer> integers = new ArrayList<>(curList);
                result.add(integers);
            }
        }

        backtrack(node.left, targetSum, curList, curSum + node.val);
        backtrack(node.right, targetSum, curList, curSum + node.val);

        // 移除当前元素
        curList.remove(curList.size() - 1);
    }

}
