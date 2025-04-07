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
    private int maxLength;

    public List<String> restoreIpAddresses(String s) {
        maxLength = s.length();
        backtrack(s, "", 0, 0);
        return result;
    }

    private void backtrack(String remainning, String curString, int curGroup, int start) {
        if (curGroup == 4) {
            if (remainning.length() == 0) {
                // 去除最后一个逗号
                curString = curString.substring(0, curString.length() - 1);
                result.add(curString);
            }
            return;
        }

        char[] chars = remainning.toCharArray();
        int length = chars.length;
        StringBuilder remainningBuilder = new StringBuilder(remainning);
        for (int i = 0; i < length; i++) {
            if (chars[0] == '0') {
                String tmpString;
                if (curString.isEmpty()) {
                    tmpString = chars[i] + ".";
                } else {
                    tmpString = curString + chars[i] + ".";
                }
                backtrack(remainningBuilder.substring(1), tmpString, curGroup + 1, start + 1);
                break;
            } else {
                String sub = remainningBuilder.substring(0, i + 1);
                int digest = Integer.parseInt(sub);
                if (digest <= 255) {
                    // 说明是一个有效的字符
                    String tmpString;
                    if (curString.isEmpty()) {
                        tmpString = remainningBuilder.substring(0, i + 1) + ".";
                    } else {
                        tmpString = curString + remainningBuilder.substring(0, i + 1) + ".";
                    }
                    if (judgeInValidate(maxLength, start, i, curGroup)) {
                        continue;
                    }
                    backtrack(remainningBuilder.substring(i + 1), tmpString, curGroup + 1, start + i + 1);
                } else {
                    break;
                }
            }
        }
    }

    private boolean judgeInValidate(int length, int start, int i, int curGroup) {
        return  length - (start + i + 1)  > (4 - curGroup - 1) * 3;
    }


    public static void main(String[] args) {

        No22_93_RestoreIpAddresses obj = new No22_93_RestoreIpAddresses();
        List<String> strings = obj.restoreIpAddresses("101023");
        System.out.println(strings);

    }
}
