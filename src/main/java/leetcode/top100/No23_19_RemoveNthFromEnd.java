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


    public ListNode removeNthFromEnd(ListNode head, int n) {



    }


}
