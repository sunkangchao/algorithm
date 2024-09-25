package nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author SunKangChao
 * @date 2021/7/6 02:22
 */
public class NioClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //无法生效。
//        ctx.writeAndFlush("hello server from client");
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello server, from client.",CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = ((ByteBuf) msg);
        System.out.println("from server: " + byteBuf.toString(CharsetUtil.UTF_8));
    }

}
