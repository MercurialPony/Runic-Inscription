package melonslise.runicinscription.client.renderer;

import org.lwjgl.opengl.GL11;

import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.client.model.ModelWritingTable;
import melonslise.runicinscription.common.tileentity.TileEntityWritingTable;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderWritingTable extends TileEntitySpecialRenderer
{
	private static final ResourceLocation texture = new ResourceLocation(RunicInscription.ID + ":" + "textures/models/WritingTable7.png");
	private ModelWritingTable model;
	
	public RenderWritingTable()
	{
		this.model = new ModelWritingTable();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(180, 0F, 0F, 1F);
		GL11.glRotatef(tileEntity.getBlockMetadata() * 90, 0.0F, 1.0F, 0.0F);
		
		this.bindTexture(texture);
		GL11.glPushMatrix();
		this.model.renderModel(0.0625F);
		
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	public void renderItem(TileEntity tileEntity, double x, double y, double z, float f)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(180, 0F, 0F, 1F);
		
		this.bindTexture(texture);
		GL11.glPushMatrix();
		this.model.renderModel(0.0625F);
		
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
