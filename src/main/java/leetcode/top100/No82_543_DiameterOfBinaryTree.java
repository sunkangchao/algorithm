package leetcode.top100;

import leetcode.top100.base.TreeNode;

import java.util.Random;
import java.util.UUID;

/**
 * No82_543_DiameterOfBinaryTree
 *
 * 543. 二叉树的直径
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 *
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 *
 * 两节点之间路径的 长度 由它们之间边数表示。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>07月 09, 2025</pre>
 */
public class No82_543_DiameterOfBinaryTree {

    // 二叉树直径
    private int maxLen;

    // dfs
    // 每个节点求解它的高度，根据高度求出经过当前节点的最大长度即可
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return maxLen;
    }

    // 提供一个求解，如何组织这个函数？什么功能？
    // 原函数行不行，为什么？原函数求解的是直径，这个函数求解的是高度，且顺便更新直径
    // 注意：最大直径等于 = 左子树高度 + 右子树高度，不用再减去2，因为左子树高度并不包含根节点本身
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = dfs(node.left);
        int rightHeight = dfs(node.right);

        // 知道左右高度，求解它的直径，并且返回最大高度
        maxLen = Math.max(maxLen, leftHeight + rightHeight);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }

}
