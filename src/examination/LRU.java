package examination;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SunKangChao
 * @date 2021/7/11 22:21
 */
public class LRU {

    private final Map<Integer, Node> map = new HashMap<>();
    private Node head;
    private Node tail;
    private int k;

    public int[] LRU(int[][] operators, int k) {

        int len = (int) Arrays.stream(operators).filter(x -> x[0] == 2).count();
        int[] rs = new int[len];
        this.k = k;

        for (int i = 0, j = 0; i < operators.length; i++) {
            if (operators[i][0] == 1) {
                set(operators[i][1], operators[i][2]);
            } else {
                //get操作
                int result = get(operators[i][1]);
                rs[j++] = result;
            }
        }

        return rs;
    }

    public void set(int key, int value) {
        if (map.size() == k) {
            remove();
        }
        Node node = new Node(key, value);
        //操作链表(放至表头)
        if (head == null) {
            head = node;
            tail = node;
        } else {
            Node oldHead = head;
            node.next = oldHead;
            oldHead.prev = node;
            head = node;
        }
        //操作map
        map.put(key, node);

    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            int result =  node.val;
            //移动到头部
            moveToHead(node);
            return result;
        }
        return -1;
    }

    //淘汰方法
    public void remove() {
        Node oldTailPrev = tail.prev;
        tail.prev.next = null;
        tail.prev = null;
        //需要把最后的key从map中移除
        map.remove(tail.key);
        tail = oldTailPrev;
    }

    public void moveToHead(Node node){
        if(node == head){
            return;
        }else if(node == tail){
            Node preNode = node.prev;
            preNode.next = null;
            node.next = null;
            tail = node.prev;
        }else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        Node oldHead = head;
        node.next = oldHead;
        node.prev = null;
        oldHead.prev = node;
        head = node;
    }

    static class Node {
        int key, val;
        Node prev, next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

    }

    public static void main(String[] args) {
        LRU instance = new LRU();
        int[] rs = instance.LRU(new int[][]{{1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}},3);
        for (int r : rs) {
            System.out.print(r+" ");
        }
    }

}
