package melonslise.runicinscription.common.spell;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.ReflectionHelper;
import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.common.entity.player.PropertiesMana;
import melonslise.runicinscription.common.network.MessageParticle;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class SpellDispel extends SpellBase
{
	protected static final Field isBadEffect = ReflectionHelper.findField(Potion.class, "isBadEffect", "field_76418_K");
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean b)
	{
		list.add(I18n.format("SpellDispel.lore"));
		list.add("");
		
		if(this.getUnlocalizedName().indexOf("Scroll") != -1 )
		{
			list.add(I18n.format("SpellMana.lore") + ": 10");
		}
		else if(this.getUnlocalizedName().indexOf("Rune") != -1)
		{
			list.add(I18n.format("SpellMana.lore") + ": 25");
		}
		
		list.add("");
		list.add(I18n.format("SpellBuff.lore"));
		list.add(I18n.format("SpellType.lore") + ": " + EnumChatFormatting.GREEN + I18n.format("SpellNature.lore"));
	}
	
	@Override
	public void useSpell(ItemStack itemStack, World world, EntityPlayer player)
	{
		PropertiesMana properties = PropertiesMana.get(player);
		
		if((player.capabilities.isCreativeMode) || ((!player.capabilities.isCreativeMode) && (this.getIsRune() == true) && (properties.consumeMana(25))) || ((!player.capabilities.isCreativeMode) && (this.getIsRune() == false) && (properties.consumeMana(10))))
		{
			TargetPoint point = new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 32.0D);

			for(int i = 0; i < 30; ++i)
			{
				RunicInscription.network.sendToAllAround(new MessageParticle("mobSpell", player.posX + ((Item.itemRand.nextInt(10) * 0.2D) - 1.0D), player.posY + 1.0D + ((Item.itemRand.nextInt(10) * 0.2D) - 1.0D), player.posZ + ((Item.itemRand.nextInt(10) * 0.2D) - 1.0D), 0.5D, 0.0D, 0.5D), point);
			}

			world.playSoundAtEntity(player, RunicInscription.ID + ":BlessingCast", 0.5F, 0.4F / ((itemRand.nextFloat() * 0.4F) + 0.8F));

			Collection<PotionEffect> effects = player.getActivePotionEffects();
			List<Integer> negativeEffects = new ArrayList<Integer>();

			try
			{
				for(PotionEffect effect : effects)
				{
					if (isBadEffect.getBoolean(Potion.potionTypes[effect.getPotionID()]) == true)
					{
						negativeEffects.add(effect.getPotionID());
					}
				}
			}
			catch (IllegalArgumentException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}

			for(Integer negativeEffect : negativeEffects)
			{
				player.removePotionEffect(negativeEffect);
			}
			
			if(this.getIsRune() == false)
			{
				--itemStack.stackSize;
			}
		}
	}
}
