package melonslise.runicinscription.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.common.entity.player.PropertiesMana;
import net.minecraft.entity.player.EntityPlayer;

public class HandlerMessageMaxMana implements IMessageHandler<MessageMaxMana, IMessage>
{
	@Override
	public IMessage onMessage(MessageMaxMana message, MessageContext ctx)
	{
		EntityPlayer player = RunicInscription.proxy.getClientPlayer();
		PropertiesMana properties = PropertiesMana.get(player);
		properties.setCurrentMana(message.getMaxMana());

		return null;
	}
}
