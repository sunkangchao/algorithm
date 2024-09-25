package study.newhand;

/**
 * 位图实现
 *
 * @author sunkangchao
 * @date 2022/7/22 15:37
 */
public class Code26_MyBitMap {


    private final long[] val;

    public Code26_MyBitMap(int nums) {
        // 扣边界，通过演算得出应该(nums+63)/64位数组长度
        val = new long[(nums + 63) / 64];
    }


    // 提供get方法
    public boolean contains(int num) {
        // 我们要算出它在哪个数组，并且算出它在哪个索引位
        return (val[num / 64] & (1L << (num & 63))) != 0;

    }

    // 提供set方法
    public void setBit(int num) {
        int i = num / 64;
        val[i] = val[i] | (1L << (num & 63));
    }

    // 提供删除方法
    public void deleteBit(int num) {
        int i = num / 64;
        val[i] = val[i] & ~(1L << (num & 63));
    }


    public static void main(String[] args) {
        Code26_MyBitMap myBitMap = new Code26_MyBitMap(128);
        myBitMap.setBit(127);
        myBitMap.setBit(100);
        System.out.println(myBitMap.contains(127));  // true
        System.out.println(myBitMap.contains(100));  // true
        myBitMap.deleteBit(127);
        System.out.println(myBitMap.contains(127)); // false
    }



}
