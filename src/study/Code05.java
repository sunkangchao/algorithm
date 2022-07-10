package study;

/**
 * 插入排序
 *
 * @author sunkangchao
 * @date 2022/7/5 03:26
 */
public class Code05 {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j >0; j--) {
                if (arr[j] > arr[j-1]) {
                    break;
                }
                swap(arr, j, j - 1);
            }
        }
    }

    public static void sort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            int j = i;
            while (j - 1 >= 0 && arr[j-1] > arr[j]) {
                swap(arr, j, j - 1);
                j--;
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
        sort2(arr);
        for (int i : arr) {
            System.out.print(i);
        }
        System.out.println();
    }
}


