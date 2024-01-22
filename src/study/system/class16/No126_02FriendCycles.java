package study.system.class16;

/**
 * 深度优先遍历
 * No126_02FriendCycles
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 19, 2024</pre>
 */
public class No126_02FriendCycles {


    public int findCyclesNum(int[][] friendCycles) {

        if (friendCycles == null || friendCycles.length == 0) {
            return 0;
        }
        boolean[] friends = new boolean[friendCycles.length];
        int result = 0;
        for (int i = 0; i < friendCycles.length; i++) {
            if (!friends[i]) {
                dfsFindFriends(friendCycles, friends, i);
                result++;
            }
        }
        return result;
    }


    private void dfsFindFriends(int[][] friendCycles, boolean[] friends, int i) {
        for (int j = 0; j < friends.length; j++) {
            if (friendCycles[i][j] == 1 && !friends[j]) {
                friends[j] = true;
                dfsFindFriends(friendCycles, friends, j);
            }
        }
    }




}
