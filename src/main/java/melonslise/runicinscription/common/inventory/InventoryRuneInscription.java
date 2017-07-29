package melonslise.runicinscription.common.inventory;

import melonslise.runicinscription.common.container.ContainerRuneInscription;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryRuneInscription implements IInventory
{
	private ItemStack[] inventory = new ItemStack[2];
	private String name = "container.inscription";

	public InventoryRuneInscription(ContainerRuneInscription containerInscription)
	{
		
	}

	@Override
	public int getSizeInventory()
	{
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		if(slot >= this.getSizeInventory())
		{
			return null;
		}
		else
		{
			return this.inventory[slot];
		}
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
	{
		if(this.inventory[slot] != null)
		{
			ItemStack itemStack;

			if(this.inventory[slot].stackSize > amount)
			{
				itemStack = this.inventory[slot].splitStack(amount);
				if (this.inventory[slot].stackSize == 0)
				{
					this.inventory[slot] = null;
				}
				return itemStack;
			}
			else
			{
				itemStack = this.inventory[slot];
				this.inventory[slot] = null;
				return itemStack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		if(this.inventory[slot] != null)
		{
			ItemStack itemStack = this.inventory[slot];
			this.inventory[slot] = null;
			return itemStack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack)
	{
		this.inventory[slot] = itemStack;
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public String getInventoryName()
	{
		return this.name;
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		if(this.name.length() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void markDirty()
	{

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return true;
	}

	@Override
	public void openInventory()
	{

	}

	@Override
	public void closeInventory()
	{

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack)
	{
		if(slot == 1)
		{
			return false;
		}
		else
		{
			return itemStack.getItem() == Items.dye;
		}
	}
}
