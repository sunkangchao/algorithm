package leetcode.top100;

import leetcode.top100.base.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * No73_144_PreorderTraversal
 *
 *144. 二叉树的前序遍历
 *
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>06月 12, 2025</pre>
 */
public class No73_144_PreorderTraversal {


    // 递归法
    public List<Integer> preorderTraversal0(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans);
        return ans;
    }

    // 递归本质上就是分解成更小规模的子问题(调用自身)，那就需要完成每个节点的事情就可以了
    private void dfs(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }

        // 对于一个节点来说 由于是前序遍历 你需要先添加到集合中
        ans.add(node.val);
        // 接着完成左子树的前序遍历
        dfs(node.left, ans);
        // 再完成右子树的前序遍历
        dfs(node.right, ans);
    }


    // 迭代法
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        // 定义一个栈结构和结果数组
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<>(); // 别老是漏掉泛型符号

        // 抽取循环结构 当节点不为空以及栈结构不为空时继续
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                ans.add(root.val);
                stack.push(root);
                root = root.left;
            }

            TreeNode node = stack.pop();
            if (node.right != null) {
                root = node.right;
            }
        }

        // 返回结果
        return ans;
    }

}
