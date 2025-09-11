package leetcode.top100;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. 复原 IP 地址
 *
 * @author sunkangchao
 * @since 2025/4/8 00:26
 */
public class No22_93_RestoreIpAddresses {


    private List<String> result = new ArrayList();

    public List<String> restoreIpAddresses1(String s) {
        if (s.length() > 12) {
            return new ArrayList();
        }
        backtrack1(s, "", 0, 0);
        return result;
    }

    private void backtrack1(String s, String cur, int start, int level) {
        if (level == 4 && start == s.length()) {
            cur = cur.substring(0, cur.length() - 1);
            result.add(cur);
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (s.charAt(start) == '0') {
                if (isInValidIp(s.length() - start, level + 1)) {
                    break;
                }
                backtrack1(s, cur + "0.", start + 1, level + 1);
                return;
            }
            if (isInValidIp(s.length() - i, level + 1)) {
                continue;
            }
            String tmp = s.substring(start, i + 1);
            int intTmp = Integer.parseInt(tmp);
            if (intTmp > 255) {
                break;
            }
            backtrack1(s, cur + tmp + ".", i + 1, level + 1);
        }
    }

    private boolean isInValidIp(int length, int level) {
        return length > (4 - level) * 3;
    }

// ------------------------以上代码忽略-------------------------------


    // 重新组织代码 回溯写法即可
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() < 4 || s.length() > 12) {
            return new ArrayList<>();
        }
        backtrack(s, 0, new StringBuilder(), 0);
        return result;
    }

    private void backtrack(String s, int start, StringBuilder sb, int level) {
        if (level == 4 && start == s.length()) {
            result.add(sb.toString());
            return;
        }
        if (level == 4) {
            return;
        }

        for (int i = start; i < s.length(); i++) {
            String temp = s.substring(start, i + 1);
            int num = Integer.parseInt(temp);
            if (num > 255) {
                break;
            }
            int len = sb.length();
            sb.append(num);
            if (level < 3) {
                sb.append(".");
            }
            backtrack(s, start + temp.length(), sb, level + 1);
            // 回溯 恢复原状
            sb.setLength(len); // 这里没有考虑把.加上去后的长度 直接恢复回start会出错的
            // 这里其实不太好理解 之所以不能用start 是前面拼接的“.”号已经让其长度发生改变了 在这里卡了很久 没看出来
            if (num == 0) {
                break;
            }
        }
    }


    public static void main(String[] args) {

        No22_93_RestoreIpAddresses obj = new No22_93_RestoreIpAddresses();
        List<String> strings = obj.restoreIpAddresses("25525511135");
        System.out.println(strings);

    }
}
