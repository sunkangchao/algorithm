package examination;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 找出只出现一次数字
 *
 * @author SunKangChao
 * @date 2021/7/16 23:45
 */
public class FoundOnceNumber {

    //key有没有重复，也是用的equals()方法，所以数值不受Integer是否在-127至128之间影响
    public int foundOnceNumber(int[] arr, int k) {
        // write code here
        return bitCalc(arr,k);

    }

    //排序方法
    public int sort(int[] arr, int k) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                i += k - 1;
            } else {
                return arr[i];
            }
        }
        return arr[arr.length - 1];
    }

    //位运算
    public int bitCalc(int[] arr, int k) {
        int result = 0;
        int[] sumArray = new int[32];
        for (int i = 0; i < sumArray.length; i++) {
            int sum = 0;
            for (int num : arr) {
                sum += num >> i & 1;
            }
            sumArray[i] = sum;
        }

        for (int i = 0; i < sumArray.length; i++) {
            int sum = sumArray[i];
            if (sum % k != 0) {
                result += 1 << i;
            }
        }

        return result;
    }

    //hashMap  num
    public int hash(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        //HashMap遍历
        for (int key : map.keySet()) {
            int value = map.get(key);
            if (value == 1) {
                return key;
            }
        }
        return -1;
    }

    //hashmap true/false表示是否重复
    public int hashByBoolean(int[] arr, int k) {
        Map<Integer, Boolean> map = new HashMap<>();
        for (int num : arr) {
            if(map.containsKey(num)){
                if(!map.get(num)){
                    map.put(num,true);
                }
            }else{
                map.put(num,false);
            }
        }

        for(int key : map.keySet()){
            if(!map.get(key)){
                return key;
            }
        }


        return -1;
    }

}
