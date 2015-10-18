package io.netty.example.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends ChannelHandlerAdapter { 

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { 
    	
    	// Discard the received data silently.
        ((ByteBuf) msg).release(); // (3)
    	
        // Print the message on the console.
    	/*ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) { // (1)
                System.out.print((char) in.readByte());
                System.out.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { 
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
