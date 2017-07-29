package melonslise.runicinscription;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import melonslise.runicinscription.common.block.BlockMonolith;
import melonslise.runicinscription.common.block.BlockMonolithChainlightning;
import melonslise.runicinscription.common.block.BlockMonolithDispel;
import melonslise.runicinscription.common.block.BlockMonolithFirebolt;
import melonslise.runicinscription.common.block.BlockMonolithFirerain;
import melonslise.runicinscription.common.block.BlockMonolithHeal;
import melonslise.runicinscription.common.block.BlockMonolithIcebolt;
import melonslise.runicinscription.common.block.BlockMonolithLightningbolt;
import melonslise.runicinscription.common.block.BlockWritingTable;
import melonslise.runicinscription.common.block.CropRoyalLily;
import melonslise.runicinscription.common.entity.EntityFirebolt;
import melonslise.runicinscription.common.entity.EntityFirerain;
import melonslise.runicinscription.common.entity.EntityIcebolt;
import melonslise.runicinscription.common.item.ItemPaperTraced;
import melonslise.runicinscription.common.item.ItemPointChisel;
import melonslise.runicinscription.common.item.ItemRoyalLilyBud;
import melonslise.runicinscription.common.item.ItemRoyalLilyRoot;
import melonslise.runicinscription.common.item.SimpleItem;
import melonslise.runicinscription.common.network.HandlerGUI;
import melonslise.runicinscription.common.network.HandlerMessageCurrentMana;
import melonslise.runicinscription.common.network.HandlerMessageGrid;
import melonslise.runicinscription.common.network.HandlerMessageMaxMana;
import melonslise.runicinscription.common.network.HandlerMessageParticle;
import melonslise.runicinscription.common.network.MessageCurrentMana;
import melonslise.runicinscription.common.network.MessageGrid;
import melonslise.runicinscription.common.network.MessageMaxMana;
import melonslise.runicinscription.common.network.MessageParticle;
import melonslise.runicinscription.common.potion.PotionEssenceMana;
import melonslise.runicinscription.common.proxy.CommonProxy;
import melonslise.runicinscription.common.spell.SpellChainlightning;
import melonslise.runicinscription.common.spell.SpellDispel;
import melonslise.runicinscription.common.spell.SpellFirebolt;
import melonslise.runicinscription.common.spell.SpellFirerain;
import melonslise.runicinscription.common.spell.SpellHeal;
import melonslise.runicinscription.common.spell.SpellIcebolt;
import melonslise.runicinscription.common.spell.SpellLightningbolt;
import melonslise.runicinscription.common.tileentity.TileEntityWritingTable;
import melonslise.runicinscription.common.worldgen.WorldGenRoyalLily;
import melonslise.runicinscription.common.worldgen.WorldGenRunestone;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

@Mod(modid = RunicInscription.ID, name = RunicInscription.NAME, version = RunicInscription.VERSION)
public class RunicInscription
{
	public static final String ID = "runicinscription";
	public static final String NAME = "Runic Inscription";
	public static final String VERSION = "1.0.0";

	@Instance(RunicInscription.ID)
	public static RunicInscription instance;

	@SidedProxy(clientSide="melonslise.runicinscription.client.proxy.ClientProxy", serverSide="melonslise.runicinscription.server.proxy.ServerProxy")
	public static CommonProxy proxy;

	public static SimpleNetworkWrapper network;

	public static CreativeTabs tabRunicInscription = new CreativeTabs("tabRunicInscription")
	{
		@Override
		public Item getTabIconItem()
		{
			return new ItemStack(itemScrollEmpty).getItem();
		}
	};
	
	public static WorldGenRoyalLily worldGenRoyalLily = new WorldGenRoyalLily();
	public static WorldGenRunestone worldGenRunestone = new WorldGenRunestone();

	public static Item itemPointChisel;
	public static Item itemRoyalLilyBud;
	public static Item itemRoyalLilyRoot;

