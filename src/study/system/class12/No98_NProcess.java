package study.system.class12;

/**
 * 二叉树折纸问题
 * 一张纸，从下往上对折N次之后，要求从上往下，输出凹凸的结果
 *
 * @author sunkangchao
 * @since 2024/1/14 05:02
 */
public class No98_NProcess {



    void nProcess(int n) {

        // 其实是一个二叉树中序遍历的结果
        process(1, n, true);

    }

    void process(int times, int n, boolean down) {
        if (times > n) {
            return;
        }
        process(times + 1, n, false);
        System.out.print(down ? "凹 " : "凸 ");
        process(times + 1, n, true);
    }

    public static void main(String[] args) {
        No98_NProcess instance = new No98_NProcess();
        instance.nProcess(3);
    }



}
