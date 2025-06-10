package leetcode.top100;

import leetcode.top100.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * No59_129_SumNumbers
 *
 * <p/>
 * 129. 求根节点到叶节点数字之和
 *
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 *
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 *
 * 叶节点 是指没有子节点的节点。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 26, 2025</pre>
 */
public class No59_129_SumNumbers {

    private int sum;

    /**
     * 思路：dfs
     *
     * 每一层判断左右节点是否为空，如果不为空则按照 curSum * 10 + root.val的方式继续往下传递当前总和
     *
     * 一步步地往下传递当前值，当它的左右节点都会null时，说明此时达到了当前分支的叶子节点，此时把当前分支总和加到成员变量sum上面。
     *
     * @param root
     * @return
     */
    public int sumNumbers1(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    private void dfs(TreeNode root, int curSum) {
        if (root.left != null) {
            dfs(root.left, curSum * 10 + root.val);
        }
        if (root.right != null) {
            dfs(root.right, curSum * 10 + root.val);
        }
        if (root.left == null && root.right == null) {
            sum += curSum * 10 + root.val;
        }
    }


    // 解法二 dfs
    public int sumNumbers2(TreeNode root) {
        return dfsCalc(root, 0);
    }

    // 像循环一样，抽取出每一层递归需要做的事情，即抽取出一个循环单元。（确定每一次循环/递归需要做的事情边界）
    private int dfsCalc(TreeNode node, int num) {
        // 确定出口
        if (node == null) {
            return 0;
        }

        // 每一个节点的总和都等于它的左右子节点总和
        int val = num * 10 + node.val;

        // 如果全部子节点同时为空就应该提前终止 不需要再往下
        if (node.left == null && node.right == null) {
            return val;
        }

        return dfsCalc(node.left, val) + dfsCalc(node.right, val);
    }

    // 解法三：广度优先搜索 使用覆盖子节点val的方式来传递总和值
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 定义队列 结果变量
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int sum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    sum += node.val;
                    continue;
                }
                if (node.left != null) {
                    // 覆盖左节点的val值
                    node.left.val = node.val * 10 + node.left.val;
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    node.right.val = node.val * 10 + node.right.val;
                    queue.offer(node.right);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        No59_129_SumNumbers obj = new No59_129_SumNumbers();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        int sum = obj.sumNumbers(root);
        System.out.println(sum);
    }


}
