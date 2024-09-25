package examination;

import java.util.Arrays;

/**
 * 排序算法
 *
 * @author SunKangChao
 * @date 2021/7/12 21:20
 */
public class Sort {

    //排序最简单的方式就是使用工具类的排序方法，你想用什么排序算法就用什么排序算法

    // Arrays.sort(int[] arr);双枢轴快速排序，这种排序算法可直接在项目中使用

    public int[] MySort(int[] arr) {
        // write code here
        return bubbleSort(arr);
    }

    //冒泡排序
    public int[] bubbleSort(int[] arr) {
        //这种情况难道不需要考虑吗，如果出现这种情况，就是NPE了

        if (arr == null || arr.length == 0) {
            return arr;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr,j,j+1);
                }
            }
        }
        return arr;
    }

    //选择排序
    public int[] selectSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        int min = arr[0];
        int minIndex = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            swap(arr,i,minIndex);
            //复位
            min = arr[i + 1];
            minIndex = i + 1;
        }
        return arr;

    }

    // 插入排序
    public int[] insertSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i; j >= 0; j--) {
                if (arr[j+1] < arr[j]){
                    swap(arr,j+1,j);
                }
            }
        }
        return arr;
    }

    //交换方法
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] arr = {5, 2, 1, 6, 3, 7, 4};
        //冒泡排序
//        sort.bubbleSort(arr);
        //选择排序
//        sort.selectSort(arr);
        //插入排序
        sort.insertSort(arr);
        Arrays.stream(arr).forEach(num -> System.out.print(num + " "));
    }


}