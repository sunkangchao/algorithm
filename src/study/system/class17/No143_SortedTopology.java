package study.system.class17;

import study.system.baseclass.graph.Graph;
import study.system.baseclass.graph.Node;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        No143_SortedTopology obj = new No143_SortedTopology();
        obj.transformAttrListToMap(list);

    }

    public Map<String, String> transformAttrListToMap(List<?> orderAttributes) {
        Map<String, String> attMap = new HashMap<>(16);

        Method curMethod;
        try {
            // 必须要指定具体参数类型是哪些，否则就会查找无参方法
            curMethod = No143_SortedTopology.class.getMethod("transformAttrListToMap", List.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        Type[] genericParameterTypes = curMethod.getGenericParameterTypes();
        if (genericParameterTypes[0] instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericParameterTypes[0];
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            String typeName = actualTypeArguments[0].getTypeName();
            System.out.println();

        }
        return attMap;

        // 总结
        // 通过发射获取的泛型的具体类型，只能是在编译阶段已经明文书写的类型，如果写的是?，返回的便是?；如果写的是T，返回的便是T。
        // 为什么 ？ 其实你想想也知道，它这些信息都是基于Class的，与当前对象无关，而当前类的类型参数实例化是处于类实例化阶段的，那么它Class能获取到的就只能说明文写在类或方法等签名上的明文信息。
    }

}
