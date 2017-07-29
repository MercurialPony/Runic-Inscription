package melonslise.runicinscription.common.spell;

import java.util.List;

import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.common.entity.EntityFirebolt;
import melonslise.runicinscription.common.entity.player.PropertiesMana;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class SpellFirebolt extends SpellBase
{
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean b)
	{
		list.add(I18n.format("SpellFirebolt.lore"));
		list.add("");
		
		if(this.getUnlocalizedName().indexOf("Scroll") != -1 )
		{
			list.add(I18n.format("SpellMana.lore") + ": 5");
		}
		else if(this.getUnlocalizedName().indexOf("Rune") != -1)
		{
			list.add(I18n.format("SpellMana.lore") + ": 10");
		}
		
		list.add("");
		list.add(I18n.format("SpellDamage.lore") + ": 4");
		list.add(I18n.format("SpellType.lore") + ": " + EnumChatFormatting.DARK_RED + I18n.format("SpellFire.lore"));
	}
	
	@Override
	public void useSpell(ItemStack itemStack, World world, EntityPlayer player)
	{
		PropertiesMana properties = PropertiesMana.get(player);
		
		if((player.capabilities.isCreativeMode) || ((!player.capabilities.isCreativeMode) && (this.getIsRune() == true) && (properties.consumeMana(10))) || ((!player.capabilities.isCreativeMode) && (this.getIsRune() == false) && (properties.consumeMana(5))))
		{
			Vec3 v3 = player.getLook(1);
			EntityFirebolt firebolt = new EntityFirebolt(world, player, v3.xCoord * 25D, v3.yCoord * 25D, v3.zCoord * 25D);
			firebolt.posY = player.posY + player.getEyeHeight();
			world.spawnEntityInWorld(firebolt);
			
			world.playSoundAtEntity(player, RunicInscription.ID + ":FireboltCast", 1F, 1F);
			
			if(this.getIsRune() == false)
			{
				--itemStack.stackSize;
			}
		}
	}
}
