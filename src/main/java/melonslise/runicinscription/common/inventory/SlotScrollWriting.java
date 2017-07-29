package melonslise.runicinscription.common.inventory;

import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.common.container.ContainerWritingTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotScrollWriting extends Slot
{
	ContainerWritingTable containerWriting;
	
	public SlotScrollWriting(IInventory inventory, ContainerWritingTable containerWriting, int index, int posX, int posY)
	{
		super(inventory, index, posX, posY);
		
		this.containerWriting = containerWriting;
	}
	
	@Override
	public void onSlotChanged()
	{
		this.containerWriting.onMatrixChanged();
	}
	
	@Override
	public int getSlotStackLimit()
	{
		if(slotNumber == 3)
		{
			return 1;
		}
		else
		{
			return 64;
		}
	}
	
	@Override
	public boolean isItemValid(ItemStack itemStack)
	{
		if(slotNumber == 3)
		{
			return false;
		}
		else if(slotNumber == 2)
		{
			return (itemStack.getItem() == Items.dye) && (itemStack.getItemDamage() == 0);
		}
		else if(slotNumber == 1)
		{
			return (itemStack.getItem() == RunicInscription.itemRuneFirebolt) || (itemStack.getItem() == RunicInscription.itemRuneIcebolt) || (itemStack.getItem() == RunicInscription.itemRuneLightningbolt) || (itemStack.getItem() == RunicInscription.itemRuneHeal) || (itemStack.getItem() == RunicInscription.itemRuneDispel) || (itemStack.getItem() == RunicInscription.itemRuneFirerain) || (itemStack.getItem() == RunicInscription.itemRuneChainlightning);
		}
		else if(slotNumber == 0)
		{
			return itemStack.getItem() == RunicInscription.itemScrollEmpty;
		}
		
		return true;
	}
	
	@Override
	public void onPickupFromSlot(EntityPlayer player, ItemStack itemStack)
	{
		if(this.slotNumber == 3)
		{
			ItemStack itemStack1 = this.inventory.getStackInSlot(0);
			ItemStack itemStack2 = this.inventory.getStackInSlot(2);

			if(itemStack1 != null)
			{
				this.inventory.decrStackSize(0, 1);
			}
			
			if(itemStack2 != null)
			{
				this.inventory.decrStackSize(2, 1);
			}
		}
	}
}
