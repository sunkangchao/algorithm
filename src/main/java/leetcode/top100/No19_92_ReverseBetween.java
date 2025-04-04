package leetcode.top100;

import leetcode.top100.base.ListNode;

/**
 * 92. 反转链表 II
 *
 * 这是一道非常重要的题目，大厂一面极其高频：腾讯、字节、美团、虾皮等等都是最近考察的一面算法题
 *
 * @author sunkangchao
 * @since 2025/4/5 00:05
 */
public class No19_92_ReverseBetween {


    /**
     * 反转链表某个范围的节点，如head = [1,2,3,4,5], left = 2, right = 4
     * 输出：[1,4,3,2,5]，即反转第2个节点和第4个节点之间的节点
     *
     * 分析：首先，看得出来，反转后4->3->2，指向需要翻转，那么可以直接使用迭代法翻转
     * 其次，1节点需要指向翻转后的4节点，2节点需要指向5节点
     * 换言之，翻转后，需要持有1，4，2，5这四个节点的引用，你才能实现翻转
     * 这道题使用迭代法来翻转就非常简单 恰好有pre,cur这两个指针
     *
     * 编码中遇到的问题：（这题边界条件很多）
     * 1）如果pre往前移动0步，那么pre就会指向空，后面结果链接会出现空指针
     * a: pre在开始指向头节点，然后中间少移动一步
     * 2）如果right节点位于尾节点，翻转后就不能返回前面的节点作为返回节点
     * a: right节点是尾巴节点，则直接返回right节点
     * 3）如何判断right节点是尾节点
     * a: 如果遍历结束后cur节点为空，那么right就是尾节点
     * 4）其实不是right节点位于尾节点出问题，而是走了几步后left节点如果还是头节点，那么翻转后就应该返回尾巴节点
     *
     * 总结：
     * 最好就是弄一个傀儡节点，然后最终返回傀儡节点的下一个节点即可。原始的思路没有错，只需要在此基础上加一个傀儡节点。
     * 如果没有傀儡节点，你会多了很多的非空判断，会遇到很多的边界问题。
     *
     * 收获：
     * 1）编码时你需要有一个例子，否则你不好展开思考
     *
     * ----------------------leetcode题解-----------------
     * 思路其实很简单，就是定义两个指针，pre以及cur
     * 第一步，先把cur移动到left的位置
     * 第二步，从left位置开始使用迭代法翻转链表
     * 最后返回头节点即可。
     *
     * 这道题最大的难点在于边界条件，如果没有做过类似的题，像LRU这类，如果不设置傀儡节点/虚拟节点，在提交的过程中会遇到非常多的边界问题。我深陷其中不能自拔，一边是不断修改不断浮现的边界问题，另一边是不愿把代码改得太过复杂臃肿。后来，我突然想到建一个虚拟节点，最后返回虚拟节点的下一个节点，所有的边界问题引刃而解。
     *
     * 收获：
     * 1）这类链表的题目，为了避免过多的非空判断和边界问题，必须要建一个虚拟节点。
     * 2）编码的实际过程中，最好要有一个具体的测试用例来辅助你思考，比如说left = 2，那应该移动多少步到达left，这些不能每次记住，只能通过一个具体的测试用例，比如head = [1,2,3,4,5], left = 2, right = 4，通过分析这个具体的用例来辅助你进行编码，会事半功倍。
     * 3）做算法题再难都是思路，编码再难也不会太难。要先有思路，才能编码，有思路才是关键。
     *
     * 作者：Waiting for me
     * 链接：https://leetcode.cn/problems/reverse-linked-list-ii/solutions/3640557/92-fan-zhuan-lian-biao-ii-java-by-wo-ai-tk6t4/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {

        if (head.next == null) {
            return head;
        }

        // 这道题使用迭代法来翻转就非常简单 恰好有pre,cur这两个指针
        ListNode pNode = new ListNode(-1);
        pNode.next = head;

        ListNode pre = pNode;
        ListNode cur = head;

        // 翻转几个节点？ left - 1个节点
        int n = left - 1;
        while (n > 0 && cur != null) {
            ListNode next = cur.next;
            pre = cur;
            cur = next;
            n--;
        }

        // 此时保存pre,cur节点作为后面链接的根
        ListNode p1 = pre;
        ListNode p2 = cur;

        int n2 = right - left + 1;
        // 开始往后翻转
        while (n2 > 0 && cur != null) {
            ListNode next = cur.next;
            // 翻转
            cur.next = pre;
            pre = cur;
            cur = next;
            n2--;
        }

        // 此时到达目标节点 pre节点为翻转结尾节点，cur为它的下一个节点，但cur可能为空
        // 如果cur为空，无需考虑
        p1.next = pre;
        p2.next = cur;

        return pNode.next;
    }


}
