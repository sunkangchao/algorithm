package examination;

import examination.basic.TreeNode;

import java.util.ArrayList;

/**
 * 找出所有根节点到叶子节点和等于sum的路径
 *
 * @author SunKangChao
 * @date 2021/7/15 01:09
 */
public class FindPathInBinaryTree {

    public ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        // write code here
        if (root == null) {
            return resultList;
        }
        ArrayList<Integer> list = new ArrayList<Integer>() {{
            add(root.val);
        }};

        return pathSum(root, sum, list);
    }

    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum, ArrayList<Integer> list) {

        if(root.left == null && root.right == null){
            int listSum = list.stream().mapToInt(t->t).sum();
            if(listSum == sum){
                resultList.add(list);
            }
        }

        if (root.left != null) {
            pathSum(root.left,sum,new ArrayList<Integer>(){{
                addAll(list);
                add(root.left.val);
            }});
        }

        if (root.right != null){
            pathSum(root.right,sum,new ArrayList<Integer>(){{
                addAll(list);
                add(root.right.val);
            }});
        }

        return resultList;


    }


}
