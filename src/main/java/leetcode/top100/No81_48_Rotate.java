package leetcode.top100;

/**
 * No81_48_Rotate
 *
 * 48. 旋转图像
 *
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>07月 04, 2025</pre>
 */
public class No81_48_Rotate {


    // 两种方式
    // 1. 正对角线翻转（1-9） + 左右翻转
    // 2. 反对角线翻转 (3-7) + 上下翻转
    public void rotate(int[][] matrix) {

        // 1. 对角翻转
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < n; j++) { // 这里j从i+1开始，对角线即i=j时可以跳过
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }


        // 2. 左右翻转
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n / 2; j++) { // 这里j到n/2 -1 结束，n/2位置为中轴线不需要翻转（考虑奇偶情况）
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j]  = temp;
            }
        }

    }


}
