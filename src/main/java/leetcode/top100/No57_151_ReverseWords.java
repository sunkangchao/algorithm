package leetcode.top100;

/**
 * No57_151_ReverseWords
 *
 * <p/>
 * 151. 反转字符串中的单词
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 *
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 *
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 *
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 * 进阶：如果字符串在你使用的编程语言中是一种可变数据类型，请尝试使用 O(1) 额外空间复杂度的 原地 解法。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 22, 2025</pre>
 */
public class No57_151_ReverseWords {


    /**
     * 思路：双指针、【直接使用trim(), split(\\w+), Collections.reverse, string.join结合】
     *
     * 逆序遍历，每次剔除空格，截取字母，最后拼接到结果字符串当中
     *
     * 时间复杂度：O(n)：遍历一次
     * 空间复杂度：O(1): 结果存储不算额外空间？
     *
     * 注意：
     * 1）这道题就是锻炼你循环跳过字符的能力，很好地锻炼的一道题
     * 2）对于循环结构体的提炼也是一种锻炼
     *
     * StringBuilder是很好的处理字符串的类，可变，因此可以直接在它上面进行删减，而不用去直接截取。
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {

        int len = s.length();

        // i作为原地指针，j作为移动指针
        int i = len - 1, j = i;
        StringBuilder sb = new StringBuilder();

        while (j >= 0) {

            // 去除空格 重置i指针
            while (j >= 0 && s.charAt(j) == ' ') j--;
            i = j;

            // 遍历到字符结尾
            while (j >= 0 && s.charAt(j) != ' ') j--;

            // 截取数据 拼接到结果集当中
            String substring = s.substring(j + 1, i + 1);
            sb.append(substring + " ");
        }

        // 移除最后的空格 移除结尾全部空格
        while (sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        No57_151_ReverseWords obj = new No57_151_ReverseWords();
        String ans = obj.reverseWords("  hello world  ");
        System.out.println(ans);
    }







}
