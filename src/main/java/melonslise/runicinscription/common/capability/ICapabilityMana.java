package melonslise.runicinscription.common.capability;

import melonslise.runicinscription.common.capability.api.ICapabilitySerializable;

public interface ICapabilityMana extends ICapabilitySerializable
{
	/**
	 * Increases the entity's current mana
	 */
	public void restore(int amount);

	/**
	 * Decreases the entitiy's current mana
	 */
	public void consume(int amount);

	/**
	 * Increases the entity's maximum mana
	 */
	public void increase(int amount);

	/**
	 * Decreases the entity's maximum mana
	 */
	public void decrease(int amount);

	/**
	 * Getter for the entity's current mana
	 */
	public int getCurrent();

	/**
	 * Setter for the entity's current mana
	 */
	public void setCurrent(int amount);

	/**
	 * Getter for the entity's maximum mana
	 */
	public int getMaximum();

	/**
	 * Setter for the entity's maximum mana
	 */
	public void setMaximum(int amount);

	/**
	 * Setter for both entity's current and maximum mana
	 */
	public void set(int current, int maximum);
}