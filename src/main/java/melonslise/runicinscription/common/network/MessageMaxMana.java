package melonslise.runicinscription.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class MessageMaxMana implements IMessage
{
	private int maxMana;

	public MessageMaxMana()
	{

	}

	public MessageMaxMana(int maxMana)
	{
		this.maxMana = maxMana;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.maxMana);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.maxMana = buf.readInt();
	}

	public int getMaxMana()
	{
		return this.maxMana;
	}
}
