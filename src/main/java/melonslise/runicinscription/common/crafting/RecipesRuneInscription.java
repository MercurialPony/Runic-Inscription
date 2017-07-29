package melonslise.runicinscription.common.crafting;

import java.util.Arrays;

import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.common.inventory.InventoryRuneInscription;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesRuneInscription
{
	public static final boolean[][] Firebolt = new boolean[5][7];
	public static final Item inputFirebolt = Items.dye;
	public static final int inputDamageFirebolt = 1;

	public static final boolean[][] Icebolt = new boolean[5][7];
	public static final Item inputIcebolt = Items.dye;
	public static final int inputDamageIcebolt = 12;

	public static final boolean[][] Lightningbolt = new boolean[5][7];
	public static final Item inputLightningbolt = Items.dye;
	public static final int inputDamageLightningbolt = 11;

	public static final boolean[][] Heal = new boolean[5][7];
	public static final Item inputHeal = Items.dye;
	public static final int inputDamageHeal = 10;

	public static final boolean[][] Dispel = new boolean[5][7];
	public static final Item inputDispel = Items.dye;
	public static final int inputDamageDispel = 10;

	public static final boolean[][] Firerain = new boolean[5][7];
	public static final Item inputFirerain = Items.dye;
	public static final int inputDamageFirerain = 1;

	public static final boolean [][] Chainlightning = new boolean[5][7];
	public static final Item inputChainlightning = Items.dye;
	public static final int inputDamageChainlightning = 11;

	private static final RecipesRuneInscription instance = new RecipesRuneInscription();

	public static boolean compare2DArray(boolean[][] grid2, boolean[][] firebolt)
	{
		for (int index = 0; index < grid2.length; index ++)
		{
			if (!Arrays.equals(grid2[index], firebolt[index]))
			{
				return false;
			}
		}

		return true;
	}

	public static final RecipesRuneInscription getInstance()
	{
		return instance;
	}

	public RecipesRuneInscription()
	{

	}

	public ItemStack findMatchingRecipe(InventoryRuneInscription inventoryInscription, boolean[][] grid)
	{
		this.setRecipes();

		ItemStack input = inventoryInscription.getStackInSlot(0);

		if(input == null)
		{
			return null;
		}
		else
		{
			if((this.compare2DArray(grid, Firebolt) ==  true) && ((input.getItem() == this.inputFirebolt) && (input.getItemDamage() == this.inputDamageFirebolt)))
			{
				return new ItemStack(RunicInscription.itemRuneFirebolt, 1);
			}
			else if((this.compare2DArray(grid, Icebolt) ==  true) && ((input.getItem() == this.inputIcebolt) && (input.getItemDamage() == this.inputDamageIcebolt)))
			{
				return new ItemStack(RunicInscription.itemRuneIcebolt, 1);
			}
			else if((this.compare2DArray(grid, Lightningbolt) ==  true) && ((input.getItem() == this.inputLightningbolt) && (input.getItemDamage() == this.inputDamageLightningbolt)))
			{
				return new ItemStack(RunicInscription.itemRuneLightningbolt, 1);
			}
			else if((this.compare2DArray(grid, Heal) ==  true) && ((input.getItem() == this.inputHeal) && (input.getItemDamage() == this.inputDamageHeal)))
			{
				return new ItemStack(RunicInscription.itemRuneHeal, 1);
			}
			else if((this.compare2DArray(grid, Dispel) ==  true) && ((input.getItem() == this.inputDispel) && (input.getItemDamage() == this.inputDamageDispel)))
			{
				return new ItemStack(RunicInscription.itemRuneDispel, 1);
			}
			else if((this.compare2DArray(grid, Firerain) ==  true) && ((input.getItem() == this.inputFirerain) && (input.getItemDamage() == this.inputDamageFirerain)))
			{
				return new ItemStack(RunicInscription.itemRuneFirerain, 1);
			}
			else if((this.compare2DArray(grid, Chainlightning) ==  true) && ((input.getItem() == this.inputChainlightning) && (input.getItemDamage() == this.inputDamageChainlightning)))
			{
				return new ItemStack(RunicInscription.itemRuneChainlightning, 1);
			}
		}

		return null;
	}

	public void setRecipes()
	{
		Firebolt[0][0] = false; Firebolt[1][0] = false; Firebolt[2][0] = true; Firebolt[3][0] = false; Firebolt[4][0] = true;
		Firebolt[0][1] = false; Firebolt[1][1] = false; Firebolt[2][1] = true; Firebolt[3][1] = true; Firebolt[4][1] = false;
		Firebolt[0][2] = false; Firebolt[1][2] = false; Firebolt[2][2] = true; Firebolt[3][2] = false; Firebolt[4][2] = false;
		Firebolt[0][3] = false; Firebolt[1][3] = false; Firebolt[2][3] = true; Firebolt[3][3] = false; Firebolt[4][3] = false;
		Firebolt[0][4] = false; Firebolt[1][4] = false; Firebolt[2][4] = true; Firebolt[3][4] = false; Firebolt[4][4] = false;
		Firebolt[0][5] = false; Firebolt[1][5] = true; Firebolt[2][5] = true; Firebolt[3][5] = false; Firebolt[4][5] = false;
		Firebolt[0][6] = true; Firebolt[1][6] = false; Firebolt[2][6] = true; Firebolt[3][6] = false; Firebolt[4][6] = false;

		Icebolt[0][0] = false; Icebolt[1][0] = false; Icebolt[2][0] = true; Icebolt[3][0] = false; Icebolt[4][0] = false;
		Icebolt[0][1] = false; Icebolt[1][1] = false; Icebolt[2][1] = true; Icebolt[3][1] = false; Icebolt[4][1] = false;
		Icebolt[0][2] = false; Icebolt[1][2] = false; Icebolt[2][2] = true; Icebolt[3][2] = false; Icebolt[4][2] = false;
		Icebolt[0][3] = false; Icebolt[1][3] = false; Icebolt[2][3] = true; Icebolt[3][3] = false; Icebolt[4][3] = false;
		Icebolt[0][4] = false; Icebolt[1][4] = false; Icebolt[2][4] = true; Icebolt[3][4] = false; Icebolt[4][4] = false;
		Icebolt[0][5] = false; Icebolt[1][5] = false; Icebolt[2][5] = true; Icebolt[3][5] = false; Icebolt[4][5] = false;
		Icebolt[0][6] = false; Icebolt[1][6] = false; Icebolt[2][6] = true; Icebolt[3][6] = false; Icebolt[4][6] = false;

		Lightningbolt[0][0] = false; Lightningbolt[1][0] = true; Lightningbolt[2][0] = true; Lightningbolt[3][0] = false; Lightningbolt[4][0] = false;
		Lightningbolt[0][1] = false; Lightningbolt[1][1] = true; Lightningbolt[2][1] = false; Lightningbolt[3][1] = true; Lightningbolt[4][1] = false;
		Lightningbolt[0][2] = false; Lightningbolt[1][2] = true; Lightningbolt[2][2] = true; Lightningbolt[3][2] = false; Lightningbolt[4][2] = false;
		Lightningbolt[0][3] = false; Lightningbolt[1][3] = true; Lightningbolt[2][3] = false; Lightningbolt[3][3] = true; Lightningbolt[4][3] = false;
		Lightningbolt[0][4] = false; Lightningbolt[1][4] = true; Lightningbolt[2][4] = false; Lightningbolt[3][4] = false; Lightningbolt[4][4] = false;
		Lightningbolt[0][5] = false; Lightningbolt[1][5] = true; Lightningbolt[2][5] = false; Lightningbolt[3][5] = false; Lightningbolt[4][5] = false;
		Lightningbolt[0][6] = false; Lightningbolt[1][6] = true; Lightningbolt[2][6] = false; Lightningbolt[3][6] = false; Lightningbolt[4][6] = false;

		Heal[0][0] = true; Heal[1][0] = false; Heal[2][0] = false; Heal[3][0] = false; Heal[4][0] = true;
		Heal[0][1] = true; Heal[1][1] = true; Heal[2][1] = false; Heal[3][1] = true; Heal[4][1] = true;
		Heal[0][2] = true; Heal[1][2] = false; Heal[2][2] = true; Heal[3][2] = false; Heal[4][2] = true;
		Heal[0][3] = true; Heal[1][3] = true; Heal[2][3] = false; Heal[3][3] = true; Heal[4][3] = true;
		Heal[0][4] = true; Heal[1][4] = false; Heal[2][4] = false; Heal[3][4] = false; Heal[4][4] = true;
		Heal[0][5] = true; Heal[1][5] = false; Heal[2][5] = false; Heal[3][5] = false; Heal[4][5] = true;
		Heal[0][6] = true; Heal[1][6] = false; Heal[2][6] = false; Heal[3][6] = false; Heal[4][6] = true;

		Dispel[0][0] = false; Dispel[1][0] = false; Dispel[2][0] = true; Dispel[3][0] = false; Dispel[4][0] = false;
		Dispel[0][1] = true; Dispel[1][1] = false; Dispel[2][1] = true; Dispel[3][1] = false; Dispel[4][1] = false;
		Dispel[0][2] = false; Dispel[1][2] = true; Dispel[2][2] = true; Dispel[3][2] = false; Dispel[4][2] = false;
		Dispel[0][3] = false; Dispel[1][3] = false; Dispel[2][3] = true; Dispel[3][3] = true; Dispel[4][3] = false;
		Dispel[0][4] = false; Dispel[1][4] = false; Dispel[2][4] = true; Dispel[3][4] = false; Dispel[4][4] = true;
		Dispel[0][5] = false; Dispel[1][5] = false; Dispel[2][5] = true; Dispel[3][5] = false; Dispel[4][5] = false;
		Dispel[0][6] = false; Dispel[1][6] = false; Dispel[2][6] = true; Dispel[3][6] = false; Dispel[4][6] = false;

		Firerain[0][0] = false; Firerain[1][0] = false; Firerain[2][0] = false; Firerain[3][0] = false; Firerain[4][0] = false;
		Firerain[0][1] = true; Firerain[1][1] = false; Firerain[2][1] = false; Firerain[3][1] = false; Firerain[4][1] = false;
		Firerain[0][2] = true; Firerain[1][2] = false; Firerain[2][2] = false; Firerain[3][2] = true; Firerain[4][2] = false;
		Firerain[0][3] = true; Firerain[1][3] = false; Firerain[2][3] = true; Firerain[3][3] = false; Firerain[4][3] = true;
		Firerain[0][4] = false; Firerain[1][4] = true; Firerain[2][4] = false; Firerain[3][4] = false; Firerain[4][4] = true;
		Firerain[0][5] = false; Firerain[1][5] = false; Firerain[2][5] = false; Firerain[3][5] = false; Firerain[4][5] = true;
		Firerain[0][6] = false; Firerain[1][6] = false; Firerain[2][6] = false; Firerain[3][6] = false; Firerain[4][6] = false;

		Chainlightning[0][0] = false; Chainlightning[1][0] = false; Chainlightning[2][0] = false; Chainlightning[3][0] = false; Chainlightning[4][0] = false;
		Chainlightning[0][1] = true; Chainlightning[1][1] = false; Chainlightning[2][1] = false; Chainlightning[3][1] = true; Chainlightning[4][1] = false;
		Chainlightning[0][2] = true; Chainlightning[1][2] = true; Chainlightning[2][2] = false; Chainlightning[3][2] = true; Chainlightning[4][2] = false;
		Chainlightning[0][3] = true; Chainlightning[1][3] = false; Chainlightning[2][3] = true; Chainlightning[3][3] = true; Chainlightning[4][3] = false;
		Chainlightning[0][4] = true; Chainlightning[1][4] = false; Chainlightning[2][4] = false; Chainlightning[3][4] = true; Chainlightning[4][4] = false;
		Chainlightning[0][5] = true; Chainlightning[1][5] = false; Chainlightning[2][5] = false; Chainlightning[3][5] = true; Chainlightning[4][5] = false;
		Chainlightning[0][6] = false; Chainlightning[1][6] = false; Chainlightning[2][6] = false; Chainlightning[3][6] = false; Chainlightning[4][6] = false;
	}
}
