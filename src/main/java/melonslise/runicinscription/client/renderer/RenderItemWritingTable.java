package melonslise.runicinscription.client.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class RenderItemWritingTable implements IItemRenderer
{
	private TileEntitySpecialRenderer render;
	private TileEntity tileEntity;
	
	public RenderItemWritingTable(TileEntitySpecialRenderer render, TileEntity tileEntity)
	{
		this.render = render;
		this.tileEntity = tileEntity;
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		if(type == IItemRenderer.ItemRenderType.ENTITY)
		{
			GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
		}
		
		((RenderWritingTable)this.render).renderItem(this.tileEntity, 0.0D, 0.0D, 0.0D, 0.0F);
	}
}
