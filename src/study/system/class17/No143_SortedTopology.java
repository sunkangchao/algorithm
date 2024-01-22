package study.system.class17;

import study.system.baseclass.graph.Graph;
import study.system.baseclass.graph.Node;

import java.util.*;

/**
 *
 * 拓扑排序-基于入度
 * No143_SortedTopology
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 22, 2024</pre>
 */
public class No143_SortedTopology {

    public List<Node> sortedTopology(Graph graph) {
        // 这种计算拓扑排序-是基于你能得到每个节点的入度信息和相邻节点信息
        // 需要一种数据结构持续地记录in的变化 map最合适
        Map<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node polled = zeroInQueue.poll();
            result.add(polled);
            for (Node next : polled.nextList) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }

}
