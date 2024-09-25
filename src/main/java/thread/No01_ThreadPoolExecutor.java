package thread;



import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * No01_ThreadPoolExecutor
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 25, 2024</pre>
 */
public class No01_ThreadPoolExecutor {

    private volatile static ThreadPoolExecutor executor;


    public void getThreadPoolExecutor() {

        if (executor == null) {
            synchronized (this) {
                if (executor == null) {
                    new ThreadPoolExecutor(
                            2,
                            4,
                            60,
                            TimeUnit.SECONDS,
                            new LinkedBlockingQueue<>(100),
//                            new ThreadFactory() {
//
//                                private final AtomicInteger i = new AtomicInteger(0);
//                                @Override
//                                public Thread newThread(Runnable r) {
//                                    Thread newThread = new Thread(r, "thread-pool-" + i.incrementAndGet());
//                                    newThread.setDaemon(false);
//                                    newThread.setUncaughtExceptionHandler(Thread.getDefaultUncaughtExceptionHandler());
//                                    return newThread;
//                                }
//                            },
                            new No02_DefaultThreadFactory(),
                            new ThreadPoolExecutor.AbortPolicy()
                    );
                }
            }
        }

    }


}
