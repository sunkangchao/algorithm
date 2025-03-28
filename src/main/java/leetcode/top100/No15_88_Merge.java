package leetcode.top100;

import study.util.PrintArray;

import java.util.ArrayList;
import java.util.List;

/**
 * No15_88_Merge
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>03月 28, 2025</pre>
 */
public class No15_88_Merge {


    public void merge1(int[] nums1, int m, int[] nums2, int n) {

        if (n == 0) {
            // 此时没有需要合并的元素
            return;
        }

        int j = 0;
        int curNum = m;
        for (int i = 0; i < n; i++) {
            while (j < nums1.length) {
                if (j > curNum - 1) {
                    nums1[j] = nums2[i];
                    j++;
                    curNum++;
                    break;
                }
                if (nums2[i] <= nums1[j]) {
                    // 此时j位置应该给j 腾出位置
                    moveNext(nums1, j, curNum);
                    nums1[j] = nums2[i];
                    curNum++;
                    break;
                } else {
                    // 否则看下一位是否符合
                    j++;
                }
            }
        }

    }

    private void moveNext(int[] nums, int x, int cur) {
        // x后的元素往后移动一位
        for(int i = cur - 1; i >= x; i--) {
            nums[i + 1] = nums[i];
        }
    }


    // 使用双指针再实现一遍 不要写那么复杂
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        int L = 0, R = 0;
        int total = m;

        while(R < n) {
            if (L < total - 1 && nums2[R] <= nums1[L]) {
                insert(nums1, total, L, nums2[R]);
                R++;
                total++;
            } else if (L >= total - 1) {
                nums1[L] = nums2[R];
                L++;
                total++;
            } else {
                L++;
            }
        }

    }

    private void insert(int[] nums, int total, int index, int target) {
        for (int i = total - 1; i >= index; i--) {
            nums[i + 1] = nums[i];
        }
        nums[index] = target;
    }



    // 方法三：使用集合来存储临时合并的结果，避免往数组插入元素时需移动后续元素
    // 思路：直接按照以前的合并有序数组的方式就可以了（以前nums1没有无效元素）
    // 注意事项：
    //  1）哪个数组遍历完是不确定的，跟数组长度无关，不是取长度小的来遍历，别搞混了
    //  2）必然有一个先遍历完成的 哪怕长度一样
    // 复杂度分析：
    // 1）时间复杂度：O(m+n)，遍历两个数组，每次遍历添加一个元素，直至添加完全部元素
    // 2）空间复杂度：O(m+n)，需要一个临时大数组来存储合并的结果
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        int L = 0, R = 0;
        List<Integer> list = new ArrayList<>();

        while (L < m && R < n) {
            if (nums1[L] <= nums2[R]) {
                list.add(nums1[L]);
                L++;
            } else {
                list.add(nums2[R]);
                R++;
            }
        }

        // 必然有一个先遍历完成的 哪怕长度异常
        if (L < m) {
            for (int i = L; i < m; i++) {
                list.add(nums1[i]);
            }
        } else {
            for (int i = R; i < n; i++) {
                list.add(nums2[i]);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            nums1[i] = list.get(i);
        }

    }


    // 解法四：倒序双指针
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }

        int L = m - 1, R = n - 1;
        int cur = nums1.length - 1;
        while (L >= 0 && R >= 0) {
            if (nums2[R] >= nums1[L]) {
                nums1[cur] = nums2[R];
                R--;
                cur--;
            } else {
                nums1[cur] = nums1[L];
                L--;
                cur--;
            }
        }
        if (R >= 0) {
            for (int i = R; i >= 0; i--) {
                nums1[cur] = nums2[i];
                cur--;
            }
        }

    }

    public static void main(String[] args) {
        int[] nums1 = {4,5,6,0,0,0};
        int m = 3;
        int[] nums2 = {1,2,3};
        int n = 3;
        No15_88_Merge instance = new No15_88_Merge();
        instance.merge(nums1, m, nums2, n);
        PrintArray.printArray(nums1);
    }


}
