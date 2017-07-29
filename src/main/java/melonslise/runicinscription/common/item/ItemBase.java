package melonslise.runicinscription.common.item;

import melonslise.runicinscription.RunicInscription;
import melonslise.runicinscription.common.entity.EntityFirebolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemBase extends Item
{
	public ItemBase(String name)
	{
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(RunicInscription.creativeTab);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand) // TODO Remove
	{
		if(!world.isRemote)
		{
			Vec3d vector = player.getLookVec();
			EntityFirebolt projectile = new EntityFirebolt(world, player, 1);
			projectile.setLocation(player.posX, player.posY + player.getEyeHeight(), player.posZ);
			world.spawnEntityInWorld(projectile);
		}
		return new ActionResult(EnumActionResult.SUCCESS, itemStack);
	}
}