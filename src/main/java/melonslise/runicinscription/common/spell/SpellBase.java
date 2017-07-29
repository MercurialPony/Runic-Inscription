package melonslise.runicinscription.common.spell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpellBase extends Item
{
	private boolean isRune;
	
	public Item setIsRune(boolean isRune)
	{
		this.isRune = isRune;
		return this;
	}
	
	public boolean getIsRune()
	{
		if(this.isRune == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if (!world.isRemote)
		{	
			this.useSpell(itemStack, world, player);
		}
		
		return itemStack;
	}
	
	public void useSpell(ItemStack itemStack, World world, EntityPlayer player)
	{
		
	}
}
