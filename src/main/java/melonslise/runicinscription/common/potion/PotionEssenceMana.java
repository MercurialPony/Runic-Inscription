package melonslise.runicinscription.common.potion;

import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.common.entity.player.PropertiesMana;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;

public class PotionEssenceMana extends Potion
{
	public PotionEssenceMana(int x, boolean y, int z)
	{
		super(x, y, z);
	}

	@Override
	public void affectEntity(EntityLivingBase thrower, EntityLivingBase victim, int amplifier, double distanceModifier)
	{
		if (victim instanceof EntityPlayer)
		{
			if (this.id == RunicInscription.potionEssenceMana.id)
			{
				PropertiesMana properties = PropertiesMana.get((EntityPlayer) victim);

				if (properties != null)
				{
					properties.setCurrentMana(properties.getCurrentMana() + ((int)((distanceModifier * (25 << amplifier)) + 0.5D)));
				}
			}
		}
		else
		{
			super.affectEntity(thrower, victim, amplifier, distanceModifier);
		}
	}

	@Override
	public boolean isInstant()
	{
		return this.id == RunicInscription.potionEssenceMana.id;
	}

	@Override
	public boolean isReady(int duration, int amplifier)
	{
		if (this.id == RunicInscription.potionEssenceMana.id)
		{
			return duration >= 1;
		}

		return super.isReady(duration, amplifier);
	}

	@Override
	public void performEffect(EntityLivingBase entity, int amplifier)
	{
		if (entity instanceof EntityPlayer)
		{
			if (this.id == RunicInscription.potionEssenceMana.id)
			{
				PropertiesMana properties = PropertiesMana.get((EntityPlayer) entity);

				if (properties != null)
				{
					properties.setCurrentMana(properties.getCurrentMana() + 30);
				}
			}
		}
	}

	@Override
	public Potion setIconIndex(int x, int y)
	{
		super.setIconIndex(x, y);
		return this;
	}
}
