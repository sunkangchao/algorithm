package leetcode.top100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * No17_20_IsValid
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>03月 28, 2025</pre>
 */
public class No17_20_IsValid {


    public boolean isValid(String s) {

        char[] chars = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();


        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(' || chars[i] == '[' || chars[i] == '{') {
                stack.push(chars[i]);
            } else {
                char c = stack.pop();
                if (chars[i] == ')' && c != '(') {
                    return false;
                }
                if (chars[i] == ']' && c != '[') {
                    return false;
                }
                if (chars[i] == '}' && c != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


}
