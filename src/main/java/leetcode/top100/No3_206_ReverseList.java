package leetcode.top100;


import leetcode.top100.base.ListNode;

/**
 * No3_206_ReverseList
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>03月 05, 2025</pre>
 */
public class No3_206_ReverseList {

    // 1. 迭代法
    public ListNode reverseList(ListNode head) {

        if (head == null) {
            return null;
        }
        // A <- 1 -> 2 -> 3 -> 4 -> 5
        // A <- 1 <- 2 -> 3 -> 4 -> 5
        // A <- 1 <- 2 <- 3 -> 4 -> 5
        // A <- 1 <- 2 <- 3 <- 4 -> 5
        // A <- 1 <- 2 <- 3 <- 4 <- 5
        // 返回最后一个元素 每次都指向上一个元素
        ListNode cur = head;
        // 创建一个伪节点
        ListNode pre = new ListNode();
        pre.next = cur;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // 删除伪节点
        head.next = null;
        // 返回上一个节点
        return pre;
    }

    // 2. 迭代法（简化版） 双指针
    // 核心就三步：1）暂存后继节点 2）当前节点指向前一个节点 3）双指针都指向其下一个节点
    public ListNode reverseList2(ListNode head) {

        // pre指针可以不用是一个伪节点 因为pre从始至终都只是作为存储的一方
        ListNode cur = head, pre = null;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;

    }

    // 3. 递归法
    public ListNode reverseList3(ListNode head) {

        // 1 -> 2 -> 3 -> 4 -> 5
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode result = reverseList3(head.next);
        ListNode next = head.next;
        next.next = head;
        head.next = null; // 这一步很关键 没有这一步 最终ListNode会形成环

        return result;
    }


    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        No3_206_ReverseList obj = new No3_206_ReverseList();
        obj.reverseList3(node1);


    }



}