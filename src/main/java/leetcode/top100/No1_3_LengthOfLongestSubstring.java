package leetcode.top100;


import java.util.HashSet;
import java.util.Set;

/**
 * No1_3_LengthOfLongestSubstring
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>03月 04, 2025</pre>
 */
public class No1_3_LengthOfLongestSubstring {


    public int lengthOfLongestSubstring(String s) {
        // 比较字符串是否为空 可以使用isEmpty()方法 该方法只比较字符长度是否为0
        if (s.isEmpty()) {
            return 0;
        }
        int len = s.length();
        Set<String> set = new HashSet<>();
        int l = 0;
        int r = 0;
        // “abcabccb”
        int maxLen = 0;
        // 貌似不需要添加l < r的限制，因为l不可能超过r, 最多相等，一旦相等，set元素为空，r必然能否往后移动，l只能一直追赶者r，而无法超越。
        int curLen = 0;
        for (; l < len; l++) {
            while (r < len) {
                String c = s.charAt(r) + "";
                if (!set.contains(c)) {
                    // 不重复
                    set.add(c);
                    curLen++;
                    // 统计最大长度
                    maxLen = Math.max(maxLen, curLen);
                    // 右指针向后移动
                    r++;
                } else {
                    // 出现重复字符串
                    // 当前长度重置为减一
                    curLen = curLen - 1;
                    // 清除L指针的字符
                    set.remove(s.charAt(l) + "");
                    break;
                }
            }
        }
        return maxLen;
    }

    /**
     * 本质上这道题是一个双层循环，但由于第二层循环需要保留遍历的位置，那么就是for+while循环的结构
     * @param s 2025.4.9 23:29
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int l = 0, r = 0;
        int len = s.length();
        int maxLen = 0;
        int curLen = 0;
        Set<Character> set = new HashSet<>();
        for (; l < len; l++) {
            // 这里使用while是不想每次r节点都从新的节点开始
            while (r < len) {
                if (!set.contains(s.charAt(r))) {
                    set.add(s.charAt(r));
                    curLen++;
                    maxLen = Math.max(maxLen, curLen);
                    r++;
                } else {
                    curLen--;
                    set.remove(s.charAt(r));
                    break;
                }
            }
        }
        return maxLen;
    }


        // 1. 你不能等重复的时候才更新最大长度 而是每次长度增加的时候更新最大长度
    // 2. 先把简单的版本写出来 再写复杂的版本 不要想着一步登天 否则你想迈出一步都难 要学会拆解目标

    public static void main(String[] args) {
        String s = " ";
        No1_3_LengthOfLongestSubstring instance = new No1_3_LengthOfLongestSubstring();
        int result = instance.lengthOfLongestSubstring(s);
        System.out.println(result);
    }



}
