package leetcode.top100;

import leetcode.top100.base.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 94. 二叉树的中序遍历
 *
 *
 * No28_94_InorderTraversal
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 14, 2025</pre>
 */
public class No28_94_InorderTraversal {

    // dfs
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    private void dfs(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        dfs(root.left, result);
        result.add(root.val);
        dfs(root.right, result);
    }

    // 不改变返回值dfs
    public List<Integer> inorderTraversal2(TreeNode root) {
        return inorderTraversal(root, new ArrayList<>());
    }

    private List<Integer> inorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return result;
        }
        List<Integer> leftReturn = inorderTraversal(root.left, result);
        leftReturn.add(root.val);
        List<Integer> rightReturn = inorderTraversal(root.right, leftReturn);
        return rightReturn;
    }


    // 栈实现
    // 就是就是模拟递归栈的过程。把每个节点的左边节点全部压入栈中，到底后弹出
    // 添加节点到结果集中，再遍历它的右节点，重复这个过程。那么对于每个节点，它的右节点都会被遍历到。
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        // 记住这个条件
        while (!stack.isEmpty() || root != null) {
            // 先判断自己是否为空 而不是去判断root.left
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            TreeNode popped = stack.pop();
            result.add(popped.val);
            root = popped.right;
        }

        return result;

    }


}
