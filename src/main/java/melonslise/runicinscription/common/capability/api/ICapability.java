package melonslise.runicinscription.common.capability.api;

import net.minecraft.util.ResourceLocation;

/**
 * Extended by other capabilities for use in the mod's capability API.
 */
public interface ICapability // TODO Replace with holder
{
	/**
	 * Retrieves the capability's unique ID in form of a ResourceLocation.
	 */
	public ResourceLocation getID();

	/**
	 * Synchronizes the server's data with the client.
	 */
	public void synchronize();
}
