package study;

/**
 * 冒泡排序
 *
 * @author sunkangchao
 * @date 2022/7/5 02:57
 */
public class Code04_BubbleSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int arrLength = arr.length;
        for (int i = 0; i < arrLength - 1; i++) {
            for (int j = 0; j < arrLength - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }
    public static void swap(int[] arr, int source, int target) {
        int temp = arr[source];
        arr[source] = arr[target];
        arr[target] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,5,6,2,7,2,1};
        sort(arr);
        for (int i : arr) {
            System.out.print(i);
        }
        System.out.println();
    }



}
