package examination;

import java.util.ArrayList;
import java.util.List;

/**
 * 删除数（有序数组）
 *
 * @author SunKangChao
 * @date 2021/8/5 03:39
 */
public class RemoveNum {

    public int removeNum(int n) {

        //n转化成数组
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        // [,,,4,,,]   3  6
        List<Integer> list = new ArrayList<>();
        int index = 0;
        int count = 0;
        while (list.size() < arr.length - 1) {
            //不包含才把添加进去
            if (!list.contains(arr[index])) {
                count++;
            }
            //count计数处理
            if (count == 3) {
                list.add(arr[index]);
                count = 0;
            }
            //索引处理
            if (index != arr.length - 1) {
                index++;
            } else {
                index = 0;
            }
        }

        for (int i = 0; i < arr.length - 1; i++) {
            if (!list.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        RemoveNum instance = new RemoveNum();
        int result = instance.removeNum(8);
        System.out.println(result);
    }

}
