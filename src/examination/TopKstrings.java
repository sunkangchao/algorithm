package examination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 出现前K名的Strings  是前K名，不是前K次，看准题目
 *
 * @author SunKangChao
 * @date 2021/8/1 02:18
 */
public class TopKstrings {

    //sort()方法的规则： 依据compareTo的返回结果
    //compareTo(o1,o2)
    //从左至右按顺序传入o1,o2,compareTo返回负数，保持位置；返回正数，位置调换；返回零，位置保持不变。


    public String[][] topKstrings(String[] strings, int k) {
        // write code here
        Map<String, Integer> map = new HashMap<>(16);
        for (String str : strings) {
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o1.getValue().equals(o2.getValue())
                ? o1.getKey().compareTo(o2.getKey()) : o2.getValue().compareTo(o1.getValue()));

        String[][] result = new String[k][2];
        for (int i = 0; i < k; i++) {
            Map.Entry<String, Integer> term = list.get(i);
            result[i] = new String[]{term.getKey(), String.valueOf(term.getValue())};
        }
        return result;
    }
}
