package melonslise.runicinscription.common.crafting;

import melonslise.runicinscription.common.item.ItemList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeList // TODO Rename
{
	public static void register()
	{
		ItemStack output;

		output = new ItemStack(ItemList.scrollEmpty);
		GameRegistry.addShapedRecipe(output, "S", "P", "S", 'S', Items.STICK, 'P', net.minecraft.init.Items.PAPER);
		output = new ItemStack(ItemList.runeBlank, 2);
		GameRegistry.addShapelessRecipe(output, Blocks.STONE_SLAB);
	}
}