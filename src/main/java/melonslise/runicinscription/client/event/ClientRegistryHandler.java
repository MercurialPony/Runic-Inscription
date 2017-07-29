package melonslise.runicinscription.client.event;

import melonslise.runicinscription.common.item.ItemList;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientRegistryHandler // TODO Rename // TODO Perhaps move to unified package
{
	@SubscribeEvent
	public static void register(ModelRegistryEvent event) // TODO Simplify / Involve ItemList class
	{
		ModelResourceLocation model;

		model = new ModelResourceLocation(ItemList.scrollEmpty.getRegistryName(), "inventory");
		ModelLoader.setCustomModelResourceLocation(ItemList.scrollEmpty, 0, model);
		model = new ModelResourceLocation(ItemList.runeBlank.getRegistryName(), "inventory");
		ModelLoader.setCustomModelResourceLocation(ItemList.runeBlank, 0, model);
	}
}