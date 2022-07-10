package study.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 打印数组
 *
 * @author sunkangchao
 * @date 2022/7/7 03:24
 */
public class PrintArray {

    public static void printArray(int[] arr) {
        System.out.print("arr数组为: ");
        for (int temp : arr) {
            System.out.print(temp + " ");
        }
        System.out.println();
    }


}
