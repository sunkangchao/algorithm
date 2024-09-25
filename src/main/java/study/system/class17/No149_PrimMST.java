package study.system.class17;

import study.system.baseclass.graph.Edge;
import study.system.baseclass.graph.Graph;
import study.system.baseclass.graph.Node;

import java.util.*;

/**
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 23, 2024</pre>
 */
public class No149_PrimMST {



    public Set<Edge> primMST(Graph graph) {

        Queue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(t -> t.weight));
        Set<Node> visited = new HashSet<>();
        Set<Edge> result = new HashSet<>();

        for (Node node : graph.nodes.values()) {
            visited.add(node);
            for (Edge edge : node.edges) {
                queue.add(edge);
            }
            // 循环应该是在这里
            // 循环的条件是edge队列不为空
            // 循环的逻辑是取出来判断是否形成环 不形成环就作为结果 并且解锁下面的边到队列 形成环就直接下一条边
            while (!queue.isEmpty()) {
                Edge edge = queue.poll();
                if (!visited.contains(edge.to)) {
                    result.add(edge);
                    visited.add(edge.to);
                    for (Edge childEdge : edge.to.edges) {
                        queue.add(childEdge);
                    }
                }
            }
        }
        return result;
    }




}
