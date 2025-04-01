package leetcode.top100;

import baseclass.base.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * No18_236_LowestCommonAncestor
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 01, 2025</pre>
 */
public class No18_236_LowestCommonAncestor {


    /**
     * 方法一：
     * 深度优先搜索dfs, 每层遍历返回flag, flag定义为当前节点或其子节点是否存在p或者q节点
     * 并且只有一个节点会满足，它是公共父节点，一旦找到公共父节点，那么后续递归回来的父节点就不可能再满足
     * 1）判断是否为父节点的条件，leftInfo.flag && rightInfo.flag || (node.val == p.val || node.val == q.val) && (leftInfo.flag || rightInfo.flag)
     * 2）判断当前节点是否包含p或者q节点，leftInfo.flag || rightInfo.flag || (node.val == p.val || node.val == q.val)
     *
     * 注意：由于p和q的值不同，且唯一存在，一旦左子树找到的是p，右子树也找到的话，右子树找到的必然是q
     *
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        return f(root, p, q).parent;
    }

    public Info f(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return new Info(null, false);
        }
        Info leftInfo = f(node.left, p, q);
        Info rightInfo = f(node.right, p, q);

        TreeNode parent = leftInfo.parent != null ? leftInfo.parent : rightInfo.parent;
        boolean flag = false;
        if (parent == null && (leftInfo.flag && rightInfo.flag) || (node.val == p.val || node.val == q.val) && (leftInfo.flag || rightInfo.flag)) {
            parent = node;
        }
        flag = leftInfo.flag || rightInfo.flag || (node.val == p.val || node.val == q.val);
        return new Info(parent, flag);
    }

    static class Info {
        // 公共祖先节点
        TreeNode parent;
        // 是否找到其中一个节点
        boolean flag;

        Info(TreeNode parent, boolean flag) {
            this.parent = parent;
            this.flag = flag;
        }
    }


    /**
     * 方法二：
     * 存储所有节点的父节点，利用hashMap存储起来
     * 然后把p的父节点全部添加到set集合当中，接着遍历q的父节点，一旦发现q的父节点在set中出现过，那么此时的父节点就是最近公共祖先
     */

    private Map<TreeNode, TreeNode> map = new HashMap<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        dfs(root);

        Set<TreeNode> set = new HashSet<>();
        while (p != null) {
            set.add(p);
            p = map.get(p);
        }

        // 遍历q节点
        while (q != null) {
            if (set.contains(q)) {
                return q;
            }
            q = map.get(q);
        }

        return null;
    }

    private void dfs(TreeNode node) {
        if (node.left != null) {
            map.put(node.left, node);
            dfs(node.left);
        }
        if (node.right != null) {
            map.put(node.right, node);
            dfs(node.right);
        }
    }



    public static void main(String[] args) {
        // root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);

        node3.left = node5;
        node3.right = node1;
        node5.left = node6;
        node5.right = node2;
        node1.left = node0;
        node1.right = node8;
        node2.left = node7;
        node2.right = node4;

        No18_236_LowestCommonAncestor obj = new No18_236_LowestCommonAncestor();
        TreeNode treeNode = obj.lowestCommonAncestor(node3, node5, node4);
        System.out.println(treeNode.val);


    }
}
