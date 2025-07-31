package leetcode.top100;

/**
 * No87_14_LongestCommonPrefix
 *
 * 14. 最长公共前缀
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>07月 31, 2025</pre>
 */
public class No87_14_LongestCommonPrefix {


    /**
     * 横向扫描
     * @param strs
     * @return
     */
    // 依次遍历，每一次都取它们的公共字符串，为0直接返回
    // chatAt写错了，是charAt
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) { // 数组是属性，字符串是方法
            return "";
        }

        // 构造一个基本的结果变量
        String ans = strs[0];
        for (String str : strs) {
            // ans 和 str比较公共字符串
            int len1 = ans.length();
            int len2 = str.length();
            int minLen = Math.min(len1, len2);
            int i = 0;
            for (; i < minLen; i++) {
                if (ans.charAt(i) != str.charAt(i)) {
                    break;
                }
            }
            // 此时把i拿出来没问题了吧
            ans = ans.substring(0, i);
            if (ans.length() == 0) { // 等于0提前返回
                return "";
            }
        }
        return ans;
    }


    /**
     * 纵向比较
     * @param strs
     * @return
     */
    public String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) { //
            return "";
        }

        // 取最小字符串
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(str.length(), minLen);
        }


        // 逐位比较 可以提供一个方法
        int i = 0;
        for (; i < minLen; i++) {
            if (!isSameCharAt(strs, i)) {
                break;
            }
        }

        return strs[0].substring(0, i);
    }

    private boolean isSameCharAt(String[] strs, int index) {
        char c = strs[0].charAt(index);
        for (int i = 1; i < strs.length; i++) {
            if (c != strs[i].charAt(index)) {
                return false;
            }
        }
        return true;
    }



    /**
     * 分治法
     * @param strs
     * @return
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    // 分治法
    private String longestCommonPrefix(String[] strs, int left, int right) {
        // 何时结束？== 2？
        if (left == right) {
            return strs[left];
        }

        int mid = (left + right) / 2;
        String leftLen = longestCommonPrefix(strs, left, mid);
        String rightLen = longestCommonPrefix(strs, mid + 1, right);
        return longestCommonPrefix(leftLen, rightLen);
    }

    // 比较两个字符串的公共前缀
    private String longestCommonPrefix(String str1, String str2) {
        int minLen = Math.min(str1.length(), str2.length());
        int i = 0;
        for (; i < minLen; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                break;
            }
        }
        return str1.substring(0, i);
    }



}
