package study.system.class16;

import java.util.LinkedList;
import java.util.Queue;

/**
 * No126_03FriendCycles
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 19, 2024</pre>
 */
public class No126_03FriendCycles {


    public int findCyclesNum(int[][] friendCycles) {
        if (friendCycles == null || friendCycles.length == 0) {
            return 0;
        }
        int friendSize = friendCycles.length;
        boolean[] friends = new boolean[friendSize];
        for (int i = 0; i < friendCycles.length; i++) {
            if (!friends[i]) {
                bfsFindCycles(friendCycles, friends, i);
                friends[i] = true;
                friendSize--;
            }
        }
        return friendSize;
    }

    private void bfsFindCycles(int[][] friendCycles, boolean[] friends, int i) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);

        while (!queue.isEmpty()) {
            Integer polled = queue.poll();
            for (int j = 0; j < friendCycles.length; j++) {
                if (friendCycles[polled][j] == 1 && !friends[j]) {
                    friends[j] = true;
                    queue.add(j);
                }
            }
        }
    }

}
