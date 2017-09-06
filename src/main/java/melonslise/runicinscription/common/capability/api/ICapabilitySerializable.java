package melonslise.runicinscription.common.capability.api;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;

/**
 * Provides read/write NBT methods to the capability's storage for cleaner
 * capability creation.
 */
public interface ICapabilitySerializable extends ICapability
{
	/**
	 * Serializes the capability instance to an NBT tag.
	 */
	public NBTBase writeNBT(EnumFacing facing);

	/**
	 * Read the capability instance from an NBT tag.
	 */
	public void readNBT(NBTBase nbt);
}
