package leetcode.top100;

import java.util.LinkedList;
import java.util.Queue;

/**
 * No86_695_MaxAreaOfIsland
 *
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 *
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 *
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>07月 25, 2025</pre>
 */
public class No86_695_MaxAreaOfIsland {


    // -------------------- 深度优先搜索解法 ----------------------
    // 深度优先搜索 在遍历过程中统计1的个数 更新最大值
    // 相比与之前岛屿数量 求解的值不同而已
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j, m, n);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    // 深度优先搜索函数
    // 参数：grid, i, j 返回值：数量和
    private int dfs(int[][] grid, int i, int j, int m, int n) {
        grid[i][j] = -1;
        int cur = 1;
        if (j - 1 >= 0 && grid[i][j - 1] == 1) {
            cur += dfs(grid, i, j - 1, m, n);
        }
        if (i - 1 >= 0 && grid[i - 1][j] == 1) {
            cur += dfs(grid, i - 1, j, m, n);
        }
        if (i + 1 < m && grid[i + 1][j] == 1) {
            cur += dfs(grid, i + 1, j, m, n);
        }
        if (j + 1 < n && grid[i][j + 1] == 1) {
            cur += dfs(grid, i, j + 1, m, n);
        }
        return cur;
    }




    // ---------------------广度优先搜索-----------------
    // 广度优先搜索 在遍历过程中统计1的个数 更新最大值
    // 相比与之前岛屿数量 求解的值不同而已
    public int maxAreaOfIsland2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxArea = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i, j});
                    grid[i][j] = 0; // 添加时就应该修改其状态
                    int area = bfs(grid, queue, m, n);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    // 广度优先搜索 这个通常使用递归来实现了(没错的) 注意元素重复的问题（在添加时就应该修改成别的状态）
    private int bfs(int[][] grid, Queue<int[]> queue, int m, int n) {
        int cur = 1;
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int i = pos[0];
            int j = pos[1];
            if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                cur++;
                queue.add(new int[]{i - 1, j});
                grid[i - 1][j] = 0;
            }
            if (i + 1 < m && grid[i + 1][j] == 1) {
                cur++;
                queue.add(new int[]{i + 1, j});
                grid[i + 1][j] = 0;
            }
            if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                cur++;
                queue.add(new int[]{i, j - 1});
                grid[i][j - 1] = 0;
            }
            if (j + 1 < n && grid[i][j + 1] == 1) {
                cur++;
                queue.add(new int[]{i, j + 1});
                grid[i][j + 1] = 0;
            }
        }
        return cur;
    }


    // ------------------并查集解法-----------------
    public int maxAreaOfIsland3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        UnionFind unionFind = new UnionFind(grid);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // grid[i][j] = 0; // 此时就标记 以免重复计算
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        unionFind.union(i * n + j, (i - 1) * n + j);
                        // grid[i - 1][j] = 0;
                    }
                    if (i + 1 < m && grid[i + 1][j] == 1) {
                        unionFind.union(i * n + j, (i + 1) * n + j);
                        // grid[i + 1][j] = 0;
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        unionFind.union(i * n + j, i * n + (j - 1));
                        // grid[i][j - 1] = 0;
                    }
                    if (j + 1 < n && grid[i][j + 1] == 1) {
                        unionFind.union(i * n + j, i * n + (j + 1));
                        // grid[i][j + 1] = 0;
                    }
                }
            }
        }

        return unionFind.maxArea;
    }



    // 并查集写法 记住它的构成要素 以及如何通过它求得结果
    static class UnionFind {

        int[] parent;  // 理解：父节点
        int[] rank;
        int count;
        int maxArea; // 增加一个字段记录最大值

        UnionFind(int[][] grid) { // 构造函数的作用就是初始化上述的各个属性值
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        parent[i * n + j] = i * n + j; // 父节点指向它自己
                        rank[i * n + j] = 1;
                        count++;
                        maxArea = 1; // 保证最少会一个值 在存在[i,j]为1时设置
                    }
                }
            }
        }

        int findParent(int i) {
            if (i != parent[i]) {
                parent[i] = findParent(parent[i]); // 路径压缩
            }
            return parent[i];
        }

        void union(int x, int y) { // 实际上这个方法也是需要处理每个属性，这样就不会漏
            int px = findParent(x);
            int py = findParent(y);
            if (px != py) {
                // 比较rank大小
                if (rank[px] >= rank[py]) {
                    rank[px] += rank[py]; // 整合在一起
                    rank[py] = 0; // 清零
                    parent[py] = px; // 更新parent指向
                    maxArea = Math.max(rank[px], maxArea); // 统计最大值
                } else {
                    rank[py] += rank[px];
                    rank[px] = 0;
                    parent[px] = py;
                    maxArea = Math.max(rank[py], maxArea);
                }
                count--;
            }
        }

    }


    public static void main(String[] args) {
        No86_695_MaxAreaOfIsland instance = new No86_695_MaxAreaOfIsland();
        int i = instance.maxAreaOfIsland3(new int[][]{{0}});
        System.out.println(i);
    }


}
