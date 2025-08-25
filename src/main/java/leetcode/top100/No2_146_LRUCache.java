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
    // 3. 覆盖值 复用原来的节点 只更改value值 因为创建新节点 导致节点的替换 出现了问题 卡在17/23用例过不去 耗费了2个小时时间

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
            moveToHead(resultNode);
        } else {
            // 不存在返回-1
            return -1;
        }
        return resultNode.value;
    }

    private void moveToHead(DLinkedNode resultNode) {
        // 先想想再写 其实很多判断没有必要 只要不会发生空指针就暂不判断
        if (resultNode == head) {
            // 表头无需处理
            return;
        } else if (resultNode == tail) {
            // 处于尾部 最好通过直接指针来判断 尾部就无需要处理右侧链接的问题
            DLinkedNode tailLeft = tail.left;
            tailLeft.right = null;
            tail.left = null;
            tail = tailLeft;
        } else {
            // 处于中间
            resultNode.left.right = resultNode.right;
            resultNode.right.left = resultNode.left;
        }
        // 移动到表头 其实moveToHead就分两步 一步是移除当前元素 另一步是添加到表头
        resultNode.left = null;
        resultNode.right = head;
        head.left = resultNode;
        head = resultNode;
    }

    public void put(int key, int value) {
        // 先判断节点是否存在
        if (map.containsKey(key)) {
            // 存在节点 则覆盖它的值 并且移动到链头
            DLinkedNode node = map.get(key);
            node.value = value; // 复用原来的DLinkedNode节点 只覆盖值
            moveToHead(node);
        } else {
            DLinkedNode node = new DLinkedNode(key, value);
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
                removeTail();
            }
        }
    }

    private void removeTail() {
        // 直接移除尾部即可 前面已经判断过的了
        DLinkedNode left = tail.left;
        left.right = null;
        tail.left = null;
        tail = left;
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


    // 2025.8.24 23:54
    static class LRUCache {

        private Map<Integer, Node> map;
        private Node head;
        private Node tail;
        private int capacity;
        private int size;

        public LRUCache(int capacity) {
            this.map = new HashMap<>();
            this.head = new Node(-1, -1);
            this.tail = new Node(-1, -1);
            head.next = tail;
            tail.pre = head;
            this.capacity = capacity; // 这里刚写错 没有加this 导致没有赋值成功 第一次get失败
            this.size = 0;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                moveToHead(node);
                return node.val;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.val = value;
                moveToHead(node);
            } else { // head -> 2 -> 1 -> tail
                Node node = new Node(key, value);
                // 添加至头节点
                Node next = head.next;
                head.next = node;
                node.pre = head;
                node.next = next;
                next.pre = node;
                map.put(key, node);
                size++;
                if (size > capacity) {
                    removeTail();
                }
            }
        }

        private void moveToHead(Node node) {
            // 移除当前节点
            Node pre = node.pre;
            Node next = node.next;
            pre.next = next;
            next.pre = pre;

            // 移动至头节点
            Node headNext = head.next;
            head.next = node;
            node.pre = head;
            node.next = headNext;
            headNext.pre = node;
        }

        private void removeTail() {
            // 移除链表的尾节点
            Node tailPre = tail.pre;
            tailPre.pre.next = tail;
            tail.pre = tailPre.pre;
            // 清空当前指针
            tailPre.next = null;
            tailPre.pre = null;
            // 移除map中的节点
            map.remove(tailPre.key);
            size--; // 这里刚写错 没有维护
        }


        static class Node {
            Node pre;
            Node next;
            int key;
            int val;

            Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
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
