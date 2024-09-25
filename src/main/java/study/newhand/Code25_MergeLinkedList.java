package study.newhand;

import examination.basic.ListNode;

/**
 * 链表合并
 *
 * @author sunkangchao
 * @date 2022/7/22 14:15
 */
public class Code25_MergeLinkedList {


    public static ListNode merge(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        ListNode p1 = head1;
        ListNode p2 = head2;
        ListNode root;
        // 处理头节点
        if (head1.val >= head2.val) {
            root = head2;
            p2 = p2.next;
        } else {
            root = head1;
            p1 = p1.next;
        }
        ListNode curNode = root;
        // 处理公共部分
        if (p1 != null && p2 != null) {
            if (p1.val >= p2.val) {
                curNode.next = p2;
                p2 = p2.next;
            } else {
                curNode.next = p1;
                p1 = p1.next;
            }
            curNode = curNode.next;
        }
        // 处理多余部分
        if (p1 != null) {
            curNode.next = p1;
        }
        if (p2 != null) {
            curNode.next = p2;
        }
        // 返回头结点
        return root;
    }


}
