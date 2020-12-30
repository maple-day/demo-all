package com.fangda.netty.first;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class MyServerHandler extends SimpleChannelInboundHandler<HttpObject> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("激活");
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {


        if (httpObject instanceof HttpRequest){
            ByteBuf buffer = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
            FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,buffer);
            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE,HttpHeaderValues.TEXT_PLAIN);
            httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH,buffer.readableBytes());
            channelHandlerContext.writeAndFlush(httpResponse);
            channelHandlerContext.channel().close();
        }

    }
}
