package leetcode;

/**
 * 是否为有效括号
 *
 * @author SunKangChao
 * @date 2022/3/30 00:23
 */
public class IsValid {
    public boolean isValid(String s) {
        // 方法一： 分多种情况来独立处理
        // 方法二： 递归（相同的子问题）
        // 方法三： 正则表达式
        // 我只算你在思考的时间，思考过程并不舒服，所以很容易懒得动脑，这是本性。

        // {(({}[{}]()))} ,({}[]())
        // 有效的括号： 外层的括号包裹的内部整体必须是成对出现的。
        // 首先你要清楚，有效的括号它的具体含义是什么。何谓正确的顺序？
        return isValid(s, 0, s.length() - 1);
    }

    public boolean isValid2(String s) {
        // 循环次数：最差的情况就是只有一种括号类型
        //   成对的去抵消，循环次数最多也就字符串长度的一半

        int loopTimes = s.length() / 2;
        for (int i = 0; i < loopTimes; i++) {
            s = s.replace("()", "")
                    .replace("[]", "")
                    .replace("{}", "");
        }
        return s.length() == 0;
    }
    // TODO 只要坚持住，你就会有质的飞跃


    // 定义区间： 左闭右闭区间
    public boolean isValid(String s, int left, int right) {
        // 左边的第一个对应右边的第一个
        // 外层满足再往里面递归，还需要注意后面的字符串是否已经遍历完
        if (left == right) {
            // 说明这是一个空字符串
            return true;
        }
        // 判断是否成对出现
        char char0 = s.charAt(left);
        int lastIndex = s.lastIndexOf(char0, right);
        if (lastIndex == -1) {
            return false;
        }

        // 递归+循环 应该如何组织代码
        boolean result = true;
//        while (lastIndex )

        return false;


    }
}
