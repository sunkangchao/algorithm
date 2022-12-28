package study;


import study.util.PrintArray;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;


/**
 * 快排
 *
 * @author sunkangchao
 * @date 2022/7/24 00:28
 */
public class Code28_PartitionAndQuickSort {


    public static void partitionAndQuickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int temp = arr[l];
        int t1 = l ,t2 = r;
        while (t1 < t2) {
            // 等于的情况放在右边
            while (t1 < t2 && arr[t2] >= temp) {
                t2--;
            }
            if (t1 < t2) {
                // 说明找到了比temp小的数，那么
                arr[t1] = arr[t2];
                t1++;
            }
            while (t1 < t2 && arr[t1] < temp) {
                t1++;
            }
            if (t1 < t2) {
                arr[t2] = arr[t1];
                t2--;
            }
        }
        // 此时必然r = l
        arr[t1] = temp;
        // 继续递归下去
        partitionAndQuickSort(arr, l, t1 - 1);
        partitionAndQuickSort(arr, t1 + 1, r);
    }

    public static void partitionAndQuickSort2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int temp = arr[l];
        int t1 = l, t2 = r;
        while (t1 < t2) {
            while (t1 < t2 && arr[t2] >= temp) {
                t2--;
            }

            if (t1 < t2) {
                arr[t1] = arr[t2];
                t1++;
            }

            while (t1 < t2 && arr[t1] < temp) {
                t1++;
            }

            if (t1 < t2) {
                arr[t2] = arr[t1];
                t2--;
            }

        }
        // 需要考虑值相等的情况
        arr[t1] = temp;
        partitionAndQuickSort2(arr, l, t1 - 1);
        partitionAndQuickSort2(arr, t2 + 1, r);
    }



    public static void partitionAndQuickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        partitionAndQuickSort(arr, 0, arr.length - 1);
    }

    public static double convertToInt(String input) {
        if (input == null || "".equals(input)) {
            return 0;
        }
        // "123.456"
        // 先转换整数部分，再转换小数部分，对两者进行相加操作
        String[] ans = input.split("\\.");
        // 转换整数部分 1，10+2，120+3  = 123
        long number = 0;
        for (int i = 0; i < ans[0].length(); i++) {
            number += ans[0].charAt(i) - '0';
            if (i != ans[0].length() - 1) {
                number *= 10;
            }
        }
        // 转换小数部分   0.4 + 0.05 + 0.006 = 0.456
        double decim = 0.0;
        double temp = 10;
        if (ans.length > 1) {
            for (int i = 0; i < ans[1].length(); i++) {
                double num = ans[1].charAt(i) - '0';
                double curNum = num / temp;
                decim += curNum;
                temp *= 10;
            }
        }
        // 123 + 0.456 = 123.456
        return number + decim;
    }



    public static void main(String[] args) {
        int[] arr = {4,4,4,4,4,4,4,4,4};
        partitionAndQuickSort2(arr, 0, arr.length - 1);
        PrintArray.printArray(arr);

        // 如何获取系统的ZoneOffset对象，如何获取指定ZondId的时区偏移
        ZoneOffset zoneOffset = ZoneId.of("Asia/Shanghai").getRules().getOffset(Instant.ofEpochSecond(33333));
        System.out.println(zoneOffset);

    }


}
