package leetcode.top100;

import leetcode.top100.base.ListNode;

/**
 * No10_21_MergeTwoLists
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>03月 12, 2025</pre>
 */
public class No10_21_MergeTwoLists {

    /**
     * 递归法解决 贼快
     * 终止条件：当两个链表都为空时，表示我们对链表已合并完成。
     * 如何递归：我们判断 l1 和 l2 头结点哪个更小，然后较小结点的 next 指针指向其余结点的合并结果。（调用递归）
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val > list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }

        ListNode head;
        if (list1.val > list2.val) {
            head = list2;
            list2 = list2.next;
        } else {
            head = list1;
            list1 = list1.next;
        }
        ListNode cur = head;


        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
            } else {
                cur.next = list1;
                cur = cur.next;
                list1 = list1.next;
            }
        }

        if (list1 == null) {
            cur.next = list2;
        } else {
            cur.next = list1;
        }

        return head;
    }




}
