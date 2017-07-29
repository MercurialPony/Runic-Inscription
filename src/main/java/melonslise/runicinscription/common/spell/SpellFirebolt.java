package melonslise.runicinscription.common.spell;

import melonslise.runicinscription.common.entity.EntityFirebolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SpellFirebolt extends Spell
{
	public SpellFirebolt(String name)
	{
		super(name);
	}

	@Override
	public void cast(World world, EntityPlayer caster)
	{
		Vec3d vector = caster.getLookVec();
		EntityFirebolt projectile = new EntityFirebolt(world, caster, 1);
		projectile.setPosition(caster.posX, caster.posY + caster.getEyeHeight(), caster.posZ);
		world.spawnEntityInWorld(projectile);
	}

	@Override
	public String getUnlocalizedName()
	{
		return null;
	}

	@Override
	public String getUnlocalizedDecription()
	{
		return null;
	}

	@Override
	public int getAmount()
	{
		return 0;
	}

	@Override
	public int getCooldown()
	{
		return 0;
	}

	@Override
	public SoundEvent getSound()
	{
		return null;
	}

	@Override
	public int getRuneCost()
	{
		return 0;
	}

	@Override
	public int getScrollCost()
	{
		return 0;
	}

	@Override
	public EnumSpellTypes getType()
	{
		return null;
	}
}