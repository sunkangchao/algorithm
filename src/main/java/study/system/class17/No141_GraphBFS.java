package study.system.class17;

import study.system.baseclass.graph.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 图的广度优先遍历
 * No141_GraphBFS
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 22, 2024</pre>
 */
public class No141_GraphBFS {


    public void bfs(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Set<Node> visits = new HashSet<>();
        visits.add(root);

        // 取-存 循环  所以必须要先存 才能让这个循环跑起来
        while (!queue.isEmpty()) {
            Node polled = queue.poll();
            // 处理
            System.out.println("polled");
//            // 存到已访问 不能再这里存 否则队列就可能出现重复的元素
//            visits.add(polled);
            for (Node node : polled.nextList) {
                if (node != null && !visits.contains(node)) {
                    queue.add(node);
                    // 应该是这里就存起来 防止队列中出现重复元素
                    visits.add(node);
                }
            }
        }
    }

    public void bfs2(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList();
        Set<Node> visited = new HashSet<>();
        queue.add(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Node nextNode : node.nextList) {
                if (!visited.contains(nextNode)) {
                    queue.add(nextNode);
                    visited.add(nextNode);
                }
            }
        }
    }



}
