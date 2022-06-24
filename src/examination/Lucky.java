package examination;

import java.util.Scanner;

/**
 * @author SunKangChao
 * @date 2021/7/8 22:34
 */
public class Lucky {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        String[] persons = new String[6];
        while (n < 6){
             String input = sc.next();
             persons[n] = input;
             n++;
        }

        //获取人员名单  计算嘉宾幸运值
        int score0 = count(persons[0]);
        //定义最小值
        int min = Integer.MAX_VALUE;
        //定义索引位
        int index = 0;
        for (int i = 1; i < persons.length; i++) {
            //当前人员幸运值与嘉宾差值
            int score = Math.abs(count(persons[i]) - score0);

            if (score < min) {
                index = i;
                min = score;

            } else if (score == min) {
                index = persons.length - 1;
            }
        }

        System.out.println(persons[index]);

    }

    public static int count(String person) {
        String lowerName = person.toLowerCase();
        int score = 0;
        for (int i = 0; i < lowerName.length(); i++) {
            score += lowerName.charAt(i) - 96;
        }
        return score;
    }

}
