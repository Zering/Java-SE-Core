package netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HelloServerHandler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		// 对接收到的信息进行处理--直接打印
		System.out.println(ctx.channel().remoteAddress() + " says :" + msg);

		// 返回客户端消息--表明已经收到了信息
		ctx.writeAndFlush("I reserved your message!\n");
	}

}
