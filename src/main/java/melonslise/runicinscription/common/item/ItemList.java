package melonslise.runicinscription.common.item;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class ItemList // TODO Rename
{
	public static Item scrollEmpty;
	public static Item runeBlank;

	public static void register(RegistryEvent.Register<Item> event)
	{
		ItemList.scrollEmpty = new ItemBase("ScrollEmpty");
		ItemList.runeBlank = new ItemBase("RuneBlank");

		event.getRegistry().registerAll(scrollEmpty, runeBlank);
	}
}