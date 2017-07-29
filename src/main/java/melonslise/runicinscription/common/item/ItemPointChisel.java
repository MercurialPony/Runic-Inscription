package melonslise.runicinscription.common.item;

import melonslise.runicinscription.RunicInscription;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPointChisel extends Item
{
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if (player.inventory.hasItem(RunicInscription.itemRuneBlank) == true)
		{
			player.openGui(RunicInscription.instance, RunicInscription.guiIDRuneInscription, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);

			if(!player.capabilities.isCreativeMode)
			{
				player.inventory.consumeInventoryItem(RunicInscription.itemRuneBlank);
			}
		}

		return itemStack;
	}
}
