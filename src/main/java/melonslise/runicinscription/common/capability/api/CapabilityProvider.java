package melonslise.runicinscription.common.capability.api;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class CapabilityProvider implements ICapabilityProvider
{
	protected Capability capability;
	protected ICapability instance;
	protected EnumFacing side;

	public CapabilityProvider(Capability capability, ICapability instance, EnumFacing facing)
	{
		this.capability = capability;
		this.instance = instance;
		this.side = facing;
	}

	@Override
	public boolean hasCapability(Capability capability, EnumFacing facing)
	{
		return capability == this.capability;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability == this.capability)
		{
			return (T) this.instance;
		}
		return null;
	}
}
