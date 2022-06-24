package examination;



import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author SunKangChao
 * @date 2021/7/8 23:10
 */
public class Matrix {

//        Vector 线程安全的ArrayList
//    StringBuffer,StringBuilder
//        LinkedHashMap
//    SimpleChannelInboundHandler

    //nextLine与hasNextLine的应用场景   读不到数据就会阻塞
    //nextInt...next都是不会把空格、tab、回车符\r读取出来的  只有nextLine会把回车符读出来并且扔掉回车符
    //next不会读取空白内容  空格、tab、回车都可以终止一个next的读取


    //总结
    //分为两类：1) next系列(next本身就是nextString)  2）nextLine
    //      第一类只读取需要的数据，第二类什么都读出来
    // has...的问题    全部has都是一直等待输入的状态  如果想终结可以使用重载方法加入终止表达式


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        String[] matrix = new String[n];

        for(int i=0;i<matrix.length;i++){
            matrix[i] = sc.nextLine();
        }

        //读取数据完毕  开始处理数据
        int result = Integer.MAX_VALUE;
        for (String s : matrix) {
            result &= Integer.parseInt(s);
        }

        System.out.println(countOneBit(result) * n);

    }

    //计算1的位数
    public static int countOneBit(int num){
        String binaryInt = Integer.toBinaryString(num);
        int count = 0;
        for (int i=0;i<binaryInt.length();i++){
            if (binaryInt.charAt(i) == 49) count++;
        }
        return count;
    }


}
