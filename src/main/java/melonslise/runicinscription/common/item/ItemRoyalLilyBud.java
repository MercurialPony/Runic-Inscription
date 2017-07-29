package melonslise.runicinscription.common.item;

import melonslise.runicinscription.common.entity.player.PropertiesMana;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class ItemRoyalLilyBud extends ItemSeedFood
{
	public ItemRoyalLilyBud(int healAmount, float saturationModifier, Block crop, Block soil)
	{
		super(healAmount, saturationModifier, crop, soil);
	}
	
    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
        return EnumPlantType.Plains;
    }
    
    @Override
    protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
		PropertiesMana properties = PropertiesMana.get((EntityPlayer) player);

		if (properties != null)
		{
			properties.setCurrentMana(properties.getCurrentMana() + 10);
		}
    }
}
