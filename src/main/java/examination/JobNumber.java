package examination;

import java.util.Scanner;

/**
 * @author SunKangChao
 * @date 2021/7/20 11:32
 */
public class JobNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x = sc.nextLong();
        int y = sc.nextInt();


        //每个员工的工号长度是一致的
        long prefixNum = (long) Math.pow(26, y);
        if (prefixNum >= x) {
            System.out.println(1);
        } else {
            long num = prefixNum;
            int k = 0;
            while (num < x) {
                num = num * 10;
                k++;
            }
            System.out.println(k);
        }


    }
}
