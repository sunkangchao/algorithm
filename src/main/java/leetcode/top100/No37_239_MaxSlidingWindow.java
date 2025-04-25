package leetcode.top100;

import study.util.PrintArray;

import java.util.*;

/**
 * No37_239_MaxSlidingWindow
 *
 * <p>
 *     239. Sliding Window Maximum
 * </p>
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 24, 2025</pre>
 */
public class No37_239_MaxSlidingWindow {


    // 思路：维护一个单调递增的双端队列
    // 1. 首先遍历至k - 1，把最大元素维护起来
    // 2. 每移动一步，先把栈中元素移除，如果已经不在窗口内的话
    // 3. 把当前元素压入栈中，控制栈中元素不超过k个
    //
    public int[] maxSlidingWindow1(int[] nums, int k) {

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        List<Integer> result = new ArrayList<>();

        int left = 0, right = k - 1;
        while (right < nums.length) {

            Integer i = deque.peekFirst();
            result.add(nums[i]);

            left = left + 1;
            right = right + 1;
            if (i < left) {
                deque.pollFirst();
            }

            if (right < nums.length) {
                while (!deque.isEmpty() && nums[right] > nums[deque.peekLast()]) {
                    deque.pollLast();
                }
                deque.offerLast(right);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // -------------------------------------

    // 优先队列解法 在遍历过程中把最大值放置在堆中
    public int[] maxSlidingWindow(int[] nums, int k) {

        // 存储[num, index]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});
        }

        int n = nums.length;
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];

        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] < i - k + 1) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }


    public static void main(String[] args) {
        No37_239_MaxSlidingWindow obj = new No37_239_MaxSlidingWindow();
        int[] arr = {1,3,1,2,0,5};
        int k = 3;
        int[] result = obj.maxSlidingWindow(arr, k);
        PrintArray.printArray(result);
    }


}
