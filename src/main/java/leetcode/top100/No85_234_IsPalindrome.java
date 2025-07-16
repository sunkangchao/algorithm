package leetcode.top100;

import leetcode.top100.base.ListNode;

/**
 * No85_234_IsPalindrome
 *
 * 234. 回文链表
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>07月 16, 2025</pre>
 */
public class No85_234_IsPalindrome {


    // 反转解法
    // 反转链表，比较前后两个字符串是否相等即可
    // 这里整条链表是否相等使用了字符串，因为提示给出val是一个一位数的数字，所以拼接时也不用分隔符
    public boolean isPalindrome0(ListNode head) {
        if (head == null) {
            return true;
        }

        // 1. 遍历原链表 记录字符串
        StringBuilder sb0 = new StringBuilder();
        ListNode node = head;
        while (node != null) {
            sb0.append(node.val);
            node = node.next;
        }

        // 2. 反转链表
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next; // 第一步
            cur.next = pre; // 第二步
            pre = cur; // 第三步
            cur = next;
        }

        // 3. 记录后续字符串
        StringBuilder sb1 = new StringBuilder();
        node = pre;
        while (node != null) {
            sb1.append(node.val);
            node = node.next;
        }

        // 4. 比较两字符串是否相等
        return sb0.toString().equals(sb1.toString());
    }


    // 快慢指针
    // 反转中点（包含）后半段的链表，然后两条链表都从头遍历，依次比较是否相等。
    public boolean isPalindrome(ListNode head) {
        // 1. 首先使用快慢指针 找到链表的中点
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode mid = slow;

        // 2. 反转中点（包含）的后续节点
        ListNode pre = null, cur = mid;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // 3. 遍历比较是否每个节点都相等
        ListNode last = pre;
        while (last != null) {
            if (head.val != last.val) {
                return false;
            }
            head = head.next;
            last = last.next;
        }
        return true;
    }


    /**
     * ------------------------------3. 递归解法 利用递归逐层返回的特性 先触底 再返回时依次比较-------------------------------------
     */
    private ListNode fNode;

    // 递归解法
    public boolean isPalindrome2(ListNode head) {
        fNode = head;
        return recursivelyCheck(head);
    }

    private boolean recursivelyCheck(ListNode node) {
        if (node == null) {
            return true;
        }
        boolean rs = recursivelyCheck(node.next); // 先到底
        if (!rs) {
            return false; // 如果在外层比较已经不是回文了 就直接结束
        }
        if (node.val != fNode.val) {
            return false; // 如果不等那就返回false
        }
        fNode = fNode.next; // 这一步很关键 fNode往前走
        return true;
    }



}
