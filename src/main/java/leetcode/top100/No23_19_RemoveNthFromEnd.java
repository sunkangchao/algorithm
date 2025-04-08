package leetcode.top100;

import leetcode.top100.base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 19. 删除链表的倒数第 N 个结点
 *
 * No23_19_RemoveNthFromEnd
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 08, 2025</pre>
 */
public class No23_19_RemoveNthFromEnd {


    /**
     * 方法一：
     * 线性表解法：先把每个节点存到list当中，后面通过索引下标来访问每个节点
     * 对于删除某个节点，只需要直接通过索引下标找到其前一个节点和后一个节点，把前一个节点指向后一个节点即可
     *
     * 注意：链表题目都先建立傀儡节点/虚拟节点，可以免除很多非空的判断
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {

        if (head == null) {
            return null;
        }
        List<ListNode> list = new ArrayList();
        while (head != null) {
            list.add(head);
            head = head.next;
        }

        int index = list.size() - n;

        ListNode removedNode = list.get(index);
        removedNode.next = null;

        ListNode preNode = null;
        ListNode nextNode = null;
        if (index - 1 >= 0) {
            preNode = list.get(index - 1);
        }
        if (index + 1 < list.size()) {
            nextNode = list.get(index + 1);
        }

        if (preNode == null) {
            return nextNode;
        }

        preNode.next = nextNode;

        return list.get(0);
    }


    /**
     * 方法二：
     * 双指针解法：定义两个指针，两个指针指向相距n步，然后一起往后遍历
     * 如果快指针已经碰到边界，那么慢指针的下一个节点就是需要删除的节点
     * 核心：两个指针之间的差值是一直保持的，到了边界就能找出倒数第n个节点
     *
     * 注意：
     * 1）需要遍历到被删除节点的前一个节点，因为你需要操作前一个节点的指向
     * 2）链表题目建立虚拟节点/傀儡节点，这样能省去头部接待您的非空判断
     *
     * leetcode官方：
     * 在对链表进行操作时，一种常用的技巧是添加一个哑节点（dummy node），它的 next 指针指向链表的头节点。这样一来，我们就不需要对头节点进行特殊的判断了。
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(-1, head);

        // 定义两个指针它们相隔n步 刚开始定义时它们在同一个起点
        ListNode slow = dummy, quick = dummy;

        while (n-- > 0) {
            quick = quick.next;
        }

        while (quick.next != null) {
            slow = slow.next;
            quick = quick.next;
        }

        // 此时slow节点为需要删除的节点的前一个节点
        ListNode tarNode = slow.next;

        slow.next = tarNode.next;
        tarNode.next = null;

        return dummy.next;
    }


}
