package melonslise.runicinscription.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityFirerain extends Entity
{
	private int ticks = 60;

	public EntityFirerain(World world)
	{
		super(world);
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
					this.worldObj.spawnParticle("flame", this.posX + (this.rand.nextInt(12) - 6) + ((this.rand.nextGaussian() * 0.1) - ((this.rand.nextGaussian() * 0.1))), this.posY + 8.5D + this.rand.nextInt(8) * 0.5, this.posZ + (this.rand.nextInt(12) - 6) + ((this.rand.nextGaussian() * 0.1) - ((this.rand.nextGaussian() * 0.1))), (this.rand.nextInt(10) - 5) * 0.02, -0.8D + this.rand.nextGaussian() * 0.02, (this.rand.nextInt(10) - 5) * 0.02);

					//TargetPoint point = new TargetPoint(this.dimension, this.posX, this.posY, this.posZ, 32.0D);

					//RunicInscription.network.sendToAllAround(new MessageParticle("flame", this.posX + (this.rand.nextInt(12) - 6) + ((this.rand.nextGaussian())* 0.1) - ((this.rand.nextGaussian() * 0.1)), this.posY + 5D + this.rand.nextInt(8) * 0.5, this.posZ +(this.rand.nextInt(12) - 6) + ((this.rand.nextGaussian()* 0.1) - ((this.rand.nextGaussian() * 0.1))), (this.rand.nextInt(10) - 5) * 0.02, -0.8D +this.rand.nextGaussian() * 0.02, (this.rand.nextInt(10) -5) * 0.02), point);
				}
			}

			--this.ticks;
		}
		else
		{
			if (!this.worldObj.isRemote)
			{
				this.setDead();
			}
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound)
	{

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{

	}
}
