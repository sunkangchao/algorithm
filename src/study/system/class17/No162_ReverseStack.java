package study.system.class17;

import java.util.Stack;

/**
 * 给你一个栈，逆序这个栈，不能申请额外的空间，只能使用递归
 * No162_ReverseStack
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 26, 2024</pre>
 */
public class No162_ReverseStack {

    private static Stack<Integer> reverseStack(Stack<Integer> stack) {
        if (stack == null || stack.isEmpty()) {
            return null;
        }
        Integer firstPushNum = getFirstPushNum(stack);
        reverseStack(stack);
        stack.push(firstPushNum);
        return stack;
    }

    private static Integer getFirstPushNum(Stack<Integer> stack) {
        if (stack.size() == 1) {
            return stack.pop();
        }
        Integer element = stack.pop();
        Integer firstPushNum = getFirstPushNum(stack);
        stack.push(element);
        return firstPushNum;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);

        Stack<Integer> result = reverseStack(stack);

        while (!result.isEmpty()) {
            Integer element = result.pop();
            System.out.print(element + " ");
        }
    }

}
