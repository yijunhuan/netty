package com.huang.socketnetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class SocketNettyMain
{
    public static void main(String[] args)
        throws Exception
    {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try
        {
            ServerBootstrap bootstrap = new ServerBootstrap();
            
            bootstrap.group(bossGroup, workerGroup)
                      .channel(NioServerSocketChannel.class)
                      .childHandler(new SocketNettyInitializer());
            
            ChannelFuture future = bootstrap.bind(9090).sync();
            future.channel().closeFuture().sync();
        }
       finally
       {
           bossGroup.shutdownGracefully();
           workerGroup.shutdownGracefully();
       }
       
    }
}
