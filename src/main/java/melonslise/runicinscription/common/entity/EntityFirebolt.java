package melonslise.runicinscription.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityFirebolt extends EntityProjectile
{
	public EntityFirebolt(World world)
	{
		super(world);
		this.setSize(0.25F, 0.25F);
	}

	public EntityFirebolt(World world, EntityLivingBase caster, double speed)
	{
		super(world, caster, speed);
		this.setSize(0.25F, 0.25F);
	}

	@Override
	protected void onImpact(RayTraceResult result)
	{
		System.out.println("IMPACT" + " is client " + this.worldObj.isRemote + ", " + result.entityHit);
		System.out.println(this.getCaster());
		if (result.entityHit != null)
		{
			if (!result.entityHit.isImmuneToFire())
			{
				boolean flag = result.entityHit.attackEntityFrom(DamageSource.inFire, 5.0F); // TODO OWN DAMAGE SRC
				if (flag)
				{
					//this.applyEnchantments(, result.entityHit); // Not sure what this is TODO
					result.entityHit.setFire(5);
				}
			}
		}
		else
		{
			boolean flag1 = this.worldObj.getGameRules().getBoolean("doFireTick");
			if (flag1)
			{
				BlockPos blockpos = result.getBlockPos().offset(result.sideHit);
				if (this.worldObj.isAirBlock(blockpos))
				{
					this.worldObj.setBlockState(blockpos, Blocks.FIRE.getDefaultState());
				}
			}
		}
		if(!this.isInWater())
		{
			for (int a = 0; a < 16; ++a)
			{
				this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, this.rand.nextGaussian() * 0.03D, 0.0D, this.rand.nextGaussian() * 0.03D, new int[0]);
			}
			for (int a = 0; a < 12; ++a) // TODO Add lava particles
			{
				this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY, this.posZ, -this.motionX * this.rand.nextGaussian() * 0.15D, -this.motionY * this.rand.nextGaussian() * 0.2D, -this.motionZ * this.rand.nextGaussian() * 0.15D, new int[0]);
			}
		}
		if(!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}

	@Override
	protected void spawnParticleTrail()
	{
		this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.3D, this.posZ, 0.0D, 0.0D, 0.0D);
	}

	@Override
	protected void entityInit()
	{
	}

	@Override
	public boolean diesInWater()
	{
		return true;
	}

	@Override
	public boolean shouldBurn()
	{
		return true;
	}
}
