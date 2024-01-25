package study.system.class17;

import study.system.baseclass.graph.Edge;
import study.system.baseclass.graph.Graph;
import study.system.baseclass.graph.Node;

import java.util.*;

/**
 * 最小生成树
 * No148_KruskalMST
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 23, 2024</pre>
 */
public class No148_KruskalMST {


    public Set<Edge> kruskalMST(Graph graph) {
        ArrayList<Node> nodes = new ArrayList<>(graph.nodes.values());
        UnionSet unionSet = new UnionSet(nodes);
        Queue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(t -> t.weight));
        for (Edge edge : graph.edges) {
            queue.add(edge);
        }
        Set<Edge> result = new HashSet<>();
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            if (!unionSet.isSameSet(edge.from, edge.to)) {
                result.add(edge);
                unionSet.union(edge.from, edge.to);
            }
        }
        return result;
    }

    private static class UnionSet {

        private Map<Node, Node> parents;
        private Map<Node, Integer> sizeMap;
        private int sets;

        public UnionSet(List<Node> nodes) {
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (Node node : nodes) {
                parents.put(node, node);
                sizeMap.put(node, 1);
                sets++;
            }
        }

        public boolean isSameSet(Node node1, Node node2) {
            return findParent(node1) == findParent(node2);
        }

        public Node findParent(Node node) {
            if (node != parents.get(node)) {
                parents.put(node, parents.get(parents.get(node)));
            }
            return node;
        }

        public void union(Node node1, Node node2) {
            Node parent1 = findParent(node1);
            Node parent2 = findParent(node2);
            if (parent1 != parent2) {
                Integer size1 = sizeMap.get(parent1);
                Integer size2 = sizeMap.get(parent2);
                Node head = size1 > size2 ? parent1 : parent2;
                Node noHead = head == parent1 ? parent2 : parent1;
                parents.put(noHead, head);
                sizeMap.put(head, size1 + size2);
                sets--;
            }
        }

    }


}
