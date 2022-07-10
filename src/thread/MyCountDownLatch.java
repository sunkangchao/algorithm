package thread;

import java.util.concurrent.CountDownLatch;

/**
 * countdownlatch用法
 *
 * @author sunkangchao
 * @date 2022/7/10 22:18
 */
public class MyCountDownLatch {

    public static void main(String[] args) {
        int size = 10;
        CountDownLatch countdownlatch = new CountDownLatch(10);

        // 等十个线程一块执行
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                try {
                    countdownlatch.await();
                    System.out.println("i am a thread " + size);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            // 每启动完一个线程，count数-1；
            countdownlatch.countDown();
        }

    }
}
