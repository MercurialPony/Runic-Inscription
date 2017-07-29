package melonslise.runicinscription.client.gui;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class GUIManaOverlay // TODO Rename
{
	public static int manaCurrent; // Getters/Setters (?)
	public static int manaMaximum;
	
	public static void render()
	{
		Minecraft minecraft = Minecraft.getMinecraft();
		ScaledResolution scaled = new ScaledResolution(minecraft);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();
		minecraft.fontRendererObj.drawString(manaCurrent + "/" + manaMaximum, width/5, height - 10, Color.BLUE.hashCode()); // TODO Remake ALLL
	}
}