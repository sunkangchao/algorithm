package leetcode.top100;

import sun.rmi.runtime.Log;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * No43_268_NthUglyNumber
 * <p>
 *
 * LCR 168. 丑数
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 30, 2025</pre>
 */
public class No43_268_NthUglyNumber {


    /**
     * 思路：使用最小堆，每次取出最小的丑数，作为第n个丑数，然后基于该丑数，分别乘以2，3，5
     * 然后加入堆中，循环如此，直至找到第n个丑数。需要注意，要对这个堆去重复，默认是不去重复的。
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(n)
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {

        // 默认是最小堆，创建最大堆本质都是通过传递比较器作为构造参数；
        // 1. 自定义 2. 使用Comparator.reverseOrder()
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        pq.offer(1L);
        set.add(1L);
        long curUgly = 1;
        int[] nums = {2,3,5};

        while (n-- > 0) {
            curUgly = pq.poll();
            for (int j : nums) {
                long k = curUgly * j;
                if (set.add(k)) {
                    pq.offer(k);
                }
            }
        }

        return (int) curUgly;
    }

    public static void main(String[] args) {
        No43_268_NthUglyNumber obj = new No43_268_NthUglyNumber();
        int i = obj.nthUglyNumber(1407);
        System.out.println(i);


    }


}
