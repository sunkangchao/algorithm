package study;

import study.base.GenerateTreeNode;

import java.util.Objects;

/**
 * 是否为镜面树
 *
 * @author sunkangchao
 * @date 2022/7/20 01:21
 */
public class Code14_IsMirrorTree {


    public boolean isMirrorTree(GenerateTreeNode<Integer> head) {
        if (head == null) {
            return true;
        }
        // 根节点不为空，那就判断左右节点是否为空
        if (head.left == null ^ head.right == null || head.left == null) {
            return false;
        }
//        if (head.left == null && head.right != null || head.left != null && head.right == null) {
//            return false;
//        }
        // 否则继续比较
        return Objects.equals(head.left.value, head.right.value) && isMirrorTree(head.left) && isMirrorTree(head.right);

    }

    public boolean isMirrorTree2(GenerateTreeNode<Integer> head) {
        return isMirrorTree2(head, head);
    }

    public boolean isMirrorTree2(GenerateTreeNode<Integer> head1, GenerateTreeNode<Integer> head2) {
        // 根节点不为空，那就判断左右节点是否为空
        if (head1 == null ^ head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        return head1.value.equals(head2.value) && isMirrorTree2(head1.left, head2.right) && isMirrorTree2(head1.right, head2.left);

    }

    public boolean isSameTree(GenerateTreeNode<Integer> head) {
        if (head == null) {
            return true;
        }
        if (head.left == null ^ head.right == null) {
            return false;
        }
        if (head.left == null) {
            return true;
        }
        // 左节点及右节点都不会空的情况
        return head.left.value.equals(head.right.value) && isSameTree(head.left) && isSameTree(head.right);

    }

    // 返回一颗树的最大深度
    public int maxDepth(GenerateTreeNode<Integer> head) {
        if (head == null) {
            return 0;
        }
        int leftDepth = maxDepth(head.left);
        int rightDepth = maxDepth(head.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }


    public static void main(String[] args) {
        GenerateTreeNode<Integer> head = new GenerateTreeNode<Integer>(1);
        GenerateTreeNode<Integer> nod2 = new GenerateTreeNode<Integer>(2);
        GenerateTreeNode<Integer> node3 = new GenerateTreeNode<Integer>(3);
        GenerateTreeNode<Integer> node4 = new GenerateTreeNode<Integer>(4);
        GenerateTreeNode<Integer> node5 = new GenerateTreeNode<Integer>(5);
        GenerateTreeNode<Integer> node6 = new GenerateTreeNode<Integer>(6);




    }


}
