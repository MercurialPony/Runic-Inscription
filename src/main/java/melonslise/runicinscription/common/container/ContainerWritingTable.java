package melonslise.runicinscription.common.container;

import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.common.crafting.RecipesScrollWriting;
import melonslise.runicinscription.common.inventory.SlotScrollWriting;
import melonslise.runicinscription.common.tileentity.TileEntityWritingTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerWritingTable extends Container
{
	private static final int Input1 = 0;
	private static final int Input2 = 1;
	private static final int Input3 = 2;
	private static final int Output = 3;
	private TileEntityWritingTable tileEntityWriting;
	
	public ContainerWritingTable(InventoryPlayer inventoryPlayer, TileEntityWritingTable tileEntity)
	{
		this.tileEntityWriting = tileEntity;
		
		this.addSlots(inventoryPlayer);
	}
	
	public void addSlots(InventoryPlayer inventoryPlayer)
	{
		this.addSlotToContainer(new SlotScrollWriting(this.tileEntityWriting, this, this.Input1, 27, 47 ));
		this.addSlotToContainer(new SlotScrollWriting(this.tileEntityWriting, this, this.Input2, 76, 47));
		this.addSlotToContainer(new SlotScrollWriting(this.tileEntityWriting, this, this.Input3, 134, 13));
		this.addSlotToContainer(new SlotScrollWriting(this.tileEntityWriting, this, this.Output, 134, 47));
		
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
	}
	
	public void onMatrixChanged()
	{
		this.tileEntityWriting.setInventorySlotContents(3, RecipesScrollWriting.getInstance().findMatchingRecipe(this.tileEntityWriting));
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int fromSlot)
	{
		ItemStack itemStack = null;
		Slot slot = (Slot)this.inventorySlots.get(fromSlot);

		if ((slot != null) && slot.getHasStack())
		{
			ItemStack itemStack1 = slot.getStack();
			itemStack = itemStack1.copy();

			if(fromSlot == 1)
			{
				if (!this.mergeItemStack(itemStack1, this.Output + 1, this.Output + 36 + 1, true))
				{
					return null;
				}
			}
			else if((fromSlot != 0) && (fromSlot != 1) && (fromSlot != 2) && (fromSlot != 3))
			{
				if(itemStack1.getItem() == Items.dye)
				{
					if(!this.mergeItemStack(itemStack1, this.Input3, this.Input3 + 1, false))
					{
						return null;
					}
				}
				else if((itemStack1.getItem() == RunicInscription.itemRuneFirebolt) || (itemStack1.getItem() == RunicInscription.itemRuneIcebolt) || (itemStack1.getItem() == RunicInscription.itemRuneLightningbolt) || (itemStack1.getItem() == RunicInscription.itemRuneHeal) || (itemStack1.getItem() == RunicInscription.itemRuneDispel) || (itemStack1.getItem() == RunicInscription.itemRuneFirerain) || (itemStack1.getItem() == RunicInscription.itemRuneChainlightning))
				{
					if(!this.mergeItemStack(itemStack1, this.Input2, this.Input2 + 1, false))
					{
						return null;
					}
				}
				else if(itemStack1.getItem() == RunicInscription.itemScrollEmpty)
				{
					if(!this.mergeItemStack(itemStack1, this.Input1, this.Input1 + 1, false))
					{
						return null;
					}
				}
				else if((fromSlot >= (this.Output + 1)) && (fromSlot < (this.Output + 27 + 1)))
				{
					if (!this.mergeItemStack(itemStack1, this.Output + 27 + 1, this.Output + 36 + 1, false))
					{
						return null;
					}
				}
				else if((fromSlot >= (this.Output + 27 + 1)) && (fromSlot < (this.Output + 36 + 1)) && !this.mergeItemStack(itemStack1, this.Output + 1, this.Output + 27 + 1, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemStack1, this.Output + 1, this.Output + 36 + 1, false))
			{
				return null;
			}

			if (itemStack1.stackSize == 0)
			{
				slot.putStack((ItemStack)null);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (itemStack1.stackSize == itemStack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(player, itemStack1);
		}

		return itemStack;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return this.tileEntityWriting.isUseableByPlayer(player);
	}
}
