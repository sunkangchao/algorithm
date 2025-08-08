package leetcode.top100;

import baseclass.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * No94_112_HasPathSum
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>08月 08, 2025</pre>
 */
public class No94_112_HasPathSum {



    // 递归1: 基于递归特性
    // 总结：调试总是看不出来问题啊 干瞪着都看不见写错了哪里？ 多是低级的错。
    // 思路基本没问题，提交失败总是很难瞪出来错在哪里 不调试不行 递归的话通过日志打印调试吧
    public boolean hasPathSum0(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return dfs(root, targetSum);
    }

    // 由于空节点的特殊性 需要额外拆一个方法出来
    // 还是犯了同样的错，叶子节点不能够通过单一的null来判断，一定要双节点都判断为空！！
    private boolean dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        int curSum = targetSum - root.val;
        if (root.left == null && root.right == null) {
            return curSum == 0;
        }

        return dfs(root.left, curSum) || dfs(root.right, curSum);
    }


    // ---------------------------------------

    // 广度优先搜索
    // 一层一层把累加值统计到队列，当触达叶子节点时判断总和是否符合
    // 方法一：维护一个双队列，一个是节点，一个是值。只要保持同时压入和弹出，那么就能保证节点和值是对应的
    // 方法二：当然，你也可以使用一个数组，把节点和值同时存储在一起，这里给你提供一个新的思路
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        // 定义双队列
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> valQueue = new LinkedList<>();
        nodeQueue.add(root);
        valQueue.add(root.val);

        // 循环，无需分层遍历 同步维护双队列的值
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int curSum = valQueue.poll();// 保持同步

            // 触达叶子节点时判断当前总和 直接返回
            if (node.left == null && node.right == null) {
//                return curSum == targetSum; // todo 这里写错了 致命之错误 你提前返回了
                if (curSum == targetSum) {
                    return true;
                }
                continue;
            }

            // 否则判空添加至队列 同步维护
            if (node.left != null) {
                nodeQueue.add(node.left);
                valQueue.add(curSum + node.left.val);
            }

            if (node.right != null) {
                nodeQueue.add(node.right);
                valQueue.add(curSum + node.right.val);
            }
        }

        // 不满足
        return false;
    }


    public static void main(String[] args) {
        No94_112_HasPathSum obj = new No94_112_HasPathSum();


    }


}
