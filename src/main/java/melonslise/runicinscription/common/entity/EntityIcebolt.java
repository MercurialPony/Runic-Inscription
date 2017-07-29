package melonslise.runicinscription.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityIcebolt extends EntityProjectile
{
	public EntityIcebolt(World world) // TODO SIZE
	{
		super(world);
	}

	public EntityIcebolt(World world, EntityLivingBase caster, double speed)
	{
		super(world, caster, speed);
	}

	@Override
	protected void onImpact(RayTraceResult result)
	{
		if (!this.worldObj.isRemote)
		{
			if (result.entityHit != null)
			{
				boolean flag = result.entityHit.attackEntityFrom(DamageSource.magic, 3.0F);
				if (flag)
				{
					this.applyEnchantments(this.getCaster(), result.entityHit); // TODO
					if(result.entityHit instanceof EntityLivingBase)
					{
						((EntityLivingBase) result.entityHit).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2 *20, 5));
					}
				}
			}
			this.setDead();
		}
		for (int a = 0; a < 10; ++a)
		{
			this.worldObj.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, -this.motionX * this.rand.nextGaussian() * 0.15D, -0.2D, -this.motionZ * this.rand.nextGaussian() * 0.15D);
		}
	}

	protected void spawnParticleTrail()
	{
		this.worldObj.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX, this.posY + 0.3D, this.posZ, 0.0D, 0.0D, 0.0D);
	}

	@Override
	protected void entityInit()
	{
	}

	@Override
	public boolean diesInWater()
	{
		return false;
	}

	@Override
	public boolean shouldBurn()
	{
		return false;
	}
}