package thread;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * No03_CompletableFuture
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>01月 25, 2024</pre>
 */
public class No03_CompletableFuture {



    public static void completableFuture() throws IOException, ExecutionException, InterruptedException {

        Runnable r1 = () -> {
            try {
                System.out.println("r1, threadName: " + Thread.currentThread().getName());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("r1 Completed!");
        };

        Runnable r2 = () -> {
            try {
                System.out.println("r2, threadName: " + Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            int i = 1 / 0;
            System.out.println("r2 Completed!");
        };

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(r1);
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(r2);

//        future1.get();
//
//        // 这个操作执行时如果没完成则由之前执行的线程执行，否则由调用者执行
//        future1.whenComplete((result, throwable) -> {
//            System.out.println("when complete1, threadName: " + Thread.currentThread().getName());
//            System.out.println("r1执行完了的接下来的操作");
//        }).whenComplete((result, throwable) -> {
//            System.out.println("when complete1, threadName: " + Thread.currentThread().getName());
//            System.out.println("r1最后的操作");
//        });
//
        future2.whenComplete((result, throwable) -> {
            System.out.println("when complete2, threadName: " + Thread.currentThread().getName());
            System.out.println("r2执行完了的接下来的操作");
        }).whenComplete((result, throwable) -> {
            System.out.println("when complete2, threadName: " + Thread.currentThread().getName());
            System.out.println("r2最后的操作");
        });
        CompletableFuture<Integer> exceptionally = CompletableFuture.allOf(future1, future2).whenComplete((r, t) -> {
            if (t != null) {
                System.out.println("出现了异常");
            }
//            throw new IllegalArgumentException();
            System.out.println("两个都执行完了，我自己执行。");
        }).exceptionally(t -> {
//            异常时会继续调用此方法 成功则忽略
            System.out.println("异常完成");
            return null;
        }).thenApply(t -> {
//              这个方法类似map，会把返回值进行一个转换
            System.out.println("then apply 1");
            return "hello";
        }).thenApply(t -> {
            System.out.println("then apply 2");
            return 1;
        }).applyToEither(
                // 谁先完成就拿谁的返回值去map
                new CompletableFuture<>(), t -> {return 2;}
        ).thenCombine(new CompletableFuture<>(), (t1, t2) -> {
            // 这个方法是把两个future的返回值一并进行map
            return 3;
        }).thenCompose(t -> {
            // 这个组合，要求把返回值拿过来，返回一个新的CompletableFuture
            return new CompletableFuture<>();
        });
        System.out.println("输出结果" + exceptionally.get());;

        // 如果还没有完成，则让它异常的完成，get方法会直接报错（其实就是提前把异常塞到结果里面）
        exceptionally.completeExceptionally(new RuntimeException());
        exceptionally.get();

        // todo 没了，CompletableFuture就上面这点内容，关键是要自己会看参数
        // todo 你自己动手写一遍，一个小时就把CompletableFuture的用法弄明白了
        System.out.println("全部任务已提交");
        System.in.read();

    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        completableFuture();


    }




}
