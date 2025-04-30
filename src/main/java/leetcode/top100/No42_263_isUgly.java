package leetcode.top100;

/**
 * No42_263_isUgly
 *
 * <p>
 * 263. 丑数
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 30, 2025</pre>
 */
public class No42_263_isUgly {


    /**
     * 思路就是循环除以2，3，5，如果能整除，则除以该数，直到不能整除，判断是否为1
     * 注意边界，小于等于0的数直接返回false
     * @param n
     * @return
     */
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        int num = n;
        while (num > 1) {
            if (num % 2 == 0) {
                num /= 2;
                continue;
            }
            if (num % 3 == 0) {
                num /= 3;
                continue;
            }
            if (num % 5 == 0) {
                num /= 5;
                continue;
            }
            return false;
        }
        return true;
    }

}
