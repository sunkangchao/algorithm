package study.newhand;

/**
 * 位运算实现加减乘除
 *
 * @author sunkangchao
 * @date 2022/7/22 16:19
 */
public class Code27_CalculateByBit {


    public static int add(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b + carry;
            b = carry;
        }
        return a;
    }

    public static int sub(int a, int b) {
        return add(a, ~b + 1);
    }

    public static int multi(int a, int b) {
        int sum = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                sum = add(sum, a);
            }
            a <<= 1;
            b >>= 1;
        }
        return sum;
    }

    public static int divide(int a, int b) {
        int ans = 0;
        // 取绝对值
        a = (~a + 1) == a ? a : ~a + 1;
        b = (~b + 1) == b ? b : ~b + 1;

        for (int i = 30; i >=0; i--) {
            if ((a >> i) >= b) {
                ans |= 1 << i;
                a = sub(a, b << i);
            }
            if (a == 0) {
                break;
            }
        }
        // 加上符号处理
        ans = ((a > 0) ^ (b > 0)) ? -ans : ans;
        return ans;
    }

    public static int divideCheckBase(int a, int b) {
        // 因为Integer.MIN_VALUE取相反值等于它本身，所以取绝对值再相除并不成立，但又不向相减一样会越界
        // 1. a = min, b = min;
        // 2. a != min, b = min;
        // 3. a = min, b != min;
        // 4. a != min, b != min;
        int min = Integer.MIN_VALUE;
        if (a == min && b == min) {
            return 1;
        } else if (a != min && b == min) {
            // 因为如果分子小于分母，它们相除的结果向下取整，必然是等于0；
            // 那你得考虑a为正数的情况
            return 0;
        } else if (a == min) {
            int c = divide(add(a, 1), b);
            return add(c, divide(sub(a, multi(c, b)), b));
        } else {
            return divide(a, b);
        }


    }



}
