package study.system.class11;

import study.base.TreeNode;

/**
 *
 * 递归方式实现先序遍历、中序遍历、后序遍历
 * No85_TreeForEach
 *
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 16, 2024</pre>
 */
public class No85_TreeForEach {

    public void pre(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        pre(node.left);
        pre(node.right);
    }

    public void in(TreeNode node) {
        if (node == null) {
            return;
        }
        in(node.left);
        System.out.println(node.val);
        in(node.right);
    }

    public void after(TreeNode node) {
        if (node == null) {
            return;
        }
        after(node.left);
        after(node.right);
        System.out.println(node.val);
    }

}
