package study;


/**
 * 单向链表实现栈
 *
 * @author sunkangchao
 * @date 2022/7/14 16:08
 */
public class Code12_LinkedListToStack {

    public static class Node<V> {
        public V val;
        public Node<V> next;

        public Node(V val) {
            this.val = val;
            next = null;
        }
    }

    public static class MyQueue<V> {

        private Node<V> head;

        private Node<V> tail;

        private int size;

        public int size() {
            return this.size;
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        public void push(V val) {
            Node<V> node = new Node<>(val);
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
            size++;
        }

        public V poll() {
            V ans = null;
            if (head != null) {
                ans = head.val;
                head = head.next;
                size--;
            }
            // 帮助最后一个节点被JVM回收
            if (head == null) {
                tail = null;
            }
            return ans;
        }

        public V peek() {
            V ans = null;
            if (head != null) {
                ans = head.val;
            }
            return ans;
        }

    }


    public static class MyStack<V> {

        private Node<V> head;

        private int size;

        // 栈提供size(), push(), pop()三个方法

        public int size() {
            return this.size;
        }

        public void push(V val) {
            Node<V> node = new Node<>(val);

            if (head == null) {
                head = node;
                size++;
                return;
            }
            node.next = head;
            head = node;
            size++;

        }

        public V pop() {
            V ans = null;
            if (head != null) {
                ans = head.val;
                head = head.next;
                size--;
            }
            return ans;
        }

        public V peek() {

            return head == null ? null : head.val;
        }

    }


}
