package com.huang.chatnetty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatSocketHandle extends SimpleChannelInboundHandler<String>
{
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg)
        throws Exception
    {
        Channel channel = ctx.channel();
        for (Channel ch : channelGroup)
        {
            if (ch != channel)
                ch.writeAndFlush(ch.remoteAddress() + "发送的消息是:" + msg + "\n");
            else
                ch.writeAndFlush("[自己]" + msg + "\n");
        }
    }
    
    @Override
    public void handlerAdded(ChannelHandlerContext ctx)
        throws Exception
    {
        Channel channel = ctx.channel();
        
        channelGroup.writeAndFlush("[服务器 -]" + channel.remoteAddress() + "加入\n");
        
        channelGroup.add(channel);
    }
    
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx)
        throws Exception
    {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[服务器 -]" + channel.remoteAddress() + "已下线\n");
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx)
        throws Exception
    {
       
        System.out.println(ctx.channel().remoteAddress() + "已上线");
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx)
        throws Exception
    {
        System.out.println(ctx.channel().remoteAddress() + "已下线");
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
        throws Exception
    {
        cause.printStackTrace();
        ctx.close();
    }
    
}
