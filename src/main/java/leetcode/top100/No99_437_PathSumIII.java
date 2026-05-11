package leetcode.top100;

import leetcode.top100.base.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * No99_437_PathSumIII
 *
 *
 * 437. 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 *
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 示例 1：
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 11, 2026</pre>
 */
public class No99_437_PathSumIII {


    private int ret = 0;
    // 定义Map<Long, Integer> prefix为当前node节点的全部前缀和，其中key为当前前缀和，value为
    // 为什么用Map？这道题类似两数之和，为的就是以O(1)的时间复杂度来快速判断是否存在目标前缀和。
    //      todo 为什么不是set, 因为相同前缀和可能不是只有1个，如果有多个路径前缀和是一样的，那么路径就会有多条（这里最容易犯错）
    // 为什么中间卡了这么久？搞不清为什么使用Map，key和value又分别定义为什么
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1); // 这条件是必须的，否则第一个节点刚好满足，在这里就会找不到结果
        pathSum(root, targetSum, prefix, 0);
        return ret;
    }

    private void pathSum(TreeNode node, int targetSum, Map<Long, Integer> prefix, long curr) {
        if (node == null) {
            return;
        }

        curr += node.val;
        int n = prefix.getOrDefault(curr - targetSum, 0);
        ret += n; // 累加结果，直接忽略判断
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);

        pathSum(node.left, targetSum, prefix, curr);
        pathSum(node.right, targetSum, prefix, curr);

        prefix.put(curr, prefix.get(curr) - 1); // 回溯，避免遍历至下一个分叉还带着当前的前缀和
    }


    // 解法二，深度优先遍历
    // 思路：每遍历一个节点，则从该节点开始一轮以其为起始节点的查找，嵌套深度优先遍历查找是否存在满足目标和为target的路径
    // 这里显然存在两层递归，所以至少需要两个函数
    public int pathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = 0;
        ret += rootSum(root, targetSum);
        ret += pathSum2(root.left, targetSum);
        ret += pathSum2(root.right, targetSum);
        return ret;
    }

    // 先捋清楚函数的定义，否则后续会很慢。该函数定义为以node为起始节点，路径和为targetSum的路径条数
    // 返回路径和为targetSum的路径数
    // targetSum参数需要是long类型 否则会越界 判断是否超过41亿
    public int rootSum(TreeNode node, long targetSum) {

        int ret = 0;
        if (node == null) {
            return ret;
        }

        if (node.val == targetSum) {
            ret++;
        }

        ret += rootSum(node.left, targetSum - node.val);
        ret += rootSum(node.right, targetSum - node.val);

        return ret;
    }


    public static void main(String[] args) {
        No99_437_PathSumIII instance = new No99_437_PathSumIII();
        instance.pathSum(null, 0);
    }

}
