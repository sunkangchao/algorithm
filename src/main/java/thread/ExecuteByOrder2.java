package thread;

import java.util.concurrent.Semaphore;

/**
 * 按顺序输出
 *
 * @author SunKangChao
 * @date 2021/8/12 01:21
 */
public class ExecuteByOrder2 {

    //通过信号量的方式实现线程按指定顺序执行

    private final Semaphore semaphore1 = new Semaphore(1);
    private final Semaphore semaphore2 = new Semaphore(0);
    private final Semaphore semaphore3 = new Semaphore(0);

    private void first() throws InterruptedException {
        //阻塞操作
        semaphore1.acquire();
        System.out.println("first执行");
        semaphore2.release();
    }

    private void second() throws InterruptedException {
        semaphore2.acquire();
        System.out.println("second执行");
        semaphore3.release();
    }

    private void third() throws InterruptedException {
        semaphore3.acquire();
        System.out.println("third执行");
        semaphore1.release();
    }

    public static void main(String[] args) {
        ExecuteByOrder2 instance = new ExecuteByOrder2();
        new Thread(() -> {
            try {
                while (true) {
                    instance.first();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    instance.second();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    instance.third();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }


}
