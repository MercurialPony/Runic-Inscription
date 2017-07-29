package melonslise.runicinscription.client.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.client.event.EventRenderGUIMana;
import melonslise.runicinscription.client.renderer.RenderItemWritingTable;
import melonslise.runicinscription.client.renderer.RenderWritingTable;
import melonslise.runicinscription.common.entity.EntityFirebolt;
import melonslise.runicinscription.common.entity.EntityFirerain;
import melonslise.runicinscription.common.entity.EntityIcebolt;
import melonslise.runicinscription.common.proxy.CommonProxy;
import melonslise.runicinscription.common.tileentity.TileEntityWritingTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{
	@Override
	public void init()
	{
		super.init();

		MinecraftForge.EVENT_BUS.register(new EventRenderGUIMana());
	}

	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityFirebolt.class, new RenderSnowball(RunicInscription.iconSpellFirebolt));
		RenderingRegistry.registerEntityRenderingHandler(EntityIcebolt.class, new RenderSnowball(RunicInscription.iconSpellIcebolt));
		RenderingRegistry.registerEntityRenderingHandler(EntityFirerain.class, new RenderSnowball(RunicInscription.iconEmpty));
		
		TileEntitySpecialRenderer render = new RenderWritingTable();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWritingTable.class, render);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RunicInscription.blockWritingTable), new RenderItemWritingTable(render, new TileEntityWritingTable()));
		
	}
	
	@Override
	public void registerTileEntitySpecialRenderer()
	{
		
	}
	
	@Override
	public EntityPlayer getClientPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}

	@Override
	public World getClientWorld()
	{
		return Minecraft.getMinecraft().theWorld;
	}
}