	public static Item itemScrollEmpty;
	public static Item itemScrollFirebolt;
	public static Item itemScrollIcebolt;
	public static Item itemScrollLightningbolt;
	public static Item itemScrollHeal;
	public static Item itemScrollDispel;
	public static Item itemScrollFirerain;
	public static Item itemScrollChainlightning;
	
	public static Item itemScrollTracing;
	public static Item itemPaperTracing;
	public static Item itemPaperTracedFirebolt;
	public static Item itemPaperTracedIcebolt;
	public static Item itemPaperTracedLightningbolt;
	public static Item itemPaperTracedHeal;
	public static Item itemPaperTracedDispel;
	public static Item itemPaperTracedFirerain;
	public static Item itemPaperTracedChainlightning;

	public static Item itemRuneBlank;
	public static Item itemRuneFirebolt;
	public static Item itemRuneIcebolt;
	public static Item itemRuneLightningbolt;
	public static Item itemRuneHeal;
	public static Item itemRuneDispel;
	public static Item itemRuneFirerain;
	public static Item itemRuneChainlightning;

	public static Item iconSpellFirebolt;
	public static Item iconSpellIcebolt;
	public static Item iconEmpty;

	public static Item itemPotionEssenceMana;
	public static Item itemPotionElixirMana;

	public static final Potion potionEssenceMana;
	
	public static Block blockWritingTable;
	public static Block blockMonolith;
	public static Block blockMonolithFirebolt;
	public static Block blockMonolithIcebolt;
	public static Block blockMonolithLightningbolt;
	public static Block blockMonolithHeal;
	public static Block blockMonolithDispel;
	public static Block blockMonolithFirerain;
	public static Block blockMonolithChainlightning;
	
	public static Block cropRoyalLily;

	public static final int guiIDRuneInscription = 0;
	public static final int guiIDScrollWriting = 1;
	public static final int guiIDTracingPaper = 2;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		blockWritingTable = new BlockWritingTable(Material.wood).setBlockName("BlockWritingTable").setBlockTextureName(RunicInscription.ID + ":WritingTable").setCreativeTab(tabRunicInscription);
		GameRegistry.registerBlock(blockWritingTable, blockWritingTable.getUnlocalizedName().substring(5));
		
		blockMonolith = new BlockMonolith(Material.rock).setBlockName("BlockMonolith").setBlockTextureName(RunicInscription.ID + ":MonolithSide");
		GameRegistry.registerBlock(blockMonolith, blockMonolith.getUnlocalizedName().substring(5));
		blockMonolithFirebolt = new BlockMonolithFirebolt(Material.rock).setBlockName("BlockMonolithFirebolt");
		GameRegistry.registerBlock(blockMonolithFirebolt, blockMonolithFirebolt.getUnlocalizedName().substring(5));
		blockMonolithIcebolt = new BlockMonolithIcebolt(Material.rock).setBlockName("BlockMonolithIcebolt");
		GameRegistry.registerBlock(blockMonolithIcebolt, blockMonolithIcebolt.getUnlocalizedName().substring(5));
		blockMonolithLightningbolt = new BlockMonolithLightningbolt(Material.rock).setBlockName("BlockMonolithLightningbolt");
		GameRegistry.registerBlock(blockMonolithLightningbolt, blockMonolithLightningbolt.getUnlocalizedName().substring(5));
		blockMonolithHeal = new BlockMonolithHeal(Material.rock).setBlockName("BlockMonolithHeal");
		GameRegistry.registerBlock(blockMonolithHeal, blockMonolithHeal.getUnlocalizedName().substring(5));
		blockMonolithDispel = new BlockMonolithDispel(Material.rock).setBlockName("BlockMonolithDispel");
		GameRegistry.registerBlock(blockMonolithDispel, blockMonolithDispel.getUnlocalizedName().substring(5));
		blockMonolithFirerain = new BlockMonolithFirerain(Material.rock).setBlockName("BlockMonolithFirerain");
		GameRegistry.registerBlock(blockMonolithFirerain, blockMonolithFirerain.getUnlocalizedName().substring(5));
		blockMonolithChainlightning = new BlockMonolithChainlightning(Material.rock).setBlockName("BlockMonolithChainlightning");
		GameRegistry.registerBlock(blockMonolithChainlightning, blockMonolithChainlightning.getUnlocalizedName().substring(5));
		
