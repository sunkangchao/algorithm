package study.system.class17;

import java.util.*;

/**
 * No144_topSort
 * 1. 使用入度来求图的拓扑排序
 * 2. 使用点次来求图的拓扑排序
 * 3. 使用深度来求图的拓扑排序
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 22, 2024</pre>
 */
public class No144_topSort {


    public List<DirectedGraphNode> topSort(List<DirectedGraphNode> graph) {
        // 没有入度 但是可以遍历每个节点，记录它的入度 然后通过之前的解法来解决
        Map<DirectedGraphNode, Integer> inMap = new HashMap<>();
        for (DirectedGraphNode graphNode : graph) {
            // 处理自身
            if (!inMap.containsKey(graphNode)) {
                inMap.put(graphNode, 0);
            }
            for (DirectedGraphNode nextNode : graphNode.nextList) {
                if (inMap.containsKey(nextNode)) {
                    inMap.put(nextNode, inMap.get(nextNode) + 1);
                } else {
                    inMap.put(nextNode, 1);
                }
            }
        }
        Queue<DirectedGraphNode> zeroInQueue = new LinkedList<>();
        for (DirectedGraphNode node : inMap.keySet()) {
            if (inMap.get(node) == 0) {
                zeroInQueue.add(node);
            }
        }
        List<DirectedGraphNode> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            DirectedGraphNode polled = zeroInQueue.poll();
            result.add(polled);
            for (DirectedGraphNode next : polled.nextList) {
                inMap.put(next, inMap.get(next) + 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }

    /**
     * 通过点次方式来求解图的拓扑排序
     * @param graph
     * @return
     */
    public List<DirectedGraphNode> topSort2(List<DirectedGraphNode> graph) {

        Map<DirectedGraphNode, Integer> records = new HashMap<>();
        for (DirectedGraphNode directedGraphNode : graph) {
            f(directedGraphNode, records);
        }
        Queue<Record> queue = new PriorityQueue<>(Comparator.comparingInt(t -> t.nodes));
        for (DirectedGraphNode directedGraphNode : records.keySet()) {
            queue.add(new Record(directedGraphNode, records.get(directedGraphNode)));
        }
        List<DirectedGraphNode> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Record record = queue.poll();
            result.add(record.node);
        }
        return result;
    }

    private int f(DirectedGraphNode node, Map<DirectedGraphNode, Integer> records) {
        if (records.containsKey(node)) {
            return records.get(node);
        }
        int nodes = 1;
        for (DirectedGraphNode nextNode : node.nextList) {
            nodes += f(nextNode, records);
        }
        records.put(node, nodes);
        return nodes;
    }

    private static class Record {
        public DirectedGraphNode node;
        public int nodes;

        public Record(DirectedGraphNode node, int nodes) {
            this.node = node;
            this.nodes = nodes;
        }
    }

    private static class DirectedGraphNode {
        public int value;
        public List<DirectedGraphNode> nextList;
    }

    public List<DirectedGraphNode> topSort3(List<DirectedGraphNode> graph) {
        // 记忆化递归
        Map<DirectedGraphNode, Integer> depthMap = new HashMap<>();
        Queue<NodeDepth> queue = new PriorityQueue<>(Comparator.comparingInt(t -> t.depth));
        for (DirectedGraphNode directedGraphNode : graph) {
            queue.add(new NodeDepth(directedGraphNode, depthMap.get(directedGraphNode)));
        }
        List<DirectedGraphNode> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll().nodes);
        }
        return result;
    }

    private int f2(DirectedGraphNode node, Map<DirectedGraphNode, Integer> depthMap) {
        if (depthMap.containsKey(node)) {
            return depthMap.get(node);
        }
        int depth = 0;
        for (DirectedGraphNode nextNode : node.nextList) {
            depth = Math.max(depth, f2(nextNode, depthMap));
        }
        depthMap.put(node, depth++);
        return depth;
    }

    private static class NodeDepth {
        public DirectedGraphNode nodes;
        public int depth;

        public NodeDepth(DirectedGraphNode nodes, int depth) {
            this.nodes = nodes;
            this.depth = depth;
        }
    }


}
