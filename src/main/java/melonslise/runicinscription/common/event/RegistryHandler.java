package melonslise.runicinscription.common.event;

import melonslise.runicinscription.common.item.ItemList;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryHandler // TODO Rename // TODO Move to registry package (?)
{
	@SubscribeEvent
	public static void register(RegistryEvent.Register<Item> event)
	{
		ItemList.register(event);
	}
}