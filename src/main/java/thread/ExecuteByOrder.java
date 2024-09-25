package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 按顺序执行
 *
 * @author SunKangChao
 * @date 2021/6/21 02:28
 */
public class ExecuteByOrder {

    private volatile int flag = 2;

    //使用一个Object对象作为锁  synchronize里面锁定这个对象作为锁即可
    private Object object = new Object();

    //这个对象每个线程都应该用同一个，否则锁就没有意义了。
    private static final ExecuteByOrder EXECUTE_BY_ORDER = new ExecuteByOrder();

//    //采用ReentrantLock解法
//    private ReentrantLock reentrantLock = new ReentrantLock();
//    private Condition condition = reentrantLock.newCondition();
//    //reentrantLock.lock(),reentrantLock.unlock;
//    //conditional.await(); condition.signal();

    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition consumerCondition = reentrantLock.newCondition();


    public void printFirst(Runnable r) throws InterruptedException {
        reentrantLock.lock();
        try {
            while (flag != 0) {
                consumerCondition.await();
            }
            r.run();
            flag++;
            consumerCondition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void printSecond(Runnable r) throws InterruptedException {
        reentrantLock.lock();
        try {
            while (flag != 1) {
                consumerCondition.await();
            }
            r.run();
            flag--;
            consumerCondition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }


//    public void printSecond(Runnable r) throws InterruptedException {
//        synchronized (this) {
//            while (flag != 1) {
//                this.wait();
//            }
//            r.run();
//            flag++;
//            this.notifyAll();
//        }
//    }

    public void printThird(Runnable r) throws InterruptedException {
        reentrantLock.lock();
        try {
            while (flag != 2) {
                //原子级地释放锁资源
                consumerCondition.await();
            }
            r.run();
            flag--;
            consumerCondition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void printFour(Runnable r) throws InterruptedException {
        reentrantLock.lock();
        try{
            while(flag != 3){
                consumerCondition.await();
            }
            r.run();
            flag--;
            consumerCondition.signal();
        }finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread threadA = new Thread() {
            @Override
            public void run() {
                try {
                    EXECUTE_BY_ORDER.printFirst(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("线程A执行");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread threadB = new Thread() {
            @Override
            public void run() {
                try {
                    EXECUTE_BY_ORDER.printSecond(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("线程B执行");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread threadC = new Thread() {
            @Override
            public void run() {
                try {
                    EXECUTE_BY_ORDER.printThird(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("线程C执行");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        threadA.start();
        threadB.start();
        threadC.start();

    }
}
