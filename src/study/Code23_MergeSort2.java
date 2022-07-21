package study;

import study.util.PrintArray;

/**
 * 非递归进行归并排序
 *
 * @author sunkangchao
 * @date 2022/7/22 00:35
 */
public class Code23_MergeSort2 {


    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int step = 1;
        int len = arr.length;

        while (step < len) {
            int start = 0;
            while (start < len) {
                int l1 = start;
                int r1 = l1 + step - 1;

                if (r1 + 1 >= len) {
                    break;
                }
                int l2 = r1 + 1;
                int r2 = Math.min(l2 + step - 1, len - 1);

                mergeSortedArray(arr, l1, r1, l2, r2);
                start = r2 + 1;
            }
            step *= 2;
        }

    }

    public static void mergeSortedArray(int[] arr, int l1, int r1, int l2, int r2) {
        if (arr == null) {
            return;
        }
        if (l1 > r1 || l2 > r2) {
            return;
        }
        if (r1 >= arr.length || r2 >= arr.length) {
            return;
        }
        int p1 = l1;
        int length1 = r1 - l1 + 1;
        int length2 = r2 - l2 + 1;
        int i = 0;
        int[] ans = new int[length1 + length2];
        // 合并公共部分
        while (l1 <= r1 && l2 <= r2) {
            if (arr[l1] <= arr[l2]) {
                ans[i++] = arr[l1++];
            } else {
                ans[i++] = arr[l2++];
            }
        }
        // 合并多余部分
        while (l1 <= r1) {
            ans[i++] = arr[l1++];
        }
        while (l2 <= r2) {
            ans[i++] = arr[l2++];
        }
        int z = 0;
        // 把数组复制回原来的数组索引位置上
        for (int j = p1; j <= r2; j++) {
            arr[j] = ans[z++];
        }
    }


    public static void main(String[] args) {
        int[] arr = {1,3,4,2,3,4,5,8,7,6,10,9,11,8};
        mergeSort(arr);
        PrintArray.printArray(arr);
    }




}
