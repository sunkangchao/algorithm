package study.system.class16;

/**
 *
 * 并查集解法
 * 给你一个二维数组 查找朋友圈/省份的个数
 * No126_01FriendCycles
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 19, 2024</pre>
 */
public class No126_01FriendCycles {


    public int findFriendCycles(int[][] findCycles) {
        int size;
        if (findCycles == null || (size = findCycles.length) == 0) {
            return 0;
        }
        UnionFind unionFind = new UnionFind(size);
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (findCycles[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets;
    }

    private class UnionFind {

        private int[] parents;
        private int[] sizes;
        private int sets;

        public UnionFind(int n) {
            parents = new int[n];
            sizes = new int[n];
            // 初始化
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                sizes[i] = 1;
                sets = n;
            }
        }

        public int findParent(int i) {
            if (i != parents[i]) {
                parents[i] = findParent(parents[i]);
            }
            return parents[i];
        }

        public void union(int i, int j) {
            int p1 = findParent(i);
            int p2 = findParent(j);
            if (p1 != p2) {
                // 小挂到大
                int size1 = sizes[p1];
                int size2 = sizes[p2];
                if (size1 > size2) {
                    parents[p2] = p1;
                    sizes[p1] = size1 + size2;
                    sizes[p2] = 0;
                } else {
                    parents[p1] = p2;
                    sizes[p2] = size1 + size2;
                    sizes[p1] = 0;
                }
                // 联合一次 少一个集合
                sets--;
            }
        }
    }


}
