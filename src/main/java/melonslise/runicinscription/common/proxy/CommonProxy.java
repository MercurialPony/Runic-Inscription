package melonslise.runicinscription.common.proxy;

import melonslise.runicinscription.common.event.EventMana;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy
{
	public void init()
	{
		MinecraftForge.EVENT_BUS.register(new EventMana());
	}

	public abstract void registerRenderers();
	
	public abstract void registerTileEntitySpecialRenderer();
	
	public abstract EntityPlayer getClientPlayer();

	public abstract World getClientWorld();
}
