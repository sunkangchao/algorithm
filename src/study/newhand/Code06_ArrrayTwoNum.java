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

    /**
     * 两个索引位的和
     */
    private static int[][] SUM2 = new int[arr.length][arr.length];

    private int[] innerArray;

    private int[][] sumArray;

    public Code06_ArrrayTwoNum(int[] arr) {
        this.innerArray = arr;
        this.sumArray = new int[arr.length][arr.length];
        this.initSum();
    }


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

    /**
     * 数组中任意两位的和
     */
    public static void initSumArray() {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j =0; j < len; j++) {
                if (i < j) {
                    SUM2[i][j] = arr[i] + arr[j];
                }
            }
        }
    }

    public void initSum() {
        int[] arr = this.innerArray;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (i == j) {
                    sumArray[i][j] = arr[i];
                    continue;
                }
                sumArray[i][j] = sumArray[i][j - 1] + arr[j];
            }
        }
    }

    public int sumResult(int begin, int end) {
        if (begin > end) {
            return sumArray[end][begin];
        }
        return sumArray[begin][end];
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
        System.out.println("======================");
        Code06_ArrrayTwoNum instance = new Code06_ArrrayTwoNum(new int[]{2,3,1,4,2,5,6});
        System.out.println(instance.sumResult(0,3));
    }


}
