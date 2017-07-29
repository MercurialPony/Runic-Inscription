package melonslise.runicinscription.common.spell;

import java.util.List;

import melonslise.runicinscription.common.entity.player.PropertiesMana;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class SpellLightningbolt extends SpellBase
{
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean b)
	{
		list.add(I18n.format("SpellLightningbolt.lore"));
		list.add("");
		
		if(this.getUnlocalizedName().indexOf("Scroll") != -1 )
		{
			list.add(I18n.format("SpellMana.lore") + ": 10");
		}
		else if(this.getUnlocalizedName().indexOf("Rune") != -1)
		{
			list.add(I18n.format("SpellMana.lore") + ": 20");
		}
		
		list.add("");
		list.add(I18n.format("SpellDamage.lore") + ": 5-7");
		list.add(I18n.format("SpellType.lore") + ": " + EnumChatFormatting.YELLOW + I18n.format("SpellLightning.lore"));
	}

	@Override
	public void useSpell(ItemStack itemStack, World world, EntityPlayer player)
	{
		PropertiesMana properties = PropertiesMana.get(player);
		
		if((player.capabilities.isCreativeMode) || ((!player.capabilities.isCreativeMode) && (this.getIsRune() == true) && (properties.consumeMana(20))) || ((!player.capabilities.isCreativeMode) && (this.getIsRune() == false) && (properties.consumeMana(10))))
		{
			MovingObjectPosition objectMouseOver = this.getDistanceFromPlayer(world, player, true);
					
			if ((objectMouseOver != null) && (objectMouseOver.typeOfHit == MovingObjectType.BLOCK))
			{
				int x = objectMouseOver.blockX;
				int y = objectMouseOver.blockY;
				int z = objectMouseOver.blockZ;
				world.addWeatherEffect(new EntityLightningBolt(world, x, y, z));

				if(this.getIsRune() == false)
				{
					--itemStack.stackSize;
				}
			}
		}
	}
	
	protected MovingObjectPosition getDistanceFromPlayer(World world, EntityPlayer player, boolean b)
	{
		float f = 1.0F;
		float f1 = player.prevRotationPitch + ((player.rotationPitch - player.prevRotationPitch) * f);
		float f2 = player.prevRotationYaw + ((player.rotationYaw - player.prevRotationYaw) * f);
		double d0 = player.prevPosX + ((player.posX - player.prevPosX) * f);
		double d1 = player.prevPosY + ((player.posY - player.prevPosY) * f) + (world.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight());
		double d2 = player.prevPosZ + ((player.posZ - player.prevPosZ) * f);
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos((-f2 * 0.017453292F) - (float) Math.PI);
		float f4 = MathHelper.sin((-f2 * 0.017453292F) - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double reachDistance = 100.0D;

		Vec3 vec31 = vec3.addVector(f7 * reachDistance, f6 * reachDistance, f8 * reachDistance);
		return world.func_147447_a(vec3, vec31, b, !b, false);
	}
}
