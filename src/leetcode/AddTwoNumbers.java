package leetcode;

import examination.basic.ListNode;

/**
 * 两数之和
 *
 * @author SunKangChao
 * @date 2022/3/29 00:22
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return null;
    }

    /**
     * 翻转链表
     * @param listNode 原链表
     * @return 翻转后的链表
     */
    private ListNode reverseList(ListNode listNode) {
        ListNode pre = null;
        ListNode cur = listNode;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
