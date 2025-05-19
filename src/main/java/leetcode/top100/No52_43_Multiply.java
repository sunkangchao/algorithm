package leetcode.top100;

/**
 * No52_43_Multiply
 *
 * <p>
 *     43. 字符串相乘
 *     给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 * </p>
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 19, 2025</pre>
 */
public class No52_43_Multiply {


    /**
     * 思路：字符串处理，遍历num2的每一位，与num1相乘，然后相加。
     * 需要对相乘后的每一位后续补0，然后执行字符串相加处理。
     *
     * 时间复杂度：O(mn + n^2)，得到每个相乘的结果需要mn的时间复杂度，相加需要n^2复杂度，因此O(mn + n^2)
     * 空间复杂度：O(1)，仅需少数中间变量
     */
    public String multiply1(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        // 定义结果变量 循环num2 取出每一位
        String result = "";
        for (int i = num2.length() - 1; i >= 0; i--) {
            int cur = num2.charAt(i) - '0';
            int carry = 0;
            StringBuilder sb = new StringBuilder();
            int j = num1.length() - 1;
            // 循环num1 每一位相乘
            for (; j >= 0; j--) {
                int k = num1.charAt(j) - '0';
                int tmp = cur * k + carry;
                sb.insert(0, tmp % 10);
                carry = tmp / 10;
            }

            if (carry != 0) {
                sb.insert(0, carry);
            }

            // 针对相乘后结果做补0处理
            for (int f = 0; f < num2.length() - 1 - i; f++) {
                sb.append("0");
            }

            // 相加
            result = addString(result, sb.toString());
        }

        // 返回结果
        return result;
    }

    // 定义一个字符串相加的方法
    private String addString(String result, String string) {
        if (result.length() == 0) {
            return string;
        }
        int i = result.length() - 1, j = string.length() - 1, carry = 0;
        StringBuilder ans = new StringBuilder();
        for (; i >= 0 || j >= 0; i--, j--) {
            int t1 = i >= 0 ? result.charAt(i) - '0' : 0;
            int t2 = j >= 0 ? string.charAt(j) - '0' : 0;
            int tmp = t1 + t2 + carry;
            carry = tmp / 10;
            ans.insert(0, tmp % 10);
        }

        // 注意处理进位
        if (carry != 0) {
            ans.insert(0, carry);
        }

        return ans.toString();
    }


    /**
     * 思路二：定义一个结果数组，已知结果数据的最大长度不会超过m+n，
     * 然后每一位运算的结果存储在i+j+1上，然后直接在数组的元素上进行运算，
     * 这种方式就不需要再额外进行字符串的相加操作了，很接近地模拟了乘法的竖式运算方法。
     *
     * 时间复杂度：O(m+n), 只需要双层for循环即可，不再需要额外进行加法操作。
     * 空间赋在读: O(m+n)，需要一个m+n的数组来存储中间结果。
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        // 定义结果数组
        int m = num1.length();
        int n = num2.length();

        int[] ans = new int[m + n];

        // 双层遍历 把结果存储在i+j+1位置上
        for (int i = n - 1; i >= 0; i--) {
            int carry = 0;
            int i1 = num2.charAt(i) - '0';
            int j = m - 1;
            for (; j >= 0; j--) {
                int i2 = num1.charAt(j) - '0';
                // 这里为了避免两次处理进行 直接取ans[i + j + 1]的值来参与计算 重新赋值
                // 否则把i3 + ans[i + j + 1]时可能产生新的进位 需要再次处理进位 如果直接参与计算 就不需要二次处理了
                int i3 = ans[i + j + 1] + i1 * i2 + carry;
                ans[i + j + 1] = i3 % 10;
                carry = i3 / 10;
            }

            // 处理进位 由于内层循环结束j-- 所以头位元素索引仍然是i + j + 1 而不是i + j
            if (carry != 0) {
                ans[i + j + 1] += carry;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            // 注意头元素的0 由于结果的位数大小是位于 m+n-1 < x < m+n的 只需要判断头位是否为0即可 如果是0则跳过前导0
            if (i == 0 && ans[i] == 0) {
                continue;
            }
            sb.append(ans[i]);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        No52_43_Multiply obj = new No52_43_Multiply();
        String multiply = obj.multiply("123", "456");
        System.out.println(multiply);
    }






}
