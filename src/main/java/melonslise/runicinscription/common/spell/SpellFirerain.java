package melonslise.runicinscription.common.spell;

import java.util.List;

import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.common.entity.EntityFirerain;
import melonslise.runicinscription.common.entity.player.PropertiesMana;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class SpellFirerain extends SpellBase
{
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean b)
	{
		list.add(I18n.format("SpellFirerain.lore"));
		list.add("");
		
		if(this.getUnlocalizedName().indexOf("Scroll") != -1 )
		{
			list.add(I18n.format("SpellMana.lore") + ": 25");
		}
		else if(this.getUnlocalizedName().indexOf("Rune") != -1)
		{
			list.add(I18n.format("SpellMana.lore") + ": 50");
		}
		
		list.add("");
		list.add(I18n.format("SpellDamage.lore") + ": 5-10");
		list.add(I18n.format("SpellType.lore") + ": " + EnumChatFormatting.DARK_RED + I18n.format("SpellFire.lore"));
	}
	
	@Override
	public void useSpell(ItemStack itemStack, World world, EntityPlayer player)
	{
		PropertiesMana properties = PropertiesMana.get(player);
		
		if((player.capabilities.isCreativeMode) || ((!player.capabilities.isCreativeMode) && (this.getIsRune() == true) && (properties.consumeMana(50))) || ((!player.capabilities.isCreativeMode) && (this.getIsRune() == false) && (properties.consumeMana(25))))
		{
			List<EntityLivingBase> entities = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(player.posX - 6, player.posY - 6, player.posZ - 6, player.posX + 6, player.posY + 6, player.posZ + 6));
			
			for (EntityLivingBase entity : entities)
			{
				if (!((entity instanceof EntityTameable) && (((EntityTameable) entity).getOwner() == player)) && (entity != player))
				{
					entity.setFire(5);
					entity.attackEntityFrom(DamageSource.onFire, 10F);
					entity.knockBack(entity, 0F, 1F, 0F);
				}
			}

			EntityFirerain firerain = new EntityFirerain(world);
			firerain.setLocationAndAngles(player.posX, player.posY, player.posZ, 0.0F, 0.0F);
			world.spawnEntityInWorld(firerain);

			world.playSoundAtEntity(player, RunicInscription.ID + ":FireRainCast", 1F, 1F);

			if(this.getIsRune() == false)
			{
				--itemStack.stackSize;
			}
		}
	}
}
