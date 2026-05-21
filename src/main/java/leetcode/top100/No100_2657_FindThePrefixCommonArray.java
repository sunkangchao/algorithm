package leetcode.top100;

/**
 * No100_2657_FindThePrefixCommonArray
 *
 * 2657. 找到两个数组的前缀公共数组
 *
 * 给你两个下标从 0 开始长度为 n 的整数排列 A 和 B 。
 *
 * A 和 B 的 前缀公共数组 定义为数组 C ，其中 C[i] 是数组 A 和 B 到下标为 i 之前公共元素的数目。
 *
 * 请你返回 A 和 B 的 前缀公共数组 。
 *
 * 如果一个长度为 n 的数组包含 1 到 n 的元素恰好一次，我们称这个数组是一个长度为 n 的 排列
 *
 *
 * 提示：
 *
 * 1 <= A.length == B.length == n <= 50
 * 1 <= A[i], B[i] <= n
 * 题目保证 A 和 B 两个数组都是 n 个元素的排列。
 *
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 21, 2026</pre>
 */
public class No100_2657_FindThePrefixCommonArray {

    // 解法一：不使用动态规划 而是每组单独计算 使用总flag来持续统计
    // 解法二：实际可以使用动态规划 ans[i] = ans[i - 1] + flag*, 需要计算对于当前索引位i的A[i]和B[i]是否为新元素
    public int[] findThePrefixCommonArray1(int[] A, int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return null;
        }

        int[] C = new int[51];
        int len = A.length;
        int[] ans = new int[len];
        int flag = 0;

        for (int i = 0; i < len; i++) {
            if (C[A[i]] == 1) { // 说明出现过
                flag++;
            } else {
                C[A[i]] = 1;
            }

            if (C[B[i]] == 1) {
                flag++;
            } else {
                C[B[i]] = 1;
            }

            // 更新结果
            ans[i] = flag;
        }

        return ans;
    }

    // 位运算
    public int[] findThePrefixCommonArray(int[] A, int[] B) {

        long p = 0, q = 0; // 因为限定了50位 需要long 64才能包含
        int[] ans = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            p |= 1L << A[i]; // todo 注意1必须要声明成1L，long类型
            q |= 1L << B[i];

            ans[i] = Long.bitCount(p & q); // 相与后计算1的个数
        }

        return ans;
    }


}
