package com.huang.httpnetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerSocketMain
{
    public static void main(String[] args) throws Exception
    {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        try
        {
            ServerBootstrap strap = new ServerBootstrap();
            strap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                  .childHandler(new ChannelHandleInitialize());
            
             ChannelFuture future = strap.bind(8090).sync();
             future.channel().closeFuture().sync();
        }
        finally
        {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}














