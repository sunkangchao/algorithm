package study.system.baseclass.graph;

/**
 * Edge
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 22, 2024</pre>
 */
public class Edge {

    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }


}
