package leetcode.top100;

import java.util.HashMap;
import java.util.Map;

/**
 * LRUCache
 *
 * @author sunkangchao
 * @since 2025/3/4 23:51
 */
public class No2_146_LRUCache {


    // 从题意上无法完全找出LRU的约束关系，需要你记住的，总结如下
    // get请求，如果不存在返回-1，如果存在返回具体值，且把数据移动到链表表头
    // put请求，（不管存在和不存在都放到表头，算是最新使用的），如果满了就把尾部删除。
    // 理解清楚这个概念，LRU就不难使用，要求O(1)时间复杂度，就可以使用hash表和双向链表来实现。
    // 注意hash表的get(key)方法，get方法的入参是key，而不是下标，这很关键。

    // 1. 双向链表需要存储key和value，不仅仅是value
    // 2. 链表表头和表尾采用伪节点，避免判空，也不用再去set表头和表尾 这是至关重要的 否则调试能搞得你怀疑人生

    public No2_146_LRUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
    }

    /**
     * 双向链表头节点
     */
    private DLinkedNode head;
    /**
     * 双向链表尾部节点
     */
    private DLinkedNode tail;
    /**
     * hash表
     */
    private final Map<Integer, DLinkedNode> map = new HashMap<>();
    /**
     * 固定容量
     */
    private final int capacity;
    /**
     * 当前长度
     */
    private int size;


    public int get(int key) {
        DLinkedNode resultNode;
        if (map.containsKey(key)) {
            resultNode = map.get(key);
            // 节点移动到表头
            moveToHead(resultNode, head);
        } else {
            // 不存在返回-1
            return -1;
        }
        return resultNode.value;
    }

    private void moveToHead(DLinkedNode resultNode, DLinkedNode head) {
        // 已经假设是存在的，head不能为空 断开左右链接
        DLinkedNode left = resultNode.left;
        DLinkedNode right = resultNode.right;
        // left和right都可能为null 需要对这两种情况做处理
        if (left == null) {
            // 说明已经是表头，无需处理
        } else if (right == null) {
            // 说明此时在链尾 无需把left链接到right上面 只需要把node挂在left左边
            resultNode.left = null;
            resultNode.right = head;
            head.left = resultNode;
            // 重置尾部指针
            tail = left;
            left.right = null;
            // 重置head指针
            this.head = resultNode;
        } else {
            // 需要同时处理两种情况
            resultNode.left = null;
            resultNode.right = head;
            head.left = resultNode;
            left.right = right;
            right.left = left;
            this.head = resultNode;
        }

    }

    public void put(int key, int value) {
        // 先判断节点是否存在
        DLinkedNode node = new DLinkedNode(key, value);
        if (map.containsKey(key)) {
            // 存在节点 则覆盖它的值 并且移动到链头
            DLinkedNode oldNode = map.get(key);

            map.put(key, node);
            // 移动前先把旧node替换成新node
            swapNode(oldNode, node);
            moveToHead(node, head);
        } else {
            map.put(key, node);
            size++;
            // 节点不存在 则放至map中，并添加到表头 需要判断表头是否存在
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.right = head;
                head.left = node;
                // 并且重置head
                head = node;
            }
            // 然后判断是否超过size，超过size
            if (size > capacity) {
                // map和size都需要处理
                map.remove(tail.key);
                size--;
                // 移除尾部节点
                removeTail(node, tail);
            }
        }
    }

    private void swapNode(DLinkedNode oldNode, DLinkedNode node) {
        node.left = oldNode.left;
        node.right = oldNode.right;
        oldNode.left = null;
        oldNode.right = null;
    }

    private void removeTail(DLinkedNode node, DLinkedNode tail) {
        // 判断尾部是否为空 已经添加过元素 至少会有一个元素 不可能为空
        if (node == tail) {
            // 说明只有一个元素 之前是没有元素的 因为新加的元素都在表头 此时无需改动
        } else {
            // 移除尾部元素
            DLinkedNode left = tail.left;
            left.right = null;
            // 重置tail指针
            this.tail = left;
        }
    }


    public static class DLinkedNode {

        public DLinkedNode left; // 左节点
        public DLinkedNode right; // 右节点
        public int key; // 后面在map删除key的时候需要用到 因为删除最后一个元素时 是通过链表查找到node的 此时需要知道key是什么才能删除map中的链尾这个元素
        public int value;

        DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        No2_146_LRUCache obj = new No2_146_LRUCache(2);
        obj.put(2, 1);
        obj.put(1, 1);
        obj.put(2, 3);
        obj.put(4, 1);
        System.out.println(obj.get(1));
        System.out.println(obj.get(2)); // 2,3 1,1 , // 4,1 | 2,3

    }



}
