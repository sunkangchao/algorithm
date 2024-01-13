package study.newhand;

import study.util.GenerateArray;

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
        for (int temp : arr) {
            System.out.print(temp + " ");
        }
        System.out.println();
        System.out.println(findCode(arr, 1));
        System.out.println(findCode(arr, 1) == test(arr, 24) ? "Nice!" : "Fucking fucked!");
    }


}
