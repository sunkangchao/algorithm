package examination;

import examination.basic.ListNode;

/**
 * 移除链表中倒数第N个元素
 *
 * @author SunKangChao
 * @date 2021/7/29 00:11
 */
public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // write code here
        if (head == null) {
            return head;
        }
        //1->2
        ListNode left = head;
        ListNode right = head;
        for (int i = 0; i < n; i++) {
            right = right.next;
        }
        //此时right可能为null 把头节点删除
        if (right == null) {
            head = head.next;
            return head;
        }
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        //此时left.next就是需要删除的node  right.next为null  target.next至少为right，所以不可能为null
        ListNode target = left.next;
        left.next = target.next;
        target.next = null;
        //left -target-----  right
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        RemoveNthFromEnd instance = new RemoveNthFromEnd();
        ListNode head = instance.removeNthFromEnd(node1, 2);
        while (head != null) {
            if (head.next == null) {
                System.out.print(head.val);
            } else {
                System.out.print(head.val + "->");
            }
            head = head.next;
        }
    }
}
