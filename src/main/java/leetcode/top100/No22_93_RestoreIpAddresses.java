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

    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12) {
            return new ArrayList();
        }
        backtrack(s, "", 0, 0);
        return result;
    }

    private void backtrack(String s, String cur, int start, int level) {
        if (level == 4 && start == s.length()) {
            cur = cur.substring(0, cur.length() - 1);
            result.add(cur);
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (s.charAt(start) == '0') {
                if (isValidIp(s.length() - start - 1, level + 1)) {
                    break;
                }
                backtrack(s, cur + "0.", start + 1, level + 1);
                return;
            }
            if (isValidIp(s.length() - i - 1, level + 1)) {
                continue;
            }
            String tmp = s.substring(start, i + 1);
            int intTmp = Integer.parseInt(tmp);
            if (intTmp > 255) {
                break;
            }
            backtrack(s, cur + tmp + ".", i + 1, level + 1);
        }
    }

    private boolean isValidIp(int length, int level) {
        return length > (4 - level) * 3;
    }



    public static void main(String[] args) {

        No22_93_RestoreIpAddresses obj = new No22_93_RestoreIpAddresses();
        List<String> strings = obj.restoreIpAddresses("0000");
        System.out.println(strings);

    }
}
