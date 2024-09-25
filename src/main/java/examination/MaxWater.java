package examination;

import javax.crypto.MacSpi;

/**
 * 最大接雨水量
 * 双指针
 *
 * @author SunKangChao
 * @date 2021/7/19 01:14
 */
public class MaxWater {

    public long maxWater(int[] arr) {
        return pinchDoublePointer(arr);
    }


    public long pinchDoublePointer(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }


        long maxWater = 0;
        int left = 0, right = 1;
        while (right < arr.length) {
            if (arr[right] >= arr[left]) {
                maxWater += countWaterLeftLowest(arr, left, right);
                left = right;
            }
            right++;
        }
        if (left <= arr.length - 1) {
            int maxPillarIndex = left;
            right = right - 1;
            left = right - 1;
            while (left >= maxPillarIndex) {
                if (arr[left] >= arr[right]) {
                    maxWater += countWaterRightLowest(arr, left, right);
                    right = left;
                }
                left--;
            }
        }
        return maxWater;
    }

    public long countWaterLeftLowest(int[] arr, int left, int right) {
        long water = 0;
        for (int i = left + 1; i < right; i++) {
            if (arr[left] > arr[i]) {
                water += arr[left] - arr[i];
            }
        }
        return water;
    }

    public long countWaterRightLowest(int[] arr, int left, int right) {
        long water = 0;
        for (int i = left + 1; i < right; i++) {
            if (arr[i] < arr[right]) {
                water += arr[right] - arr[i];
            }
        }
        return water;
    }


    public long doublePointer(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        long maxWater = 0;
        //定义左右双指针
        int left = 0, right = 1;
        boolean flag = false;
        while (left < arr.length - 1) {
            while (right < arr.length) {
                if (arr[right] >= arr[left]) {
                    //计算区间(left,right)的蓄水量
                    maxWater += countWater(arr, left, right, false);
                    left = right;
                }
                if (flag) {
                    maxWater += countWater(arr, left, right, true);
                    left = right;
                    right = arr.length - 1;
                }
                right++;
            }

            if (right == arr.length && left < arr.length - 1) {
                right = findMaxPillarIndex(arr, left);
                if (!flag) {
                    flag = true;
                }
            }
        }
        return maxWater;

    }

    public long countWater(int[] arr, int left, int right, boolean flag) {
        long water = 0;
        if (!flag) {
            for (int i = left + 1; i < right; i++) {
                if (arr[i] < arr[left]) {
                    water += arr[left] - arr[i];
                }
            }
        } else {
            for (int i = left + 1; i < right; i++) {
                if (arr[i] < arr[right]) {
                    water += arr[right] - arr[i];
                }
            }
        }
        return water;
    }

    //找出区间最大值索引
    public int findMaxPillarIndex(int[] arr, int left) {
        int maxPillarIndex = left + 1;
        int max = arr[maxPillarIndex];
        for (int i = left + 2; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                maxPillarIndex = i;
            }
        }
        return maxPillarIndex;
    }


    public long maxWaterByLoopMaxPillar(int[] arr) {
        //严谨性判断不能漏
        if (arr == null || arr.length < 2) {
            return 0;
        }

        // write code here

        int maxPillar = 1;
        long maxWater = 0;

        for (int currentLayer = 1; currentLayer <= maxPillar; currentLayer++) {
            //前面是否有柱子
            boolean flag = false;

            for (int i = 0, j = 0; j < arr.length; j++) {

                if (arr[j] > maxPillar) {
                    maxPillar = arr[j];
                }
                if (arr[j] >= currentLayer && !flag) {
                    flag = true;
                    i = j;
                } else if (arr[j] >= currentLayer && flag) {
                    maxWater += j - i - 1;
                    i = j;
                }
            }
        }

        return maxWater;
    }


    public static void main(String[] args) {
        MaxWater instance = new MaxWater();
        //数组传递无法简写
        long maxWater = instance.maxWater(new int[]{3, 1, 2, 5, 2, 4});
        System.out.println(maxWater);
    }
}
