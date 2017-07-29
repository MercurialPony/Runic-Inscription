package melonslise.runicinscription.client.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import melonslise.runicinscription.common.entity.player.PropertiesMana;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class EventRenderGUIMana
{
	@SubscribeEvent
	public void onRender(RenderGameOverlayEvent.Post event)
	{
		if (event.isCancelable() || (event.type != ElementType.EXPERIENCE))
		{
			return;
		}

		Minecraft mc = Minecraft.getMinecraft();
		PropertiesMana properties = PropertiesMana.get(mc.thePlayer);

		if (properties == null)
		{
			return;
		}
		
        ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();
        
		mc.fontRenderer.drawStringWithShadow(EnumChatFormatting.DARK_BLUE + "" + properties.getCurrentMana() + "/" + properties.getMaxMana(), width/5, height - 10, 1);
	}
}
