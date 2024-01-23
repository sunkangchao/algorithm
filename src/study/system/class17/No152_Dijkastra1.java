package study.system.class17;

import study.system.baseclass.graph.Edge;
import study.system.baseclass.graph.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * 迪瑞卡斯拉算法
 * 求给定节点，到每个可达节点的最小值
 * No152_Dijkastra1
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 23, 2024</pre>
 */
public class No152_Dijkastra1 {


    public Map<Node, Integer> dijkastra(Node from) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        Set<Node> selectedNode = new HashSet<>();

        distanceMap.put(from, 0);
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNode);

        while (minNode != null) {
            Integer distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            selectedNode.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNode);
        }
        return distanceMap;
    }

    private Node getMinDistanceAndUnselectedNode(Map<Node, Integer> distanceMap, Set<Node> selectedNode) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;

        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            Integer distance = entry.getValue();
            if (!selectedNode.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }


}
