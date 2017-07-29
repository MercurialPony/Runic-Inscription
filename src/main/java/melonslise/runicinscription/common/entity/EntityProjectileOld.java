package melonslise.runicinscription.common.entity;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityProjectileOld extends Entity // TODO Rename to magic projectile (?) // CURRENTLY UNUSED
{
	private EntityLivingBase caster;
	private String casterName; // TODO Remove
	/** Speed is measured in blocks/tick or 20 blocks/second. */ // TODO Replace speed with motion (?)
	private double speedX, speedY, speedZ;
	private int ticksAlive;

	public EntityProjectileOld(World worldIn) // TODO SET SIZE
	{
		super(worldIn); // TODO add player's motion 
	}

	public EntityProjectileOld(World world, EntityLivingBase caster, double speed)
	{
		super(world);
		this.caster = caster;
		this.setPosition(caster.posX, caster.posY, caster.posZ);
		this.setSpeedFromEntity(caster, speed);
	}

	public EntityProjectileOld(World world, EntityLivingBase caster, double speedX, double speedY, double speedZ)
	{
		super(world);
		this.caster = caster;
		this.setPosition(caster.posX, caster.posY,caster.posZ);
		this.setSpeed(speedX, speedY, speedZ); // TODO inacc
	}

	/**
	 * Not sure what this is used for :confused:
	 */
	@Override
	protected void entityInit()
	{
	}

	/**
	 * Sets the projectile's position in the world. Does the same thing as {@link #setPosition(double, double, double)}, but does not update the bounding box.
	 */
	public void setLocation(double coordinateX, double coordinateY, double coordinateZ)
	{
		this.posX = coordinateX;
		this.posY = coordinateY;
		this.posZ = coordinateZ;
	}

	@Override
	public void onUpdate() // TODO COMBINE CODE WITH COLLISION CHECK (?)
	{
		if(this.shouldBurn())
		{
			this.setFire(1);
		}
		// Collision
		super.onUpdate();
		RayTraceResult result = this.checkCollision();
		if (result != null)
		{
			if (result.typeOfHit == RayTraceResult.Type.BLOCK && this.worldObj.getBlockState(result.getBlockPos()).getBlock() == Blocks.PORTAL)
			{
				this.setPortal(result.getBlockPos());
			}
			else
			{
				//if(!net.minecraftforge.common.ForgeHooks.onThrowableImpact(this, raytraceresult)) // TODO OWN HOOK
				this.onImpact(result);
			}
		}
		// Movement
		this.setLocation(this.posX + this.speedX, this.posY + this.speedY, this.posZ + this.speedZ);
		// Water collision
		float multiplier = 1;
		if (this.isInWater()) // TODO FIX + DEATH
		{
			for (int j = 0; j < 4; ++j)
			{
				float f3 = 0.25F;
				this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.speedX * 0.25D, this.posY - this.speedY * 0.25D, this.posZ - this.speedZ * 0.25D, this.speedX, this.speedY, this.speedZ, new int[0]);
			}
			multiplier = 0.8F;
		}
		this.speedX *= (double) multiplier;
		this.speedY *= (double) multiplier;
		this.speedZ *= (double) multiplier;
		// TODO set up hasnogravity and gravity velocity
		this.setPosition(this.posX, this.posY, this.posZ); // Not sure why this is needed, but I suspect it has something to do with it's aabb
		// TODO Particle trail
		this.spawnParticleTrail();
	}

	protected RayTraceResult checkCollision()
	{
		Vec3d vector1 = new Vec3d(this.posX, this.posY, this.posZ);
		Vec3d vector2 = new Vec3d(this.posX + this.speedX, this.posY + this.speedY, this.posZ + this.speedZ);
		RayTraceResult result1 = this.worldObj.rayTraceBlocks(vector1, vector2);
		if (result1 != null)
		{
			vector2 = new Vec3d(result1.hitVec.xCoord, result1.hitVec.yCoord, result1.hitVec.zCoord);
		}
		Entity entity1 = null;
		List<Entity> entities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.speedX, this.speedY, this.speedZ).expandXyz(1.0D)); // TODO FIGURE OUT WHY EXPAND BY 1
		double d0 = 0.0D;
		for (int a = 0; a < entities.size(); ++a)
		{
			Entity entity2 = (Entity)entities.get(a);
			if(entity2.canBeCollidedWith() && entity2 != this.caster) // Exclude entities here
			{
				AxisAlignedBB aabb = entity2.getEntityBoundingBox().expandXyz(0.30000001192092896D); // Why expand?
				RayTraceResult result2 = aabb.calculateIntercept(vector1, vector2);
				if (result2 != null)
				{
					double d1 = vector1.squareDistanceTo(result2.hitVec);
					if (d1 < d0 || d0 == 0.0D)
					{
						entity1 = entity2;
						d0 = d1;
					}
				}
			}
		}
		if (entity1 != null)
		{
			result1 = new RayTraceResult(entity1);
		}
		return result1;
	}

	/**
	 * Called when the projectile collides with a block or entity.
	 */
	protected void onImpact(RayTraceResult result)
	{
		this.setDead();
	}

	/**
	 * Spawns particles around the projectile. Called from {@link #onUpdate()}.
	 */
	protected void spawnParticleTrail()
	{
		
	}

	/**
	 * Determines if the projectile will die after colliding with water.
	 */
	public boolean diesInWater()
	{
		return false;
	}

	/**
	 * Determines if the projectile should burn like a fireball.
	 * @return
	 */
	public boolean shouldBurn()
	{
		return false;
	}

	/**
	 * Sets the projectile's speed on the three axes.
	 */
	public void setSpeed(double speedX, double speedY, double speedZ)
	{
		this.speedX = speedX;
		this.speedY = speedY;
		this.speedZ = speedZ;
	}

	/**
	 * Sets the projectile's speed depending on the caster's facing.
	 */
	public void setSpeedFromEntity(EntityLivingBase caster, double speed)
	{
		Vec3d vector = caster.getLookVec();
		this.setSpeed(vector.xCoord * speed, vector.yCoord * speed, vector.zCoord * speed);
	}

	/**
	 * Returns the projectile's three-dimensional vector. The basis is measured in block/tick or 20 blocks/second.
	 */
	public Vec3d getSpeedVector()
	{
		Vec3d vector = new Vec3d(this.speedX, this.speedY, this.speedZ);
		return vector;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) // TODO Improve caster save/read
	{
		// Speed
		this.speedX = nbt.getDouble("SpeedX");
		this.speedY = nbt.getDouble("SpeedY");
		this.speedZ = nbt.getDouble("SpeedZ");
		// Caster
		this.caster = null;
		this.casterName = nbt.getString("ownerName");
		if (this.casterName != null && this.casterName.isEmpty())
		{
			this.casterName = null;
		}
		this.caster = this.getCaster();
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		// Speed
		nbt.setDouble("SpeedX", this.speedX);
		nbt.setDouble("SpeedY", this.speedY);
		nbt.setDouble("SpeedZ", this.speedZ);
		// Caster
		if ((this.casterName == null || this.casterName.isEmpty()) && this.caster instanceof EntityPlayer)
		{
			this.casterName = this.caster.getName();
		}
		nbt.setString("ownerName", this.casterName == null ? "" : this.casterName);
	}

	@Nullable
	public EntityLivingBase getCaster()
	{
		if (this.caster == null && this.casterName != null && !this.casterName.isEmpty())
		{
			this.caster = this.worldObj.getPlayerEntityByName(this.casterName);

			if (this.caster == null && this.worldObj instanceof WorldServer)
			{
				try
				{
					Entity entity = ((WorldServer)this.worldObj).getEntityFromUuid(UUID.fromString(this.casterName));
					if (entity instanceof EntityLivingBase)
					{
						this.caster = (EntityLivingBase)entity;
					}
				}
				catch (Throwable error)
				{
					this.caster = null;
				}
			}
		}
		return this.caster;
	}
}