package examination;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 判断链表是否存在环结构
 *
 * @author SunKangChao
 * @date 2021/7/11 23:36
 */
public class LinkedCycle {

    //因为每一个节点的next只能指向一个节点，如果有环，一定是链表的最后一个元素指向前面的元素

    //快慢指针
    public boolean hasCycle(ListNode head) {
        ListNode quickNode = head;
        ListNode slowNode = head;

        while (quickNode != null && quickNode.next != null){
            quickNode = quickNode.next.next;
            slowNode = slowNode.next;

            if (Objects.equals(quickNode,slowNode)) {
                return true;
            }
        }
        return false;
    }

    //Set集合
    public boolean hasCycle2(ListNode head){
        Set<ListNode> set = new HashSet<>();
        while (head != null){
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }

    private static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val){
            this.val = val;
            next = null;
        }
    }

    public static void main(String args[]) {

    }
}
