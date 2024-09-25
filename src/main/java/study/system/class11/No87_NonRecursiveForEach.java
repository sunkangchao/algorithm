package study.system.class11;

import study.base.TreeNode;
import java.util.Stack;

/**
 *
 * 非递归方式实现前序、中序、后序遍历
 * No87_NonRecursiveForEach
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 16, 2024</pre>
 */
public class No87_NonRecursiveForEach {

    public void pre(TreeNode node) {
        if (node == null) {
            return;
        }
        // 跟广度优先遍历的地方不同在于：这里用的是栈结构，而广度优先遍历用的是队列
        Stack<TreeNode> stack = new Stack<>();
        stack.add(node);

        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            System.out.println(treeNode.val + " ");
            if (treeNode.right != null) {
                stack.add(treeNode.right);
            }
            if (treeNode.left != null) {
                stack.add(treeNode.left);
            }
        }
    }

    public void in(TreeNode node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            TreeNode curNode = stack.pop();
            System.out.println(curNode.val + " ");
            node = curNode.right;
        }
    }

    public void in2(TreeNode node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode curNode = stack.pop();
                System.out.println(curNode.val + " ");
                node = curNode.right;
            }
        }
    }

    /**
     * 前序遍历(根-左-右) -》 根-右-左 -》按顺序添加到另一个栈当中 即为左->右->根 也就是后序遍历
     * @param node
     */
    public void after(TreeNode node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            TreeNode curNode = stack.pop();
            stack2.push(curNode);
            if (curNode.left != null) {
                stack.push(curNode.left);
            }
            if (curNode.right != null) {
                stack.push(curNode.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().val + " ");
        }
    }


}
