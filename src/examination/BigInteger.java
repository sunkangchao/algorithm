package examination;


/**
 * 大数加法
 *
 * @author SunKangChao现在　
 * @date 2021/7/16 16:57
 */
public class BigInteger {


    public String solve(String s, String t) {
        // write code here
        int leftIndex = s.length() - 1;
        int rightIndex = t.length() - 1;
        int flag = 0;
        StringBuilder result = new StringBuilder();

        while (leftIndex >= 0 || rightIndex >= 0 || flag == 1) {
            int num1 = 0;
            int num2 = 0;
            if (leftIndex >= 0) {
                num1 = s.charAt(leftIndex) - 48;
            }
            if (rightIndex >= 0) {
                num2 = t.charAt(rightIndex) - 48;
            }
            int tempNum = num1 + num2 + flag;
            if (tempNum >= 10) {
                flag = 1;
                tempNum = tempNum % 10;
            } else {
                flag = 0;
            }

            result.insert(0, tempNum);
            leftIndex--;
            rightIndex--;

        }

        return result.toString();

    }

    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger();
        System.out.println(bigInteger.solve("9", "99999999999999999999999999999999999999999999999999999999999994"));
    }

}
