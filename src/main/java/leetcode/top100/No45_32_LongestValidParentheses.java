package leetcode.top100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * No45_32_LongestValidParentheses
 *
 * <p>
 *     32. 最长有效括号
 * </p>
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 08, 2025</pre>
 */
public class No45_32_LongestValidParentheses {


    /**
     * 解法一：双向遍历
     * 思路：往每个方向遍历，一旦碰到无效字符，则重置i,j指针
     * 其中，i指针用来记录有效扣号的开始，j指针用来记录当前遍历到的索引位
     * 且使用一个额外的变量来记录左右括号的差值，一旦差值等于0，则更新最大长度
     *
     * 最关键是要想明白，为什么从i开始遍历到j，到j无效之后，下一次遍历是基于j + 1开始的。
     * 正常的思维你很容易想从i + 1开始，此时复杂度就是O(n^2)。
     *
     * a: 假设i到j是有效的字符串，如果你从i+1开始，显然少了一个左括号，那下一个要么是右括号，那此时立马无效，从i+2又开始，最终到j之前肯定又提前无效了。
     * 下一个要么是左括号，你少了一个左括号，那么你的有效括号长度肯定也是小于j的，也就是i到j时已经把区间的最大长度遍历出来的
     * 这个时候再从i+1开始是没有意义的。对于求解最大长度来说，这样遍历是没有意义的。
     *
     *
     * @param s
     * @return
     */
    public int longestValidParentheses1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int i = 0, j = 0, k = 0;
        int max = 0;

        // 左往右遍历
        while (j < s.length()) {
            if (s.charAt(j) == '(') {
                k++;
            } else if (s.charAt(j) == ')') {
                k--;
                if (k == 0) {
                    max = Math.max(max, j - i + 1);
                }
                if (k < 0) {
                    i = j + 1;
                    k = 0;
                }
            }
            j += 1;
        }

        i = s.length() - 1;
        j = s.length() - 1;
        k = 0;

        // 右往左遍历
        while (j >= 0) {
            if (s.charAt(j) == ')') {
                k++;
            } else if (s.charAt(j) == '(') {
                k--;
                if (k == 0) {
                    max = Math.max(max, i - j + 1);
                }
                if (k < 0) {
                    i = j - 1;
                    k = 0;
                }
            }
            j -= 1;
        }
        return max;
    }


    /**
     * 解法二：动态规划
     *
     * 思路：很容易可以想到，最大的有效括号都是基于长度更小的长度+2得来的，也就是说
     * 可以由小的有效括号往大的有效扣号转移，只需要做一些判断。
     *
     * 只有两种情况，要么是小的有效括号右边拼（）,要么就是小的有效括号最左和左右分别拼(和)
     * 想清楚这一点，转移公式就可以讨论这两种情况
     *
     * 定义dp[i]为以第i个元素结尾的最大有效括号长度
     * 且 dp[i] = dp[i - 2] + 2, 如果chars[i] == ')' 且chars[i - 1] == '(';
     *
     * dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2]
     *
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        if (s.length() == 0) {
            return 0;
        }

        int[] dp = new int[s.length()];
        int max = 0;

        dp[0] = 0;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                continue;
            }

            if (s.charAt(i - 1) == '(') {
                dp[i] = i >= 2 ? dp[i - 2] + 2 : 2;
            } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                dp[i] = dp[i - 1] + 2 + ((i - dp[i - 1] - 2) >= 0 ? dp[i - dp[i - 1] - 2] : 0);
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }


    /**
     * 解法三：基于栈实现
     * 思路：左扣号压入栈当中，碰到右边扣号就出栈，并且把当前元素和栈顶元素取差值，更新当前最大的有效括号
     * 栈中存储的是左括号的索引位，并且记录上一个未被匹配的右括号索引（用来记录有效括号的开始）
     * 重点：栈中最多只会存储最新的未被匹配的右括号，只会存储一个，因为存储多个的话，你无法判断后面的")"是否能匹配
     *
     * 注意：在起始时需要压入-1，来表示它上一个无效右扣号所在的位置，避免类似()()()()时，无法判断它的有效长度
     * 也就是栈当中始终要有一个无效右扣号的起始位索引。
     *
     * 栈解法是每一次弹出都会更新一次最大值的，所以不会出现“双向遍历”解法漏掉"((((()"这种解的问题
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int max = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }
            if (s.charAt(i) == ')') {
                // 每次先弹一个出来 如果为空 说明当前右括号没匹配上 因为至少会有无效的右括号兜底
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        No45_32_LongestValidParentheses obj = new No45_32_LongestValidParentheses();
        int i = obj.longestValidParentheses("()(())");
        System.out.println(i);
    }

}
