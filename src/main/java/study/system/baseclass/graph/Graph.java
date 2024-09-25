package study.system.baseclass.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Graph
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 22, 2024</pre>
 */
public class Graph {

    public final Map<Integer, Node> nodes = new HashMap<>();
    public final List<Edge> edges = new ArrayList<>();

}
