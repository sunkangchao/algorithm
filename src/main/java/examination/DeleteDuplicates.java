package examination;

import examination.basic.ListNode;

/**
 * 删除链表中冗余的元素（冗余元素全部删除）
 * 要求空间复杂度为1
 *
 * @author SunKangChao
 * @date 2021/7/27 20:32
 */
public class DeleteDuplicates {


    //想好思路再写，不然就是极度浪费时间
    //1→2→3→3→3→4→4→5
    public ListNode deleteDuplicates(ListNode head) {
        // write code here

        if (head == null || head.next == null) {
            return head;
        }

        ListNode node = new ListNode(-1);
        node.next = head;

        ListNode p = node;
        ListNode q = head;

        while (q != null && q.next != null) {
            if (q.val != q.next.val){
                p = q;
                q = q.next;
            }else {
                while (q.next != null && q.val == q.next.val){
                    q = q.next;
                }
                q = q.next;
                p.next = q;
            }
        }

        return node.next;
    }

    public static void main(String[] args) {
        DeleteDuplicates instance = new DeleteDuplicates();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        ListNode node7 = new ListNode(4);
        ListNode node8 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;

        ListNode head = instance.deleteDuplicates(node1);
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
