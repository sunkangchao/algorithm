package study;

/**
 * 打印二进制
 *
 * @author huey.sun
 * @date 2022/7/2 23:51
 */
public class Code01PrintB {

    public static void print(int number) {
        StringBuilder sb = new StringBuilder();
        // 正式因为底层十进制就是二进制数
        for (int i = 0; i < Integer.SIZE; i++) {
            sb.insert(0, (number & (1 << i)) == 0 ? 0 : 1);
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        print(1);
        print(2);
        print(3);
        print(4);
    }
}
