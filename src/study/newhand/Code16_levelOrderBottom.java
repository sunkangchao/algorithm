package study.newhand;

import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树按层遍历并收集节点
 *
 * @author sunkangchao
 * @date 2022/7/21 01:20
 */
public class Code16_levelOrderBottom {

    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }


    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // 广度优先搜索，按层循环
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> temp = new LinkedList<>();
        temp.add(root);

        while (!temp.isEmpty()) {
            int size = temp.size();
            List<Integer> curAns = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = temp.poll();
                curAns.add(curNode.value);
                if (curNode.left != null) {
                    temp.push(curNode.left);
                }
                if (curNode.right != null) {
                    temp.push(curNode.right);
                }
            }
            ans.add(0, curAns);
        }
        return ans;
    }




}
