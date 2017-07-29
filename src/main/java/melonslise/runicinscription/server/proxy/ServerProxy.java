package melonslise.runicinscription.server.proxy;

import melonslise.runicinscription.common.proxy.CommonProxy;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerProxy extends CommonProxy
{
	@Override
	public void registerEntityRenderers()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public IThreadListener getThreadListener(MessageContext context)
	{
		return context.getServerHandler().playerEntity.mcServer;
	}
}