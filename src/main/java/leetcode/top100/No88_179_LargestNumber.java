package leetcode.top100;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * No88_179_LargestNumber
 *
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 *
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 * 示例 1：
 *
 * 输入：nums = [10,2]
 * 输出："210"
 * 示例 2：
 *
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>07月 31, 2025</pre>
 */
public class No88_179_LargestNumber {


    /**
     * 思路：本题的核心难点在于如何定义一个比较器，来使得它的结果是按照
     * 两个字符串的高位往低位依次比较
     *
     * 1）相加，两者字符串相加，让它们处理相同位数，即可
     * 2）相乘，让它们分别乘以它的10的次方，变成相同位数，再比较。
     *
     * 本质上两者都是把，比如34, 35，转化成3435 和 3534 来比较
     *
     * 注意：第二种方法本质上是先求精准的位数，但是需要注意会越界。
     *
     * @param nums
     * @return
     */
    public String largestNumber1(int[] nums) {

        // 转化成字符串数组
        if (nums == null || nums.length == 0) {
            return "";
        }

        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        // 自定义排序函数
        Arrays.sort(strs, (a, b) -> {
            String s1 = a + b;
            String s2 = b + a;
            return s2.compareTo(s1);
        });

        // 补充校验
        if (strs[0].equals("0")) {
            return "0";
        }

        // 拼接结果
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }



    public String largestNumber(int[] nums) {

        // 转化成字符串数组
        if (nums == null || nums.length == 0) {
            return "";
        }

        Integer[] numsArr = Arrays.stream(nums)
                .boxed().toArray(Integer[]::new);

        // 自定义排序函数
        Arrays.sort(numsArr, (a, b) -> {
            // 求得a,b的位数
            long ax = 10, bx = 10;
            while (a >= ax) {
                ax *= 10;
            }

            while (b >= bx) {
                bx *= 10;
            }

            long ay = a * bx + b;
            long by = b * ax + a;

            // 相同位数再比较 按照从大到小排序
//            return by > ay ? 1 : (by == ay) ? 0 : -1;
            return Long.compare(by, ay);
        });

        // 补充校验
        if (nums[0] == 0) {
            return "0";
        }

        // 拼接结果
        StringBuilder sb = new StringBuilder();
        for (Integer str : numsArr) {
            sb.append(str);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        No88_179_LargestNumber obj = new No88_179_LargestNumber();
        int[] arr = {0,9,8,7,6,5,4,3,2,1};
        String s = obj.largestNumber(arr);
        System.out.println(s);

    }




}
