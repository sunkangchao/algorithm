package leetcode.top100;

import com.sun.org.apache.xalan.internal.lib.NodeInfo;
import leetcode.top100.base.TreeNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * No89_662_widthOfBinaryTree
 *
 * 662. 二叉树最大宽度
 *
 * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
 *
 * 树的 最大宽度 是所有层中最大的 宽度 。
 *
 * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
 *
 * 题目数据保证答案将会在  32 位 带符号整数范围内。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>08月 01, 2025</pre>
 */
public class No89_662_widthOfBinaryTree {


    // 思路一：广度优先遍历，给每个节点编号
    // 每遍历完一层，去最左和最右节点的编号相减再加1，即为当前层级的宽度
    // 数据结构：双端队列实现
    // 注意：1）需要考虑好双端队列该往哪边add，哪边poll，
    // 2）传统的广度遍历，节点在队列中的顺序刚好是反的。
    // 3）完全二叉树/满二叉树，根节点从0开始，左节点位置2*n+1,右节点位置2*n+2
    //     根节点从1开始，左节点位置2*n，右节点位置2*n+1
    //
     // 总的来说，这两种方式实现的关键都是对节点进行编号，然后利用编号相减来求得宽度
    public int widthOfBinaryTree1(TreeNode root) {

        // 校验
        if (root == null) {
            return 0;
        }

        // 定义双端队列 最大宽度变量
        Deque<NodeInfo> queue = new LinkedList<>();
        queue.offer(new NodeInfo(root, 0));
        int maxWidth = 0;

        // 每层统计当前最大宽度 更新变量
        while (!queue.isEmpty()) {

            NodeInfo peekLast = queue.peekLast();
            NodeInfo peekFirst = queue.peekFirst();
            maxWidth = Math.max(maxWidth, peekLast.index - peekFirst.index + 1);

            int size = queue.size();
            while (size-- > 0) {
                NodeInfo nodeInfo = queue.pollFirst();
                TreeNode node = nodeInfo.node;
                if (node.left != null) {
                    queue.addLast(new NodeInfo(node.left, nodeInfo.index * 2 + 1));
                }
                if (node.right != null) {
                    queue.addLast(new NodeInfo(node.right, nodeInfo.index * 2 + 2));
                }
            }

        }

        return maxWidth;
    }


    private static class NodeInfo {
        TreeNode node;
        int index;

        NodeInfo(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }


    // 深度优先解法
    // 每一层都记录它最左边的节点的编号，然后每次到达相同层级，进行一遍相减，更新最大值
    // 分清楚这dfs, bfs遍历的顺序 搞清楚都好说
    private int maxWidth = 0;

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, 0, 0, new HashMap<>());
        return maxWidth;
    }

    private void dfs(TreeNode node, int level, int i, Map<Integer, Integer> map) {

        Integer index = map.get(level);
        if (index == null) { // 当前层级第一个节点
            map.put(level, i);
        } else {
            // 更新当前最大值
            int curDis = i - index + 1;
            maxWidth = Math.max(maxWidth, curDis);
        }

        if (node.left != null) {
            dfs(node.left, level + 1, i * 2 + 1, map);
        }

        if (node.right != null) {
            dfs(node.right, level + 1, i * 2 + 2, map);
        }
    }


    public static void main(String[] args) {
        No89_662_widthOfBinaryTree obj = new No89_662_widthOfBinaryTree();

        TreeNode node0 = new TreeNode(1);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(9);

        node0.left = node1;
        node0.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;

        int i = obj.widthOfBinaryTree(node0);
        System.out.println(i);


    }




}
