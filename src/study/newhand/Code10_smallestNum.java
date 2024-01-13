package study.newhand;

import study.util.PrintArray;

/**
 * 局部最小值的索引
 *
 * @author sunkangchao
 * @date 2022/7/8 03:58
 */
public class Code10_smallestNum {


    public static int smallestNum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }
        if (arr.length == 2) {
            return arr[0] < arr[1] ? 0 : 1;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 2] > arr[arr.length - 1]) {
            return arr.length - 1;
        }
        int l = 0;
        int r = arr.length - 1;
        while (l < r - 1) {
            int mid = (l + r) / 2;
            // 不需要进行超出区间判断，因为如果有机会走到这一步，在前面的baseCase中已经返回了
            if (arr[mid - 1] > arr[mid] && arr[mid] < arr[mid + 1]) {
                return mid;
            } else if (arr[mid - 1] > arr[mid]) {
                // 取右半区间，必然存在局部最小值
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return arr[l] < arr[r] ? l : r;
    }


    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen);
        int[] arr = new int[len];

        if (len > 0) {
            arr[0] = (int) (Math.random() * maxValue);
            for (int i = 1; i < len; i++) {
                do {
                    arr[i] = (int) (Math.random() * maxValue);
                } while (arr[i - 1] == arr[i]);
            }
        }
        return arr;
    }

    // 命名从简
    // 边界条件判断 + 边界值确定
    // 先自己有一遍自己的思路，否则你会走很多弯路
    // 要一边思考一边代码，别写代码图快不带脑子！
    // 着急就意味着要很慢，效率会很低
    public static boolean check(int[] arr, int minIndex) {
        if (arr.length == 0) {
            return minIndex == -1;
        }
        if (arr.length == 1) {
            return minIndex == 0;
        }
        int left = minIndex - 1;
        int right = minIndex + 1;
        if (left == -1) {
            return arr[minIndex] < arr[right];
        }
        if (right == arr.length) {
            return arr[minIndex] < arr[left];
        }
        return arr[minIndex] < arr[left] && arr[minIndex] < arr[right];
    }

    public static void main(String[] args) {
        int times = 10000;
        System.out.println("测试开始");
        int[] arr = {};
        for (int i = 0; i < times; i++) {
            arr = randomArray(1000, 100);
            int minIndex = smallestNum(arr);
            if (!check(arr, minIndex)) {
                PrintArray.printArray(arr);
                System.out.println("mindex: " + minIndex);
                System.out.println("defeated!");
            }
        }

        System.out.println("测试结束");
    }



}
