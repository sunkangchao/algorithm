package thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocalTest
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>03月 27, 2025</pre>
 */
public class ThreadLocalTest {

    private static final ThreadLocal<String> t = new TransmittableThreadLocal<>();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);


    public static void main(String[] args) throws IOException {

        t.set("1111");
        System.out.println(t.get());

//        new Thread(() -> {
//            System.out.println(t.get());
//        }).start();





//        executorService.execute(() -> {
//            System.out.println("创建线程池");
//        });

        executorService.execute(TtlRunnable.get(() -> {
            String s = t.get();
            System.out.println(s);
        }));


    }



}
