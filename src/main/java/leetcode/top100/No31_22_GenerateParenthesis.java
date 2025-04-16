package leetcode.top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 22. 括号生成
 *
 * No31_22_GenerateParenthesis
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 16, 2025</pre>
 */
public class No31_22_GenerateParenthesis {


    /**
     * 暴力递归，给2n个空间，每个位置都填入左扣号/右括号，然后长度等于2n时，判断是否有效
     * @param n
     * @return
     */
    public List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesis("", 0, 2 * n, result);
        return result;
    }

    private void generateParenthesis(String cur, int pos, int n, List<String> result) {
        if (cur.length() == n) {
            if (isValid(cur)) {
                result.add(cur);
            }
            return;
        }
        generateParenthesis(cur + "(", pos + 1, n, result);
        generateParenthesis(cur + ")", pos + 1, n, result);
    }

    private boolean isValid(String str) {
        char[] chars = str.toCharArray();
        int a = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                a += 1;
            } else {
                a -= 1;
            }
            if (a < 0) {
                return false;
            }
        }
        return a == 0;
    }



    // --------------------分割线------------------------

    /**
     * 在方法一的基础上，再加一个balance值作为判断，也就是在添加的过程就判断是否有效，
     * 而不是在结束时才判断，这样可以一定程度上剪枝。在整个过程中保证有效。
     *
     * 其实本质就是把方法一的判断是否有效这个过程，融入回溯当中，动态去判断是否要添加左括号/右扣号。
     *
     * 回溯技巧：如果传入下一层的变量是一个临时变量，在递归结束时，就不会手动去回溯，可以实现自动回溯。因为我不改变原方法传入的参数。
     * 除非你改变了原方法传入的参数，你才需要手动去回溯，去撤回到原参数的样子。
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<>();
        backtrack("", 0, 0, n, result);
        return result;
    }

    private void backtrack(String cur, int open, int close, int n, List<String> result) {
        if (cur.length() == 2 * n) {
            result.add(cur);
            return;
        }

        // 左括号最多只能添加n个
        if (open < n) {
            backtrack(cur + "(", open + 1, close, n, result);
        }

        // 只有右括号小于左括号时才能添加右扣号
        if (close < open) {
            backtrack(cur + ")", open, close + 1, n, result);
        }
    }

    /**
     * 方法三，还有一种比较聪明的做法，可以优化一些常数的时间复杂度
     * 即不需要遍历整个2*n的字符数组，只需要遍历小于等于2*n。
     *
     * 我们开始先把数组全部填充上右扣号，然后在遍历过程中添加左/右括号，但要保持两个约束
     * 1）左括号的数量不能超过n
     * 2）左扣号的数量始终要大于等于右括号，只有保持这个条件，整体才是有效的。这是解答这道题的根本。
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        char[] chars = new char[2 * n];
        List<String> result = new ArrayList<>();
        Arrays.fill(chars, ')');
        dfs(chars, 0, 0, n, result);
        return result;
    }

    private void dfs(char[] chars, int left, int right, int n, List<String> result) {
        if (left == n) {
            result.add(new String(chars));
            return;
        }
        chars[left + right] = '(';
        dfs(chars, left + 1, right, n, result);

        // 恢复现场
        chars[left + right] = ')';
        if (left > right) {
            dfs(chars, left, right + 1, n, result);
        }
    }



    public static void main(String[] args) {
        No31_22_GenerateParenthesis obj = new No31_22_GenerateParenthesis();
        List<String> strings = obj.generateParenthesis(3);
        System.out.println(strings);
    }



}
