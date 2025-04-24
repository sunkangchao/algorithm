package leetcode.top100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * No38_155_MinStack
 *
 * 它是一个特殊的栈，它比常规的栈多了一个功能，就是它能够在O(1)时间复杂度获取栈中的最小值
 *
 * 它与单调递增栈有何区别？
 * 1）它维护着当前最小的元素集合，也是递增的，但是属于非严格单调递增，它会存在相等的元素。
 * 2）它维护着当前的最小值，而单调递增栈一旦发现比栈顶大的元素，会以此弹出，再压入。
 * 3）使用场景：最小值维护着栈最小值，使的在常数时间内获取栈中最小值。单调栈常用于以更高的效率获取左边/右边第一个比它小/大的元素。
 *
 * 它是如何实现的？
 * 1）新增一个辅助栈，当push的元素x时，同步往两个栈中push，如果x元素 >= minStack.peek，则压入minStack.peek，或者压入x。
 * 2）维护一条单向链表，链表每个元素不仅有val, next，还多了一个minValue属性，每个节点都保存着当前的最小值。
 *
 * 注意：
 * 1）为什么两个栈要同步push？如果minStack只存一份最小值，那么主stack在pop最小值时，minStack就没有这一份最小值了，但主栈不止一个最小值，这种情况就会出问题。
 * 2）minStack在刚push时，使用Math.min(val, minStack.peek())会报空指针，为了使用这种写法而不报错，可以在创建对象时往minStack压入Integer.MAX_VALUE
 * 3）Deque实现的栈都是基于头节点操作，push或者pop等
 * 4）操作栈中元素注意判空，pop操作会异常，peek操作会返回null。
 *
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 24, 2025</pre>
 */
public class No38_155_MinStack {

    private Deque<Integer> stack1;
    private Deque<Integer> minStack;


    public No38_155_MinStack() {
        stack1 = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int val) {
        stack1.push(val);
        minStack.push(Math.min(val, minStack.peek()));
    }

    public void pop() {
        stack1.pop();
        minStack.pop();
    }

    public int top() {
        return stack1.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}
