package com.huang.hellonetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloNetty
{
    public static void main(String[] args)
        throws Exception
    {
        // 定义主从线程池
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        
        ServerBootstrap bootstrap = new ServerBootstrap();
        
        bootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).
            childHandler(new HelloServerInitializer());
        
        ChannelFuture  channelFuture = bootstrap.bind(8080).sync();
        // 监听关闭的channel，设置异步方式
        channelFuture.channel().closeFuture().sync();
        
    }
}
