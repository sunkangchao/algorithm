package examination;

import examination.basic.ListNode;

import java.util.Stack;

/**
 * 反转链表
 *
 * @author SunKangChao
 * @date 2021/7/12 00:57
 */
public class ReverseLinked {

    public static ListNode reverseList(ListNode head) {

        //循环方式解决链表反转  栈方式
        if (head == null || head.next == null) {
            return head;
        }
        Stack<ListNode> nodeStack = new Stack<>();
        //涉及到重复判断时用do-while循环结构
        do {
            nodeStack.push(head);
            head = head.next;

        } while (head != null);

        ListNode rsHead = nodeStack.pop();
        ListNode root = rsHead;
        while (!nodeStack.isEmpty()) {
            ListNode node = nodeStack.pop();
            root.next = node;
            root = node;
            node.next = null;

        }
        return rsHead;
    }

    private static ListNode rsNode;

    //递归方式实现链表反转
    public static ListNode recursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode nextNode = head.next;
        if (nextNode.next == null) {
            rsNode = head.next;
        }
        recursion(head.next);
        nextNode.next = head;
        head.next = null;
        return rsNode;
    }


//        if (head.next.next != null) {
//            //进入递归
//            headNode = ReverseList(head.next);
//            ListNode nextNode = head.next;
//            nextNode.next = head;
//            head.next = null;
//            return headNode;
//        }
//        // 单向链表处理两头 双向链表处理四头
//        ListNode tail = head.next;
//        tail.next = head;
//        head.next = null;
//
//        return tail;


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode rsNode = recursion(head);

        while (rsNode != null) {
            if (rsNode.next == null) {
                System.out.print(rsNode.val);
            } else {
                System.out.print(rsNode.val + " ");
            }
            rsNode = rsNode.next;
        }

    }
    //计算机从来不会说谎，至少它对你是绝对的忠诚
}
