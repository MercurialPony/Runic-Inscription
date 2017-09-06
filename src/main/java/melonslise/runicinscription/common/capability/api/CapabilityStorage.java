package melonslise.runicinscription.common.capability.api;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CapabilityStorage<T extends ICapabilitySerializable> implements IStorage<T>
{
	@Override
	public NBTBase writeNBT(Capability<T> capability, T instance, EnumFacing side)
	{
		return instance.writeNBT(side);
	}

	@Override
	public void readNBT(Capability<T> capability, T instance, EnumFacing side, NBTBase nbt)
	{
		instance.readNBT(nbt);
	}
}
