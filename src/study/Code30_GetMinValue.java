package study;

/**
 * 获取最小值
 *
 * @author sunkangchao
 * @since 2022/12/19 22:59
 */
public class Code30_GetMinValue {


    public static class MyStack<T extends Comparable<T>> {

        private T[] arr;
        private T[] sortedArr;
        private int pushI;

        public MyStack(T[] arr, T[] sortedArr) {
            this.arr = arr;
            this.pushI = 0;
            this.sortedArr = sortedArr;
        }

        public void push(T element) {
            arr[pushI] = element;
            if (pushI == 0) {
                sortedArr[0] = element;
            } else {
                T temp = sortedArr[pushI - 1];
                if (temp.compareTo(element) < 0) {
                    // 互换位置
                    sortedArr[pushI - 1] = element;
                    sortedArr[pushI] = temp;
                } else {
                    sortedArr[pushI] = element;
                }
            }
            pushI++;
        }

        public T pop() {
            if (pushI - 1 < 0) {
                throw new IllegalArgumentException("the stack is empty.");
            }
            T element = arr[pushI - 1];
            arr[pushI - 1] = null;
            // 处理最小栈
            int targetIndex = getTargetIndex(element);
            // 从targetIndex开始，后面的元素全部往前面移动一位
            moveBack(targetIndex);
            pushI--;
            return element;
        }

        public boolean isEmpty() {
            return pushI == 0;
        }

        public T peek() {
            if (pushI - 1 < 0) {
                throw new IllegalArgumentException(("the stack is empty."));
            }
            return arr[pushI - 1];
        }

        public T getMinValue() {
            if (pushI - 1 < 0) {
                throw new IllegalArgumentException(("the stack is empty."));
            }
            return sortedArr[pushI - 1];
        }

        private int getTargetIndex(T element) {
            for (int i = 0; i < sortedArr.length; i++) {
                if (sortedArr[i].equals(element)) {
                    return i;
                }
            }
            // 逻辑约束，不会产生-1值
            return -1;
        }

        private void moveBack(int starIndex) {
            // 从指定索引位开始，全部元素往前移动一位
            int i;
            for (i = starIndex; i < pushI - 1; i++) {
                sortedArr[i] = sortedArr[i+1];
            }
            sortedArr[i] = null;
        }

    }

    public static void main(String[] args) {
        MyStack<Double> myStack = new MyStack<>(new Double[5], new Double[5]);
        myStack.push(1.0);
        myStack.push(2.0);
        myStack.push(3.0);
        myStack.push(4.0);
        myStack.push(5.0);

        // peek: 5, getMinValue: 1, pop: 5
        System.out.print("peek: " + myStack.peek() + " ");
        System.out.print("getMinValue: " + myStack.getMinValue() + " ");
        System.out.print("pop: " + myStack.pop());
        System.out.println("------");

        // peek: 4, getMinValue: 1, pop: 4
        System.out.print("peek: " + myStack.peek() + " ");
        System.out.print("getMinValue: " + myStack.getMinValue() + " ");
        System.out.print("pop: " + myStack.pop());
        System.out.println("-------");

        // peek: 3, getMinValue: 1, pop: 3
        System.out.print("peek: " + myStack.peek() + " ");
        System.out.print("getMinValue: " + myStack.getMinValue() + " ");
        System.out.print("pop: " + myStack.pop());
        System.out.println("-------");

        // peek: 2, getMinValue: 1, pop: 2
        System.out.print("peek: " + myStack.peek() + " ");
        System.out.print("getMinValue: " + myStack.getMinValue() + " ");
        System.out.print("pop: " + myStack.pop());
        System.out.println("-------");

        // peek: 1, getMinValue: 1, pop: 1
        System.out.print("peek: " + myStack.peek() + " ");
        System.out.print("getMinValue: " + myStack.getMinValue() + " ");
        System.out.print("pop: " + myStack.pop());
        System.out.println("-------");

        // throw e;
        System.out.print("peek: " + myStack.peek() + " ");
        System.out.print("getMinValue: " + myStack.getMinValue() + " ");
        System.out.print("pop: " + myStack.pop());
        System.out.println("-------");

    }




}
