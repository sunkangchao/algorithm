package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SunKangChao
 * @date 2022/3/25 01:12
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> elementMap = new HashMap<>(nums.length);
        for (int index = 0; index < nums.length; index++) {
            int element = nums[index];
            boolean contains = elementMap.containsKey(target - element);
            if (contains) {
                return new int[]{elementMap.get(target - element), index};
            }
            elementMap.put(element, index);
        }
        return null;
    }

}
