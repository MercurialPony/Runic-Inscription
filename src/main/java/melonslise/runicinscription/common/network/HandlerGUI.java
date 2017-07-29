package melonslise.runicinscription.common.network;

import cpw.mods.fml.common.network.IGuiHandler;
import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.client.gui.GUIRuneInscription;
import melonslise.runicinscription.client.gui.GUITracingPaper;
import melonslise.runicinscription.client.gui.GUIWritingTable;
import melonslise.runicinscription.common.container.ContainerRuneInscription;
import melonslise.runicinscription.common.container.ContainerWritingTable;
import melonslise.runicinscription.common.item.ItemPaperTraced;
import melonslise.runicinscription.common.tileentity.TileEntityWritingTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class HandlerGUI implements IGuiHandler
{

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(ID == RunicInscription.guiIDRuneInscription)
		{
			return new GUIRuneInscription(player.inventory, world, x, y, z);
		}
		
		if(ID == RunicInscription.guiIDScrollWriting)
		{
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			
			if(tileEntity != null)
			{
				if(tileEntity instanceof TileEntityWritingTable)
				{
					return new GUIWritingTable(player.inventory, (TileEntityWritingTable)tileEntity);
				}
			}
		}
		
		if(ID == RunicInscription.guiIDTracingPaper)
		{
			return new GUITracingPaper(((ItemPaperTraced) player.getHeldItem().getItem()).getSpellType());
		}

		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(ID == RunicInscription.guiIDRuneInscription)
		{
			return new ContainerRuneInscription(player.inventory, world, x, y, z);
		}
		
		if(ID == RunicInscription.guiIDScrollWriting)
		{
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			
			if(tileEntity != null)
			{
				if(tileEntity instanceof TileEntityWritingTable)
				{
					return new ContainerWritingTable(player.inventory, (TileEntityWritingTable)tileEntity);
				}
			}
		}

		return null;
	}

}
