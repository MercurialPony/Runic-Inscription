package melonslise.runicinscription.common.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockMonolithBase extends Block
{
	@SideOnly(Side.CLIENT)
    protected IIcon iconFront;
	
	public BlockMonolithBase(Material material) 
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
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
    {
        int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
    	for(int quantity = 1; quantity < random.nextInt(5); ++quantity)
    	{
    		world.spawnParticle("happyVillager", x + (random.nextInt(10) - 5) + ((random.nextGaussian() * 0.1) - ((random.nextGaussian() * 0.1))), y + (random.nextInt(8) * 0.5), z + (random.nextInt(10) - 5) + ((random.nextGaussian() * 0.1) - ((random.nextGaussian() * 0.1))), (random.nextInt(8) - 4) * 0.01, 0.7D + (random.nextGaussian() * 0.03), (random.nextInt(8) - 4) * 0.01);
    	}
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        return side == 1 ? this.blockIcon : (side == 0 ? this.blockIcon : (metadata == 2 && side == 2 ? this.iconFront : (metadata == 3 && side == 5 ? this.iconFront : (metadata == 0 && side == 3 ? this.iconFront : (metadata == 1 && side == 4 ? this.iconFront : this.blockIcon)))));
    }
}
