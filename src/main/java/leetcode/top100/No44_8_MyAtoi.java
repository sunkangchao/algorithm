package leetcode.top100;

/**
 * No44_8_MyAtoi
 * <p>
 *     8. 字符串转换整数 (atoi)
 * </p>
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 30, 2025</pre>
 */
public class No44_8_MyAtoi {


    public int myAtoi1(String s) {

        if (s.isEmpty()) {
            return 0;
        }

        char[] chars = s.toCharArray();
        int i = 0;
        long ans = 0;
        boolean flag = true;
        // 1. 去除前导空格
        while (i < chars.length && chars[i] == ' ') {
            i++;
        }

        if (i >= chars.length) {
            return 0;
        }

        // 2. 判断正负
        if (chars[i] == '-' || chars[i] == '+') {
            flag = chars[i] == '+';
            i++;
        }

        // 3. 去除前导0
        while (i < chars.length && Character.isDigit(chars[i])) {
            ans = ans * 10 + (chars[i] - '0');
            i++;

            long temp = ans;
            if (!flag) {
                // 如何取反
                temp = -ans;
                if (temp < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            } else {
                if (temp > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
            }
        }

        // 4. 读取到下一个非数字字符或字符串结尾
        return flag ? (int) ans : (int) -ans;

        // 5. 默认值返回0，即

    }


    // 20250912
    public int myAtoi(String s) {
        // 定义指针 结果变量
        int index = 0;
        int len = s.length();
        long rs = 0;
        boolean flag = true;

        // 跳过前导的空格
        while (index < len && s.charAt(index) == ' ') {
            index++;
        }
        if (index >= len) {
            return (int) rs;
        }

        // 检查符号
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            flag = s.charAt(index) == '+';
            index++;
        }

        // 转换
        while (index < len && Character.isDigit(s.charAt(index))) {
            rs = rs * 10 + (s.charAt(index) - '0');
            index++;

            // 拼接过多 也超出了long的限制 变成了负数 应该在此处进行判断
            long temp = flag ? rs : -rs;
            if (temp > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (temp < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }

        // 最终拼接符号
        rs = flag ? rs : -rs;
        return (int) rs;
    }

    public static void main(String[] args) {
        No44_8_MyAtoi obj = new No44_8_MyAtoi();
        int ans = obj.myAtoi("+1");
        System.out.println(ans);

    }

}
