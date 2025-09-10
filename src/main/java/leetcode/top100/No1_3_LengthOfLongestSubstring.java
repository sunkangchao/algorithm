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

    // 其实这个就是版本1的改进版，把外层的循环去掉，只需要去遍历右层循环即可
    public int lengthOfLongestSubstring3(String s) {

        // 定义左右指针，以及set维持不重复字符串，以及最大长度
        int left = 0, right = 0;
        int maxLen = 0, curLen = 0;
        Set<Character> set = new HashSet<>();
        
        // 右指针到达边界就可以结束了
        while (right < s.length()) {
            // 判断右指针的字符是否在set中
            if (set.add(s.charAt(right))) {
                curLen++;
                maxLen = Math.max(maxLen, curLen);
                right++;
            } else {
                curLen--;
                set.remove(s.charAt(left));
                left++;
            }
        }

        return maxLen;
    }

    // abcabccb pwwkew
    // 卡在set.remove方法，你应该移除最左left指针处的值 而不是当前right指向的值
    // 另外，由于存在left和right指针，所以并不需要再维护一个cur长度
    // 2025.9.10 编写10分钟之内 调试13分钟 23分钟ak
    public int lengthOfLongestSubstring4(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 定义无重复字符串集合 最长长度 左右指针
        Set<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        int maxLen = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            if (set.add(c)) {
                right++;
                maxLen = Math.max(maxLen, right - left);
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }

        return maxLen;
    }




        // 1. 你不能等重复的时候才更新最大长度 而是每次长度增加的时候更新最大长度
    // 2. 先把简单的版本写出来 再写复杂的版本 不要想着一步登天 否则你想迈出一步都难 要学会拆解目标

    public static void main(String[] args) {
        String s = "pwwkew";
        No1_3_LengthOfLongestSubstring instance = new No1_3_LengthOfLongestSubstring();
        int result = instance.lengthOfLongestSubstring4(s);
        System.out.println(result);
    }



}