		cropRoyalLily = new CropRoyalLily().setBlockName("CropRoyalLily");
		GameRegistry.registerBlock(cropRoyalLily, cropRoyalLily.getUnlocalizedName().substring(5));
		
		itemPointChisel = new ItemPointChisel().setUnlocalizedName("ItemPointChisel").setTextureName(RunicInscription.ID + ":PointChisel2").setCreativeTab(tabRunicInscription);
		GameRegistry.registerItem(itemPointChisel, itemPointChisel.getUnlocalizedName().substring(5));
		
		itemRoyalLilyBud = new ItemRoyalLilyBud(0, 0.0F, cropRoyalLily, Blocks.grass).setAlwaysEdible().setUnlocalizedName("ItemRoyalLilyBud").setTextureName(RunicInscription.ID + ":RoyalLilyBud1").setCreativeTab(tabRunicInscription);
		GameRegistry.registerItem(itemRoyalLilyBud, itemRoyalLilyBud.getUnlocalizedName().substring(5));
		//itemRoyalLilyRoot = new ItemRoyalLilyRoot(0, 0.0F, false).setAlwaysEdible().setUnlocalizedName("ItemRoyalLilyRoot").setTextureName(RunicInscription.ID + ":RoyalLilyRoot").setCreativeTab(tabRunicInscription);
		//GameRegistry.registerItem(itemRoyalLilyRoot, itemRoyalLilyRoot.getUnlocalizedName().substring(5));

		itemScrollEmpty = new SimpleItem().setUnlocalizedName("ItemScrollEmpty").setTextureName(RunicInscription.ID + ":ScrollEmpty").setCreativeTab(tabRunicInscription);
		GameRegistry.registerItem(itemScrollEmpty, itemScrollEmpty.getUnlocalizedName().substring(5));
		
		itemScrollFirebolt = new SpellFirebolt().setIsRune(false).setUnlocalizedName("ItemScrollFirebolt").setTextureName(RunicInscription.ID + ":ScrollFirebolt").setCreativeTab(tabRunicInscription);
		GameRegistry.registerItem(itemScrollFirebolt, itemScrollFirebolt.getUnlocalizedName().substring(5));
		itemScrollIcebolt = new SpellIcebolt().setIsRune(false).setUnlocalizedName("ItemScrollIcebolt").setTextureName(RunicInscription.ID + ":ScrollIcebolt").setCreativeTab(tabRunicInscription);
		GameRegistry.registerItem(itemScrollIcebolt, itemScrollIcebolt.getUnlocalizedName().substring(5));
		itemScrollLightningbolt = new SpellLightningbolt().setIsRune(false).setUnlocalizedName("ItemScrollLightningbolt").setTextureName(RunicInscription.ID + ":ScrollLightningbolt").setCreativeTab(tabRunicInscription);
		GameRegistry.registerItem(itemScrollLightningbolt, itemScrollLightningbolt.getUnlocalizedName().substring(5));
		itemScrollHeal = new SpellHeal().setIsRune(false).setUnlocalizedName("ItemScrollHeal").setTextureName(RunicInscription.ID + ":ScrollHeal").setCreativeTab(tabRunicInscription);
		GameRegistry.registerItem(itemScrollHeal, itemScrollHeal.getUnlocalizedName().substring(5));
		itemScrollDispel = new SpellDispel().setIsRune(false).setUnlocalizedName("ItemScrollDispel").setTextureName(RunicInscription.ID + ":ScrollDispel").setCreativeTab(tabRunicInscription);
		GameRegistry.registerItem(itemScrollDispel, itemScrollDispel.getUnlocalizedName().substring(5));
		itemScrollFirerain = new SpellFirerain().setIsRune(false).setUnlocalizedName("ItemScrollFirerain").setTextureName(RunicInscription.ID + ":ScrollFirerain").setCreativeTab(tabRunicInscription);
		GameRegistry.registerItem(itemScrollFirerain, itemScrollFirerain.getUnlocalizedName().substring(5));
		itemScrollChainlightning = new SpellChainlightning().setIsRune(false).setUnlocalizedName("ItemScrollChainlightning").setTextureName(RunicInscription.ID + ":ScrollChainlightning").setCreativeTab(tabRunicInscription);
		GameRegistry.registerItem(itemScrollChainlightning, itemScrollChainlightning.getUnlocalizedName().substring(5));
		
