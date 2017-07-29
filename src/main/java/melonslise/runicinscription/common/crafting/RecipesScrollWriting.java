package melonslise.runicinscription.common.crafting;

import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.common.tileentity.TileEntityWritingTable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesScrollWriting
{
	public static final Item input2Firebolt = RunicInscription.itemRuneFirebolt;
	public static final Item input2Icebolt = RunicInscription.itemRuneIcebolt;
	public static final Item input2Lightningbolt = RunicInscription.itemRuneLightningbolt;
	public static final Item input2Heal = RunicInscription.itemRuneHeal;
	public static final Item input2Dispel = RunicInscription.itemRuneDispel;
	public static final Item input2Firerain = RunicInscription.itemRuneFirerain;
	public static final Item input2Chainlightning = RunicInscription.itemRuneChainlightning;
	
	public static final Item input1 = RunicInscription.itemScrollEmpty;
	
	public static final int input3Damage = 0;
	public static final Item input3 = Items.dye;
	
	private static final RecipesScrollWriting instance = new RecipesScrollWriting();
	
	public static final RecipesScrollWriting getInstance()
	{
		return instance;
	}
	
	RecipesScrollWriting()
	{
		
	}
	
	public ItemStack findMatchingRecipe(TileEntityWritingTable tileEntityWriting)
	{
		ItemStack input1 = tileEntityWriting.getStackInSlot(0);
		ItemStack input2 = tileEntityWriting.getStackInSlot(1);
		ItemStack input3 = tileEntityWriting.getStackInSlot(2);
		
		if((input1 == null) || (input2 == null) || (input3 == null))
		{
			return null;
		}
		else
		{
			if((input1.getItem() == this.input1) && (input2.getItem() == this.input2Firebolt) && ((input3.getItem() == this.input3) && (input3.getItemDamage() == this.input3Damage)))
			{
				return new ItemStack(RunicInscription.itemScrollFirebolt, 1);
			}
			else if((input1.getItem() == this.input1) && (input2.getItem() == this.input2Icebolt) && ((input3.getItem() == this.input3) && (input3.getItemDamage() == this.input3Damage)))
			{
				return new ItemStack(RunicInscription.itemScrollIcebolt, 1);
			}
			else if((input1.getItem() == this.input1) && (input2.getItem() == this.input2Lightningbolt) && ((input3.getItem() == this.input3) && (input3.getItemDamage() == this.input3Damage)))
			{
				return new ItemStack(RunicInscription.itemScrollLightningbolt, 1);
			}
			else if((input1.getItem() == this.input1) && (input2.getItem() == this.input2Heal) && ((input3.getItem() == this.input3) && (input3.getItemDamage() == this.input3Damage)))
			{
				return new ItemStack(RunicInscription.itemScrollHeal, 1);
			}
			else if((input1.getItem() == this.input1) && (input2.getItem() == this.input2Dispel) && ((input3.getItem() == this.input3) && (input3.getItemDamage() == this.input3Damage)))
			{
				return new ItemStack(RunicInscription.itemScrollDispel, 1);
			}
			else if((input1.getItem() == this.input1) && (input2.getItem() == this.input2Firerain) && ((input3.getItem() == this.input3) && (input3.getItemDamage() == this.input3Damage)))
			{
				return new ItemStack(RunicInscription.itemScrollFirerain, 1);
			}
			else if((input1.getItem() == this.input1) && (input2.getItem() == this.input2Chainlightning) && ((input3.getItem() == this.input3) && (input3.getItemDamage() == this.input3Damage)))
			{
				return new ItemStack(RunicInscription.itemScrollChainlightning, 1);
			}
		}
		
		return null;
	}
}
