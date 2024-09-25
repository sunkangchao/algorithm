package study.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

/**
 * 生成随机数组
 *
 * @author sunkangchao
 * @date 2022/7/6 02:42
 */
public class GenerateArray {

    public static int[] generateArray(int maxLength, int maxValue) {
        int length = (int) (Math.random() * (maxLength + 1));
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
//        int[] ans = generateArray(2,5);
//        for (int temp : ans) {
//            System.out.print(temp + " ");
//        }
        int[] arr = {2,1,5,4,7,8,9,10};
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        arr[0] = 10;
        arr[1] = 100;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arr2[i]) {
                throw new IllegalArgumentException();
            }
        }

    }


}
