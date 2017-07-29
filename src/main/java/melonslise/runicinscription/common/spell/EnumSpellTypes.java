package melonslise.runicinscription.common.spell;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;

public enum EnumSpellTypes // TODO
{
	FIRE("", TextFormatting.DARK_RED),
	ICE("", TextFormatting.BLUE),
	LIGHTNING("", TextFormatting.YELLOW),
	NATURE("", TextFormatting.GREEN);

	/** Is the type's unlocalized lang file name. */
	private String name;
	/** Is the type's Minecraft color code. */
	private TextFormatting color;

	private EnumSpellTypes(String name, TextFormatting color)
	{
		this.name = name;
		this.color = color;
	}

	/**
	 * Returns the type's unlocalized lang file name.
	 */
	public String getUnlocalizedName()
	{
		return this.name();
	}

	/**
	 * Returns the type's Minecraft color code.
	 */
	public TextFormatting getColor()
	{
		return this.color;
	}

	/**
	 * Return the type's translated lang file name combined with it's Minecraft color code.
	 */
	public String getFormattedName()
	{
		String name = this.color + I18n.format(this.name);
		return name;
	}
}