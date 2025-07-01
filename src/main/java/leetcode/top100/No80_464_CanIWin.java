package leetcode.top100;

import java.util.HashMap;
import java.util.Map;

/**
 * No80_464_CanIWin
 *
 * 464. 我能赢吗
 * 在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和 达到或超过  100 的玩家，即为胜者。
 *
 * 如果我们将游戏规则改为 “玩家 不能 重复使用整数” 呢？
 *
 * 例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
 *
 * 给定两个整数 maxChoosableInteger （整数池中可选择的最大数）和 desiredTotal（累计和），若先出手的玩家能稳赢则返回 true ，否则返回 false 。假设两位玩家游戏时都表现 最佳 。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>07月 01, 2025</pre>
 */
public class No80_464_CanIWin {


    // 记忆化递归
    // 原问题可以拆分成不同规模的子问题，比如a必赢吗，其实就可以等价于a挨个选了一遍以后，b是否必输。而必输可以直接等同于原必赢函数取相反，这是因为如果b不是必赢的，那么对于a来说，它的表现是最佳的，必然可以找到让b必输的方法。
    // 动态规划需要明确的遍历路径
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        // 1. 校验
        if (maxChoosableInteger >= desiredTotal) { // 直接选择最大的必胜
            return true;
        }
        if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) { // 累加和不超过desired
            return false;
        }

        // 2. 递归验证
        return dfs(0, maxChoosableInteger, desiredTotal, 0, new HashMap<>(), new HashMap<>());
    }

    // 定义递归函数：当前状态下，是否必胜
    // 参数：当前状态（哪些数值已被选择，用位表示），最大可选整数，目标值，当前和
    // 整个选择过程都可以拆分成多个判断当前状态下是否必胜的子问题
    private boolean dfs(int state, int maxChoosableInteger, int desiredTotal, int cur, Map<Integer, Boolean> map, Map<Integer, Boolean> map1) {

        if (map.containsKey(state)) {
            return map.get(state);
        }
        // 遍历maxChoosableInteger，判断当前是否满足必胜条件
        for (int i = 1; i <= maxChoosableInteger; i++) {
            if (((1 << i) & state) != 0) {
                continue; // 说明已经被选择过
            }
            if (cur + i >= desiredTotal) {
                map.put(state, true);
                return true; // 这里直接满足必胜
            }

            // 如果不满足必胜，则遍历取每个数值时，它是否为非必胜的（B非必胜，对于A来说就是必胜的）
            if (!dfs2((1 << i) | state, maxChoosableInteger, desiredTotal, cur + i, map, map1)) {
                map.put(state, true);
                return true;
            }
        }

        // 如果都没有满足必胜的，那就返回false
        map.put(state, false);
        return false;
    }


    // 是否必输函数
    private boolean dfs2(int state, int maxChoosableInteger, int desiredTotal, int cur, Map<Integer, Boolean> map, Map<Integer, Boolean> map1) {
        if (map1.containsKey(state)) {
            return map1.get(state);
        }

        // 遍历maxChoosableInteger，判断当前是否满足必胜条件
        for (int i = 1; i <= maxChoosableInteger; i++) {
            if (((1 << i) & state) != 0) {
                continue; // 说明已经被选择过
            }
            if (cur + i >= desiredTotal) {
                map1.put(state, false);
                return false; // 这里直接满足不是必输的
            }

            // 如果存在b不是必赢的，那么a就不是必输的
            if (!dfs((1 << i) | state, maxChoosableInteger, desiredTotal, cur + i, map, map1)) {
                map1.put(state, false);
                return false;
            }
        }

        // 如果上面都不满足，说明a时必输的
        map1.put(state, true);
        return true;

    }



}
