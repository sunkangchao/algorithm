package examination;

import examination.basic.TreeNode;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 求二叉树的层序遍历
 *
 * @author SunKangChao
 * @date 2021/8/19 23:16
 */
public class LevelOrder {

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // write code here
        if (Objects.isNull(root)) {
            return new ArrayList<>();
        }
        ArrayList<TreeNode> nodes = new ArrayList<TreeNode>() {{
            add(root);
        }};
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        levelOrder(result, nodes);
        return result;

    }

    public void levelOrder(ArrayList<ArrayList<Integer>> result, ArrayList<TreeNode> nodes) {
        if (nodes.isEmpty()) {
            return;
        }
        ArrayList<Integer> saved = new ArrayList<>();
        nodes.forEach(node -> {
            saved.add(node.val);
        });
        result.add(saved);

        ArrayList<TreeNode> list = new ArrayList<>();
        nodes.forEach(node -> {
            if (node.left != null) {
                list.add(node.left);
            }
            if (node.right != null) {
                list.add(node.right);
            }
        });
        levelOrder(result, list);
    }
}
