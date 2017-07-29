package melonslise.runicinscription.common.proxy;

import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public abstract class CommonProxy
{
	public abstract void registerEntityRenderers();

	public abstract IThreadListener getThreadListener(MessageContext context);
}