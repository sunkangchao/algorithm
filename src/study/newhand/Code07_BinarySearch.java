package study.newhand;

import study.util.GenerateArray;

import java.util.Arrays;

/**
 * 二分法
 *
 * @author sunkangchao
 * @date 2022/7/7 01:41
 */
public class Code07_BinarySearch {

    /** 
     * 二分查找法
     * @param arr
     * @return
     */
    public static int findCode(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return findCode(arr, 0, arr.length - 1, target);
    }

    /**
     * 重新定义成左闭右闭区间
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int findCode(int[] arr, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        int mediumIndex = (left + right) / 2;
        int mediumValue = arr[mediumIndex];

        if (target == mediumValue){
            return mediumIndex;
        } else if (target < mediumValue) {
            return findCode(arr, left, mediumIndex - 1, target);
        } else {
            return findCode(arr, mediumIndex + 1, right, target);
        }
    }


    /**
     * 只要可以有大问题拆分成相同子规模问题，就能无脑使用递归
     * @param arr
     * @param target
     * @return
     */
    public static int binarySearch(int[] arr, int target) {
        if (arr == null) {
            return -1;
        }
        return binarySearch(arr, 0, arr.length - 1, target);
    }

    public static int binarySearch(int[] arr, int left, int right, int target) {
        if (right - left < 0) {
            return -1;
        }
        int midIndex = (left + right) / 2;
        if (target == arr[midIndex]) {
            return midIndex;
        }
        if (target < arr[midIndex]) {
            return binarySearch(arr, left, midIndex - 1, target);
        } else {
            return binarySearch(arr, midIndex + 1, right, target);
        }
    }

    public static int binarySearch2(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (target == arr[mid]) {
                return mid;
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }


    public static int test(int[] arr, int target) {
        for (int temp : arr) {
            if (temp == target) {
                return temp;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = GenerateArray.generateArray(10,10);
        Arrays.sort(arr);
        for (int temp : arr) {
            System.out.print(temp + " ");
        }
        System.out.println();
//        System.out.println(findCode(arr, 1));
//        System.out.println(findCode(arr, 1) == test(arr, 24) ? "Nice!" : "Fucking fucked!");
        System.out.println(binarySearch2(arr, 2));
    }


}
