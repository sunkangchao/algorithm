package study.system.baseclass.graph;

import java.util.List;

/**
 * Node
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 22, 2024</pre>
 */
public class Node {

    public int value;
    public int in;
    public int out;
    public List<Node> nextList;
    public List<Edge> edges;

    public Node(int value) {
        this.value = value;
    }


}
