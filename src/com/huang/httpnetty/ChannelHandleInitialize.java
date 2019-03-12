package com.huang.httpnetty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class ChannelHandleInitialize extends ChannelInitializer<SocketChannel>
{
    
    @Override
    protected void initChannel(SocketChannel ch)
        throws Exception
    {
        ch.pipeline().addLast("httpServerCode", new HttpServerCodec());
        ch.pipeline().addLast("httpHandle", new HttpHandle());
    }
    
}
