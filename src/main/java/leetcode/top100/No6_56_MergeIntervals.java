package leetcode.top100;

import study.util.PrintArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并区间
 *
 * @author sunkangchao
 * @since 2025/3/9 17:21
 */
public class No6_56_MergeIntervals {


    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            if (result.isEmpty() || result.get(result.size() - 1)[1] < start) {
                result.add(new int[]{start, end});
            } else {
                result.get(result.size() - 1)[1] = Math.max(result.get(result.size() - 1)[1], end);
            }
        }
        int[][] ints = new int[result.size()][];
        return result.toArray(ints);
    }


    public int[][] merge2(int[][] intervals) {

        // 定义一个Boolean类型的数组 数组长度是多少呢？总长度是一万
        boolean[] flags = new boolean[10000 * 2 + 1];

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            // 乘以2再填充
            for (int i = start * 2; i <= end * 2; i++) {
                flags[i] = true;
            }
        }

        List<int[]> result = new ArrayList<>();

        // 填充以后开始合并区间
        int i = 0;
        while (i < flags.length) {
            // 取出起点区间
            while (i < flags.length && !flags[i]) i++;
            // 结束时flags[i]为true
            int j = i + 1;
            while (j < flags.length && flags[j]) j++;
            // 结束时flag[j]为false 此时取出该区间]
            if (j <= flags.length) {
                result.add(new int[]{i / 2, (j - 1) / 2});
            }
            i = j;
        }

        return result.toArray(new int[result.size()][]);
    }


    public static void main(String[] args) {

        No6_56_MergeIntervals instance = new No6_56_MergeIntervals();
//        int[][] arrs = new int[][] {{1,3},{2,6},{8,10},{15,18}};
        int[][] arrs = new int[][] {{1,3},{8,10000},{3,8}};

        int[][] ints = instance.merge2(arrs);
        List<int[]> ints1 = Arrays.asList(ints);
        for (int[] arr : ints1) {
            PrintArray.printArray(arr);
        }

    }



}
