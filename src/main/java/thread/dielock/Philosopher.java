package thread.dielock;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.channels.SocketChannel;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 哲学家
 *
 * @author SunKangChao
 * @date 2021/7/6 23:15
 */
public class Philosopher implements Runnable{

    private final String name;

    private final long sleepTime;

    private final Chopsticks rigthChopstick;

    private final Chopsticks leftChopstick;

    //            Set<SocketChannel> channelSet = Collections.newSetFromMap(new ConcurrentHashMap<>());


    public Philosopher(String name,Chopsticks rigthChopstick,Chopsticks leftChopstick,long sleepTime){
        this.name = name;
        this.rigthChopstick = rigthChopstick;
        this.leftChopstick = leftChopstick;
        this.sleepTime = sleepTime;
    }


//    ServerBootstrap
//    ChannelGroup
//    ChannelInitializer
//    StringEncoder
//    SimpleChannelInboundHandler




    @Override
    public void run() {

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (rigthChopstick){
            System.out.println(this.name + "拿到了右筷子" + rigthChopstick.name);
            synchronized (leftChopstick){
                System.out.println(this.name + "拿到了左筷子" + leftChopstick.name);
                System.out.println(this.name + "开始吃饭了");
            }
            System.out.println(this.name + "吃完饭了.");
        }

    }
}
