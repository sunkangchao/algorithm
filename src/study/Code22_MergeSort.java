package study;

import study.util.PrintArray;

/**
 * 归并排序
 *
 * @author sunkangchao
 * @date 2022/7/21 20:45
 */
public class Code22_MergeSort {


    public static int[] mergeSort(int[] arr) {
        if (arr == null) {
            return null;
        }
        return null;
    }

//    public static void merge


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
//        PrintArray.printArray(ans);
        for (int j = p1; j <= r2; j++) {
            arr[j] = ans[z++];
        }
//        PrintArray.printArray(arr);
    }

    public static void main(String[] args) {
        int[] arr = {1,3,4,2,3,4};
        int l = 0;
        int r = arr.length - 1;
        int midIndex = l + ((r - l) >> 1);
        System.out.println("l: " + l + ", r: " + r + ", midIndex: " + midIndex);
        mergeSortedArray(arr,0, midIndex, midIndex + 1, arr.length - 1);
    }


}
