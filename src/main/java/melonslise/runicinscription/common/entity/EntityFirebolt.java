package melonslise.runicinscription.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFirebolt extends EntityFireball
{
	public EntityFirebolt(World world)
	{
		super(world);
		this.setSize(0.3125F, 0.3125F);
	}

	public EntityFirebolt(World world, double x, double y, double z, double j, double k, double l)
	{
		super(world, x, y, z, j, k, l);
		this.setSize(0.3125F, 0.3125F);
	}

	public EntityFirebolt(World world, EntityLivingBase entity, double x, double y, double z)
	{
		super(world, entity, x, y, z);
		this.setSize(0.3125F, 0.3125F);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float x)
	{
		return false;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return false;
	}

	@Override
	protected void onImpact(MovingObjectPosition position)
	{
		if (!this.worldObj.isRemote)
		{
			if (position.entityHit != null)
			{
				if (!position.entityHit.isImmuneToFire() && position.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 5.0F))
				{
					position.entityHit.setFire(4);
				}
			}
			else
			{
				switch (position.sideHit)
				{
				case 0:
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
				}
			}

			this.setDead();
		}

		for (int a = 0; a < 16; ++a)
		{
			this.worldObj.spawnParticle("largesmoke", this.posX, this.posY, this.posZ, this.rand.nextGaussian() * 0.03D, 0.0D, this.rand.nextGaussian() * 0.03D);
		}

		for (int b = 0; b < 12; ++b)
		{
			this.worldObj.spawnParticle("flame", this.posX, this.posY, this.posZ, -this.motionX * this.rand.nextGaussian() * 0.15D, -this.motionY * this.rand.nextGaussian() * 0.2D, -this.motionZ * this.rand.nextGaussian() * 0.15D);
		}

	}
}
