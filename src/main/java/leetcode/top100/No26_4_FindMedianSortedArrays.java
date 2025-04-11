package leetcode.top100;

/**
 * No26_4_FindMedianSortedArrays
 *
 * 4. 寻找两个正序数组的中位数
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 11, 2025</pre>
 */
public class No26_4_FindMedianSortedArrays {


    /**
     * 思路：把问题转化成在两个有序数组中，求第n小的数
     * 为什么之前写着难 写不出来 那是因为你对解法没有彻底的掌握 你没有彻底弄明白 这是根源
     *
     * 1，2，3，4，5，6
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        if (len % 2 == 1) {
            return findK(nums1, nums2, len / 2 + 1);
        }
        return (findK(nums1, nums2, len / 2) + findK(nums1, nums2, len / 2 + 1)) * 0.5;
    }

    private double findK(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int L = 0, R = 0;
        while (true) {
            if (L == m) {
                return nums2[R + k - 1];
            }
            if (R == n) {
                return nums1[L + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[L], nums2[R]);
            }
            int len = k / 2;

            // 求出移动长度 这里要求长度不要具体位置 否则增加
            int lMinLen = Math.min(len, m - L);
            int rMinLen = Math.min(len, n - R);

            // 确定1/2数组的移动长度
            if (nums1[L + lMinLen - 1] >= nums2[R + rMinLen - 1]) {
                R += rMinLen;
                k -= rMinLen;
            } else {
                L += lMinLen;
                k -= lMinLen ;
            }
        }
    }

    public static void main(String[] args) {

        No26_4_FindMedianSortedArrays obj = new No26_4_FindMedianSortedArrays();
        int[] nums1 = {1,3,4,6,7};
        int[] nums2 = {2,5,6,9,10};
        double result = obj.findMedianSortedArrays(nums1, nums2);
        System.out.println(result);

    }


}

