package examination;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SunKangChao
 * @date 2021/8/3 09:58
 */
public class MergeSimilarTerms {

    private Pattern p = Pattern.compile("[-+]?\\d*X\\^\\d+");

    public static void main(String[] args) {
        // -7X^5+5X^3+8X^9
        // [-\\+]X^\\d{1}
        MergeSimilarTerms main = new MergeSimilarTerms();
        String result = main.handler("X^5+5X^3+8X^9+4X^9-X^5");

        System.out.println(result);
    }

    public String handler(String str) {

        Matcher matcher = p.matcher(str);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        //合并同类项  指数降序排列
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (String t : list) {
            String[] twoNum = t.split("X\\^");
            int suffixNum = Integer.parseInt(twoNum[1]);
            int prefixNum;
            //没有正负，没有系数
            if (twoNum[0].charAt(0) == '+' || twoNum[0].charAt(0) == '-') {
                if ('+' == twoNum[0].charAt(0)) {
                    prefixNum = Integer.parseInt(twoNum[0].substring(1));
                } else {
                    prefixNum = Integer.parseInt(twoNum[0].substring(1)) * -1;
                }
            } else {
                //3000X  X
                if ("".equals(twoNum[0])){
                    prefixNum = 1;
                }else{
                    prefixNum = Integer.parseInt(twoNum[0]);
                }
            }


            if (map.containsKey(suffixNum)) {
                List<Integer> nums = map.get(suffixNum);
                nums.add(prefixNum);
            } else {
                List<Integer> list2 = new ArrayList<Integer>() {{
                    add(prefixNum);
                }};
                map.put(suffixNum, list2);
            }
        }

        //合并
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i : map.keySet()) {
            List<Integer> integers = map.get(i);
            int sum = 0;
            for (Integer j : integers) {
                sum += j;
            }
            map1.put(i, sum);
        }

        //排序
        List<Map.Entry<Integer, Integer>> resultList = new ArrayList<>(map1.entrySet());
        resultList.sort((o1, o2) -> o2.getKey().compareTo(o1.getKey()));

        //转化成字符串
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : resultList) {
            int key = entry.getKey();
            if (key > 0) {
                if (entry.getValue() != 1) {
                    result.append("+").append(entry.getValue()).append("X^").append(entry.getKey());
                } else {
                    result.append("+" + "X^").append(entry.getKey());
                }
            } else {
                if (entry.getValue() != 1) {
                    result.append(entry.getValue()).append("X^").append(entry.getKey());
                } else {
                    result.append("X^").append(entry.getKey());
                }
            }
        }
        if (result.charAt(0) == '+') {
            result = new StringBuilder(result.substring(1));
        }

        return result.toString();

    }


    //int suffix = t.indexOf("^");
    //            int prefix = t.indexOf("X");
    //            int suffixNum = Integer.parseInt(t.substring(suffix + 1));
    //            int prefixNum;
    ////            if ('+' == (prefixStr.charAt(0)) && !"".equals(prefixStr.substring(1,prefix))) {
    ////                suffixNum = Integer.parseInt(prefixStr.substring(1));
    ////            } else if ('-' == (prefixStr.charAt(0)) && !"".equals(prefixStr.substring(1,prefix))) {
    ////                suffixNum = Integer.parseInt(prefixStr);
    ////            } else {
    ////                suffixNum = 1;
    ////            }
    //
    //            char charAt0 = t.charAt(0);
    //            String num = t.substring(1, prefix);
    //            if ("".equals(num)) {
    //                prefixNum = 1;
    //            } else {
    //                if (charAt0 == '-') {
    //                    prefixNum = -(Integer.parseInt(num));
    //                } else {
    //                    prefixNum = Integer.parseInt(num);
    //                }
    //            }
}
