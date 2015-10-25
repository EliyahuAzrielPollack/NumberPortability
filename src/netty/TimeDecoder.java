package netty;

import java.util.List;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class TimeDecoder extends ByteToMessageDecoder {
    
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) {
		
		 if (in.readableBytes() <= 4) {
			 for (int i = 0; i < in.capacity(); i ++) { byte b = in.getByte(i);
			      System.out.print((char) b); }
	            //return; 
	     }

	     out.add(in.readBytes(4)); 
	}
}
