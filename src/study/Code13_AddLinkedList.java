package study;


import java.util.Arrays;

/**
 * 链表相加
 *
 * @author sunkangchao
 * @date 2022/7/17 21:44
 */
public class Code13_AddLinkedList {


    public static class Node<T> {
        public T value;
        public Node<T> next;

        public Node(T value) {
            this.value = value;
            next = null;
        }
    }

    public static class IntNode {
        public int value;
        public IntNode next;

        public IntNode(int value) {
            this.value = value;
        }
    }

    public static <T> Node<T> reverseLinkedList(Node<T> head) {
        // A -> B -> C -> D -> E
        if (head == null) {
            return null;
        }
        if (head.next != null) {
            Node<T> targetHead = reverseLinkedList(head.next);
            // 做一步操作
            head.next.next = head;
            head.next = null;
            return targetHead;
        }
        return head;
    }

    public static Node<Integer> addLinkedList(Node<Integer> head1, Node<Integer> head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        // 先反转链表
        Node<Integer> h1 = reverseLinkedList(head1);
        Node<Integer> h2 = reverseLinkedList(head2);
        // 找出最长链表, 保证h1是长链表，h2是短链表
        Node<Integer> h = findLongestLinkedList(head1, head2);
        if (h != h1) {
            Node<Integer> temp = h1;
            h1 = h2;
            h2 = temp;
        }
        // 链表相加
        int carry = 0;
        int result;
        Node<Integer> rh = null;
        Node<Integer> head = null;
        while (h2 != null) {
            int temp = h1.value + h2.value + carry;
            result = temp % 10;
            carry = temp / 10;
            Node<Integer> tn = new Node<>(result);
            if (head == null) {
                rh = tn;
                head = tn;
            } else {
                head.next = tn;
                head = head.next;
            }
            h1 = h1.next;
            h2 = h2.next;
        }
        while (h1 != null) {
            int temp = h1.value + carry;
            result = temp % 10;
            carry = temp / 10;
            Node<Integer> tn = new Node<>(result);
            head.next = tn;
            head = head.next;
            h1 = h1.next;
        }
        if (carry != 0) {
            head.next = new Node<>(1);
        }
        return rh;
    }

    public static <T>Node<T> findLongestLinkedList(Node<T> head1, Node<T> head2) {
        int len1 = 0;
        int len2 = 0;
        Node<T> h1 = head1;
        Node<T> h2 = head2;
        while (head1 != null) {
            len1++;
            head1 = head1.next;
        }
        while (head2 != null) {
            len2++;
            head2 = head2.next;
        }
        return len1 >= len2 ? h1 : h2;
    }

    public static void main(String[] args) {
        Node<Integer> head = new Node<>(1);
        Node<Integer> head2 = new Node<>(2);
        Node<Integer> head3 = new Node<>(3);
        Node<Integer> head4 = new Node<>(4);
        Node<Integer> head5 = new Node<>(5);

        head.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;

        Node<Integer> targetHead = reverseLinkedList(head);
        while (targetHead != null) {
            System.out.print(targetHead.value + " ");
            targetHead = targetHead.next;
        }
        Arrays.sort();

    }




}
