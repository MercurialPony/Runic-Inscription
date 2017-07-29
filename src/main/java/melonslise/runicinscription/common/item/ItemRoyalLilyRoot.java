package melonslise.runicinscription.common.item;

import melonslise.runicinscription.common.entity.player.PropertiesMana;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRoyalLilyRoot extends ItemFood
{
	public ItemRoyalLilyRoot(int healAmount, float saturationModifier, boolean isWolfFood)
	{
		super(healAmount, saturationModifier, isWolfFood);
	}
	
    @Override
    protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
		PropertiesMana properties = PropertiesMana.get((EntityPlayer) player);

		if (properties != null)
		{
			properties.setMaxMana(properties.getMaxMana() + 1);
		}
    }
}
