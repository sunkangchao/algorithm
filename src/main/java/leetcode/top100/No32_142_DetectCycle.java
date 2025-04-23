package leetcode.top100;

import leetcode.top100.base.ListNode;

/**
 * 142. 环形链表 II
 *
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * @author sunkangchao
 * @since 2025/4/20 21:48
 */
public class No32_142_DetectCycle {



    public ListNode detectCycle(ListNode head) {

        // 头尾节点开始处于同一个位置 待深思 明白了 快慢指针都是开始指向同一个节点 只有这样 快指针走完时慢指针才会走一半
        // 假如刚开始时快指针就比慢指针多一个节点 那么快指针走完时 慢指针指向的并不是中点 而是中点的前一个节点
        // 所以 快慢指针的题目 快慢指针需要指向同一个节点
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }

        if (fast == null || fast.next == null) {
            // 此时链表无环
            return null;
        }

        // 此时两节点第一次相遇
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }

        return fast;

    }


}
