package leetcode.top100;

import leetcode.top100.base.ListNode;

/**
 * No95_25_ReverseKGroup
 *
 * 25. K 个一组翻转链表
 *
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 *
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 *
 * 提示：
 * 链表中的节点数目为 n
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 *
 *
 * 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
 *
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>09月 08, 2025</pre>
 */
public class No95_25_ReverseKGroup {


    /**
     * 总结：
     * 1）和那道翻转部分节点的题目一致 这道题都需要进行记录四个节点 以做连接
     * 2）区别在于我们翻转的是一个循环动作 每次更新好pre和head指针 把握好循环的边界即可
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 定义虚拟节点 pre head节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;

        // 循环：head节点不为空
        while (head != null) {
            ListNode tail = pre;
            for (int i = 0; i < k && tail != null; i++) {
                tail = tail.next;
            }
            if (tail == null) {
                break;
            }
            // 记录和反转
            ListNode tailNext = tail.next;
            tail.next = null; // 这里记录为空 可以使用翻转以null作为循环的终止条件 点睛之笔

            pre.next = reverseList(head);
            head.next = tailNext;
            pre = head;
            head = tailNext;
        }

        // 返回虚拟节点的下一个节点
        return dummy.next;
    }

    // 使用迭代法翻转 并且翻转后返回头节点
    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // 递归法翻转
    private ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode rs = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return rs;
    }


    // 1. 组织好代码 简化无关变量
    // 2. 清晰命名可以显著清晰思路 一共四个变量即可 pre head tail tailNext 以此命名思路就清晰多了
    // 3. 写完一定要总体回顾一次 非常关键 形成闭环 才不至于学完就忘
    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null || k <= 0) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;

        while (head != null) {

            ListNode tail = pre;
            while (tail != null && k-- > 0) {
                tail = tail.next;
            }
            if (tail == null) {
                break; // 保持不变
            }
            ListNode tailNext = tail.next;
            tail.next = null; // 将其置为空 技巧1


            pre.next = reverseList(head);
            head.next = tailNext;
            // 更新指针
            pre = head;
            head = tailNext;
        }

        return dummy.next;
    }



}