		itemScrollTracing = new SimpleItem().setUnlocalizedName("ItemScrollTracing").setTextureName(RunicInscription.ID + ":ScrollTracing").setCreativeTab(tabRunicInscription);
		GameRegistry.registerItem(itemScrollTracing, itemScrollTracing.getUnlocalizedName().substring(5));
		itemPaperTracing = new SimpleItem().setUnlocalizedName("ItemPaperTracing").setTextureName(RunicInscription.ID + ":PaperTracing").setCreativeTab(tabRunicInscription);
		GameRegistry.registerItem(itemPaperTracing, itemPaperTracing.getUnlocalizedName().substring(5));
		itemPaperTracedFirebolt = new ItemPaperTraced().setSpellType("Firebolt").setUnlocalizedName("ItemPaperTracedFirebolt").setTextureName(RunicInscription.ID + ":PaperTraced");
		GameRegistry.registerItem(itemPaperTracedFirebolt, itemPaperTracedFirebolt.getUnlocalizedName().substring(5));
		itemPaperTracedIcebolt = new ItemPaperTraced().setSpellType("Icebolt").setUnlocalizedName("ItemPaperTracedIcebolt").setTextureName(RunicInscription.ID + ":PaperTraced");
		GameRegistry.registerItem(itemPaperTracedIcebolt, itemPaperTracedIcebolt.getUnlocalizedName().substring(5));
		itemPaperTracedLightningbolt = new ItemPaperTraced().setSpellType("Lightningbolt").setUnlocalizedName("ItemPaperTracedLightningbolt").setTextureName(RunicInscription.ID + ":PaperTraced");
		GameRegistry.registerItem(itemPaperTracedLightningbolt, itemPaperTracedLightningbolt.getUnlocalizedName().substring(5));
		itemPaperTracedHeal = new ItemPaperTraced().setSpellType("Heal").setUnlocalizedName("ItemPaperTracedHeal").setTextureName(RunicInscription.ID + ":PaperTraced");
		GameRegistry.registerItem(itemPaperTracedHeal, itemPaperTracedHeal.getUnlocalizedName().substring(5));
		itemPaperTracedDispel = new ItemPaperTraced().setSpellType("Dispel").setUnlocalizedName("ItemPaperTracedDispel").setTextureName(RunicInscription.ID + ":PaperTraced");
		GameRegistry.registerItem(itemPaperTracedDispel, itemPaperTracedDispel.getUnlocalizedName().substring(5));
		itemPaperTracedFirerain = new ItemPaperTraced().setSpellType("Firerain").setUnlocalizedName("ItemPaperTracedFirerain").setTextureName(RunicInscription.ID + ":PaperTraced");
		GameRegistry.registerItem(itemPaperTracedFirerain, itemPaperTracedFirerain.getUnlocalizedName().substring(5));
		itemPaperTracedChainlightning = new ItemPaperTraced().setSpellType("Chainlightning").setUnlocalizedName("ItemPaperTracedChainlightning").setTextureName(RunicInscription.ID + ":PaperTraced");
		GameRegistry.registerItem(itemPaperTracedChainlightning, itemPaperTracedChainlightning.getUnlocalizedName().substring(5));

