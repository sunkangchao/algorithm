package leetcode.top100;

import baseclass.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author sunkangchao
 * @since 2025/3/7 01:17
 */
public class No5_103_ZigzagLevelOrder {



    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        // 先进后出 使用栈结构应该就是这道题最好的解法
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        ArrayList<List<Integer>> result = new ArrayList<>();
        zigzagLevelOrder(stack, result, true);
        return result;
    }

    private void zigzagLevelOrder(Stack<TreeNode> stack, List<List<Integer>> result, boolean odd) {
        List<Integer> list = new ArrayList<>();
        int size = stack.size();
        Stack<TreeNode> childStack = new Stack<>();

        while (size > 0) {
            TreeNode treeNode = stack.pop();
            list.add(treeNode.val);
            if (odd) {
                if (treeNode.left != null) {
                    childStack.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    childStack.add(treeNode.right);
                }
            } else {
                if (treeNode.right != null) {
                    childStack.add(treeNode.right);
                }
                if (treeNode.left != null) {
                    childStack.add(treeNode.left);
                }
            }
            size--;
        }
        result.add(list);
        if (!childStack.isEmpty()) {
            zigzagLevelOrder(childStack, result, (odd ? false : true));
        }
    }



}
