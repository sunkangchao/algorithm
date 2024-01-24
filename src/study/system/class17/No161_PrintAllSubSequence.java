package study.system.class17;

/**
 * No161_PrintAllSubSequence
 *
 * 打印给的字符串的全部子序列
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 24, 2024</pre>
 */
public class No161_PrintAllSubSequence {


    public void printAllSubSequence(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        f(str.toCharArray(), 0, "");
    }

    private void f(char[] chars, int index, String path) {
        if (index == chars.length) {
            System.out.println(new String(path));
            return;
        }
        f(chars, index + 1, path);
        f(chars, index + 1, path + chars[index]);
    }

    public static void main(String[] args) {
        // 学会慢下来 动起手来 沉下心复习
        No161_PrintAllSubSequence instance = new No161_PrintAllSubSequence();
        String str = "aac";
        instance.printAllSubSequence(str);
    }



}
