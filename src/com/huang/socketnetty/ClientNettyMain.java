package com.huang.socketnetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientNettyMain
{
    public static void main(String[] args) throws Exception
    {
        EventLoopGroup group = new NioEventLoopGroup();
        
        try
        {
            Bootstrap strap = new Bootstrap();
            strap.group(group).channel(NioSocketChannel.class).handler(new ClientNettyInitializer());
            ChannelFuture channelFuture = strap.connect("localhost", 9090).sync();
            channelFuture.channel().closeFuture().sync();
        }
        finally
        {
           group.shutdownGracefully();
        }
    }
}
