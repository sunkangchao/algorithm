package study.newhand;

/**
 * 选择排序
 *
 * @author sunkangchao
 * @date 2022/7/5 02:36
 */
public class Code03_SelectionSort {


    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int arrLength = arr.length;
        for (int i = 0; i < arrLength - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arrLength; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // exchange
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,4,5,6,2,7,2,1};
        selectionSort(arr);
        for (int i : arr) {
            System.out.print(i);
        }
        System.out.println();
    }

}
