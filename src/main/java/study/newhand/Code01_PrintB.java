package study.newhand;

/**
 * 打印二进制
 *
 * @author huey.sun
 * @date 2022/7/2 23:51
 */
public class Code01_PrintB {

    public static String print(int number) {
        StringBuilder sb = new StringBuilder();
        // 正式因为底层十进制就是二进制数
        for (int i = 0; i < Integer.SIZE; i++) {
            sb.insert(0, (number & (1 << i)) == 0 ? 0 : 1);
        }
        return sb.toString();
    }

    public static String printBinaryString(int number) {

        // 以此循环每一位 每一位与1相与 不为0则取出拼接1 否则拼接0
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Integer.SIZE; i++) {
            char bit = ((number & (1 << i)) == 0) ? '0' : '1';
            sb.insert(0, bit);
        }
        return sb.toString();
    }

    public static void main(String[] args) {


        int a = 1;
        int b = 2;
        int c = -1;
        System.out.println("a|b : " + print(a | b));
        System.out.println("a&b : " + print(a & b));
        System.out.println("a^b : " + print(a ^ b));
        System.out.println("~a : " + print(~a));
        System.out.println("~c : " + print(~c));
        System.out.println(print(-2));

        System.out.println("================================================================");
        int i = 4;
        System.out.println("i: " + printBinaryString(i));


    }



}
