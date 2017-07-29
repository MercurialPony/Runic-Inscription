package melonslise.runicinscription.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockMonolith extends Block
{
	public BlockMonolith(Material material)
	{
		super(material);
		
		this.setHardness(1.5F);
		this.setResistance(40);
		this.setStepSound(soundTypeStone);
	}
	
    @Override
    public Item getItemDropped(int i1, Random random, int i2)
    {
    	return null;
    }
}
