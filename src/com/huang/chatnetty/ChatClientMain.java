package com.huang.chatnetty;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ChatClientMain
{
    
    public static void main(String[] args) throws Exception
    {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        
        try
        {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ChatClientInitialize());
            Channel channel = bootstrap.connect("localhost", 8080).sync().channel();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (;;)
            {
                channel.writeAndFlush(in.readLine() + "\n");
            } 
        }
        finally
        {
            eventLoopGroup.shutdownGracefully();
        }
    }
    
}
