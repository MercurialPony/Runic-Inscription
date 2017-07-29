package melonslise.runicinscription.common.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.common.entity.player.PropertiesMana;
import melonslise.runicinscription.common.network.MessageCurrentMana;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class EventMana
{
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			if (PropertiesMana.get((EntityPlayer) event.entity) == null)
			{
				PropertiesMana.register((EntityPlayer) event.entity);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(LivingUpdateEvent event)
	{
		if(event.entity instanceof EntityPlayer)
		{
			PropertiesMana properties = PropertiesMana.get((EntityPlayer) event.entity);

			if (properties.getCurrentMana() < properties.getMaxMana())
			{
				properties.ticksMana++;

				if (properties.ticksMana >= 20)
				{
					properties.setCurrentMana(properties.getCurrentMana() + 1);
					properties.ticksMana = 0;
				}
			}
		}
	}
	
	@SubscribeEvent
	public void entityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityPlayerMP)
		{
			PropertiesMana properties = PropertiesMana.get((EntityPlayerMP) event.entity);

			if (properties != null)
			{
				RunicInscription.network.sendTo(new MessageCurrentMana(properties.getCurrentMana()), (EntityPlayerMP) event.entity);
				RunicInscription.network.sendTo(new MessageCurrentMana(properties.getMaxMana()), (EntityPlayerMP) event.entity);
			}
		}
	}
	
	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone event)
	{
		if(event.wasDeath)
		{
			NBTTagCompound compound = new NBTTagCompound();
			PropertiesMana.get(event.original).saveNBTData(compound);
			PropertiesMana.get(event.entityPlayer).loadNBTData(compound);
		}
	}
}
