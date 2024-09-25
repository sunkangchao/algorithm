package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 腐烂的橘子
 * OrangesRotting
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 30, 2024</pre>
 */
public class OrangesRotting {


    public int orangesRotting(int[][] grid) {
        int refresh = 0;
        Queue<Pair> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new Pair(i, j));
                }
                if (grid[i][j] == 1) {
                    refresh++;
                }
            }
        }
        if (refresh == 0) {
            return 0;
        }
        int result = -1;
        while (!queue.isEmpty()) {
            int i, j;
            int size = queue.size();
            result++;
            while (size > 0) {
                Pair pair = queue.poll();
                i = pair.i;
                j = pair.j;
                if (i - 1 >=0 && grid[i -1][j] == 1) {
                    queue.add(new Pair(i - 1, j));
                    grid[i - 1][j] = 2;
                }
                if (i + 1 < grid.length && grid[i + 1][j] == 1) {
                    queue.add(new Pair(i + 1, j));
                    grid[i + 1][j] = 2;
                }
                if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                    queue.add(new Pair(i, j - 1));
                    grid[i][j - 1] = 2;
                }
                if (j + 1 < grid[i].length && grid[i][j + 1] == 1) {
                    queue.add(new Pair(i, j + 1));
                    grid[i][j + 1] = 2;
                }
                size--;
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return result;
    }

    private static class Pair {
        public int i;
        public int j;
        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }




}
