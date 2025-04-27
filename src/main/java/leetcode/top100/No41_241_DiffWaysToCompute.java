package leetcode.top100;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * No41_241_DiffWaysToCompute
 * <p>
 *     241. 为运算表达式设计优先级
 * </p>
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 27, 2025</pre>
 */
public class No41_241_DiffWaysToCompute {


    // 第一种思路，暴力递归所有的可能性
    // 分治的思想，每次都按照不同的符号把原字符分割成两部分，两部分的的全部结果两两相操作就是原字符的全部结果。
    // 如expression = "2*3-4*5"，第一次按照*分割成 "2"和“3-4*5”，可以发现“3-4*5”和原问题一样，都是求它的所有可能结果
    // 求出“3-4*5”的全部结果，再用2和这全部结果相乘就是整个表达式的全部结果。
    // 就是不停的拆分成子问题（相同算法不同规模），解决全部子问题，问题本身就解决了。

    // 可以由递归从顶到底实现的，就一定能够使用动态规划从底到上来实现。因为动态规划本质就是迭代版的递归。
    // 明白这一点，就可以不用刻意去追求动态规划，你把递归想明白了，动态规划也明白了。
    public List<Integer> diffWaysToCompute1(String expression) {
        // expression = "2*3-4*5"
        List<Integer> list = new ArrayList<>();
        int len = expression.length();
        int i = 0;
        for (; i < len; i++) {
            if (!Character.isDigit(expression.charAt(i))) {
                break;
            }
        }
        if (i == len) {
            list.add(Integer.parseInt(expression));
            return list;
        }

        for (int start = 0; start < len; start++) {
            if (!Character.isDigit(expression.charAt(start))) {
                List<Integer> left = diffWaysToCompute1(expression.substring(0, start));
                List<Integer> right = diffWaysToCompute1(expression.substring(start + 1));
                char op = expression.charAt(start);
                for (int j : left) {
                    for (int k : right) {
                        if (op == '+') {
                            list.add(j + k);
                        } else if (op == '-') {
                            list.add(j - k);
                        } else if (op == '*') {
                            list.add(j * k);
                        }
                    }
                }
            }
        }
        return list;
    }


    // 动态规划解法
    // 解法一会出现重复求解的情况，只要是递归可以解答的，动态规划就一定可以，也就是我们从底至顶来求解原问题本身。
    // 如原问题expression = "2*3-4*5"，也就是我们要求解[2]和[3-4*5]，假如求解[2*3]和[4*5]，其实就是求这个范围的解
    // 那么，我们就可以定义一个二维数组，dp[i][j]表示以第i个字符和第j个字符构成的表达式的全部结果组合。
    // 自然而然，你会想到先把长度为1的表达式结果求出来，再把长度为3的表达式结果求出来，以此类推，转移公式就如下：
    // dp[i][j] = dp[i][k] (op) dp[k][j].
    // 注意：
    // 1）需要先对数组进行预处理，比如"23*45+10-20"的字符串，把每个操作数和操作符分别作为一个元素。
    // 2）包含重复结果，要的是不去重的结果。


    // 1）for循环和while循环的区别就是for循环多了初始值和步进，while是没有的，你可以根据是否需要初始值和步进来决定使用哪个
    // 2）编码也是一样，先想好再写，不然你有时改来改去，寸步难行。
    public List<Integer> diffWaysToCompute(String expression) {

        int len = expression.length();
        List<String> list = new ArrayList<>();

        // 1. 数组预处理
        int left = 0, right = 0;
        while (right < len) {
            if (!Character.isDigit(expression.charAt(right))) {
                // 添加数字
                list.add(expression.substring(left, right));
                // 添加符号
                list.add(expression.charAt(right) + "");
                left = right + 1;
            }
            right++;
        }
        // 把最后一段添加到集合
        list.add(expression.substring(left));

        // 2. 分步长求解
        int size = list.size();
        List<Integer>[][] dp = new List[size][size];

        // 初始化
        for (int i = 0; i < list.size(); i += 2) {
            List<Integer> l = new ArrayList<>();
            l.add(Integer.parseInt(list.get(i)));
            dp[i][i] = l;
        }

        // 把这个填表一步步拆分，降维，是一种强大的能力。
        // 区间长度，由于需要初始值，也需要步进，直接使用for循环
        for (int i = 3; i <= list.size(); i += 2) {
            for (int j = 0; j + i - 1 < list.size(); j += 2) {
                int l = j;
                int r = i + j - 1;
                List<Integer> ans = new ArrayList<>();
                for (int k = l + 1; k < r; k += 2) {
                    // 每次想名字都是个问题
                    List<Integer> part1 = dp[l][k - 1];
                    List<Integer> part2 = dp[k + 1][r];
                    String op = list.get(k);
                    for (int num1 : part1) {
                        for (int num2 : part2) {
                            if (Objects.equals(op, "+")) {
                                ans.add(num1 + num2);
                            } else if (Objects.equals(op, "-")) {
                                ans.add(num1 - num2);
                            } else if (Objects.equals(op, "*")) {
                                ans.add(num1 * num2);
                            }
                        }
                    }
                }
                dp[l][r] = ans;
            }
        }

        // 返回结果
        return dp[0][size - 1];
    }

    public static void main(String[] args) {
        No41_241_DiffWaysToCompute obj = new No41_241_DiffWaysToCompute();
        List<Integer> integers = obj.diffWaysToCompute("23*45+10-20");
        System.out.println(integers);
    }


}
