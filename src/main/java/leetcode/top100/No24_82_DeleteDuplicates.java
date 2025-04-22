package leetcode.top100;

import leetcode.top100.base.ListNode;

/**
 * 82. 删除排序链表中的重复元素 II
 *
 * @author sunkangchao
 * @since 2025/4/8 23:17
 */
public class No24_82_DeleteDuplicates {

    /**
     * 写的乱七八糟 写这么乱 基本上都是错的了
     * 方向已经错了
     * 教训：
     * 1）思路弄清楚是关键 如果思路不够清晰 先把思路捋清楚
     * 2）靠调试是很慢的 效率非常低的
     * 3）我不是不能看答案 想得实在差太远那就看看答案 我是要从中学到东西的
     * 4）思路不清楚 不要动手写 否则你会越写越乱 陷入一个调试的怪圈 都因为你没想清楚就开始写
     * 5）一定要限定时间 在半小时内写不出来就马上看答案 你要么是思路不清晰，要么是思路有问题，总之这个时候看答案就没错 你本身也是想能从答案中进步
     * 6）注意效率 规定时间做不出来就看答案 完全没有问题的 你看下答案或许你就豁然开朗了 否则按照错的思路纠结一道题 你一道题能玩一天
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates1(ListNode head) {
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
            }
        }

        // 增加判断，如果left指针没有向右移动，则直接返回null
        if (left == dummy) {
            return null;
        }

        // right为空，说明到达了链表尾部，返回头节点
        return dummy.next;

    }


    /**
     *
     * 这道题很经典，是一道启蒙的题目，好好消化总结
     *
     * 三指针解法
     *
     * pre指向确定无重复的最新元素，cur和cur.next比较
     * 1）cur.val == cur.next.val，那么往后挪动，直至cur.val不等于旧的val（此处使用cur来作为循环条件 而不是cur.next），
     *  然后把pre.next预指向cur，但是不更新pre的指针。这一步可谓关键步骤，之前也遇到过这种做法，确实精妙
     * 2）cur.val != cur.next.val，那么此时就可以更新pre和cur指针
     *
     * 注意：为什么你会想出三层循环来，就是以你为你在第一步找到cur.val不等于旧val的时候，你发现需要再次比较cur.val和cur.next.val值，
     * 然后你就自然而然地想出了再加一层循环，而你不曾经发觉，你其实只需要重复步骤一就可以了。所以应该是一个双层循环
     *
     * 看着官方题解很简洁，但写出来，整理好却不容易
     *
     * 教训：想清楚思路再去编码，否则这个编码就会变形，你会为了通过而编码，而不会重视了思路本身已经有问题
     *
     * 复杂度分析：
     * 1）时间复杂度：O(n)，遍历一次
     * 2）空间复杂度：O(1)，仅需少数变量
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummyNode = new ListNode(-1, head);

        ListNode pre = dummyNode, cur = head;
        while (cur != null && cur.next != null) {

            if (cur.val == cur.next.val) {
                int val = cur.val;
                // 很显然 第一次执行这一块 是必然相等的 但为了不用cur.next来判断 重复走这一遍又是非常值的的 不然你很容易乱
                while (cur != null && cur.val == val) {
                    cur = cur.next;
                }
                // 这一步可谓精辟 先指向 但不挪动指针 一遍后面有重复可以指针往后挪 你刚开始其实就是卡在这一步 然后才想出那三层循环的玩意
                pre.next = cur;
            } else {
                pre = pre.next;
                cur = cur.next;
            }
        }

        return dummyNode.next;

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
