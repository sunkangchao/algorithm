package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * GCTest
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>02月 26, 2024</pre>
 */
public class GCTest {

    /**
     -verbose:gc
     -Xms10m
     -Xmx10m
     -XX:+UseG1GC
     -XX:+PrintGCDetails
     -XX:+PrintGCDateStamps
     -XX:+PrintGCTimeStamps
     -XX:MaxGCPauseMillis=200m
     一个region 1M（日志最后的region size 1024K）
     一个大小达到甚至超过分区Region 50%以上的对象称为巨型对象（Humongous Object）
     故，低于512k是触发young gc，大于512k是G1 Humongous Allocation
     触发young gc的条件，超过60%堆，即超过6M
     */

    static int size = 1024*1024;

    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i=1; i<1000; i++) {
            System.out.println("----------"+i+"----------");
            byte[] array = new byte[size];
            list.add(array);
        }
        list = null;
        System.gc();
        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
