package netty;

import java.sql.Date;
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
       
    	//test 1 - work.
    	/*ByteBuf m = (ByteBuf) msg;
        buf.writeBytes(m); 
        m.release();

        if (buf.readableBytes() >= 4) {
    	
	     long currentTimeMillis = (buf.readUnsignedInt() - 2208988800L) * 1000L;    
	     System.out.println(new Date((long)currentTimeMillis));
         ctx.close();
        }*/
        
    	//test 2 - does't work.
        /*List<Object> out = new ArrayList<Object>();
	    new TimeDecoder().decode(ctx, (ByteBuf)msg, out);
	    long currentTimeMillis = (((ByteBuf) out).readUnsignedInt() - 2208988800L) * 1000L;
        System.out.println(new Date((long)currentTimeMillis));
        ctx.close();*/
    	
    	//test 3 -
    	ByteBuf m = (ByteBuf) msg; 
        try {
            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        } finally {
            m.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
