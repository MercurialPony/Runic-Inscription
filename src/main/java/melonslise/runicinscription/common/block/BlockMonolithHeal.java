package melonslise.runicinscription.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import melonslise.runicinscription.RunicInscription;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockMonolithHeal extends BlockMonolithBase
{
	public BlockMonolithHeal(Material material)
    {
		super(material);
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.iconFront = iconRegister.registerIcon(RunicInscription.ID + ":" + "Monolith4");
        this.blockIcon = iconRegister.registerIcon(RunicInscription.ID + ":" + "MonolithSide"); 
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
    	ItemStack heldItem = player.inventory.getCurrentItem();
    	
    	if ((heldItem != null) && (heldItem.getItem() == RunicInscription.itemScrollTracing))
    	{
    		player.inventory.addItemStackToInventory(new ItemStack(RunicInscription.itemScrollHeal, 1));
    		if(!player.capabilities.isCreativeMode)
    		{
    			player.inventory.consumeInventoryItem(heldItem.getItem());
    		}
    		
    		return true;
    	}
    	else if((heldItem != null) && (heldItem.getItem() == RunicInscription.itemPaperTracing))
    	{
    		player.inventory.addItemStackToInventory(new ItemStack(RunicInscription.itemPaperTracedHeal, 1));
    		if(!player.capabilities.isCreativeMode)
    		{
    			player.inventory.consumeInventoryItem(heldItem.getItem());
    		}
    		
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
}
