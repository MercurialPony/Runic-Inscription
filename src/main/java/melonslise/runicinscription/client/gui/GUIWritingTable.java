package melonslise.runicinscription.client.gui;

import org.lwjgl.opengl.GL11;

import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.common.container.ContainerWritingTable;
import melonslise.runicinscription.common.tileentity.TileEntityWritingTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIWritingTable extends GuiContainer
{
	private ResourceLocation texture = new ResourceLocation(RunicInscription.ID + ":" + "textures/gui/container/ScrollWriting.png");
	private TileEntityWritingTable tileEntityWriting;
	
	public GUIWritingTable(InventoryPlayer inventoryPlayer, TileEntityWritingTable tileEntity)
	{
		super(new ContainerWritingTable(inventoryPlayer, tileEntity));
		
		this.tileEntityWriting = tileEntity;
		
		this.xSize = 176;
		this.ySize = 166;
	}

	public void drawGuiContainerForegroundLayer(int i, int j)
	{
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, (this.ySize - 96) + 2, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.writing"), 60, (this.ySize - 160) + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i1, int i2)
	{
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
}
