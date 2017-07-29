package melonslise.runicinscription.common.capability.api;

import net.minecraft.util.ResourceLocation;

/**
 *  Extended by other capabilities for use in the mod's capability provider API.
 */
public interface ICapabilityBase // TODO Replace with holder
{
	/**
	 * Retrieves the capability's unique ID in form of a ResourceLocation.
	 * 
	 * @return A ResourceLocation containing a unique ID and preferably the mod's ID.
	 */
	public ResourceLocation getID();

	/**
	 * Synchronizes the server's data with the client.
	 */
	public void synchronize();
}