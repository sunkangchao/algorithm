package leetcode.top100;


import leetcode.top100.base.TreeNode;

import java.util.*;

/**
 * 199. 二叉树的右视图
 *
 * 最近太浮躁了，学习方法错了，如果先刷思路，你压根就不很难去把代码写一遍，你只想着继续刷思路
 * 时间久了，累积的题目越来越多没写，行动起来更难上加难，会让你变得急于求成，趋易避难。
 *
 * 更要命的是，你先刷思路再写代码这种方式，慢慢已经让你远离题解了，而且也不会有耐心去看每一道题的所有解法，
 * 你只想着快快写完然后把下一题的算法也写一遍
 *
 * 所以这种刷题方式其实不适合你 你应该还是按照原来的 一道题一道题去刷去写去看题解 这种方式
 * 以精刷一道题来达到学会一类题的方法 看似效率低 实则触类旁通 举一反三。
 *
 * 刷算法切忌浮躁，脚踏实地，以一道题来触类旁通，举一反三，这样来刷题。
 *
 * No27_199_RightSideView
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 14, 2025</pre>
 */
public class No27_199_RightSideView {


    /**
     * 输入：root = [1,2,3,null,5,null,4]
     *
     * 输出：[1,3,4]
     * @param root
     * @return
     */
    public List<Integer> rightSideView1(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> result = new ArrayList<>();


        while (!queue.isEmpty()) {
            // 此处下一层的元素已添加完毕，头节点就是要添加的节点
            result.add(queue.peekFirst().val);

            int size = queue.size();
            while (size-- > 0) {
                TreeNode polled = queue.pollLast();
                if (polled.left != null) {
                    queue.offerFirst(polled.left);
                }
                if (polled.right != null) {
                    queue.offerFirst(polled.right);
                }
            }
        }

        return result;
    }


    private List<Integer> result = new ArrayList<>();

    /**
     * 方法二：深度优先搜索dfs
     * 思路：先遍历右节点再遍历左节点，如果是深度遍历，其实你是
     * 不容易拿到每一层的节点的，那么此时要你返回每一层最右侧的节点，
     * 你可以借助一个level的变量，保证每个level只有在level == result.size时
     * 才把结果添加到结果集当中，因为先右再左，能够保证每一层第一个符合的就是最右的元素。
     *
     * 为什么边界条件是level == result.size？举例子
     * 比如第1层，当前节点要添加到集合当中，那么此时size == 0，
     * 比如第2层，当前节点要添加到集合当中，此时size == 1
     * 比如第3层，当前节点要添加到结合当中，此时size == 2，以此类推
     * 如果level从0开始，那么就是level == result.size时添加到集合当中，当然不从0开始也可以。
     *
     * 疑问：有没有可能当前节点都为空，然后没有节点添加至集合，那么对于下一层来说不就轮套了吗？
     * 好的，你能问出这个问题，说明你有去思考了。你忽略了一个点，当前层被跳过的前提是它的左/右子节点都是null
     * 那么不就不会存在它的下下层这一说。
     *
     *
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 1);
        return result;
    }


    private void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        System.out.println("root = " + root.val + ", level = " + level + ", result.size = " + result.size());
        if (level - 1 == result.size()) { // 因为能够保证是从上往下遍历的，只要满足这个条件，一定是当前层级的右边第一个元素
            result.add(root.val);
        }
        dfs(root.right, level + 1);
        dfs(root.left, level + 1);
    }


        public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(4);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.right = treeNode5;
        treeNode3.right = treeNode4;

        No27_199_RightSideView obj = new No27_199_RightSideView();
        List<Integer> integers = obj.rightSideView(treeNode1);
        System.out.println(integers);
    }

}
