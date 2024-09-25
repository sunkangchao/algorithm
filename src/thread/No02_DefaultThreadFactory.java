package thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * No02_DefaultThreadFactory
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 25, 2024</pre>
 */
public class No02_DefaultThreadFactory implements ThreadFactory {

    private final static AtomicLong poolNumber = new AtomicLong(1);
    private final ThreadGroup threadGroup;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String prefix;

    public No02_DefaultThreadFactory() {
        threadGroup = Thread.currentThread().getThreadGroup();
        prefix = "pool-" + poolNumber.getAndIncrement()
                + "thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(threadGroup, r,
                prefix + threadNumber.getAndIncrement(),
                0);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }
}
