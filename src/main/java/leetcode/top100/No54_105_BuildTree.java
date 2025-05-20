package leetcode.top100;

import leetcode.top100.base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * No54_105_BuildTree
 *
 * <p/>
 *105. 从前序与中序遍历序列构造二叉树
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 20, 2025</pre>
 */
public class No54_105_BuildTree {


    /**
     * 思路：dfs
     *
     * 只要能够从前序遍历、中序遍历中划分出每个左/右子树的边界，这棵树就通过递归的方式来构建出来。
     * 构造了根节点后，就需要构造左子树和右子树，会发现左子树和右子树也是相同的问题，只是规模变小，因此可以递归（调用自身）来解决，确定递归的出口即可。
     *
     * 问题：
     * 1）如果确定前序遍历的根节点在中序遍历中的位置？
     * 由于题意中给出每个元素是不重复的，因此可以遍历求解，但是这样的时间复杂度为O(n)
     * 或者提前遍历一次，存储在map当中。这样可以降低复杂度到O(1)
     *
     * 复杂度分析：
     * 1）时间复杂度：O(n)，数组中的每一个元素都需要构建一个对应的节点，剩余的都是常数操作，时间复杂度O(n)
     * 2）空间复杂度：O(n)，递归栈的深度为所需的额外空间，栈的深度等于二叉树的高度，最坏情况下树形成链表，空间复杂度O(n)
     *
     *
     * 注意：
     *
     * 1）
     * 前序遍历的构成：  根节点 + 左子树 + 右子树
     * 中序遍历的构成：  左子树 + 根节点 + 右子树
     * 后序遍历爹构成：  左子树 + 右子树 + 根节点
     *
     * 2）这样的重构二叉树是要要求元素不重复的，否则无法确立前序数组中的某个元素指代的是中序遍历中的哪一个元素，除非提供其它可以区分的方法。
     *
     * 清楚上面的这个构成关系，再做这道题就简单了，这才是从遍历顺序中剥离出了本质，否则你是很难理解的。
     *
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return dfs(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode dfs1(int[] preorder, int pl, int pr, int[] inorder, int il, int ir, Map<Integer, Integer> map) {
        if (pl > pr || il > ir) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[pl]);
        Integer mid = map.get(preorder[pl]);
        root.left = dfs(preorder, pl + 1, pl + mid - il, inorder, il, mid - il + il, map);
        root.right = dfs(preorder, pl + mid - il + 1, pr, inorder, mid + 1, ir, map);

        return root;
    }

    private TreeNode dfs(int[] preorder, int pl, int pr, int[] inorder, int il, int ir, Map<Integer, Integer> map) {
        if (pl > pr || il > ir) {
            return null;
        }

        Integer mid = map.get(preorder[pl]);
        TreeNode leftNode = dfs(preorder, pl + 1, pl + mid - il, inorder, il, mid - il + il, map);
        TreeNode rightNode = dfs(preorder, pl + mid - il + 1, pr, inorder, mid + 1, ir, map);

        TreeNode root = new TreeNode(preorder[pl]);
        root.left = leftNode;
        root.right = rightNode;

        return root;
    }



}
