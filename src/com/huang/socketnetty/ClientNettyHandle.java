package com.huang.socketnetty;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientNettyHandle extends SimpleChannelInboundHandler<String>
{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg)
        throws Exception
    {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress());
        System.out.println("from server: " + msg);
        channel.writeAndFlush("from client :" +new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()));
    }
    
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
        throws Exception
    {
        cause.printStackTrace();
        ctx.close();
    }
}
