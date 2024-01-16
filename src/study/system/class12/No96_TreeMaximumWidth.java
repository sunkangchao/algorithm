package study.system.class12;

import study.base.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * No96_TreeMaximumWidth
 *
 * 求树的最大宽度，建立机制的方法去做
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 16, 2024</pre>
 */
public class No96_TreeMaximumWidth {



    public int getMaxWidth(TreeNode node) {
        TreeNode curEnd = node;
        TreeNode nextEnd = null;
        int curLevelWidth = 0;
        int maxLevelWidth = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            curLevelWidth++;
            if (curNode.left != null) {
                nextEnd = curNode.left;
                queue.add(curNode.left);
            }
            if (curNode.right != null) {
                nextEnd = curNode.right;
                queue.add(curNode.right);
            }
            if (curNode == curEnd) {
                curEnd = nextEnd;
                maxLevelWidth = Math.max(maxLevelWidth, curLevelWidth);
                curLevelWidth = 0;
            }
        }
        return maxLevelWidth;
    }

    /**
     * 使用容器的方式来解这道题目
     * Map<key, value> key为node节点 value为层数
     * @return
     */
    public int getMaxLevelWidthUsingMap(TreeNode head) {
        if (head == null) {
            return 0;
        }
        // 因为不知道每一层什么时候结束 所以这些使用map结构来存储每一层
        // 一旦层数不一样了 就知道进入到下一层了 相当于队列的元素绑着（元素和层数）两个信息
        Queue<TreeNode> queue= new LinkedList<>();
        queue.add(head);

        int currentLevel = 1;
        int curNodes = 0;
        int maxNodes = 0;

        Map<TreeNode, Integer> nodeLevelMap = new HashMap<>();
        nodeLevelMap.put(head, currentLevel);
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            Integer level = nodeLevelMap.get(curNode);
            if (curNode.left != null) {
                queue.add(curNode.left);
                nodeLevelMap.put(curNode.left, level);
            }
            if (curNode.right != null) {
                queue.add(curNode.right);
                nodeLevelMap.put(curNode.right, level);
            }
            if (currentLevel == level) {
                curNodes++;
            } else {
                // 说明到了下一层
                currentLevel++;
                maxNodes = Math.max(maxNodes, curNodes);
                curNodes = 1;
            }
        }
        // 最后一层 这里需要注意 因为没有节点了 元素遍历完了 但是没有更新最后一层的宽度
        maxNodes = Math.max(maxNodes, curNodes);
        return maxNodes;
    }

    /**
     * leetcode上的办法 最快解决二叉树最大宽度问题
     * @param node
     * @return
     */
    public int getMaxWidth2(TreeNode node) {
        if (node == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            maxWidth = Math.max(maxWidth, currentLevelSize);
            while (currentLevelSize > 0) {
                TreeNode curNode = queue.poll();
                if (curNode.left != null) {
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                }
                currentLevelSize--;
            }
        }
        return maxWidth;
    }

}
