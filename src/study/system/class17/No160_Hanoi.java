package study.system.class17;

/**
 * No160_Hanoi
 * 汉诺塔问题
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 24, 2024</pre>
 */
public class No160_Hanoi {

    public void leftToRight(int N) {
        if (N == 1) {
            System.out.println("move 1 from left to right");
            return;
        }
        leftToMid(N - 1);
        System.out.println("move " + N + " from left to right");
        midToRight(N - 1);
    }

    private void midToRight(int N) {
        if (N == 1) {
            System.out.println("move 1 from mid to right");
            return;
        }
        midToLeft(N - 1);
        System.out.println("move " + N + " from mid to right");
        leftToRight(N - 1);
    }

    private void midToLeft(int N) {
        if (N == 1) {
            System.out.println("move 1 from mid to left");
            return;
        }
        midToRight(N - 1);
        System.out.println("move " + N + " from mid to left");
        rightToLeft(N - 1);
    }

    private void rightToLeft(int N) {
        if (N == 1) {
            System.out.println("move 1 from right to left");
            return;
        }
        rightToMid(N - 1);
        System.out.println("move " + N + " from right to left");
        midToLeft(N - 1);
    }

    private void rightToMid(int N) {
        if (N == 1) {
            System.out.println("move 1 from right to mid");
            return;
        }
        rightToLeft(N - 1);
        System.out.println("move " + N + " from right to mid");
        leftToMid(N - 1);
    }

    private void leftToMid(int N) {
        if (N == 1) {
            System.out.println("move 1 from left to mid");
            return;
        }
        leftToRight(N - 1);
        System.out.println("move " + N + " from left to mid");
        rightToMid(N - 1);
    }

    public static void main(String[] args) {
        No160_Hanoi instance = new No160_Hanoi();
//        instance.leftToRight(4);
        System.out.println("=======================");
        instance.hanoi(3, "left", "right", "mid");
    }

    public void hanoi(int N, String from, String to, String other) {
        if (N == 1) {
            System.out.println("move 1 from " + from + " to " + to);
            return;
        }
        hanoi(N - 1, from, other, to);
        System.out.println("move " + N + " from " + from + " to " + to);
        hanoi(N-1, other, to, from);
    }


}
