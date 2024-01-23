package study.system.class17;

import study.system.baseclass.graph.Node;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 *
 * 递归方式实现-图的深度优先遍历
 * No143_GraphDFS2
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 22, 2024</pre>
 */
public class No143_GraphDFS2 {


    public void dfs(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.push(root);
        set.add(root);
        System.out.println(root.value);

        while (!stack.isEmpty()) {
            Node popped = stack.pop();
//            // 打印不能放这里，否则会重复
//            System.out.println(popped.value);
            for (Node node : popped.nextList) {
                if (!set.contains(node)) {
                    set.add(node);
                    // 精髓的一步
                    stack.push(popped);
                    stack.push(node);
                    System.out.println(node.value);
                }
            }
        }
    }

    public void dfs2(Node root) {
        if (root == null) {
            return;
        }
        Set<Node> touched = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        touched.add(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            for (Node nextNode : node.nextList) {
                if (!touched.contains(nextNode)) {
                    stack.push(node);
                    stack.push(nextNode);
                    System.out.println(node.value);
                    touched.add(nextNode);
                }
            }
        }
    }

}
