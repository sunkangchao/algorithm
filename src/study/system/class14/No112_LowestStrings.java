package study.system.class14;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 *
 * 101. 给定一个由字符串组成的数组strs，必须把所有的字符串拼接起来，返回所有可能的拼接结果中，字典序最小的结果。
 * 字典序严格定义：如果长度不一样的字符串，那就往左对齐，不足长度的部分补最小的ascii码，再比大小。
 * No112_LowestStrings
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 18, 2024</pre>
 */
public class No112_LowestStrings {


    public String getLowestStrings(String[] strings){
        if (strings == null || strings.length == 0) {
            return null;
        }
        Arrays.sort(strings, new StringComparator());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            sb.append(strings[i]);
        }
        return sb.toString();
    }

    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo((o2 + o1));
        }
    }

    /**
     * 写一个全排序的最小的代码
     * @param string
     * @return
     */
    public String getLowestString2(String[] string) {
        // 看了很多遍，不写一遍，永远都不算会
        TreeSet<String> strings = listAllString(string);
        return strings.first();
    }

    // 递归重复的子问题 是写递归
    private TreeSet<String> listAllString(String[] string) {
        if (string.length == 0) {
            TreeSet<String> set = new TreeSet<>();
            set.add("");
            return set;
        }
        TreeSet<String> result = new TreeSet<>();
        for (int i = 0; i < string.length; i++) {
            String s = string[i];
            String[] newStrings = removeTargetIndex(string, i);
            TreeSet<String> strings = listAllString(newStrings);
            for (String subString : strings) {
                result.add(s + subString);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        No112_LowestStrings instance = new No112_LowestStrings();
        String[] strings = new String[]{"a", "b", "c"};
        TreeSet<String> treeSet = instance.listAllString(strings);
        System.out.println(treeSet);
    }


    private String[] removeTargetIndex(String[] strings, int index) {
        String[] result = new String[strings.length - 1];
        int j = 0;
        for (int i = 0; i < strings.length; i++) {
            if (i != index) {
                result[j++] = strings[i];
            }
        }
        return result;
    }


}
