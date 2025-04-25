package leetcode.top100;

/**
 * No40_233_NumberOfDigitOne
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 25, 2025</pre>
 */
public class No40_233_NumberOfDigitOne {


    /**
     * 思路：通过求得每一位上“1”出现的个数，累加整数n每一个位上出现的1个数就是结果
     * 公式如下，确定好上界 + 下界
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        int mulk = 1;
        int ans = 0;
        while (n >= mulk) {
            // 注意运算符的优先级
            ans += (n / (mulk * 10) * mulk) + Math.min(Math.max(n % (mulk * 10)  - mulk + 1, 0), mulk);
            mulk *= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        No40_233_NumberOfDigitOne obj = new No40_233_NumberOfDigitOne();
        int ans = obj.countDigitOne(13);
        System.out.println(ans);
    }


}
