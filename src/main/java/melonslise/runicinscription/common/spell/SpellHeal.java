package melonslise.runicinscription.common.spell;

import java.util.List;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.common.entity.player.PropertiesMana;
import melonslise.runicinscription.common.network.MessageParticle;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class SpellHeal extends SpellBase
{
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean b)
	{
		list.add(I18n.format("SpellHealing.lore"));
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
		list.add(I18n.format("SpellHeal.lore") + ": 5");
		list.add(I18n.format("SpellType.lore") + ": " + EnumChatFormatting.GREEN + I18n.format("SpellNature.lore"));
	}
	
	@Override
	public void useSpell(ItemStack itemStack, World world, EntityPlayer player)
	{
		PropertiesMana properties = PropertiesMana.get(player);
		
		if((player.capabilities.isCreativeMode) || ((!player.capabilities.isCreativeMode) && (this.getIsRune() == true) && (properties.consumeMana(20))) || ((!player.capabilities.isCreativeMode) && (this.getIsRune() == false) && (properties.consumeMana(10))))
		{
			player.heal(5);

			world.playSoundAtEntity(player, RunicInscription.ID + ":BlessingCast", 0.5F, 0.4F / ((itemRand.nextFloat() * 0.4F) + 0.8F));

			TargetPoint point = new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 32.0D);

			for (int a = 0; a < 10; ++a)
			{
				RunicInscription.network.sendToAllAround(new MessageParticle("happyVillager", player.posX + 1.0D, player.posY + (a * 0.2), player.posZ, 0.0D, 0.7D, 0.0D), point);

				RunicInscription.network.sendToAllAround(new MessageParticle("happyVillager", player.posX - 1.0D, player.posY + (a * 0.2), player.posZ, 0.0D, 0.7D, 0.0D), point);

				RunicInscription.network.sendToAllAround(new MessageParticle("happyVillager", player.posX, player.posY + (a * 0.2), player.posZ + 1.0D, 0.0D, 0.7D, 0.0D), point);

				RunicInscription.network.sendToAllAround(new MessageParticle("happyVillager", player.posX, player.posY + (a * 0.2), player.posZ - 1.0D, 0.0D, 0.7D, 0.0D), point);
			}
			
			if(this.getIsRune() == false)
			{
				--itemStack.stackSize;
			}
		}
	}
}
