package examination;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author SunKangChao
 * @date 2021/7/20 11:41
 */

public class Split {


    //12
    //12abc-abCABc-4aB@
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();
        String str = sc.nextLine();

        String[] inputBeforeDeal = str.split("-");
        StringBuilder sb = new StringBuilder(inputBeforeDeal[0] + "-");
        for (int i = 1; i < inputBeforeDeal.length; i++) {
            sb.append(inputBeforeDeal[i]);
        }
        String[] input = sb.toString().split("-");

        List<String> list = new ArrayList<>();
        list.add(input[0]);
        for (int i = 1; i < input.length; i++) {
            String strSource = input[i];
            int startIndex = 0;

            for (int j = 0; j < strSource.length(); j++) {
                if ((j + 1) % k == 0) {
                    String subStr = strSource.substring(startIndex, j + 1);
                    startIndex = j + 1;
                    list.add(subStr);
                }
            }

            if (startIndex < strSource.length()) {
                String subStr = strSource.substring(startIndex);
                list.add(subStr);
            }
        }

        String[] processedArr = new String[list.size()];
        list.toArray(processedArr);
        for (int i = 1; i < processedArr.length; i++) {
            String subStr = processedArr[i];

            int lowerCaseCount = 0;
            int upperCaseCount = 0;
            for (int z = 0; z < subStr.length(); z++) {
                int c = subStr.charAt(z);
                if (c >= 'a' && c <= 'z') {
                    lowerCaseCount++;
                } else if (c >= 'A' && c <= 'Z') {
                    upperCaseCount++;
                }
            }
            if (lowerCaseCount > upperCaseCount) {
                processedArr[i] = subStr.toLowerCase();
            } else if (lowerCaseCount < upperCaseCount) {
                processedArr[i] = subStr.toUpperCase();
            }
        }

        String rs = String.join("-",processedArr);
        System.out.println(rs);


    }
}
