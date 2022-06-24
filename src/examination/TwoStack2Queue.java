package examination;

import java.util.Stack;

/**
 * 两个栈实现队列
 *
 * @author SunKangChao
 * @date 2021/8/12 22:20
 */
public class TwoStack2Queue {

    //    Stack<Integer> stack1 = new Stack<Integer>();
//    Stack<Integer> stack2 = new Stack<Integer>();
//    int size = 0;
//
//    public void push(int node) {
//        stack1.push(node);
//        size++;
//    }
//
//    public int pop() {
//        if (size == 0) {
//            return -1;
//        }
//        while (!stack1.isEmpty()) {
//            int node = stack1.pop();
//            stack2.push(node);
//        }
//        int result = stack2.pop();
//        while (!stack2.isEmpty()) {
//            int node = stack2.pop();
//            stack1.push(node);
//        }
//        size--;
//        return result;
//    }
    //方法二
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }
    //JKFJDKJKJJjjkjJ健康健康健康健康JJJJJ

    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        TwoStack2Queue instance = new TwoStack2Queue();
        instance.push(1);
        instance.push(2);
        instance.push(3);
        instance.push(4);
        instance.push(5);

        for(;;) {
            int node = instance.pop();
            System.out.print(node + " ");
        }
    }


}
