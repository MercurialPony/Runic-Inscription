package melonslise.runicinscription.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityFireRain extends Entity
{
	private int ticks = 60;

	public EntityFireRain(World world)
	{
		super(world);
	}

	public EntityFireRain(World worldIn, EntityPlayer caster)
	{
		super(worldIn);
		this.setLocationAndAngles(caster.posX, caster.posY, caster.posZ, caster.rotationYaw, caster.rotationPitch);
	}

	@Override
	protected void entityInit()
	{
		
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (this.ticks > 0)
		{
			if (this.ticks % 5 == 0)
			{
				for (int i = 0; i < 125; ++i)
				{
					this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX + (this.rand.nextInt(12) - 6) + ((this.rand.nextGaussian() * 0.1) - ((this.rand.nextGaussian() * 0.1))), this.posY + 8.5D + this.rand.nextInt(8) * 0.5, this.posZ + (this.rand.nextInt(12) - 6) + ((this.rand.nextGaussian() * 0.1) - ((this.rand.nextGaussian() * 0.1))), (this.rand.nextInt(10) - 5) * 0.02, -0.8D + this.rand.nextGaussian() * 0.02, (this.rand.nextInt(10) - 5) * 0.02);
				}
			}
			--this.ticks;
		}
		else
		{
			if (!this.worldObj.isRemote)
			{
				this.setDead(); // TODO Check if needs to be on client as well
			}
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
		this.ticks = nbt.getInteger("Ticks");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("Ticks", this.ticks);
	}
}