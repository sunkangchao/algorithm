package examination;

import examination.basic.ListNode;

/**
 * 链表排序
 *
 * @author huey.sun
 * @date 2021/9/7 00:13
 */
public class SortInList {

    public ListNode sortInList(ListNode head) {
        // write code here
        //首先找到思路，再找最优，不然就会在这两者之间纠结
        //实现空间复杂度为1的解法
        //冒泡方式

        int length = 0;
        //一个节点永远指向头部节点
        ListNode root = head;
        while (head != null) {
            length++;
            head = head.next;
        }
        ListNode preNode = new ListNode(-1);
        preNode.next = root;
        for (int i = 0; i < length - 1; i++) {
            head = root;
            while (head.next != null) {
                if (head.val > head.next.val) {
                    //交换位置
                    swapPosition(preNode,head,head.next);
                    preNode = head.next;
                } else {
                    //head重置
                    preNode = head;
                    head = head.next;
                }
            }
        }
        return root;
    }

    public void swapPosition(ListNode preNode, ListNode left, ListNode right) {
        //a-b-c-d-e-f 比如bc交换位置 记录前指针双指针是唯一优雅解
        //保存right的下一个节点
        ListNode rightNextNode = right.next;
        //开始交换
        preNode.next = right;
        right.next = left;
        left.next = rightNextNode;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        SortInList instance = new SortInList();
        ListNode result = instance.sortInList(node1);
        while (result != null){
            System.out.print(result.val + "->");
            result = result.next;
        }
    }
}
