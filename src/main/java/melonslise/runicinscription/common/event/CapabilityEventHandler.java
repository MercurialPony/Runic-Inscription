package melonslise.runicinscription.common.event;

import melonslise.runicinscription.common.capability.CapabilityMana;
import melonslise.runicinscription.common.capability.ICapabilityMana;
import melonslise.runicinscription.common.capability.api.CapabilityProviderSerializable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

@Mod.EventBusSubscriber
public class CapabilityEventHandler
{
	@CapabilityInject(ICapabilityMana.class)
	public static final Capability<ICapabilityMana> capability = null; // TODO Move

	@SubscribeEvent
	public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
	{
		if(event.getObject() instanceof EntityPlayer)
		{
			CapabilityMana instance = new CapabilityMana((EntityPlayer) event.getObject());
			event.addCapability(CapabilityMana.ID, new CapabilityProviderSerializable(capability, instance, null));
		}
	}

	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerLoggedInEvent event)
	{
		if(event.player.hasCapability(capability, null))
		{
			ICapabilityMana capability1 = event.player.getCapability(capability, null);
			capability1.synchronize();
		}
	}

	@SubscribeEvent
	public static void onClonePlayer(PlayerEvent.Clone event)
	{
		if(event.getOriginal().hasCapability(capability, null) && event.getEntityPlayer().hasCapability(capability, null))
		{
			ICapabilityMana capability1 = event.getOriginal().getCapability(capability, null);
			ICapabilityMana capability2 = event.getEntityPlayer().getCapability(capability, null);
			if(!event.isWasDeath())
			{
				capability2.setCurrent(capability1.getCurrent());
			}
			capability2.setMaximum(capability1.getMaximum());
		}
	}
}