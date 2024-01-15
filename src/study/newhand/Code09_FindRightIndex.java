package study.newhand;

import study.util.GenerateArray;
import study.util.PrintArray;

import java.util.Arrays;
import java.util.Random;

/**
 * 有序数组中<=num的最右位置
 *
 * @author sunkangchao
 * @date 2022/7/7 03:14
 */
public class Code09_FindRightIndex {

    public static int findRightIndex(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        while (left <= right) {
            int mediumIndex = (left + right) / 2;
            if (arr[mediumIndex] <= target) {
                result = mediumIndex;
                left = mediumIndex + 1;
            } else {
                right = right -1;
            }
        }
        return result;
    }

    // 对数器
    public static int test(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int result1;
        int result2;
        int loopTimes = 10000;
        for (int i = 0; i < loopTimes; i++) {
            int[] arr = GenerateArray.generateArray(10,10);
            // 使得数组变得有序
            Arrays.sort(arr);
            int target = new Random().nextInt(10);
            result1 = findRightIndex(arr, target);
            result2 = test(arr, target);
            if (result1 != result2) {
                System.out.println("arr: ");
                PrintArray.printArray(arr);
                System.out.println();
                System.out.println("target: " + target);
            }
        }
        System.out.println("success!");
    }

}
