package melonslise.runicinscription.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import melonslise.runicinscription.RunicInscription;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GUITracingPaper extends GuiScreen
{
	//private ResourceLocation texture = new ResourceLocation(RunicInscription.ID + ":" + "textures/gui/tracing/TracingPaper.png");
    private int ImageWidth = 132;
    private int ImageHeight = 167;
    private String type;
    private GuiButton buttonDone;
    
    public GUITracingPaper(String type)
    {
    	this.type = type;
    }
	
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
    	this.drawDefaultBackground();
    	super.drawScreen(mouseX, mouseY, partialTicks);

    	ResourceLocation texture = new ResourceLocation(RunicInscription.ID + ":" + "textures/gui/tracing/TracingPaper" + Character.toUpperCase(this.type.charAt(0)) + this.type.substring(1) + ".png");

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        byte b0 = 2;
        this.drawTexturedModalRect((this.width - this.ImageWidth) / 2, b0, 0, 0, this.ImageWidth, this.ImageHeight);

        this.fontRendererObj.drawString(I18n.format("Spell" + this.type + ".gui"), ((this.width - this.ImageWidth) / 2) + 45, ((this.height - this.ImageHeight) / 2) - 25, 0);
    }
    
    @Override
    public void initGui()
    {
    	this.buttonList.clear();
    	this.buttonList.add(this.buttonDone = new GuiButton(0, this.width / 2 - 50, 10 + this.ImageHeight, 100, 20, I18n.format("gui.done")));
    }
    
    @Override
    public void actionPerformed(GuiButton button)
    {
        if (button == buttonDone)
        {
        	this.mc.displayGuiScreen((GuiScreen)null);
        }
    }
	
	@Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
