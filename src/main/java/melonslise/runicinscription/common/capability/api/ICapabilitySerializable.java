package melonslise.runicinscription.common.capability.api;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;

public interface ICapabilitySerializable extends ICapabilityBase
{
	/**
	 * Is accessed from its capability's storage class for more centralized capability implementation.
	 * Serializes the capability instance to an NBT tag.
	 * 
	 * @return A sub class of NBTBase holding the data. Null can be passed if no data needs to be stored.
	 */
	public NBTBase writeNBT(EnumFacing facing);

	/**
	 * Is accessed from its capability's storage class for more centralized capability implementation.
	 * Read the capability instance from an NBT tag.
	 * 
	 * @param nbt An NBT holding the data. Null shouldn't be passed as then there is no point in calling this function.
	 */
	public void readNBT(NBTBase nbt);
}