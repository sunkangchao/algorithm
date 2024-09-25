package study.newhand;

import study.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 路径和集合，需要求出所有的路径
 *
 * @author sunkangchao
 * @date 2022/7/21 19:43
 */
public class Code21_PathSum {


    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        pathSum(root, curList, 0, ans, targetSum);
        return ans;
    }

    public void pathSum(TreeNode root, List<Integer> curList, int curSum, List<List<Integer>> ans, int targetSum) {
        if (root == null) {
            return;
        }
        curList.add(root.val);
        curSum += root.val;
        if (root.left == null && root.right == null) {
            if (curSum == targetSum) {
                ans.add(copy(curList));
            }
        }
        pathSum(root.left, curList, curSum, ans, targetSum);
        pathSum(root.right, curList, curSum, ans, targetSum);
        curList.remove(curList.size() - 1);
    }

    public List<Integer> copy(List<Integer> list) {
        return new ArrayList<>(list);
    }



}
