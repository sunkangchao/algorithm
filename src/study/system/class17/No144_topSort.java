package study.system.class17;

import java.util.*;

/**
 * No144_topSort
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
            for (DirectedGraphNode next : polled.nextList) {
                inMap.put(next, inMap.get(next) + 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }


    private static class DirectedGraphNode {
        public int value;
        public List<DirectedGraphNode> nextList;
    }

}
