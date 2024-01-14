package study.newhand;

/**
 * 有序数组中>=num的最左位置
 *
 * @author sunkangchao
 * @date 2022/7/7 02:32
 */
public class Code08_FindLeftestIndex {

    public static int findLeftestIndex(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return findLeftestIndex(arr, 0, arr.length - 1, target);

    }

    private static int leastIndex = -1;
    public static int findLeftestIndex(int[] arr, int left, int right, int target) {
        if (left > right) {
            return leastIndex;
        }

        int mediumIndex = (left + right) / 2;
        if (arr[mediumIndex] >= target) {
            leastIndex = mediumIndex;
            return findLeftestIndex(arr, left, mediumIndex -1, target);
        } else {
            return findLeftestIndex(arr, mediumIndex + 1, right, target);
        }
    }

    public static int findMinIndex(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int minIndex = -1;
        while (left <= right) {
            int midIndex = (left + right) / 2;
            if (target == arr[midIndex]) {
                minIndex = midIndex;
                right = midIndex - 1;
            } else if (target < arr[midIndex]) {
                right = midIndex - 1;
            } else {
                left = midIndex + 1;
            }
        }
        return minIndex;
    }


    public static void main(String[] args) {
        int[] arr = {1,1,1,2,2,2,3,4,4,5,5,6,7};
        System.out.println(findMinIndex(arr, 1));
    }




}
