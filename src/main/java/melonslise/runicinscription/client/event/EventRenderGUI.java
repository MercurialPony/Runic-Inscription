package melonslise.runicinscription.client.event;

import melonslise.runicinscription.client.gui.GUIManaOverlay;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventRenderGUI // TODO Rename
{
	@SubscribeEvent
	public static void onRender(RenderGameOverlayEvent.Post event)
	{
		GUIManaOverlay.render();
	}
}