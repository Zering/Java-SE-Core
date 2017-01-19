package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloServer {

	/**
	 * 服务器监听的端口
	 */
	private static final int portNumber = 7878;

	public static void main(String[] args) throws InterruptedException{

		// 开启两个事件循环组，自动构建EventLoop，一般开两个提升效率
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup,workGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new HelloServerInitializer());
			
			
			//服务器绑定监听端口
			ChannelFuture future = bootstrap.bind(portNumber).sync();
			
			//监听服务器关闭监听
			future.channel().closeFuture().sync();
		} finally {
			//优雅退出
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
}
