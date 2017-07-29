package melonslise.runicinscription.server.proxy;

import melonslise.runicinscription.common.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ServerProxy extends CommonProxy
{
	@Override
	public void init()
	{

	}

	@Override
	public void registerRenderers()
	{

	}
	
	@Override
	public void registerTileEntitySpecialRenderer()
	{
		
	}
	
	@Override
	public EntityPlayer getClientPlayer()
	{
		return null;
	}

	@Override
	public World getClientWorld()
	{
		return null;
	}
}
