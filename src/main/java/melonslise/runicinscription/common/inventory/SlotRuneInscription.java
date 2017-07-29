package melonslise.runicinscription.common.inventory;

import melonslise.runicinscription.common.container.ContainerRuneInscription;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotRuneInscription extends Slot
{
	ContainerRuneInscription containerInscription;

	public SlotRuneInscription(IInventory inventory, ContainerRuneInscription containerInscription, int index, int posX, int posY)
	{
		super(inventory, index, posX, posY);

		this.containerInscription = containerInscription;
	}

	@Override
	public void onSlotChanged()
	{
		if(this.slotNumber == 0)
		{
			this.containerInscription.onMatrixChanged();
		}
	}

	@Override
	public int getSlotStackLimit()
	{
		if(slotNumber == 1)
		{
			return 1;
		}
		else
		{
			return this.inventory.getInventoryStackLimit();
		}
	}

	@Override
	public boolean isItemValid(ItemStack itemStack)
	{
		if(slotNumber == 1)
		{
			return false;
		}
		else
		{
			return itemStack.getItem() == Items.dye;
		}
	}

	@Override
	public void onPickupFromSlot(EntityPlayer player, ItemStack itemStack)
	{
		if(this.slotNumber == 1)
		{
			ItemStack itemStack1 = this.inventory.getStackInSlot(0);

			if(itemStack1 != null)
			{
				this.inventory.decrStackSize(0, 1);
			}

			for(int x = 0; x < 5; ++x)
			{
				for(int y = 0; y < 7; ++y)
				{
					this.containerInscription.grid[x][y] = true;
				}
			}
		}
	}
}
