package melonslise.runicinscription.common.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class MessageParticle implements IMessage
{
	private String particleType;
	private double posX;
	private double posY;
	private double posZ;
	private double velX;
	private double velY;
	private double velZ;

	public MessageParticle()
	{

	}

	public MessageParticle(String particleType, double posX, double posY, double posZ, double velX, double velY, double velZ)
	{
		this.particleType = particleType;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.velX = velX;
		this.velY = velY;
		this.velZ = velZ;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, this.particleType);
		buf.writeDouble(this.posX);
		buf.writeDouble(this.posY);
		buf.writeDouble(this.posZ);
		buf.writeDouble(this.velX);
		buf.writeDouble(this.velY);
		buf.writeDouble(this.velZ);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.particleType = ByteBufUtils.readUTF8String(buf);
		this.posX = buf.readDouble();
		this.posY = buf.readDouble();
		this.posZ = buf.readDouble();
		this.velX = buf.readDouble();
		this.velY = buf.readDouble();
		this.velZ = buf.readDouble();
	}

	public String getParticleType()
	{
		return this.particleType;
	}

	public double getPosX()
	{
		return this.posX;
	}

	public double getPosY()
	{
		return this.posY;
	}

	public double getPosZ()
	{
		return this.posZ;
	}

	public double getVelX()
	{
		return this.velX;
	}

	public double getVelY()
	{
		return this.velY;
	}

	public double getVelZ()
	{
		return this.velZ;
	}
}
