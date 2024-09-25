package study.base;

/**
 * @author sunkangchao
 * @date 2022/7/20 01:20
 */
public class GenerateTreeNode<T> {

    public T value;

    public GenerateTreeNode<T> left;

    public GenerateTreeNode<T> right;

    public GenerateTreeNode(T value) {
        this.value = value;
    }

}
