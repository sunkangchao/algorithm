package leetcode.top100;

import java.util.HashMap;
import java.util.Map;

/**
 * No79_446_NumberOfArithmeticSlices
 *
 * 446. 等差数列划分 II - 子序列
 *
 * 给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。
 *
 * 如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。
 *
 * 例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。
 * 再例如，[1, 1, 2, 5, 7] 不是等差序列。
 * 数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。
 *
 * 例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
 * 题目数据保证答案是一个 32-bit 整数。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>06月 27, 2025</pre>
 */
public class No79_446_NumberOfArithmeticSlices {


    // 动态规划
    public int numberOfArithmeticSlices(int[] nums) {

        // 1. 校验
        int len = nums.length;
        if (len < 3) {
            return 0;
        }

        // 2. 定义Map数组 结果变量 长度变量
        Map<Long,Integer>[] arr = new Map[len];
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new HashMap<>();
        }

        // 3. 双层遍历数组 获取公差 并且统计公差 [它的子序列个数 = 前子序列个数 + 1]
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                long d = ((long) nums[i]) - nums[j]; // 需要使用long类型
                int count = arr[i].getOrDefault(d, 0);
                Integer subNum = arr[j].get(d);
                if (subNum != null) {
                    // 说明存在 存在就加上
                    ans += subNum; // 这里不用 + 1
                    arr[i].put(d, count + subNum + 1); // + 1的是序列个数 而答案是只需要长度>=3的 无需加1
                } else {
                    // 否则就直接给它加一个1
                    arr[i].put(d, count + 1);
                }
            }
        }
        // 4. 序列长度如何解决？ 只要它前一个存储在子序列 再加1 就序列长度必然>=3 在这个过程中统计个数
        return ans;
    }


}
