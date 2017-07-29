package melonslise.runicinscription.common.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import melonslise.runicinscription.RunicInscription;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class CropRoyalLily extends BlockCrops
{
	Random random = new Random();
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icon;
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iiconRegister)
	{
		this.icon = new IIcon[4];
		
		for(int i = 0; i < this.icon.length; ++i)
		{
			this.icon[i] = iiconRegister.registerIcon(RunicInscription.ID + ":" + this.getUnlocalizedName().substring(9) + (i + 1));
		}
	}
	
	@Override
	public IIcon getIcon(int side, int metadata)
	{
		if(metadata < 7)
		{
			if(metadata == 6)
			{
				metadata = 5;
			}
			
			return this.icon[metadata >> 1];
		}
		
		return this.icon[3];
	}
	
	@Override
	public int getRenderType()
	{
		return 1;
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}
	
	@Override
	protected Item func_149866_i()
	{
		double chance = Math.random() * 100;
		
		if(chance < 65)
		{
			return RunicInscription.itemRoyalLilyBud;
		}
		else
		{
			return null;
		}
	}
	
	@Override
    protected Item func_149865_P()
    {
		/*
		double chance = Math.random() * 100;
		
		if(chance < 4)
		{
			return RunicInscription.itemRoyalLilyRoot;
		}
		else
		{
			return null;
		}
		*/
		
		return null;
    }
}