		itemRuneBlank = new SimpleItem().setUnlocalizedName("ItemRuneBlank").setTextureName(RunicInscription.ID + ":RuneBlank").setCreativeTab(tabRunicInscription);
		GameRegistry.registerItem(itemRuneBlank, itemRuneBlank.getUnlocalizedName().substring(5));
		
		itemRuneFirebolt = new SpellFirebolt().setIsRune(true).setUnlocalizedName("ItemRuneFirebolt").setTextureName(RunicInscription.ID + ":RuneFirebolt").setCreativeTab(tabRunicInscription).setMaxStackSize(1);
		GameRegistry.registerItem(itemRuneFirebolt, itemRuneFirebolt.getUnlocalizedName().substring(5));
		itemRuneIcebolt = new SpellIcebolt().setIsRune(true).setUnlocalizedName("ItemRuneIcebolt").setTextureName(RunicInscription.ID + ":RuneIcebolt").setCreativeTab(tabRunicInscription).setMaxStackSize(1);
		GameRegistry.registerItem(itemRuneIcebolt, itemRuneIcebolt.getUnlocalizedName().substring(5));
		itemRuneLightningbolt = new SpellLightningbolt().setIsRune(true).setUnlocalizedName("ItemRuneLightningbolt").setTextureName(RunicInscription.ID + ":RuneLightningbolt").setCreativeTab(tabRunicInscription).setMaxStackSize(1);
		GameRegistry.registerItem(itemRuneLightningbolt, itemRuneLightningbolt.getUnlocalizedName().substring(5));
		itemRuneHeal = new SpellHeal().setIsRune(true).setUnlocalizedName("ItemRuneHeal").setTextureName(RunicInscription.ID + ":RuneHeal").setCreativeTab(tabRunicInscription).setMaxStackSize(1);
		GameRegistry.registerItem(itemRuneHeal, itemRuneHeal.getUnlocalizedName().substring(5));
		itemRuneDispel = new SpellDispel().setIsRune(true).setUnlocalizedName("ItemRuneDispel").setTextureName(RunicInscription.ID + ":RuneDispel").setCreativeTab(tabRunicInscription).setMaxStackSize(1);
		GameRegistry.registerItem(itemRuneDispel, itemRuneDispel.getUnlocalizedName().substring(5));
		itemRuneFirerain = new SpellFirerain().setIsRune(true).setUnlocalizedName("ItemRuneFirerain").setTextureName(RunicInscription.ID + ":RuneFirerain").setCreativeTab(tabRunicInscription).setMaxStackSize(1);
		GameRegistry.registerItem(itemRuneFirerain, itemRuneFirerain.getUnlocalizedName().substring(5));
		itemRuneChainlightning = new SpellChainlightning().setIsRune(true).setUnlocalizedName("ItemRuneChainlightning").setTextureName(RunicInscription.ID + ":RuneChainlightning").setCreativeTab(tabRunicInscription).setMaxStackSize(1);
		GameRegistry.registerItem(itemRuneChainlightning, itemRuneChainlightning.getUnlocalizedName().substring(5));

		iconSpellFirebolt = new SimpleItem().setUnlocalizedName("IconSpellFirebolt").setTextureName(RunicInscription.ID + ":Firebolt");
		GameRegistry.registerItem(iconSpellFirebolt, iconSpellFirebolt.getUnlocalizedName().substring(5));
		iconSpellIcebolt = new SimpleItem().setUnlocalizedName("IconSpellIcebolt").setTextureName(RunicInscription.ID + ":Icebolt");
		GameRegistry.registerItem(iconSpellIcebolt, iconSpellIcebolt.getUnlocalizedName().substring(5));
		iconEmpty = new SimpleItem().setUnlocalizedName("IconEmpty").setTextureName(RunicInscription.ID + ":IconEmpty");
		GameRegistry.registerItem(iconEmpty, iconEmpty.getUnlocalizedName().substring(5));

