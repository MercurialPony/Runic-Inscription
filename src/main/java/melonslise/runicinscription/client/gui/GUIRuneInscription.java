package melonslise.runicinscription.client.gui;

import org.lwjgl.opengl.GL11;

import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.common.container.ContainerRuneInscription;
import melonslise.runicinscription.common.network.MessageGrid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GUIRuneInscription extends GuiContainer
{
	private ResourceLocation texture = new ResourceLocation(RunicInscription.ID + ":" + "textures/gui/container/RuneInscription.png");
	private ContainerRuneInscription containerInscription = (ContainerRuneInscription) this.inventorySlots;

	public GUIRuneInscription(InventoryPlayer inventoryPlayer, World world, int x, int y, int z)
	{
		super(new ContainerRuneInscription(inventoryPlayer, world, x, y, z));

		this.xSize = 176;
		this.ySize = 206;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j)
	{
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, (this.ySize - 96) + 2, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inscription"), 5, (this.ySize - 203) + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f1, int i1, int i2)
	{
		GL11.glColor4f(1F, 1F, 1F, 1F);

		Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);

		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		this.renderCarvingGrid();
	}
	
	public void renderCarvingGrid()
	{
		GL11.glColor4f(1F, 1F, 1F, 1F);

		this.mc.getTextureManager().bindTexture(this.texture);

		for(int x = 0; x < 5; ++x)
		{
			for(int y = 0; y < 7; ++y)
			{
				if (this.containerInscription.grid[x][y] == false)
				{
					this.drawTexturedModalRect(this.guiLeft + 43 + (x * 16), this.guiTop + 6 + (y * 16), 0, 206, 16, 16);
				}
				else if (this.containerInscription.grid[x][y] == true)
				{
					this.drawTexturedModalRect(this.guiLeft + 43 + (x * 16), this.guiTop + 6 + (y * 16), 16, 206, 16, 16);
				}
			}
		}
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton)
	{
		super.mouseClicked(mouseX, mouseY, mouseButton);

		if (mouseButton == 0)
		{
			for (int x = 0; x < 5; ++x)
			{
				for (int y = 0; y < 7; ++y)
				{
					if ((((mouseX - this.guiLeft) >= (43 + (x * 16))) && ((mouseX - this.guiLeft) < (43 + ((x + 1) * 16)))) && (((mouseY - this.guiTop) >= (6 + (y * 16))) && ((mouseY - this.guiTop) < (6 + ((y + 1) * 16)))))
					{
						//this.containerInscription.grid[x][y] = true;
						RunicInscription.network.sendToServer(new MessageGrid(true, x, y));
					}
				}
			}
		}
	}
}
