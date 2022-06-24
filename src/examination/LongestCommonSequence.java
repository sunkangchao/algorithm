package examination;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 最长公共子序列
 *
 * @author SunKangChao
 * @date 2021/7/21 21:17
 */
public class LongestCommonSequence {

    //动态规划问题，首先需要找出递归公式，再利用从下往上递推的方式来找到最优解

    public String lcs(String s1, String s2) {
        // write code here
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        //定义dp[i][j]是s1长度为i-1,s2长度为j-1的最长公共子序列
        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int i = s1.length();
        int j = s2.length();
        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i][j - 1] > dp[i - 1][j]) {
                j--;
            } else if (dp[i][j - 1] < dp[i - 1][j]) {
                i--;
            } else {
                j--;
            }
        }
        return sb.toString();
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        Arrays.sort(input);
        ArrayList<Integer> result = new ArrayList<>();
        IntStream.range(0, k + 1)
                .forEach(index -> result.add(input[index]));
        return result;
    }

    public static void main(String[] args) {
//        LongestCommonSequence instance = new LongestCommonSequence();
//        String rs = instance.lcs(
//                "1A2C3D4B56","B1D23A456A");
//        System.out.println(rs);
        List<Number> list = new ArrayList<>();
        list.add(0.1f);
        list.add(0.2f);
        test(list);

    }

    /*? extents xxx 可以安全的取出 ，xxx本身或其父类可以安全的接收。
    ? super xxx 可以安全的写入 ,xxx的本身和子类可以安全的写入。*/
    //PECS原则(Producer Extents Consumer Super)
    public static void test(List<? extends Number> list) {
        Number number = list.get(0);
        if (number instanceof Float) {
            Float fl = ((Float) number);
            System.out.println(fl);
        } else {
            System.out.println("不符合类型");
        }

        List<? super Number> list2 = new ArrayList<>();
        list2.add(0.1);

        Map<String, String> hashMap = new HashMap<>();
        if (hashMap.getClass().equals(Map.class)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

}
