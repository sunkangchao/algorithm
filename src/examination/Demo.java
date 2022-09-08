package examination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sunkangchao
 * @date 2022/9/7 14:37
 */
public class Demo {




    // 1. good and gbc
    // 2. m * 10 + n % 10;
    // 3. (end - begin) * (Math.PI * d);


    // int[]{1,4,2,3} -- 4

    public static int maxProfit(int[] arr) {
        int[] flag = new int[arr.length + 1];
        int[] dp = new int[arr.length + 1];
        // dp[i] = flag[i - 1] != 1 ? dp[i - 1] + ((arr[i] - arr[i -1]) > 0 ? arr[i] - arr[i - 1] : 0) ? dp[i - 1];

        flag[0] = 0;
        dp[0] = 0;

        for (int i = 1; i <= arr.length; i++) {
            int temp = arr[i] - arr[i - 1];
            if (flag[i - 1] != 1) {
                if (temp > 0) {
                    flag[i] = 1;
                    dp[i] = dp[i - 1] + temp;
                }
            } else {
                flag[i] = 0;
                dp[i] = dp[i - 1];
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }



    public static class Node {
        int value;
        Node left;
        Node right;
    }

    public static void printNodes(Node root) {
        if (root == null) {
            return;
        }
        printNode(new ArrayList<Node>() {{
            add(root);
        }});
    }

    public static void printNode(List<Node> nodes) {
        List<Node> nextList = new ArrayList<>();
        for (Node node : nodes) {
            System.out.print(node.value + " ");
            if (node.left != null) {
                nextList.add(node.left);
            }
            if (node.right != null) {
                nextList.add(node.right);
            }
        }
        System.out.println();
        printNode(nextList);
    }


    // 邀请者 id, user_id, phone_number, activity_id, created_at, updated_at, is_valid
    // 受邀者 id, user_id, phone_number, activity_id, inviter_id, created_at, updated_at, is_valid 4247483647

    // 奖励  id, user_id, activity_id, task_id, reward_pool_id, amount, number, status, created_at, updated_at, is_valid

    // 任务完成情况
    // 提现







}
