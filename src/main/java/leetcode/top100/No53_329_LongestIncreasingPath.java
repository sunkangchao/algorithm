package leetcode.top100;

import java.util.*;

/**
 * No53_329_LongestIncreasingPath
 *
 * <p>
 *     329. 矩阵中的最长递增路径
 *     给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 *
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 *
 * </p>
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 19, 2025</pre>
 */
public class No53_329_LongestIncreasingPath {


    /**
     * 思路：动态规划解法
     * 题目要求求解矩形中的最大递增路径，最简单的解法就是挨个遍历二维数组中的每个元素，
     * 使用dfs方式遍历，求解出以每个二维数组元素为起点的最大路径。
     *
     * 所以，同样我们可以得知二维数组中的某个元素中为起点的最长路径，可以等于它上下左右四个方向的元素为起点的最长路径，
     * 再加1，就是当前的元素为起点的最长路径。
     *
     * 也就是dp[i][j] = max(dp[CI][CJ]) + 1
     *
     * 它的值取决于周围的值，那么应该如何遍历？这是本道题的难点。
     *
     * 我们通过分析可以知道，如果按照从大到小的元素来求解，那么它遇到的比它大的周边值一定是求解过的。
     * 所以需要把二维数组中的元素按照从大到小来排序，然后依次遍历这个列表，最终返回最长递增路径。
     *
     * <p/>
     * 复杂度分析：
     * 时间复杂度：O(mnlog(mn) + mn)，先对数组进行排序，然后遍历整个二维数组
     * 空间复杂度：O(mn)，需要一个二维数组存储中间的运算结果。
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath1(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        // 遍历二维数组 存储到集合当中
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                list.add(new int[]{matrix[i][j], i, j});
            }
        }

        // 排序
        list.sort(Comparator.<int[]>comparingInt(a -> a[0]).reversed());

        // 定义四个方向
        int[][] direction = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

        // 按照集合遍历求解 定义二维数组结果集
        int[][] dp = new int[m][n];

        // 初始化
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], 1);
        }

        int ans = 1;
        for (int[] arr : list) {
            int i = arr[1];
            int j = arr[2];
            for (int[] dire : direction) {
                int i1 = i + dire[0];
                int i2 = j + dire[1];
                if (i1 >= 0 && i1 < m && i2 >= 0 && i2 < n) {
                    if (matrix[i][j] < matrix[i1][i2]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i1][i2] + 1);
                    }
                }
            }
            ans = Math.max(ans, dp[i][j]);
        }

        return ans;
    }


    /**
     * 思路：dfs 深度优先遍历
     * 遍历二维数组的每个元素，然后使用dfs求解以当前元素为起点的最长递增路径。
     * 遍历过程中，如果遇到比当前元素小的元素，则跳过。
     * 遍历过程中，如果遇到比当前元素大的元素，则更新当前元素的最长递增路径。
     *
     * 注意：
     * 1）为了避免dfs时形成环，需要使用set来记录遍历过的点。
     * 但是其实不需要使用set，因为每次遍历都是一个比当前大的元素，所以不可能会往回走
     *
     * 2）会出现大量的重复遍历问题，会超时，因此需要使用记忆化递归
     *
     * 3）相同元素的数组使用equals方法比较是不相等的
     *
     * 4）key1+key2, value，这种映射可以使用二维数组，不一定要用map.
     *
     * <p/>
     * 复杂度分析：
     * 时间复杂度：O(mn)，遍历整个二维数组，且是记忆化搜索。
     * 空间复杂度：O(mn)，需要一个二维数组存储计算过的结果，实现记忆化。
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {

        // 遍历二维数组
        int maxLen = 0;
        int[][] arr = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                // 优化点：对于已经统计过的点就需要
                if (arr[i][j] == 0) {
                    maxLen = Math.max(maxLen, dfs(matrix, i, j,
                            new int[][]{{-1,0}, {0, -1}, {1, 0}, {0, 1}}, arr));
                }
            }
        }
        return maxLen;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] directions, int[][] arr) {

        int row = matrix.length;
        int col = matrix[0].length;

        if (arr[i][j] != 0) {
            return arr[i][j];
        }

        int ans = 0;
        // 递归出口
        for (int[] dir : directions) {
            int r = i + dir[0];
            int c = j + dir[1];
            if (r >= 0 && r < row && c >= 0 && c < col && matrix[r][c] > matrix[i][j]) {
                int rs = dfs(matrix, r, c, directions, arr);
                ans = Math.max(ans, rs);
            }
        }

        // 最大长度 + 1
        int rs = ans + 1;
        arr[i][j] = rs;
        return rs;
    }


    public static void main(String[] args) {
        No53_329_LongestIncreasingPath obj = new No53_329_LongestIncreasingPath();
        int[][] arr = {{9,9,4},{6,6,8},{2,1,1}};
        int i = obj.longestIncreasingPath(arr);
        System.out.println(i);

//        int[] arr1 = new int[] {1,2};
//        int[] arr2 = new int[] {1,2};
//
//        System.out.println(arr1.equals(arr2));

    }

}
