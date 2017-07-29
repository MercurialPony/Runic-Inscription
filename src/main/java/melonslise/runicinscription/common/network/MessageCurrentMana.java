package melonslise.runicinscription.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class MessageCurrentMana implements IMessage
{
	private int currentMana;

	public MessageCurrentMana()
	{

	}

	public MessageCurrentMana(int currentMana)
	{
		this.currentMana = currentMana;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.currentMana);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.currentMana = buf.readInt();
	}

	public int getCurrentMana()
	{
		return this.currentMana;
	}
}
