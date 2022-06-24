package examination;

import com.sun.tools.javac.util.List;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import examination.basic.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表的入口点
 *
 * @author SunKangChao
 * @date 2021/7/16 19:44
 */
public class EntryNodeOfLoop {

    public ListNode entryNodeOfLoop(ListNode pHead) {
        //第一个重复的点就是环形链表的入口点
        Set<ListNode> nodeSet = new HashSet<>();
        ListNode entryNode = null;

        while(pHead != null){
            if (nodeSet.contains(pHead)) {
                entryNode = pHead;
                break;
            }
            nodeSet.add(pHead);
            pHead = pHead.next;
        }
        return entryNode;
    }
}
