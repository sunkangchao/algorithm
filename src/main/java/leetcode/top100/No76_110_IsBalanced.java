package leetcode.top100;

import leetcode.top100.base.TreeNode;

/**
 * No76_110_IsBalanced
 *
 * 110. 平衡二叉树
 *
 * 给定一个二叉树，判断它是否是 平衡二叉树
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>06月 16, 2025</pre>
 */
public class No76_110_IsBalanced {


    // 判断一棵树是否为平衡二叉树 它的左/右子树都是平衡二叉树 且左右子树的高度差不超过1
    public boolean isBalanced1(TreeNode root) {
        // 需要两个信息，一个是高度，另一个是是否平衡 树形dp
        Info info = dfs(root);
        return info.isBalanced;
    }

    private Info dfs(TreeNode node) {
        // 注意出口信息
        if (node == null) {
            return new Info(true, 0);
        }

        // 递归计算左右子树的信息
        Info leftInfo = dfs(node.left);
        Info rigthInfo = dfs(node.right);
        int height = Math.max(leftInfo.height, rigthInfo.height) + 1;
        boolean isBalanced = leftInfo.isBalanced && rigthInfo.isBalanced && Math.abs(leftInfo.height - rigthInfo.height) <= 1;
        return new Info(isBalanced, height);
    }


    private static class Info {
        boolean isBalanced = false;
        int height = 0;

        Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }


    // 方法二：使用一个int合并表示高度和平衡两种状态 这样相对来说判断平衡更加简洁
    // 即先判断是否平衡，如果不平衡返回-1，只有平衡时返回其真实高度
    // 当然，递归可以是从顶至底，可以从底至顶，如果从顶至底需要重复判断子树的高度，时间复杂度O(n^2)；
    // 如果从底至顶，只需要判断一次，时间复杂度O(n)
    public boolean isBalanced(TreeNode root) {
        return dfs1(root) >= 0;
    }

    private int dfs1(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = dfs1(node.left);
        int rightHeight = dfs1(node.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
