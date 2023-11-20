package study;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个队列实现栈结构
 *
 * @author sunkangchao
 * @since 2022/12/16 01:29
 */
public class Code29_TwoQueueImplementStack<T> {

    private Queue<T> queueLeft;

    private Queue<T> queueRight;

    private int size;

    public Code29_TwoQueueImplementStack(Queue<T> queueLeft, Queue<T> queueRight) {
        this.queueLeft = queueLeft;
        this.queueRight = queueRight;
        size = 0;
    }

    /**
     * 压入元素
     * @param element
     */
    public void push(T element) {
        Queue<T> targetQueue = findNotNullQueue();
        targetQueue.add(element);
        size++;
    }

    /**
     * 弹出元素
     * @return
     */
    public T pop() {
        if (size == 0) {
            throw new RuntimeException("stack is empty!");
        }
        // 从不为空的队列中来操作
        Queue<T> targetQueue = findNotNullQueue();
        Queue<T> anotherQueue = findAnotherQueue(targetQueue);
        int time = size - 1;
        while (time > 0) {
            anotherQueue.add(targetQueue.poll());
            time--;
        }
        size--;
        return targetQueue.poll();
    }

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 查看栈顶元素
     * @return
     */
    public T peek() {
        if (size == 0) {
            throw new RuntimeException("stack is empty!");
        }
        Queue<T> notNullQueue = findNotNullQueue();
        Queue<T> anotherQueue = findAnotherQueue(notNullQueue);
        int time = size - 1;
        while (time > 0) {
            anotherQueue.add(notNullQueue.poll());
            time--;
        }
        T peekElement = notNullQueue.peek();
        anotherQueue.add(notNullQueue.poll());
        return peekElement;
    }

    /**
     * 返回为空的
     * @return
     */
    private Queue<T> findNotNullQueue() {
        if (queueLeft.isEmpty() && queueRight.isEmpty()) {
            return queueLeft;
        }
        return queueLeft.isEmpty() ? queueRight : queueLeft;
    }

    private Queue<T> findAnotherQueue(Queue<T> queue) {
        return queue == queueLeft ? queueRight : queueLeft;
    }


    public static void main(String[] args) {
        Code29_TwoQueueImplementStack<Integer> stack = new Code29_TwoQueueImplementStack<>(new LinkedList<>(), new LinkedList<>());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }


}
