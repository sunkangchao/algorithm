package examination;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和(时间复杂度为O(n))
 * 假设只存在一个解
 * @author SunKangChao
 * @date 2021/7/29 01:22
 */
public class TwoSum {

    public int[] twoSum (int[] numbers, int target) {
        // write code here
        //hashMap解法
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int temp = target - numbers[i];
            if(!map.containsKey(temp)){
                map.put(numbers[i],i+1);
            }else {
                return new int[]{map.get(temp),i+1};
            }
        }
        return null;
    }
}