		EntityRegistry.registerModEntity(EntityFirebolt.class, "Firebolt", 1, this, 64, 2, true);
		EntityRegistry.registerModEntity(EntityIcebolt.class, "Icebolt", 2, this, 64, 2, true);
		EntityRegistry.registerModEntity(EntityFirerain.class, "Firerain", 3, this, 64, 2, true);
		
		GameRegistry.registerWorldGenerator(worldGenRoyalLily, 0);
		GameRegistry.registerWorldGenerator(worldGenRunestone, 0);

		proxy.registerRenderers();

		network = NetworkRegistry.INSTANCE.newSimpleChannel("RunicInscription");

		network.registerMessage(HandlerMessageCurrentMana.class, MessageCurrentMana.class, 1, Side.CLIENT);
		network.registerMessage(HandlerMessageMaxMana.class, MessageMaxMana.class, 2, Side.CLIENT);
		network.registerMessage(HandlerMessageParticle.class, MessageParticle.class, 3, Side.CLIENT);
		network.registerMessage(HandlerMessageGrid.class, MessageGrid.class, 4, Side.SERVER);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new HandlerGUI());
		
		GameRegistry.registerTileEntity(TileEntityWritingTable.class, "TileEntityWritingTable");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		GameRegistry.addRecipe(new ItemStack(itemScrollEmpty), new Object[] {"S", "P", "S", 'S', Items.stick, 'P', Items.paper});
		GameRegistry.addRecipe(new ItemStack(itemPointChisel), new Object[] {"I", "I", 'I', Items.iron_ingot});
		GameRegistry.addShapelessRecipe(new ItemStack(itemRuneBlank), new Object[] {Blocks.stone_slab});
		GameRegistry.addRecipe(new ItemStack(blockWritingTable), new Object[] {"TIF", "WW ", "PP ", 'T', Blocks.torch, 'I', new ItemStack(Items.dye, 1, 0), 'F', Items.feather, 'W', Blocks.log, 'P', Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(blockWritingTable), new Object[] {"TIF", " WW", " PP", 'T', Blocks.torch, 'I', new ItemStack(Items.dye, 1, 0), 'F', Items.feather, 'W', Blocks.log, 'P', Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(blockWritingTable), new Object[] {"TFI", "WW ", "PP ", 'T', Blocks.torch, 'I', new ItemStack(Items.dye, 1, 0), 'F', Items.feather, 'W', Blocks.log, 'P', Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(blockWritingTable), new Object[] {"TFI", " WW", " PP", 'T', Blocks.torch, 'I', new ItemStack(Items.dye, 1, 0), 'F', Items.feather, 'W', Blocks.log, 'P', Blocks.planks});
		GameRegistry.addShapelessRecipe(new ItemStack(itemScrollTracing), new Object[] {Items.feather, itemScrollEmpty, new ItemStack(Items.dye, 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(itemPaperTracing), new Object[] {Items.feather, Items.paper, new ItemStack(Items.dye, 1, 0)});
		
		proxy.init();
	}
	
	static
	{
		potionTypes();

		potionEssenceMana = (new PotionEssenceMana(32, false, 0)).setIconIndex(0, 0).setPotionName("PotionEssenceMana");
	}

	private static void potionTypes()
	{
		Potion[] potionTypes = null;

		for (Field f : Potion.class.getDeclaredFields())
		{
			f.setAccessible(true);
			try
			{
				if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a"))
				{
					Field modfield = Field.class.getDeclaredField("modifiers");
					modfield.setAccessible(true);
					modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

					potionTypes = (Potion[])f.get(null);
					final Potion[] newPotionTypes = new Potion[256];
					System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
					f.set(null, newPotionTypes);
				}
			}

			catch (Exception e)
			{
				System.err.println("Severe error, please report this to the mod author:");
				System.err.println(e);
			}
		}
	}
}
