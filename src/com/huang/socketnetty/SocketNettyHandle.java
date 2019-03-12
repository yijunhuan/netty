package com.huang.socketnetty;

import java.util.UUID;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SocketNettyHandle extends SimpleChannelInboundHandler<String>
{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg)
        throws Exception
    {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "," + msg);
        
        channel.writeAndFlush("from server: "+ UUID.randomUUID());
    }
    
    

    @Override
    public void channelActive(ChannelHandlerContext ctx)
        throws Exception
    {
        Channel channel = ctx.channel();
        channel.writeAndFlush("服务器你好");
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
        throws Exception
    {
        cause.printStackTrace();
        ctx.channel().close();
    }
    
    
    
}
