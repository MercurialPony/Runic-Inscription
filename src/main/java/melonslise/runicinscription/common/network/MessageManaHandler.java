package melonslise.runicinscription.common.network;

import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.client.gui.GUIManaOverlay;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageManaHandler implements IMessageHandler<MessageMana, IMessage>
{
	@Override
	public IMessage onMessage(final MessageMana message, MessageContext context)
	{
		Runnable action = new Runnable()
		{
			@Override
			public void run()
			{
				GUIManaOverlay.manaCurrent = message.getCurrent();
				GUIManaOverlay.manaMaximum = message.getMaximum();
			}
		};
		RunicInscription.proxy.getThreadListener(context).addScheduledTask(action);
		return null;
	}
}
