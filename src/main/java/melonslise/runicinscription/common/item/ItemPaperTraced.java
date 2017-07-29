package melonslise.runicinscription.common.item;

import melonslise.runicinscription.RunicInscription;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPaperTraced extends Item
{
	private String type;
	
	public Item setSpellType(String type)
	{
		this.type = type;
		return this;
	}
	
	public String getSpellType()
	{
		return this.type;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		player.openGui(RunicInscription.instance, RunicInscription.guiIDTracingPaper, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);

		return itemStack;
	}
}
