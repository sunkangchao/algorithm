package leetcode.top100;

/**
 * No35_165_CompareVersion
 *
 * <p>
 *     165. 比较版本号
 * </p>
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 24, 2025</pre>
 */
public class No35_165_CompareVersion {


    public int compareVersion(String version1, String version2) {

        int i = 0, j = 0;
        while (i < version1.length() || j < version2.length()) {
            // 去除前导0
            int num1 = 0;
            while (i < version1.length() && version1.charAt(i) == '0') {
                i++;
            }
            while (i < version1.length() && version1.charAt(i) != '.') {
                num1 = num1 * 10 + (version1.charAt(i) - '0');
                i++;
            }

            // 去除前导0
            int num2 = 0;
            while (j < version2.length() && version2.charAt(j) == '0') {
                j++;
            }
            while (j < version2.length() && version2.charAt(j) != '.') {
                num2 = num2 * 10 + (version2.charAt(j) - '0');
                j++;
            }

            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }

            // 相等则继续比较下一个版本号
            i++;
            j++;
        }
        return 0;
    }

    public static void main(String[] args) {
        No35_165_CompareVersion obj = new No35_165_CompareVersion();
        int r = obj.compareVersion("1.2", "1.10");
        System.out.println(r);
    }


//    private int getNextVersion(String s, int index) {
//        int version = 0;
//        while (index < s.length() && s.charAt(index))
//    }

}
