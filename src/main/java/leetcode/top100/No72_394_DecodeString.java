package leetcode.top100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * No72_394_DecodeString
 *
 * 394. 字符串解码
 *
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例 1：
 *
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 *
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 *
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 *
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>06月 11, 2025</pre>
 */
public class No72_394_DecodeString {


    /**
     *
     * 问题1: 数字的值并不是0-9，它的取值范围是1-300 循环读取来解决
     * 问题2: 中括号是存在嵌套关系的 递归解决 本质上就是规模更小的子问题
     *
     * 收获：
     * 重点在于记录过程。原问题看似复杂，通过记录的方式一步步拆分成更多的小问题，然后一步步地去解决小问题，那么一个再难的题目你都可以解决。
     * @param s
     * @return
     */
    public String decodeString0(String s) {
        // 1. 遍历截取方括号中的元素 sb来拼接
        int len = s.length(), start = 0;
        StringBuilder sb = new StringBuilder();
        // 2. 每次循环，定义指针start, start < len
        while (start < len) {
            // 判断是否为数字 不是数字直接拼接
            if (!Character.isDigit(s.charAt(start))) {
                sb.append(s.charAt(start));
                start++; // todo 补充移动
                continue;
            }
            // 否则就是数字 定义指针end 找到下一个中括号结束的位置
            int end = start;
            while (end < len && Character.isDigit(s.charAt(end))) {
                end++;
            }
            // 多位整数字符串转化为整数 且重置start, end指针 直接跳到'['括号位置
            int loopNum = Integer.parseInt(s.substring(start, end));
            start = end = end + 1;

            // 定义一个变量来记录有效括号
            int count = 1;
            while (end < len) {
                if (s.charAt(end) == '[') {
                    count++;
                } else if (s.charAt(end) == ']') {
                    count--;
                }
                if (count == 0) {
                    break;
                }
                end++;
            }
            // 此时start, end的位置已确定 循环次数为
            String subStr = s.substring(start, end);
            if (subStr.contains("[")) {
                subStr = decodeString0(subStr);
            }

            // 否则直接拼接
            while (loopNum-- > 0) {
                sb.append(subStr);
            }
            // 最后重置start指针
            start = end + 1;
        }
        return sb.toString();
    }


    /**
     * 使用栈来实现
     * 比如例子：3[a2[c]]
     * 遇到数字先把数字整体解析出来，压入栈中。
     * 然后遇到左括号和字符也是压入栈中。当需要右括号']'时，依次出栈，直至出栈的元素为'['停止。然后按照此时栈顶的数字，拼接成内层解析后的字符串，
     * 即cc，然后把解析后的字符压回栈中，再往后遍历，碰到']'再继续出栈，再解析拼接。这种方法也可以解决这个括号嵌套的问题。
     *
     * 所以，抽取循环结构，哪部分是重复的结构？压入元素 + 碰到右括号弹出 + 解析 + 拼接。
     *
     * 为什么要抽取一个循环的结构，这是因为里面碰到右括号弹出这部分是个循环的操作。
     * 
     * 这里需要一个方便进行从后往前遍历的数据结构，原始栈也可以，往头部依次插入就可以。
     *
     * 判断是否为数字的两种方式：
     * 1）Character.isDigit();
     * 2) c >= '0' && c <= '9'，取等号
     *
     * 为什么使用do-while循环？
     * 因为需要先做一个操作，再去决定是否循环。而while循环是先判断，如果无法先行判断就需要使用do-while.
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {

        // 1. 定义栈结构 以及指针start 结果变量sb
        Deque<String> stack = new ArrayDeque<>();
        int start = 0, len = s.length();
        StringBuilder sb = new StringBuilder();

        // 2. 遍历整个字符串 解析和压入字符
        while (start < len) {
            if (s.charAt(start) >= '0' && s.charAt(start) <= '9') {
                int end = start;
                while (end < len && s.charAt(end) >= '0' && s.charAt(end) <= '9') {
                    end++;
                }
                // 下一个字符必然是'['
                stack.push(s.substring(start, end));
                start = end;
                continue;
            }
            // 只要不是']'，比如是普通字符或者'['，直接压入栈中
            if (s.charAt(start) != ']') {
                stack.push(s.charAt(start) + "");
            } else {
                // 此时为']'字符 需要弹出栈
                StringBuilder sb0 = new StringBuilder();
                while (!stack.peek().equals("[")) {
                    sb0.insert(0, stack.pop());
                }
                // 接着继续弹出'['
                stack.pop();

                // 并且此时栈顶必然是数字 因为每次都是先压入数字
                int loop = Integer.parseInt(stack.pop());
                StringBuilder sb1 = new StringBuilder();
                while (loop-- > 0) {
                    sb1.append(sb0);
                }

                // 最后结果压回栈中
                stack.push(sb1.toString());
            }
            start++;
        }

        // 3. 判断遇到右括号出栈操作 并且按照栈顶元素拼接 再压入栈中 
        
        // 4. 最后从底向上遍历栈结构 拼接字符串
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }

        return sb.toString();
    }



    public static void main(String[] args) {
        No72_394_DecodeString obj = new No72_394_DecodeString();
        String ans = obj.decodeString("100[leetcode]");
        System.out.println(ans);
    }


}
