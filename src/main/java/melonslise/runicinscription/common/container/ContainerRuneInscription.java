package melonslise.runicinscription.common.container;

import melonslise.runicinscription.common.crafting.RecipesRuneInscription;
import melonslise.runicinscription.common.inventory.InventoryRuneInscription;
import melonslise.runicinscription.common.inventory.SlotRuneInscription;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerRuneInscription extends Container
{
	private static final int Input = 0;
	private static final int Output = 1;
	public boolean grid[][] = new boolean[5][7];
	private InventoryRuneInscription inventoryInscription = new InventoryRuneInscription(this);
	private World worldobj;
	
	public ContainerRuneInscription(InventoryPlayer inventoryPlayer, World world, int x, int y, int z)
	{
		this.worldobj = world;
		this.addSlots(inventoryPlayer);
	}

	public void addSlots(InventoryPlayer inventoryPlayer)
	{
		this.addSlotToContainer(new SlotRuneInscription(this.inventoryInscription, this, Input, 8, 47));
		this.addSlotToContainer(new SlotRuneInscription(this.inventoryInscription, this, Output, 152, 47));

		for (int i = 0; i < 3; ++i)
		{
			for (int k = 0; k < 9; ++k)
			{
				this.addSlotToContainer(new Slot(inventoryPlayer, k + (i * 9) + 9, 8 + (k * 18), 124 + (i * 18)));
			}
		}

		for (int i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + (i * 18), 182));
		}
	}

	public void onMatrixChanged()
	{
		this.inventoryInscription.setInventorySlotContents(1, RecipesRuneInscription.getInstance().findMatchingRecipe(this.inventoryInscription, this.grid));
	}

	@Override
	public void onContainerClosed(EntityPlayer player)
	{
		super.onContainerClosed(player);

		for(int x = 0; x < 5; ++x)
		{
			for(int y = 0; y < 7; ++y)
			{
				this.grid[x][y] = false;
			}
		}

		if(!this.worldobj.isRemote)
		{
			ItemStack itemStack = this.inventoryInscription.getStackInSlotOnClosing(0);

			if (itemStack != null)
			{
				player.dropPlayerItemWithRandomChoice(itemStack, false);
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
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
			else if((fromSlot != 0) && (fromSlot != 1))
			{
				if(itemStack1.getItem() == Items.dye)
				{
					if(!this.mergeItemStack(itemStack1, this.Input, this.Input + 1, false))
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
}
