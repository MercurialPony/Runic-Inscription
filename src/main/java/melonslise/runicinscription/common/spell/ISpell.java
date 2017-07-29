package melonslise.runicinscription.common.spell;

import net.minecraft.util.SoundEvent;

public interface ISpell // Unused in current version
{
	/**
	 * What the spell does. Is called from the spell's item class on use.
	 */
	public void cast();

	/**
	 * Returns the spell's name for identification. // TODO Replace with int id (?)
	 */
	public String getRegistryName();

	/**
	 * Returns the spell's lang name.
	 */
	public String getUnlocalizedName();

	/**
	 * Returns the spell's lang description.
	 */
	public String getUnlocalizedDecription();

	/**
	 * Returns the spell's damage or heal amount. Used in the spell's item description only, but maybe used for the cast() method as well.
	 */
	public int getAmount();

	/**
	 * Returns the spell's cooldown time in ticks.
	 */
	public int getCooldown();

	/**
	 * Returns the spell's cast sound. Can be null.
	 */
	public SoundEvent getSound();

	/**
	 * Returns the spell's mana cost in rune form.
	 */
	public int getRuneCost();

	/**
	 * Returns the spell's mana cost in scroll form.
	 */
	public int getScrollCost();

	/**
	 * Returns the spell's mana cost in current form.
	 * @return
	 */
	public int getCost();

	/**
	 * Returns the spell's type. Used in the spell's item description only.
	 */
	public EnumSpellTypes getType();
}