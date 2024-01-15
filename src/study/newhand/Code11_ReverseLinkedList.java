package study.newhand;

import study.base.DoubleNode;
import study.base.Node;

/**
 * 反转链表
 *
 * @author sunkangchao
 * @date 2022/7/14 15:21
 */
public class Code11_ReverseLinkedList {


    public static Node reverseLinkedList(Node head) {
        Node prev = null;
        Node next = null;

        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode prev = null;
        DoubleNode next = null;

        while (head != null) {
            next = head.next;
            head.next = prev;
            head.last = next;
            prev = head;
            head = next;
        }
        return prev;

    }




}
