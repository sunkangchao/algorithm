package leetcode.top100;

import java.util.LinkedList;
import java.util.Queue;

/**
 * No13_200_NumIslands
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>03月 19, 2025</pre>
 */
public class No13_200_NumIslands {


    /**
     * 第一个解法：并查集
     * 遍历二维数组中的每一个点，把当前点改成0，表示已经联合过。
     * 如果当前是岛屿'1'，在边界满足的前提下，往它的四周联合，它的四周需要是岛屿才联合，不是岛屿你联合什么
     * 最终获取并查集中的最后的数量
     *
     * 注意：初始化的岛屿数量，以及rank数组存在的意义，findParent时顺便把其路径上的节点的父节点都改成根节点
     */
    public int numIslands1(char[][] grid) {

       if (grid == null || grid.length == 0) {
           return 0;
       }

       UnionFind uf = new UnionFind(grid);
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {

                    // 每次遍历到再更新状态
                    grid[i][j] = '0';

                    if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                        uf.union(i * n + j, i * n + j - 1);
                    }
                    if (j + 1 <= n - 1 && grid[i][j + 1] == '1') {
                        uf.union(i * n + j, i * n + j + 1);
                    }
                    if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                        uf.union(i * n + j, (i - 1) * n + j);
                    }
                    if (i + 1 <= m - 1 && grid[i + 1][j] == '1') {
                        uf.union(i * n + j, (i + 1) * n + j);
                    }
                }
            }
        }

        return uf.count;
    }


    static class UnionFind {

        int[] parents;

        // 记录节点排名 每次联合排名加1 以此来作为后续谁作为父节点的依据
        int[] rank;

        int count;


        public UnionFind(char[][] grid) {
            // 初始化
            count = 0;
            int m = grid.length;
            int n = grid[0].length;

            parents = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        // 初始化父元素为自身
                        parents[i * n + j] = i * n + j;
                        count++;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public int findParent(int i) {
            if (parents[i] != i) {
                // 这里可以做一步优化 每次查找都把它的parent改为顶部
                parents[i] = findParent(parents[i]);
            }
            return parents[i];
        }


        public void union(int x, int y) {
            int rootX = findParent(x);
            int rootY = findParent(y);
            if (rootX != rootY) {
                // 可以联合 小连到大的
                if (rank[rootX] >= rank[rootY]) {
                    parents[rootY] = rootX;
                    rank[rootX] += 1;
                } else {
                    parents[rootX] = rootY;
                    rank[rootY] += 1;
                }
                --count;
            }

        }

    }


    /**
     * 方法二：深度优先搜索
     * 遍历二维数组中的每个节点，如果是岛屿‘1’，则往其四周延伸，沿途把搜索过的点改为‘0’，
     * 一个方向完全搜索完再搜索下一个方向，深度优先。
     */
    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }


    private void dfs(char[][] grid, int x, int y) {
        int m = grid.length;
        int n = grid[0].length;

        grid[x][y] = '0';
        if (x - 1 >= 0 && grid[x - 1][y] == '1') {
            dfs(grid, x - 1, y);
        }
        if (x + 1 <= m - 1 && grid[x + 1][y] == '1') {
            dfs(grid, x + 1, y);
        }
        if (y - 1 >= 0 && grid[x][y - 1] == '1') {
            dfs(grid, x, y - 1);
        }
        if (y + 1 <= n - 1 && grid[x][y + 1] == '1') {
            dfs(grid, x, y + 1);
        }
    }


    /**
     * 方法三：广度优先遍历
     * 遍历整个二维数组，针对每个岛屿，把它前后左右四个方向的岛屿存储下来，
     * 下一层遍历上次存起来的集合，每次遍历把当前元素的前后左右四个方向的岛屿再存储下来，
     * 最终实现层序遍历，同样改状态的时机，即把岛屿的‘1’改成'0'放置在遍历到当前元素时
     */
    public int numIslands3(char[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    queue.add(new int[]{i, j});
                    bfs(grid, queue);
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, Queue<int[]> queue) {

        int m = grid.length;
        int n = grid[0].length;

        // 因为不需要分层 不需要使用size来控制遍历的层级
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int i = point[0];
            int j = point[1];
            if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                queue.add(new int[]{i - 1, j});
                grid[i - 1][j] = '0';
            }
            if (i + 1 <= m - 1 && grid[i + 1][j] == '1') {
                queue.add(new int[]{i + 1, j});
                grid[i + 1][j] = '0';
            }
            if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                queue.add(new int[]{i, j - 1});
                grid[i][j - 1] = '0';

            }
            if (j + 1 <= n - 1 && grid[i][j + 1] == '1') {
                queue.add(new int[]{i, j + 1});
                grid[i][j + 1] = '0';
            }
        }
    }

    public static void main(String[] args) {
        char[][] chars = new char[][]{{'1'},{'1'}};
        No13_200_NumIslands instance = new No13_200_NumIslands();
        int result = instance.numIslands2(chars);
        System.out.println(result);
    }
}
