package melonslise.runicinscription;

import melonslise.runicinscription.common.capability.CapabilityMana;
import melonslise.runicinscription.common.capability.ICapabilityMana;
import melonslise.runicinscription.common.capability.api.CapabilityStorage;
import melonslise.runicinscription.common.crafting.RecipeList;
import melonslise.runicinscription.common.entity.EntityFireRain;
import melonslise.runicinscription.common.entity.EntityFirebolt;
import melonslise.runicinscription.common.entity.EntityIcebolt;
import melonslise.runicinscription.common.entity.EntityProjectile;
import melonslise.runicinscription.common.network.MessageMana;
import melonslise.runicinscription.common.network.MessageManaHandler;
import melonslise.runicinscription.common.proxy.CommonProxy;
import melonslise.runicinscription.common.tab.CreativeTabBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = RunicInscription.ID, name = RunicInscription.NAME, version = RunicInscription.VERSION, acceptedMinecraftVersions = RunicInscription.ACCEPTEDVERSIONS)
public class RunicInscription
{
	// Mod Info
	public static final String ID = "runicinscription";
	public static final String NAME = "Runic Inscription";
	public static final String VERSION = "2.0.0"; // TODO 
	public static final String ACCEPTEDVERSIONS = "[1.9.4, 1.10.2]";

	// Proxy
	@SidedProxy(serverSide = "melonslise.runicinscription.server.proxy.ServerProxy", clientSide = "melonslise.runicinscription.client.proxy.ClientProxy")
	public static CommonProxy proxy;

	// Packet Channel
	public static SimpleNetworkWrapper network;

	// Creative Tab
	public static final CreativeTabs creativeTab = new CreativeTabBase();

	@Mod.EventHandler
	public void preInitialization(FMLPreInitializationEvent event)
	{
		proxy.registerEntityRenderers(); // TODO Move

		CapabilityManager.INSTANCE.register(ICapabilityMana.class, new CapabilityStorage<ICapabilityMana>(), CapabilityMana.class); // TODO Move/Check for other way to register + Add generalized factory (?)

		network = NetworkRegistry.INSTANCE.newSimpleChannel(RunicInscription.ID);
		network.registerMessage(MessageManaHandler.class, MessageMana.class, 0, Side.CLIENT);
	}

	@Mod.EventHandler
	public void initialization(FMLInitializationEvent event)
	{
		RecipeList.register();
		// TODO inc variavle
		EntityRegistry.registerModEntity(EntityFirebolt.class, "Firebolt", 0, this, 64, 1, true); // TODO Move
		EntityRegistry.registerModEntity(EntityIcebolt.class, "Icebolt", 1, this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityFireRain.class, "FireRain", 2, this, 64, 1, true); // TODO Replace with tick handler
	}

	@Mod.EventHandler
	public void postInitialization(FMLPostInitializationEvent event)
	{
	}
}