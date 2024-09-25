package study.newhand;


import study.util.PrintArray;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 寻找出现K次的值
 *
 * @author sunkangchao
 * @date 2022/12/12 11:06
 */
public class Code100_NTimesK {


    // 一个数出现K次，其它数出现了M次，K < M，找到出现了K次的值
    public static int findKTimesValue(int[] arr, int k, int m) {
        int[] bitCounts = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                bitCounts[i] += num >>> i & 1;
            }
        }
        int result = 0;
        for (int i = 0; i < bitCounts.length; i++) {
            if (bitCounts[i] % m != 0) {
                // 说明出现k次的值在次位上有1 把这个1赋值上去
                result |= 1 << i;
            }
            // 如果这一位是为0


        }
        return result;
    }

    // 使用hash表常规方法实现
    public static int findKTimeValueByHash(int[] arr, int k, int m) {
        Map<Integer, Integer> timesMap = new HashMap<>();
        for (int num : arr) {
            if (timesMap.containsKey(num)) {
                timesMap.put(num, timesMap.get(num) + 1);
            } else {
                timesMap.put(num, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : timesMap.entrySet()) {
            int value = entry.getValue();
            if (value == k) {
                return entry.getKey();
            }
        }
        // 表示没有找到出现k次的数
        return -1;
    }

    // 写对数器
    public static int[] randomArr(int mKinds, int k, int m, int maxRange) {
        // k出现次数 + m出现次数 * 种类
        int[] arr = new int[mKinds * m + k];
        Set<Integer> set = new HashSet<>();
        int kTimeValue = (int) (Math.random() * (maxRange + 1));
        set.add(kTimeValue);
        int index = 0;
        for (; index < k; index++) {
            arr[index] = kTimeValue;
        }
        int kinds = mKinds;
        while (kinds > 0) {
            int mTimeValue = (int) (Math.random() * (maxRange + 1));
            if (set.contains(mTimeValue)) {
                continue;
            }
            set.add(mTimeValue);
            for (int i = 0; i < m; i++) {
                arr[index++] = mTimeValue;
            }
            kinds--;
        }
        disOrderArr(arr);
        return arr;
    }

    public static void disOrderArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            swap(arr, i, swapIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        // 检测次数
        int times = 1000000;
        // MaxKinds
        int maxKinds = 10;
        int maxTimes = 100;
        int maxRange = 200;

        for (int i = 0; i < times; i++) {
            int mkinds = (int) (Math.random() * (maxKinds - 1)) + 2;
            int k = (int) (Math.random() * maxTimes) + 1;
            int m = (int) (Math.random() * maxTimes) + 1;
            // 保证k < m
            if (k >= m) {
                // 恶心死人了
                m = k + 1;
            }
            int[] randomArray = randomArr(mkinds, k, m, maxRange);
            int result1 = findKTimesValue(randomArray, k, m);
            int result2 = findKTimeValueByHash(randomArray, k, m);
            if (result1 != result2) {
                System.out.println("-----------------");
                System.out.printf("mKinds: %d, k: %d, m: %d", mkinds, k, m);
                System.out.println();
                PrintArray.printArray(randomArray);
                System.out.println("result1: " + result1 + ", result2: " + result2);
                System.out.println("method error!!!");
                System.out.println("-----------------");
                System.exit(0);
            }
        }
        System.out.println("method pass!!!");
    }

}
