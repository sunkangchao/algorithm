package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 电话号码的字母组合
 * No17_letterCombinations
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>07月 04, 2024</pre>
 */
public class No17_letterCombinations {

    private List<List<String>> letterMap = new ArrayList<>();
    private List<String> result = new ArrayList<>();

    {
        letterMap.add(Arrays.asList("a", "b", "c"));
        letterMap.add(Arrays.asList("d", "e", "f"));
        letterMap.add(Arrays.asList("g", "h", "i"));
        letterMap.add(Arrays.asList("j", "k", "l"));
        letterMap.add(Arrays.asList("m", "n", "o"));
        letterMap.add(Arrays.asList("p", "q", "r", "s"));
        letterMap.add(Arrays.asList("t", "u", "v"));
        letterMap.add(Arrays.asList("w", "x", "y", "z"));
    }


    public List<String> letterCombinations(String digits) {

        // 电话号码的集合
        // 先存起来 怎么存？
        // Map<Integer, List<String>>?  List<List<String>> ! done

        // 增加边界条件
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }

        char[] digitsArray = digits.toCharArray();
        // 字母转数字 如何？ '1' - 48
        // 集合 vs 集合的 全排列问题
        // {a,b,c} {d,e,f}
        // 转成List<List<String>>, 递归遍历 + 回溯
        List<List<String>> list = new ArrayList<>();

        // 23
        for (int i = 0; i < digitsArray.length; i++) {
            int number = digitsArray[i] - 48;
            list.add(letterMap.get(number - 2));
        }

        // 得到list，那么进行递归，定义结果list
        backTrace(list, 0, "");
        return result;
    }

    // n表示取到第几个集合 tmp表示当前的拼接
    private void backTrace(List<List<String>> list, int n, String tmp) {
        if (n == list.size()) {
            result.add(tmp);
            return;
        }

        List<String> strings = list.get(n);
        for (String letter : strings) {
            // 拼接当前
            tmp += letter;
            backTrace(list, n + 1, tmp);
            // 回溯
            tmp = tmp.substring(0, tmp.length() - 1);
        }
    }

    // 优化点：
    // 1. 拼接可以使用StringBuffer/StringBuilder
    // 2. List可以直接Map来存储电话号码的字母 取值更清晰

    // 复杂度分析：
    // 1. 时间复杂度：O(3^m * 4^n) 全组合的种数 递归忽略时间复杂度
    // 2. 空间复杂度，哈希表可以看做常数，空间复杂度等同递归调用的最大深度，就是等于传进来多少个数字

    public static void main(String[] args) {
        No17_letterCombinations instance = new No17_letterCombinations();
        List<String> resultList = instance.letterCombinations("23");
        System.out.println(resultList);
    }



}
