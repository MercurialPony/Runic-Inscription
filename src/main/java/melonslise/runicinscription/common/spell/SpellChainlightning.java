package melonslise.runicinscription.common.spell;

import java.util.List;

import melonslise.runicinscription.common.entity.player.PropertiesMana;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class SpellChainlightning extends SpellBase
{
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean b)
	{
		list.add(I18n.format("SpellChainlightning.lore"));
		list.add("");
		
		if(this.getUnlocalizedName().indexOf("Scroll") != -1 )
		{
			list.add(I18n.format("SpellMana.lore") + ": 20");
		}
		else if(this.getUnlocalizedName().indexOf("Rune") != -1)
		{
			list.add(I18n.format("SpellMana.lore") + ": 40");
		}
		
		list.add("");
		list.add(I18n.format("SpellDamage.lore") + ": 5-7");
		list.add(I18n.format("SpellType.lore") + ": " + EnumChatFormatting.YELLOW + I18n.format("SpellLightning.lore"));
	}
	
	@Override
	public void useSpell(ItemStack itemStack, World world, EntityPlayer player)
	{
		PropertiesMana properties = PropertiesMana.get(player);
		
		if((player.capabilities.isCreativeMode) || ((!player.capabilities.isCreativeMode) && (this.getIsRune() == true) && (properties.consumeMana(40))) || ((!player.capabilities.isCreativeMode) && (this.getIsRune() == false) && (properties.consumeMana(20))))
		{
			List<EntityLivingBase> entities = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(player.posX - 6, player.posY - 6, player.posZ - 6, player.posX + 6, player.posY + 6, player.posZ + 6));
			
			for (EntityLivingBase entity : entities)
			{
				if (!((entity instanceof EntityTameable) && (((EntityTameable) entity).getOwner() == player)) && (entity != player))
				{
					world.addWeatherEffect(new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ));
					entity.knockBack(entity, 0F, 1F, 0F);
				}
			}

			player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "ambient.weather.thunder", 10000.0F, 0.8F + (world.rand.nextFloat() * 0.2F));

			if(this.getIsRune() == false)
			{
				--itemStack.stackSize;
			}
		}
	}
}
