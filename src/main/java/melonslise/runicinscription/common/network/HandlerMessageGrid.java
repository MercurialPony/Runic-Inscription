package melonslise.runicinscription.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import melonslise.runicinscription.common.container.ContainerRuneInscription;
import net.minecraft.inventory.Container;

public class HandlerMessageGrid implements IMessageHandler<MessageGrid, IMessage>
{
	public boolean grid;
	public int posX;
	public int posY;

	@Override
	public IMessage onMessage(MessageGrid message, MessageContext ctx)
	{
		this.grid = message.getGrid();
		this.posX = message.getPosX();
		this.posY = message.getPosY();

		Container container = ctx.getServerHandler().playerEntity.openContainer;
		if((container != null) && (container instanceof ContainerRuneInscription))
		{
			((ContainerRuneInscription)container).grid[this.posX][this.posY] = this.grid;
			((ContainerRuneInscription)container).onMatrixChanged();
		}

		return null;
	}
}