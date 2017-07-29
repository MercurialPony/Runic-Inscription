package melonslise.runicinscription.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageMana implements IMessage
{
	private int manaCurrent;
	private int manaMaximum;

	public MessageMana() 
	{
		
	}

	public MessageMana(int current, int maximum) // TODO Separate (?)
	{
		this.manaCurrent = current;
		this.manaMaximum = maximum;
	}

	@Override
	public void fromBytes(ByteBuf buffer)
	{
		this.manaCurrent = buffer.readInt();
		this.manaMaximum = buffer.readInt();
	}

	@Override
	public void toBytes(ByteBuf buffer)
	{
		buffer.writeInt(this.manaCurrent);
		buffer.writeInt(this.manaMaximum);
	}

	public int getCurrent()
	{
		return this.manaCurrent;
	}

	public int getMaximum()
	{
		return this.manaMaximum;
	}
}