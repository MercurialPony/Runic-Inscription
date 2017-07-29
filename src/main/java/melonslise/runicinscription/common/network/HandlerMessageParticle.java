package melonslise.runicinscription.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import melonslise.runicinscription.RunicInscription;
import net.minecraft.world.World;

public class HandlerMessageParticle implements IMessageHandler<MessageParticle, IMessage>
{
	@Override
	public IMessage onMessage(MessageParticle message, MessageContext ctx)
	{
		World world = RunicInscription.proxy.getClientWorld();
		world.spawnParticle(message.getParticleType(), message.getPosX(), message.getPosY(), message.getPosZ(), message.getVelX(), message.getVelY(), message.getVelZ());

		return null;
	}
}
