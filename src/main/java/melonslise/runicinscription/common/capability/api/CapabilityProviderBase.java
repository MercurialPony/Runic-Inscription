package melonslise.runicinscription.common.capability.api;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class CapabilityProviderBase implements ICapabilityProvider
{
	protected Capability capability; // Getters/Setters (?)
	protected ICapabilityBase instance;
	protected EnumFacing side;

	public CapabilityProviderBase(Capability capability, ICapabilityBase instance, EnumFacing facing)
	{
		this.capability = capability;
		this.instance = instance;
		this.side = facing;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if(capability == this.capability)
		{
			return true;
		}
		return false;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if(capability == this.capability)
		{
			return (T) this. instance;
		}
		return null;
	}
}
