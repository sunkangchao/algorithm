package leetcode.top100;

/**
 * No36_69_MySqrt
 *
 * <p>
 *     69. x 的平方根
 * </p>
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 24, 2025</pre>
 */
public class No36_69_MySqrt {


    public int mySqrt1(int x) {
        if (x == 0){
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        if (x == 2) {
            return 1;
        }
        if (x == 3) {
            return 1;
        }

        int mid = x / 2 + 1;
        for (int i = 1; i <= mid; i++) {
            long total = (long) i * i;
            if (total == x) {
                return i;
            } else if (total > x) {
                return i - 1;
            }
        }
        return -1;
    }


    public int mySqrt2(int x) {
        if (x == 0) {
            return 0;
        }
        int index = 0;
        for (int i = 1; i <= x / i ; i++) {
            index = i;
        }
        return index;
    }

    // 技巧：使用除法来代替乘法 就可以避免整型溢出了 但是除法也带来了结果除不尽而向下取整，从而导致区间选错的风险
    // 但这道题不存在这个问题，可以直接使用除法。

    // 这道题掌握二分法解法就可 当前不过深探讨数学问题

    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }

        int left = 1, right = x;
        int index = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (mid > (x / mid)) {
                right = mid - 1;
            } else {
                index = mid;
                left = mid + 1;
            }
        }
        return index;
    }



    public static void main(String[] args) {
        No36_69_MySqrt obj = new No36_69_MySqrt();
        int i = obj.mySqrt2(2147483647);
        System.out.println(i);
    }


}
