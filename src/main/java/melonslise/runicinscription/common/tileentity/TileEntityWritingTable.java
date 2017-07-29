package melonslise.runicinscription.common.tileentity;

import melonslise.runicinscription.RunicInscription;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWritingTable extends TileEntity implements ISidedInventory
{
	private ItemStack[] inventory = new ItemStack[4];
	private String name = "container.writing";
	
	public TileEntityWritingTable()
	{
		
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
			}
			else
			{
				itemStack = this.inventory[slot];
				this.inventory[slot] = null;
			}
			
			return itemStack;
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
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		if(this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this)
		{
			return false;
		}
		else
		{
			return player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64;
		}
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
		if(slot == 3)
		{
			return false;
		}
		else if(slot == 2)
		{
			return (itemStack.getItem() == Items.dye) && (itemStack.getItemDamage() == 0);
		}
		else if(slot == 1)
		{
			if((itemStack.getItem() == RunicInscription.itemRuneFirebolt) || (itemStack.getItem() == RunicInscription.itemRuneIcebolt) || (itemStack.getItem() == RunicInscription.itemRuneLightningbolt) || (itemStack.getItem() == RunicInscription.itemRuneHeal) || (itemStack.getItem() == RunicInscription.itemRuneDispel) || (itemStack.getItem() == RunicInscription.itemRuneFirerain) || (itemStack.getItem() == RunicInscription.itemRuneChainlightning))
			{
				return true;
			}
		}
		else if(slot == 0)
		{
			return itemStack.getItem() == RunicInscription.itemScrollEmpty;
		}
		
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int slots)
	{
		//TODO
		return null;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemStack, int side)
	{
		//TODO
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemStack, int side)
	{
		//TODO
		return false;
	}

	@Override
	public int getSizeInventory()
	{
		return this.inventory.length;
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
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		NBTTagList list = nbt.getTagList("Items", 10);
		this.inventory = new ItemStack[this.getSizeInventory()];
		for(int i = 0; i < list.tagCount(); ++i)
		{
			NBTTagCompound nbt1 = ((NBTTagCompound) list.getCompoundTagAt(i));
			byte b = nbt1.getByte("Slot");
			
			if((b >= 0) && (b < this.inventory.length))
			{
				this.inventory[b] = ItemStack.loadItemStackFromNBT(nbt1);
			}
		}
		
		if(nbt.hasKey("CustomName"))
		{
			this.name = nbt.getString("CustomName");
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		NBTTagList list = new NBTTagList();
		
		for(int i = 0; i < this.inventory.length; ++i)
		{
			if(this.inventory[i] != null)
			{
				NBTTagCompound nbt1 = new NBTTagCompound();
				nbt1.setByte("Slot", (byte) i);
				this.inventory[i].writeToNBT(nbt1);
				list.appendTag(nbt1);
			}
		}
		
		nbt.setTag("Items", list);
		
		if(this.hasCustomInventoryName())
		{
			nbt.setString("CustomName", this.name);
		}
	}
}
