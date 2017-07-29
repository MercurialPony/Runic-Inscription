package melonslise.runicinscription.common.tab;

import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.common.item.ItemList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabBase extends CreativeTabs
{
	public CreativeTabBase()
	{
		super(RunicInscription.ID);
	}

	@Override
	public Item getTabIconItem()
	{
		return ItemList.scrollEmpty;
	}
}