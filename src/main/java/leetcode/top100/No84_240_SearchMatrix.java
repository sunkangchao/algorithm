package leetcode.top100;

/**
 * No84_240_SearchMatrix
 *
 * 240. 搜索二维矩阵 II
 *
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>07月 14, 2025</pre>
 */
public class No84_240_SearchMatrix {


    /**
     * 方法一：遍历整个二维数组，时间复杂度O(mn)。
     * 方法二：针对每一行作二分法，时间复杂度O(mLogn)。
     * 方法三：二维二分法，每次只能排除一个小区域（左下、左上、右下、右上），时间复杂度比较高。
     * 方法四：Z字形搜索，从左上角开始，当(x,y) > target, y--；当(x,y) < target, x++。这种搜索方法能够充分利利用题目给出的递增规律，是这道题的最优解法。时间复杂度O(m+n)。
     *
     * 注意：不管哪种解法，只要越界，都认为是不存在答案。
     * @param matrix
     * @param target
     * @return
     */
    // O(m + n)的解法 实在是没想到 钻二分法里面去了
    public boolean searchMatrix(int[][] matrix, int target) {
        // 1. 定义边界
        int m = matrix.length;
        int n = matrix[0].length;
        // (m,0) (0,n)
        int i = 0, j = n - 1;

        // 2. 循环判断
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }

        // 3. 返回结果
        return false;
    }


}
