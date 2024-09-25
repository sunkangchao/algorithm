package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * @author sunkangchao
 * @date 2022/7/10 23:18
 */
public class MyCountDownLatch2 {


    // 实现让全部线程执行完，主线程再执行
    public static void main(String[] args) {
        CountDownLatch countdownlatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("第 " + finalI + " 个线程开始执行");
                countdownlatch.countDown();
            }).start();
        }
        try {
            countdownlatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("主线程最后执行，执行完成。");
    }
}
