package examination;

/**
 * @author SunKangChao
 * @date 2021/7/13 17:35
 * 回文字符串判断
 */
public class ReverseString {

    //字符串反转
    public String solve(String str) {
        // write code here

        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    //方法一：头尾互换位置
    public static boolean exchangeIndex(String str) {

        if (str == null || str.length() == 0) {
            return false;
        }

        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) == str.charAt(str.length() - 1 - i)) {
                continue;
            }
            return false;
        }
        return true;
    }

    //方法二： 拼接方式
    // 取值取一半怎么做：
    //从左到右，无论是奇长度还是偶长度，都是i<length/2
    //从右到左，无论是奇长度还是偶长度，都是i>=length/2
    public static boolean appendIndex(String str) {

        if (str == null || str.length() == 0) {
            return false;
        }

        StringBuilder sb = new StringBuilder();
        //如果不设置这个变量，str的长度一直处于动态变化
        int length = str.length();
        //a b c d e
        for (int i = str.length() - 1; i >= length / 2; i--) {
            String splitStr = str.substring(i);
            str = str.substring(0, i);
            sb.append(splitStr);
        }

        //或语句判断
        return str.substring(0, str.length() - 1).equals(sb.toString())
                || str.equals(sb.toString());
    }

    public static void main(String[] args) {
        System.out.println(exchangeIndex("abcddcba"));
        System.out.println(appendIndex("abbaccabba"));
    }


}
