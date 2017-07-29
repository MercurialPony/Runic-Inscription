package melonslise.runicinscription.common.entity.player;

import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.common.network.MessageCurrentMana;
import melonslise.runicinscription.common.network.MessageMaxMana;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants;

public class PropertiesMana implements IExtendedEntityProperties
{
	public final static String EXT_PROP_MANA = RunicInscription.ID + "_Mana";

	public static final PropertiesMana get(EntityPlayer player)
	{
		return (PropertiesMana) player.getExtendedProperties(EXT_PROP_MANA);
	}

	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(EXT_PROP_MANA, new PropertiesMana(player));
	}
	
	public int ticksMana;
	
	private final EntityPlayer player;

	private int currentMana;
	private int maxMana;

	public PropertiesMana(EntityPlayer player)
	{
		this.player = player;
		this.currentMana = 75;
		this.maxMana = 75;
	}

	public boolean consumeMana(int amount)
	{
		if (this.currentMana >= amount)
		{
			this.setCurrentMana(this.currentMana - amount);
			return true;
		}
		else
		{
			if (!this.player.worldObj.isRemote)
			{
				RunicInscription.network.sendTo(new MessageCurrentMana(this.currentMana), (EntityPlayerMP) this.player);
			}
			return false;

		}
	}
	
	public void replenishMana()
	{
		this.setCurrentMana(this.maxMana);
	}

	public int getCurrentMana()
	{
		return this.currentMana;
	}

	public int getMaxMana()
	{
		return this.maxMana;
	}

	@Override
	public void init(Entity entity, World world)
	{

	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		if (compound.hasKey(EXT_PROP_MANA, Constants.NBT.TAG_COMPOUND))
		{
			NBTTagCompound properties = compound.getCompoundTag(EXT_PROP_MANA);

			this.currentMana = properties.getInteger("currentMana");
			this.maxMana = properties.getInteger("maxMana");
		}
	}

	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = new NBTTagCompound();

		properties.setInteger("currentMana", this.currentMana);
		properties.setInteger("maxMana", this.maxMana);

		compound.setTag(EXT_PROP_MANA, properties);
	}

	public void setCurrentMana(int amount)
	{
		if (amount <= 0)
		{
			this.currentMana = 0;
		}
		else if (amount < this.maxMana)
		{
			this.currentMana = amount;
		}
		else
		{
			this.currentMana = this.maxMana;
		}

		if (!this.player.worldObj.isRemote)
		{
			RunicInscription.network.sendTo(new MessageCurrentMana(this.currentMana), (EntityPlayerMP) this.player);
		}
	}

	public void setMaxMana(int amount)
	{
		if (amount <= 0)
		{
			this.maxMana = 0;
		}
		else
		{
			this.maxMana = amount;
			this.setCurrentMana(this.currentMana + amount);
		}

		if (!this.player.worldObj.isRemote)
		{
			RunicInscription.network.sendTo(new MessageMaxMana(this.maxMana), (EntityPlayerMP) this.player);
		}
	}
}
