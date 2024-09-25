package examination;

/**
 * 最长公共前缀
 *
 * @author SunKangChao
 * @date 2021/7/31 00:09
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        // write code here
        if (strs == null || strs.length == 0) {
            return "";
        }

        int length = (int)1e9;
        for (String str : strs) {
           length = Math.min(length,str.length());
        }

        StringBuilder result = new StringBuilder();
        ok:
        for (int i = 0; i < length; i++) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[0].charAt(i) != strs[j].charAt(i)) {
                    break ok;
                }
            }
            result.insert(i, strs[0].charAt(i));
        }
        return result.toString();

    }

    public static void main(String[] args) {
        LongestCommonPrefix instance = new LongestCommonPrefix();
        String result = instance.longestCommonPrefix(new String[]{"a", "b"});
        System.out.println(result);
    }
}
