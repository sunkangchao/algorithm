package leetcode.top100;

/**
 * No77_470_Rand10
 *
 * 470. 用 Rand7() 实现 Rand10()
 *
 * 给定方法 rand7 可生成 [1,7] 范围内的均匀随机整数，试写一个方法 rand10 生成 [1,10] 范围内的均匀随机整数。
 *
 * 你只能调用 rand7() 且不能调用其他方法。请不要使用系统的 Math.random() 方法。
 *
 * 每个测试用例将有一个内部参数 n，即你实现的函数 rand10() 在测试时将被调用的次数。请注意，这不是传递给 rand10() 的参数。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>06月 17, 2025</pre>
 */
public class No77_470_Rand10 {


    /**
     * 拒绝采样方式，以及0/1进制 + 拒绝采样，这两种都是通用的转换方式。
     *
     * 1）拒绝采样策略：利用rank7()获取rank2()以及rank5()随机生成器，然后映射成rank10即可。
     * 2）01进制 + 拒绝采样，采用编码的方式生成目标随机生成器，先生成01随机生成器，对于编码生成多出来的部分采用拒绝采样，重新生成。
     *
     * @return
     */
    // 拒绝采样方式
    public int _rand10() {
        int first, second;
        while ((first = rand7()) > 2);
        while ((second = rand7()) > 5);
        return (first - 1) * 5 + second;
    }

    private int rand7()  {
        // 系统默认提供
        return 0;
    }


    // 01进制 + 拒绝采样方式实现
    public int rand10() {
        int cur;
        do {
            cur = 0;
            int len = 4;
            for (int i = 0; i < len; i++) {
                cur = cur << 1 | rank1();
            }
        } while (cur > 10 || cur == 0);
        return cur;
    }

    // 由rank7获取01生成器
    public int rank1() {
        int idx;
        while ((idx = rand7()) > 6);
        return (idx & 1) == 1 ? 1 : 0;
    }


}
