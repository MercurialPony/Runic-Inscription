package melonslise.runicinscription.common.block;

import java.util.Random;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.common.tileentity.TileEntityWritingTable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockWritingTable extends BlockContainer
{
	private Random rand = new Random();

	public BlockWritingTable(Material material)
	{
		super(material);
		
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setLightLevel(1.0F);
		this.setStepSound(soundTypeWood);
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
	{
		int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, l, 2);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote)
		{
			FMLNetworkHandler.openGui(player, RunicInscription.instance, RunicInscription.guiIDScrollWriting, world, x, y, z);
		}
		
		return true;
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int x)
	{
		return new TileEntityWritingTable();
	}
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        int l = world.getBlockMetadata(x, y, z);

        if (l == 0)
        {
            world.spawnParticle("smoke", x + 0.875D, y + 1.5D, z + 0.5D, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", x + 0.875D, y + 1.5D, z + 0.5D, 0.0D, 0.0D, 0.0D);
        }
        else if (l == 1)
        {
            world.spawnParticle("smoke", x + 0.5D, y + 1.5D, z + 0.875D, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", x + 0.5D, y + 1.5D, z + 0.875D, 0.0D, 0.0D, 0.0D);
        }
        else if (l == 2)
        {
            world.spawnParticle("smoke", x + 0.125D, y + 1.5D, z + 0.5D, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", x + 0.125D, y + 1.5D, z + 0.5D, 0.0D, 0.0D, 0.0D);
        }
        else if (l == 3)
        {
            world.spawnParticle("smoke", x + 0.5D, y + 1.5D, z + 0.125D, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", x + 0.5D, y + 1.5D, z + 0.125D, 0.0D, 0.0D, 0.0D);
        }
    }
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block oldBlock, int oldMetadata)
	{
		TileEntityWritingTable tileEntityWriting = (TileEntityWritingTable) world.getTileEntity(x, y, z);
		
		if(tileEntityWriting != null)
		{
			for(int i = 0; i < tileEntityWriting.getSizeInventory() - 1; ++i)
			{
				ItemStack itemStack = tileEntityWriting.getStackInSlot(i);
				
				if(itemStack != null)
				{
					float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
					float f3 = this.rand.nextFloat() * 0.8F + 0.1F;
					
					while(itemStack.stackSize > 0)
					{
						int j = this.rand.nextInt(21) + 10;
						
						if(j > itemStack.stackSize)
						{
							j = itemStack.stackSize;
						}
						
						itemStack.stackSize -= j;
						
						EntityItem item = new EntityItem(world, (double)((float) x + f1), (double)((float) y + f2), (double)((float) z + f3), new ItemStack(itemStack.getItem(), j, itemStack.getItemDamage()));
						
						if(itemStack.hasTagCompound())
						{
							item.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
						}
						
                        world.spawnEntityInWorld(item);
					}
				}
			}
			
			world.func_147453_f(x, y, z, oldBlock);
		}
		
		super.breakBlock(world, x, y, z, oldBlock, oldMetadata);
	}
}
