package examination;
import examination.basic.ListNode;

/**
 * 翻转K组链表
 * 要求空间复杂度为1
 *
 * @author SunKangChao
 * @date 2021/7/24 06:44
 */
public class ReverseKGroup {

    private ListNode headNode;
    private ListNode tailNode;
    private int size;
    private ListNode startNode;
    private int current;
    private int k;


    public ListNode reverseKGroup(ListNode head, int k) {
        // write code here
        if (head == null) {
            return head;
        }

        ListNode root = head;
        this.k = k;
        this.startNode = root;
        this.tailNode = head;
        while (head != null) {
            size++;
            head = head.next;
        }
        head = root;

        if (k <= size) {
            for (int i = 0; i < k; i++) {
                this.headNode = head;
                head = head.next;
            }
        }else {
            return head;
        }

        for (int i = 0; i < size; i++) {
            current++;
            if (current % k == 0) {
                reverseGroup(startNode);
            }
        }
        if (startNode != null) {
            tailNode.next = startNode;
            tailNode = null;
        }

        return this.headNode;

    }

    public void reverseGroup(ListNode beginNode) {

        ListNode sourceHead = beginNode;
        ListNode endNode;
        endNode = beginNode.next;
        for (int i = 1; i < k; i++) {
            if (i == 1) {
                beginNode.next = null;
            }
            ListNode tempNode = endNode.next;
            endNode.next = beginNode;
            beginNode = endNode;
            endNode = tempNode;
        }

        if (tailNode != startNode) {
            tailNode.next = beginNode;
        }
        tailNode = sourceHead;
        startNode = endNode;

    }

    public static void main(String[] args) {
        ReverseKGroup instance = new ReverseKGroup();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        //1->2->3->4->5
        ListNode head = instance.reverseKGroup(node1, 2);
        while (head != null) {
            if (head.next == null) {
                System.out.print(head.val);
            } else {
                System.out.print(head.val + "->");
            }
            head = head.next;
        }
    }
}
