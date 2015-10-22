package netty;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter {
	
	private ByteBuf buf;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        buf = ctx.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        buf.release();
        buf = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        /*ByteBuf m = (ByteBuf) msg;
        buf.writeBytes(m); 
        m.release();*/

        //if (buf.readableBytes() >= 4) {
    	    //new TimeDecoder(ctx,)
            
            List<Object> out = new ArrayList<Object>();
    	    new TimeDecoder().decode(ctx, (ByteBuf)msg, out);
    	    long currentTimeMillis = (((ByteBuf) out).readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date((long)currentTimeMillis));
            ctx.close();
        //}
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
