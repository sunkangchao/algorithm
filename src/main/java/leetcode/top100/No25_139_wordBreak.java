package leetcode.top100;

import java.util.*;

/**
 * 139. 单词拆分
 *
 * No25_139_wordBreak
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 09, 2025</pre>
 */
public class No25_139_wordBreak {

    private boolean result;

    /**
     * 方法一：常规思路，暴力递归 （但是会超时）
     * 遍历完所有可能，从0-len，中间有满足是单词的，就往下一层递归
     * 最终结果如果start == 0，那么就是刚好凑够，此时更新全局变量result
     *
     * 注意：
     * 1）转换成Set是时间复杂度O(1)的，它的contains方法是O(1)的，List的Contains的O(n)的，字符串的contains必然也是O(n)
     * 2）不需要每次subString，直接在原数组上操作，使用一个start指针指向下一个开始的元素就够
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak0(String s, List<String> wordDict) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        // 转换成Set，O(1)时间复杂度判断是否存在
        wordBreak0(s, 0, new HashSet<>(wordDict));
        return result;
    }

    private void wordBreak0(String s, int start, Set<String> wordDict) {
        if (start == s.length()) {
            result = true;
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String cur = s.substring(start, i + 1);
            if (wordDict.contains(cur)) {
                wordBreak0(s, i + 1, wordDict);
                if (result) {
                    break;
                }
            }
        }
    }

    // ----------------------------分割线----------------------------------

    public boolean wordBreak1(String s, List<String> wordDict) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        // 转换成Set，O(1)时间复杂度判断是否存在
        wordBreak1(s, 0, new HashSet<>(wordDict), new HashSet<>());
        return result;
    }

    /**
     * 优化版本，使用记忆化递归
     * 总结：
     * 1）如果方法没有返回值，那么其实只需要判断key存在不存在，如果存在，就不需要再走一遍这个方法了。这是很重要的经验总结。
     * 换言之，就不需要使用Map结构，而是使用Set结构就可以。
     * 2）其次，我们再改造记忆化递归版本时，主要是看方法参数，有几个参数再变化，就知道是要存几个参数作为key；
     * 然后，通过是否有返回值来判断使用Set还是Map。这些
     *
     * 以上这些就是实操之后的很重要的经验总结。但是你如果没做过，这种疑惑你必然是会碰到的。
     *
     * @param s
     * @param start
     * @param wordDict
     * @param set
     */
    private void wordBreak1(String s, int start, Set<String> wordDict, Set<Integer> set) {
        if (start == s.length()) {
            result = true;
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String cur = s.substring(start, i + 1);
            if (wordDict.contains(cur) && !set.contains(i + 1)) {
                wordBreak1(s, i + 1, wordDict, set);
                if (result) {
                    set.add(i + 1);
                    break;
                }
            }
        }
        // 遍历完都没有break，说明以start开始的不能构成s，结果存起来
        set.add(start);
    }



    // ----------------------------分割线----------------------------------


    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreak(s, new HashSet<>(wordDict));
    }
        /**
         * 方法三：动态规划版本 口诀：定、转、初、遍、返，写在多遍都不如一首口诀记得牢固
         * 思路：从记忆化版本再改动态规划，其实可以看得出来，也是从每个索引位开始往前遍历
         * 只要遍历到构成的区间元素是一个单词时，然后再取它已经判断过的值，即
         * 1）定义dp[i]为以第i个元素结尾的字符串是否能被完整构成
         * 2）转移方程：dp[i] = dp[j] && workDict.contains(s.subString(j, i));
         * 3）初始值，借助字符串空串，即dp[0] = true，因为字典中的元素可以不选择，那么空串自然是可以构成的
         * 4）遍历方向，一维度的，从左到右
         * 5）返回dp[n]
         *
         * 总结：
         * 1）借助字符串空串是为了辅助遍历过程，为什么选空串，什么时候选空串，当空串对于动态转移方程也是一个有效值时
         * 2）初始值一旦是错的，后面推导的结果全是错的，重要性不言而喻。
         *
         * @param s
         * @param wordDict
         */
    public boolean wordBreak(String s, Set<String> wordDict) {

        int len = s.length();
        boolean[] dp = new boolean[len + 1];

        // 差一个初始值全错
        dp[0] = true;

        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (wordDict.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }
}
