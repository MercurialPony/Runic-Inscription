package melonslise.runicinscription.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class MessageGrid implements IMessage
{
	private boolean grid;
	private int posX;
	private int posY;

	public MessageGrid()
	{

	}

	public MessageGrid(boolean grid, int posX, int posY)
	{
		this.grid = grid;
		this.posX = posX;
		this.posY = posY;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(this.grid);
		buf.writeInt(this.posX);
		buf.writeInt(this.posY);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.grid = buf.readBoolean();
		this.posX = buf.readInt();
		this.posY = buf.readInt();
	}

	public boolean getGrid()
	{
		return this.grid;
	}

	public int getPosX()
	{
		return this.posX;
	}

	public int getPosY()
	{
		return this.posY;
	}
}