package leetcode.top100;

import leetcode.top100.base.ListNode;

/**
 * 2. 两数相加
 *
 * <p>
 *     给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * </p>
 *
 *
 *
 * @author sunkangchao
 * @since 2025/5/2 23:29
 */
public class No48_2_AddTwoNumbers {



    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbers(l1, l2, false);
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2, boolean flag) {
        if (l1 == null && l2 == null) {
            return flag ? new ListNode(1) : null;
        }
        int i = l1 == null ? 0 : l1.val;
        int j = l2 == null ? 0 : l2.val;

        int t = i + j + (flag ? 1 : 0);
        ListNode cur = new ListNode(t % 10);

        cur.next = addTwoNumbers(l1 == null ? null : l1.next, l2 == null ? null : l2.next, t >= 10);
        return cur;
    }


}
