package melonslise.runicinscription.common.capability;

import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.common.network.MessageMana;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class CapabilityMana implements ICapabilityMana
{
	public static final ResourceLocation ID = new ResourceLocation(RunicInscription.ID, "CapabilityMana");

	private EntityPlayer player;
	private int manaCurrent = 50;
	private int manaMaximum = 50; // TODO Default facing

	public CapabilityMana(EntityPlayer player)
	{
		this.player = player;
	}

	@Override
	public void restore(int amount)
	{
		if(amount < 0)
		{
			this.consume(amount);
		}
		else
		{
			this.setCurrent(this.getCurrent() + amount);
		}
	}

	@Override
	public void consume(int amount) // TODO Unify with above method
	{
		if(amount < 0)
		{
			this.restore(amount);
		}
		else
		{
			this.setCurrent(this.getCurrent() - amount);
		}
	}

	@Override
	public void increase(int amount)
	{
		if(amount < 0)
		{
			this.decrease(amount);
		}
		else
		{
			this.setMaximum(this.getMaximum() + amount);
		}
	}

	@Override
	public void decrease(int amount) // TODO Unify with above method
	{
		if(amount < 0)
		{
			this.increase(amount);
		}
		else
		{
			this.setMaximum(this.getMaximum() - amount);
		}
	}

	// Getter/Setters

	@Override
	public int getCurrent()
	{
		return this.manaCurrent;
	}

	@Override
	public void setCurrent(int amount)
	{
		if(amount <= 0)
		{
			this.manaCurrent = 0;
		}
		else if(amount >= this.manaMaximum)
		{
			this.manaCurrent = this.manaMaximum;
		}
		else if(amount < this.manaMaximum)
		{
			this.manaCurrent = amount;
		}
		this.synchronize();
	}

	@Override
	public int getMaximum()
	{
		return this.manaMaximum;
	}

	@Override
	public void setMaximum(int amount)
	{
		if(amount <= 0)
		{
			this.manaCurrent = this.manaMaximum = 0;
		}
		else
		{
			if(this.manaCurrent >= amount)
			{
				this.manaCurrent = amount;
			}
			this.manaMaximum = amount;
		}
		this.synchronize();
	}

	@Override
	public NBTBase writeNBT(EnumFacing facing)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("ManaCurrent", this.getCurrent());
		nbt.setInteger("ManaMaximum", this.getMaximum());
		return  nbt;
	}

	@Override
	public void readNBT(NBTBase nbt)
	{
		this.manaCurrent = (((NBTTagCompound) nbt).getInteger("ManaCurrent"));
		this.manaMaximum = (((NBTTagCompound) nbt).getInteger("ManaMaximum"));
	}

	@Override
	public ResourceLocation getID()
	{
		return this.ID;
	}

	@Override
	public void set(int current, int maximum)
	{
		this.setCurrent(current);
		this.setMaximum(maximum);
	}

	@Override
	public void synchronize()
	{
		RunicInscription.network.sendTo(new MessageMana(this.getCurrent(), this.getMaximum()), (EntityPlayerMP) this.player);
	}
}