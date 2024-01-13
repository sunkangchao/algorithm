package study.newhand;

/**
 * 计算1!+2!+3!+...+N!的结果
 *
 * @author sunkangchao
 * @date 2022/7/4 01:17
 */
public class Code02_CalcN {

    /**
     * 暴力解法，依次计算每一项的阶乘
     * @param n
     * @return
     */
    public static long calculate(int n) {
        long result = 0;
        for (int i = 1; i <= n; i++) {
            long temp = 1;
            for (int j = 1; j <= i; j++) {
                temp *= j;
            }
            result += temp;
        }
        return result;
    }

    /**
     * 第N项的阶乘，等于N-1项阶乘的结果乘以N;
     * @param n
     * @return
     */
    public static long calculate2(int n) {
        long result = 0;
        long temp = 1;
        for (int i = 1; i <= n; i++) {
            temp *= i;
            result += temp;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(calculate2(1));;
        System.out.println(calculate2(2));
        System.out.println(calculate2(3));
        System.out.println(calculate2(4));
    }


}
