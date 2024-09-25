package study.system.class17;

import study.system.baseclass.graph.Node;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * 图的深度优先遍历-递归实现
 * No142_GraphDFS
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 22, 2024</pre>
 */
public class No142_GraphDFS {



    public void bfs(Node root) {
        if (root == null) {
            return;
        }
        HashSet<Node> visits = new HashSet<>();
        visits.add(root);
        bfs(root, visits);
    }

    public void bfs(Node root, Set<Node> visits) {
        // 这种递归就不需要空来作为出口了 而是以集合中的元素为出口 集合中的元素就是有限的
        System.out.println(root.value);
        for (Node node : root.nextList) {
            // 假设不为空 防止重复遍历
            if (!visits.contains(node)) {
                visits.add(node);
                bfs(node, visits);
            }
        }
    }

    public void bfs2(Node root) {
        if (root == null) {
            return;
        }
        Set<Node> touched = new HashSet<>();
        touched.add(root);
        bfs(root, touched);
    }

    private void bfs2(Node root, Set<Node> touched) {
        System.out.println(root.value);
        for (Node nextNode : root.nextList) {
            if (!touched.contains(nextNode)) {
                touched.add(nextNode);
                bfs(nextNode, touched);
            }
        }
    }

}
