package study.system.class18;

/**
 * No167_RobotWay
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>02月 20, 2024</pre>
 */
public class No167_RobotWay {


    public static int way(int start, int reset, int aim, int N) {
        if (reset == 0) {
            return start == aim ? 1 : 0;
        }
        if (start == 1) {
            return way(start + 1, reset - 1, aim, N);
        }
        if (start == N) {
            return way(start - 1, reset - 1, aim, N);
        }
        return way(start - 1, reset - 1, aim, N) + way(start + 1, reset - 1, aim, N);
    }

    public static void main(String[] args) {
        int rs = way3(1, 3, 2, 3);
        System.out.println(rs);
    }

    // 一个二维坐标与它的值，怎么建立映射最合适？使用Map结构需要联合两维坐标来共同作为key，如果使用二维数组，则非常简单，但是数组需要确定范围
    public static int way2(int start, int reset, int aim, int N) {
        int[][] dp = new int[N + 1][reset + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < reset + 1; j++) {
                dp[i][j] = -1;
            }
        }
        return way2(start, reset, aim, N, dp);
    }

    public static int way2(int start, int reset, int aim, int N, int[][] dp) {
        if (reset == 0) {
            return start == aim ? 1 : 0;
        }
        if (dp[start][reset] != -1) {
            return dp[start][reset];
        }
        int result;
        if (start == 1) {
            result = way2(start + 1, reset - 1, aim, N, dp);
        } else if (start == N) {
            result = way2(start - 1, reset - 1, aim, N, dp);
        } else {
            result = way2(start - 1, reset - 1, aim, N, dp) + way2(start + 1, reset - 1, aim, N, dp);
        }
        dp[start][reset] = result;
        return result;
    }

    public static int way3(int start, int rest, int aim, int N) {
        if (rest == 0) {
            return start == aim ? 1 :0;
        }
        int[][] dp = new int[N][rest + 1];
        dp[aim][0] = 1;

        for (int i = 1; i < rest + 1; i++) {
            dp[0][i] = dp[1][i - 1];
            for (int j = 1; j < N - 1; j++) {
                dp[j][i] = dp[j - 1][i - 1] + dp[j + 1][i - 1];
            }
            dp[N - 1][i] = dp[N - 2][i-1];
        }

        return dp[start][rest];
    }


}
