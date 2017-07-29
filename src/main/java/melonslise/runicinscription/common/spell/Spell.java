package melonslise.runicinscription.common.spell;

import melonslise.runicinscription.common.capability.ICapabilityMana;
import melonslise.runicinscription.common.event.CapabilityEventHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public abstract class Spell
{
	private final String name;

	public Spell(String name)
	{
		this.name = name;
	}

	/**
	 * Checks if the spell can be cast. Is called first from spell's item class.
	 */
	public void check(World world, EntityPlayer caster)
	{
		if(!world.isRemote)
		{
			if(CapabilityEventHandler.capability != null && caster.hasCapability(CapabilityEventHandler.capability, null))
			{
				ICapabilityMana capability = caster.getCapability(CapabilityEventHandler.capability, null);
				if(capability.getCurrent() >= this.getCost())
				{
					capability.consume(this.getCost());
					this.cast(world, caster);
				}
			}
		}
	}

	/**
	 * What the spell does. Is called after the spell handles the checks.
	 */
	public abstract void cast(World world, EntityPlayer caster);

	/**
	 * Returns the spell's name for identification. // TODO Replace with int id (?)
	 */
	public String getRegistryName()
	{
		return this.getRegistryName();
	}

	/**
	 * Returns the spell's unlocalized lang file name.
	 */
	public abstract String getUnlocalizedName();

	/**
	 * Returns the spell's unlocalized lang file description.
	 */
	public abstract String getUnlocalizedDecription();

	/**
	 * Return's the spell's translated lang file name.
	 */
	public String getFormattedName()
	{
		String format = I18n.format(this.getUnlocalizedName());
		return format;
	}

	/**
	 * Returns the spell's translated lang file description.
	 */
	public String getFormattedDescription()
	{
		String format = I18n.format(this.getUnlocalizedDecription()); // TODO Merge
		return format;
	}

	/**
	 * Returns the spell's damage or heal amount. Used in the spell's item description only, but maybe used for the cast() method as well.
	 */
	public abstract int getAmount();

	/**
	 * Returns the spell's cooldown time in ticks.
	 */
	public abstract int getCooldown();

	/**
	 * Returns the spell's cast sound. Can be null.
	 */
	public abstract SoundEvent getSound(); // TODO Change return type to soundevent

	/**
	 * Returns the spell's mana cost in rune form.
	 */
	public abstract int getRuneCost();

	/**
	 * Returns the spell's mana cost in scroll form.
	 */
	public abstract int getScrollCost();

	/**
	 * Returns the spell's mana cost in current form.
	 */
	public int getCost() // TODO
	{
		return 0;
	}

	/**
	 * Returns the spell's type. Used in the spell's item description only.
	 */
	public abstract EnumSpellTypes getType();
}