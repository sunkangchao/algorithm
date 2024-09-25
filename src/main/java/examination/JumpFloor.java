package examination;

import java.util.Arrays;

/**
 * 青蛙跳台阶
 * 一个算法的好坏应该是从时间复杂度和算法复杂度来衡量的
 * <p>
 * 光是教光是听是学不会的，一定要动用自己的思维去思考，这样得到的结果才是属于你的，编程的关键！
 *
 * @author SunKangChao
 * @date 2021/8/19 23:27
 */
public class JumpFloor {

    //动态规划解法
    public int jumpFloor(int target) {
        //定义dp[n] = dp[n-1] + dp[n-2];
        if (target <= 1) {
            return 1;
        }

        int[] dp = new int[target + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= target; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[target];
    }

    //变态跳台阶
    public int jumpFloor2(int target) {
        if (target <= 1) {
            return 1;
        }
        int[] dp = new int[target + 1];
        for (int i = 2; i <= target; i++) {
            int result = Arrays.stream(dp).reduce(Integer::sum).getAsInt();
            dp[i] = result;
        }

        return dp[target];
    }

    //变态跳台阶（递归解法）
    public int jumpFloor3(int target) {
        if (target <= 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        int result = 0;
        for (int i = target - 1; i > 0; i--) {
            result += jumpFloor3(i);
        }
        return result;
    }


    //变态跳台阶（递归解法）
    public int jumpFloor4(int target) {
        if (target <= 1) {
            return 1;
        }

        int n1 = 1;
        int n2 = 2;

        int result = 0;
        for (int i = 2; i < target; i++) {
            result = n1 + n2;
            n1 = n2;
            n2 = result;
        }
        return result;
    }


}
