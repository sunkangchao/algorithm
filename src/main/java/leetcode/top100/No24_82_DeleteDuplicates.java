package leetcode.top100;

import leetcode.top100.base.ListNode;

/**
 * 82. 删除排序链表中的重复元素 II
 *
 * @author sunkangchao
 * @since 2025/4/8 23:17
 */
public class No24_82_DeleteDuplicates {

    public ListNode deleteDuplicates(ListNode head) {
        // 还是同样的思路，建立傀儡节点
        ListNode dummy = new ListNode(-1, head);

        // 定义两个指针，一个指向确定无重复的前置指针，一个是用于校验重复的后置指针
        ListNode left = dummy, right = dummy;

        while (right != null) {
            do {
                int curVal = right.val;
                do {
                    right = right.next;
                } while (right != null && right.val == curVal);

            } while (right != null && right.next != null && right.val == right.next.val);

            // 跳出循环，此时right.next可能为空，或者值不相等
            if (right != null) {
                left.next = right;
                left = right;
//                right = right.next;
            }
        }

        // right为空，说明到达了链表尾部，返回头节点
        return dummy.next;

    }

    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(4);
        ListNode node7 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        No24_82_DeleteDuplicates obj = new No24_82_DeleteDuplicates();
        ListNode listNode = obj.deleteDuplicates(node1);
        while (listNode != null) {
            System.out.print(listNode.val + ", ");
            listNode = listNode.next;
        }

    }

}
