package study;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据先序遍历和中序遍历数组重构一棵树
 *
 * @author sunkangchao
 * @date 2022/7/20 02:36
 */
public class Code15_BulidTree {

    public class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    public TreeNode buildTree(int[] arr1, int start1, int end1, int[] arr2, int start2, int end2, Map<Integer, Integer> map) {
        // 首先这是任意一棵树，所以我们要先判断边界条件
        if (start1 > end1) {
            return null;
        }
        if (start1 == end1) {
            return new TreeNode(arr1[start1]);
        }

        // 先不考虑它们节点相同的情况
        int targetIndex = map.get(arr1[start1]);
        // 左子树的先序部分以及左子树的中序部分
        TreeNode leftNode = buildTree(arr1, start1 + 1, start1 + targetIndex - start2, arr2, start2, targetIndex - 1, map);
        // 右子树的先序部分以及右子树的中序部分
        TreeNode rightNode = buildTree(arr1, start1 + targetIndex - start2 + 1, end1, arr2, targetIndex + 1, end2, map);
        TreeNode node = new TreeNode(arr1[start1]);
        node.left = leftNode;
        node.right = rightNode;
        return node;
    }

}
