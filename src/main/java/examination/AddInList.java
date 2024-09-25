package examination;

import examination.basic.ListNode;

import java.util.Stack;

/**
 * 两个链表相加
 *
 * @author SunKangChao
 * @date 2021/7/15 15:42
 */
public class AddInList {

    public ListNode addInList(ListNode head1, ListNode head2) {
        // write code here
        // 9->3->7
        // 6->3
        // 1->0->0->0
        head1 = reverseLinkedList(head1);
        head2 = reverseLinkedList(head2);

        //刚声明出来的变量和null有什么区别   刚声明出来没有初始化  赋值为null已经初始化 不一样的
        //必须初始化才能进行操作（调用或作参数）
        ListNode nextHead1;
        ListNode nextHead2;

        //进位标志
        int flag = 0;

        ListNode headNode = null;
        ListNode nextNode = null;

        while (head1 != null || head2 != null || flag == 1) {

            if (head1 != null) {
                nextHead1 = head1;
                head1 = head1.next;
            } else {
                nextHead1 = new ListNode(0);
                head1 = null;
            }

            if (head2 != null) {
                nextHead2 = head2;
                head2 = head2.next;
            } else {
                nextHead2 = new ListNode(0);
                head2 = null;
            }

            int currentSum = nextHead1.val + nextHead2.val + flag;

            if (currentSum >= 10) {
                flag = 1;
                currentSum = currentSum % 10;
            } else {
                //重置flag操作
                flag = 0;
            }

            if (headNode == null) {
                headNode = new ListNode(currentSum);
                nextNode = headNode;
            } else {
                nextNode.next = new ListNode(currentSum);
                nextNode = nextNode.next;
            }

        }

        return reverseLinkedList(headNode);

    }

    //反转链表
    public ListNode reverseLinkedList(ListNode head) {

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
            node.next = null;
            root.next = node;
            root = node;

        }
        return rsHead;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(9);
        head1.next = new ListNode(3);
        head1.next.next = new ListNode(7);

        ListNode head2 = new ListNode(6);
        head2.next = new ListNode(3);

        AddInList instance = new AddInList();
        ListNode rsNode = instance.addInList(head1, head2);

        while (rsNode != null) {
            if (rsNode.next == null) {
                System.out.print(rsNode.val);
            } else {
                System.out.print(rsNode.val + "->");
            }
            rsNode = rsNode.next;
        }
    }
}
