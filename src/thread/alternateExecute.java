package thread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.Buffer;
import java.nio.channels.SelectionKey;

/**
 * 交替输出
 *
 * @author SunKangChao
 * @date 2021/6/21 02:27
 */
public class alternateExecute {

    //多线程交替输出的核心在于每次线程执行完一次就休眠一段时间，让其它线程去执行。

    public static void main(String[] args) {
        int[] n = {3};
        int[] m = {3};
        Thread threadA = new Thread(){
            @Override
            public void run() {
                while(n[0]>0){
                    FooBar.foo();
                    n[0]--;
                    try {
                        sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        Thread threadB = new Thread(){
            @Override
            public void run() {
                while(m[0]>0){
                    FooBar.bar();
                    m[0]--;
                    try {
                        sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        threadA.start();
        threadB.start();


    }

    public static class FooBar{
        public static void foo(){
            System.out.print("foo");
        }

        public static void bar(){
            System.out.print("bar");
        }



    }
}
