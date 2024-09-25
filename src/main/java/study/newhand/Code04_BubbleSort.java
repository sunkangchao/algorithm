package study.newhand;

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

    // 算法至少是要针对每一道题清楚解题思路和能够写得出来的 细节可能忽略了一些 但你要有能力补充回来
    public static int[] bubbleSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return arr;
        }

        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {

            for (int j = 1; j <= len - 1 - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                }
            }
        }
        return arr;
    }





    public static void swap(int[] arr, int source, int target) {
        int temp = arr[source];
        arr[source] = arr[target];
        arr[target] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,5,6,2,7,2,1};
        bubbleSort(arr);
        for (int i : arr) {
            System.out.print(i);
        }
        System.out.println();
    }



}
