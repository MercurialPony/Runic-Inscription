package melonslise.runicinscription.client.proxy;

import melonslise.runicinscription.common.entity.EntityFirebolt;
import melonslise.runicinscription.common.entity.EntityIcebolt;
import melonslise.runicinscription.common.item.ItemList;
import melonslise.runicinscription.common.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityFirebolt.class, this.rendererProvider); // TODO Move to separate class
		RenderingRegistry.registerEntityRenderingHandler(EntityIcebolt.class, this.rendererProvider);
	}

	IRenderFactory rendererProvider = new IRenderFactory()
	{
		@Override
		public Render createRenderFor(RenderManager manager)
		{
			return new RenderSnowball(manager, ItemList.scrollEmpty, Minecraft.getMinecraft().getRenderItem());
		}
	};

	@Override
	public IThreadListener getThreadListener(MessageContext context)
	{
		return Minecraft.getMinecraft();
	}
}