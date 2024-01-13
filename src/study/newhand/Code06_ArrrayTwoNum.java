package study.newhand;

/**
 * 数组任意两个索引位的累加和
 *
 * @author sunkangchao
 * @date 2022/7/5 15:57
 */
public class Code06_ArrrayTwoNum {

    private static int[] arr = {1,4,3,2,6,7,2,3,8,9};
    private static int[][] sum = new int[arr.length][arr.length];


    static {
        int length = sum.length;
        // 赋值基础数据
        for (int i = 0; i < arr.length; i++) {
            sum[i][i] = arr[i];
        }
        // 填充二维表数据
        for (int i = 0; i < length; i++) {
            for (int j = 1; j < length; j++) {
                if (i < j) {
                    sum[i][j] = sum[i][j - 1] + arr[j];
                }
            }
        }

    }

    public static int rangeSum(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException();
        }
        return sum[start][end];
    }

    public static void main(String[] args) {
        for (int i = 0; i < sum.length; i++) {
            int[] arr = sum[i];
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[j]);
            }
            System.out.println();
        }
//        System.out.println(rangeSum(1, 3));
    }


}
