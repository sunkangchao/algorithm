package study.system.class17;

import study.system.baseclass.graph.Edge;
import study.system.baseclass.graph.Graph;
import study.system.baseclass.graph.Node;

/**
 * No140_Converter
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 22, 2024</pre>
 */
public class No140_Converter {



    public Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (Integer[] integers : matrix) {
            int weight = integers[0];
            int from = integers[1];
            int to = integers[2];

            // 你拿到的是一个int值，怎么转换为一个node值，并且能够固定的转换
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(weight, fromNode, toNode);
            // in, out, nextList, edges
            toNode.in++;
            fromNode.out++;
            fromNode.nextList.add(toNode);
            fromNode.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }
}
