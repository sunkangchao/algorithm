package leetcode.top100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * No30_232_MyQueue
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 16, 2025</pre>
 */
public class No30_232_MyQueue {

    private Deque<Integer> inStack;
    private Deque<Integer> outStack;

    public No30_232_MyQueue() {
        this.inStack = new ArrayDeque<>();
        this.outStack = new ArrayDeque<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        if (outStack.isEmpty()) {
            int2Out();
        }
        return outStack.pop();
    }

    public int peek() {
        if (outStack.isEmpty()) {
            int2Out();
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void int2Out() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }


}
