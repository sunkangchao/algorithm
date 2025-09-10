package leetcode.top100;

import java.util.HashSet;
import java.util.Set;

/**
 * No97_N_LongestCommonSubSequence
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>09月 10, 2025</pre>
 */
public class No97_N_LongestCommonSubSequence {



    public String longestCommonSubSequence(String s1, String s2) {
        // 求解长度，填dp表格
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return "";
        }

        // 回溯组装最终字符串
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];
        // 初始化免了 默认都是0

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[m][n]);

        // 求解了dp[m][n] 回溯组装字符串
        int i = m, j = n;
        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.insert(0, s1.charAt(i - 1));
                i--;
                j--;
            } else{
                if (dp[i - 1][j] >= dp[i][j - 1]) {
                    // 不相等直接更新
                    i--;
                } else {
                    j--;
                }
            }
        }

        return sb.toString();
    }

// ----------------------变种题------------------------------

    // 返回全部符合最大长度的字符串
    public Set<String>  longestCommonSubSequence2(String s1, String s2) {
        // 求解长度，填dp表格
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return new HashSet<>();
        }

        // 回溯组装最终字符串
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];
        // 初始化免了 默认都是0

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[m][n]);

        // 回溯求解
        return backtrack(s1, s2, m, n, dp);
    }

    // 不是带着合好的字符串往下 而是使用尾递归方式合并返回结果
    // 重要的经验总结，面试时没有调试，那就通过打印来进行调试！！！！！！！！！！
    private Set<String> backtrack(String s1, String s2, int m, int n, int[][] dp) {
        Set<String> result = new HashSet<>();

        if (m == 0 || n == 0) {
            result.add(""); // 也可以不进行判断 再结果返回处添加一个""空字符串元素 否则下面的for循环就无法把递归出口的单个字符添加到集合当中（下层只能加判断，不够简洁）
            return result;
        }

        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            String temp = s1.charAt(m - 1) + "";
            Set<String> subRs = backtrack(s1, s2, m - 1, n - 1, dp);
                for (String str : subRs) {
                    result.add(str + temp);
                }
//            System.out.println(result);
        } else {
            if (dp[m - 1][n] >= dp[m][n - 1]) {
                result.addAll(backtrack(s1, s2, m - 1, n, dp));
            }
            if (dp[m][n - 1] >= dp[m - 1][n]) {
                result.addAll(backtrack(s1, s2, m, n - 1, dp));
            }
        }
//        System.out.println(result);

        return result;
    }


    public static void main(String[] args) {
        No97_N_LongestCommonSubSequence obj = new No97_N_LongestCommonSubSequence();
        String s1 = "ABCBDAB";
        String s2 = "BDCABA";
        System.out.println("LCS: " + obj.longestCommonSubSequence2(s1, s2)); // LCS: BCBA

    }


}
