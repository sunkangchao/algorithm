package leetcode.top100;

/**
 * @author sunkangchao
 *
 * <p>
 *     70. 爬楼梯
 * </p>
 * @since 2025/5/3 00:35
 */
public class No46_70_ClimbStairs {

    public int climbStairs(int n) {

        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int pre = 1;
        int cur = 2;

        for (int i = 2; i < n; i++) {
            int temp = cur;
            cur = pre + cur;
            pre = temp;
        }

        return cur;

    }


}
