package leetcode.top100;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * No47_76_MinWindow
 *
 * <p>
 *     76. 最小覆盖子串
 *     给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * </p>
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 13, 2025</pre>
 */
public class No47_76_MinWindow {

    /**
     * 思路：滑动窗口
     *
     * 定义左右两个指针，右指针往右遍历，如果当前左右指针构成的区间没有覆盖目标字符串，
     * 则右指针往右递增，直至满足构成的区间覆盖目标字符串。此时，开始压缩左指针，直至
     * 找到不能覆盖目标字符串为止，在这个过程中记录最小覆盖子串的起始位和长度。
     * （当然记录起始位和结束位的索引位也可以，因为结果是需要返回最小覆盖子串本身）
     *
     * 其次，再次递增右指针，直至再次满足区间覆盖目标字符串，接着压缩左指针，重复这个过程，找到最小的子串，边界条件为右指针到达边界。
     *
     * 问题1：如果判断当前左右指针构成的滑动窗口覆盖了目标子字符串
     * a1：记录目标子字符串每个字符出现的次数，通过Map记录，每次比较遍历这个Map，判断是否每个字符的个数大于等于目标子串。
     * a2：解法一的时间复杂度为O(n)，其中n为目标字符串的长度。可以使用一个统计变量，
     *  1）在这个遍历的过程中统计满足要求的字符个数，或者
     *  2）统计目标字符出现的长度。
     *  通过判断这个变量与目标字符个数，或是与目标字符串长度是否相等来判断是否覆盖目标字符串。
     *
     *
     * 问题2：为什么按这个思路遍历能够覆盖全部情况，能找到最小覆盖子串？
     *
     *
     * 1）写代码前，首先明确思路，明确操作步骤，确定循环终止条件。想清楚了再写。明确每一步要做什么了，代码写起来就简单了。
     *    否则你很容易写了前面忘了后面，写复杂的题目，都需要先把思路写出来，养成这个习惯。
     * 2）写复杂代码可能会写错，所以需要调试，把写错的地方改过来。使用条件表达式来调试，直接跳到目标代码行。
     */
    public String minWindow(String s, String t) {

        // 0. 基本校验，提前结束
        if (s == null || s.isEmpty() || t == null || t.isEmpty() || s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> targetMap = new HashMap<>();
        Map<Character, Integer> windowMap = new HashMap<>();

        // 1. 首先把t的每个字符出现的次数统计起来
        for (int i = 0; i < t.length(); i++) {
            targetMap.put(t.charAt(i), targetMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        // 2. 定义左右指针，统计目标字符字符出现的次数
        int L = 0, R = 0, count = 0, ansStart = 0, minLen = s.length() + 1;
        while (R < s.length()) {
            char c = s.charAt(R);
            if (targetMap.containsKey(c) && windowMap.getOrDefault(c, 0) < targetMap.get(c)) {
                count++;
            }

            // 统计窗口中的字符个数
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);

            // 3. 判断是否覆盖了目标字符串 如果覆盖了新建一个循环压缩左指针 并且更新最小覆盖字符的起始位和长度
            while (count == t.length()) {
                if (R - L + 1 < minLen) {
                    ansStart = L;
                    minLen = R - L + 1;
                }

                // 在这一步 字符c出现在目标字符串中 但字符c的个数在窗口的次数可能还大于在目标字符串中的次数
                // 此时少一个字符并不会导致窗口无法覆盖字符串t，所以只有c在窗口出现的次数等于目标字符串中的次数时，才进行减一。
                char leftChar = s.charAt(L);
                if (targetMap.containsKey(leftChar) && Objects.equals(windowMap.getOrDefault(leftChar, 0), targetMap.get(leftChar))) {
                    count--;
                }

                windowMap.put(leftChar, windowMap.getOrDefault(leftChar, 0) - 1);
                L += 1;
            }

            R += 1;
        }

        // 4. 重复2、3步骤，直至右指针遍历到字符串s的结尾

        // 5. 判断最小字符串的长度，返回最小子串
        return minLen == s.length() + 1 ? "" : s.substring(ansStart, ansStart + minLen);

    }

    // 20250912
    public String minWindow2(String s, String t) {
        // 统计t字符串中各字符出现的次数
        Map<Character, Integer> windowMap = new HashMap<>();  // 动态扣减map
        Map<Character, Integer> targetMap = new HashMap<>(); // 需要这样一个map来记录原始的每个字符的个数

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        // 定义最小字符串集合 及最小长度 left right指针
        int count = t.length();
        int left = 0;
        int right = 0;

        String result = "";
        int minLen = Integer.MAX_VALUE;

        while (right < s.length()) {
            char c = s.charAt(right);
            if (targetMap.containsKey(c) && windowMap.getOrDefault(c, 0) < targetMap.get(c)) {
                count--;
            }
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
            right++; // 已经扣完了直接下一轮

            // 否则往左边压缩left 直至不再覆盖
            while (count == 0) {
                if (right - left < minLen) { // 更新结果长度
                    result = s.substring(left, right);
                    minLen = right - left;
                }
                // 判断left位置字符是否在字符串t内
                char leftChar = s.charAt(left);
                if (targetMap.containsKey(leftChar) && windowMap.get(leftChar).intValue() == targetMap.get(leftChar).intValue()) { // bug1 leftChar写成left bug2 忽略了Integer不能使用==比较
                    count++; // bug2
                }
                windowMap.put(leftChar, windowMap.get(leftChar) - 1);
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        No47_76_MinWindow obj = new No47_76_MinWindow();
        String ans = obj.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(ans);
    }

}

