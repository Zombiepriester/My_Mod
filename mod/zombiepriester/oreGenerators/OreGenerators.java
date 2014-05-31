package mod.zombiepriester.oreGenerators;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import mod.zombiepriester.oreGenerators.proxy.ServerProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="oregenerators", name="Ore Generators Mod", version="1.0.0")
public class OreGenerators{
	
	@Instance(value="GenericModID")
	public static OreGenerators instance;
	
	@SidedProxy(clientSide="mod.zombiepriester.oreGenerators.proxy.ClientProxy", serverSide="mod.zombiepriester.oreGenerators.proxy.ServerProxy")

	
	public static ServerProxy proxy;

	private GeneratorBlock ironGenerator;

	private Item generatorCircute;

	private CreativeTabs OG_Tab;

	private GeneratorBlock goldGenerator;
	
	private GeneratorBlock kupferGenerator;
	
	private GeneratorBlock zinnGenerator;

	private int circuiteID;

	private int ironGenID;

	private int goldGenID;

	private int copperGenID;

	private int tinGenID;

	private GeneratorBlock uranGenerator;

	private int uranGenID;

	private OreDisgenerator disgenerator;

	private int disgeneratorID;

	private GeneratorBlock apatGenerator;

	private int apatGenID;

	private int rubyGenID;

	private GeneratorBlock rubyGenerator;

	private int NikoGenID;

	private GeneratorBlock NikoGenerator;

	private GeneratorBlock silverGenerator;

	private int silverGenID;

	private GeneratorBlock smaragdGenerator;

	private int smaragdGenID;

	private GeneratorBlock saphGenerator;

	private int saphGenID;
	
	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event) {
		
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		instance = this;

		
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		circuiteID = config.get(Configuration.CATEGORY_ITEM, "Generator Circute ID", 300).getInt();
		disgeneratorID = config.get(Configuration.CATEGORY_BLOCK, "Ore Disgenerator ID", 200).getInt();
		ironGenID = config.get(Configuration.CATEGORY_BLOCK, "Iron Generator ID", 201).getInt();
		goldGenID = config.get(Configuration.CATEGORY_BLOCK, "Gold Generator ID", 202).getInt();
		copperGenID = config.get(Configuration.CATEGORY_BLOCK, "Copper Generator ID", 203).getInt();
		tinGenID = config.get(Configuration.CATEGORY_BLOCK, "Tin Generator ID", 204).getInt();
		uranGenID = config.get(Configuration.CATEGORY_BLOCK, "Uranium Generator ID", 205).getInt();
		apatGenID = config.get(Configuration.CATEGORY_BLOCK, "Apatite Generator ID", 206).getInt();
		rubyGenID = config.get(Configuration.CATEGORY_BLOCK, "Rubin Generator ID", 207).getInt();
		saphGenID = config.get(Configuration.CATEGORY_BLOCK, "Saphier Generator ID", 208).getInt();
		smaragdGenID = config.get(Configuration.CATEGORY_BLOCK, "Smaragd Generator ID", 209).getInt();
		silverGenID = config.get(Configuration.CATEGORY_BLOCK, "Silber Generator ID", 210).getInt();
		NikoGenID = config.get(Configuration.CATEGORY_BLOCK, "Nikolit Generator ID", 211).getInt();
		config.save();
	}
	
	@Mod.Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		OG_Tab = new CreativeTabs("Ore Generators"){
			public ItemStack getIconItemStack()
		    {
		        return new ItemStack(generatorCircute,1,0);
		    }
		};
		loadBlocks();
		loadItems();
		loadRecipes();
	}
	
	private void loadRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(generatorCircute,1), "RRR","IDI","RRR",'R',Item.redstone,'I',Item.ingotIron,'D',Item.diamond);
		GameRegistry.addShapedRecipe(new ItemStack(ironGenerator, 1), "IHI","HCH","IHI",'I',Block.stone,'H',Block.planks,'C',generatorCircute);
		GameRegistry.addShapedRecipe(new ItemStack(goldGenerator, 1), "IHI","HCH","IHI",'I',Item.ingotIron,'H',Block.planks,'C',generatorCircute);
	}

	private void loadItems() {
		generatorCircute = new GeneratorCircute(circuiteID);
		generatorCircute.setCreativeTab(OG_Tab).setItemName("Generator Schaltkreis");
		GameRegistry.registerItem(generatorCircute, "Generator Schaltkreis");
		LanguageRegistry.addName(generatorCircute, "Generator Schaltkreis");
	}

	private void loadBlocks() {
		disgenerator = new OreDisgenerator(disgeneratorID, null);
		disgenerator.setCreativeTab(OG_Tab).setBlockName("DisGenerator");
		GameRegistry.registerBlock(disgenerator, "DisGenerator");
		LanguageRegistry.addName(disgenerator, "Erz Disgenerator");
		GameRegistry.registerTileEntity(TileDisgenerator.class, "Disgenerator");
		
		ironGenerator = new GeneratorBlock(ironGenID, 0, "Eisen");
		ironGenerator.setCreativeTab(OG_Tab).setBlockName("Eisen Generator");
		ironGenerator.setErz(Block.oreIron.blockID);
		GameRegistry.registerBlock(ironGenerator, "Eisen Generator");
		LanguageRegistry.addName(ironGenerator, "Eisen Generator");
		OreDictionary.registerOre("oreNikolite", ironGenerator);
		
		goldGenerator = new GeneratorBlock(goldGenID, 1, "Gold");
		goldGenerator.setCreativeTab(OG_Tab).setBlockName("Gold Generator");
		goldGenerator.setErz(Block.oreGold.blockID);
		GameRegistry.registerBlock(goldGenerator, "Gold Generator");
		LanguageRegistry.addName(goldGenerator, "Gold Generator");
	}

	@Mod.PostInit
	public void postInit(FMLPostInitializationEvent event) {
		//IC2
		if(OreDictionary.getOres("oreCopper").size() > 0){
			kupferGenerator = new GeneratorBlock(copperGenID, 2, "Kupfer");
			kupferGenerator.setCreativeTab(OG_Tab).setBlockName("Kupfer Generator");
			kupferGenerator.setErz(OreDictionary.getOreID("oreCopper"));
			GameRegistry.registerBlock(kupferGenerator, "Kupfer Generator");
			LanguageRegistry.addName(kupferGenerator, "Kupfer Generator");
		}
		if(OreDictionary.getOres("oreTin").size() > 0){
			zinnGenerator = new GeneratorBlock(tinGenID, 3, "Zinn");
			zinnGenerator.setCreativeTab(OG_Tab).setBlockName("Zinn Generator");
			zinnGenerator.setErz(OreDictionary.getOreID("oreTin"));
			GameRegistry.registerBlock(zinnGenerator, "Zinn Generator");
			LanguageRegistry.addName(zinnGenerator, "Zinn Generator");
		}
		if(OreDictionary.getOres("oreUranium").size() > 0){
			uranGenerator = new GeneratorBlock(uranGenID, 4, "Uran");
			uranGenerator.setCreativeTab(OG_Tab).setBlockName("Uran Generator");
			uranGenerator.setErz(OreDictionary.getOreID("oreUranium"));
			GameRegistry.registerBlock(uranGenerator, "Uran Generator");
			LanguageRegistry.addName(uranGenerator, "Uran Generator");
		}
		//Forestry
		if(OreDictionary.getOres("oreApatite").size() > 0){
			apatGenerator = new GeneratorBlock(apatGenID, 5, "Apatit");
			apatGenerator.setCreativeTab(OG_Tab).setBlockName("Apatit Generator");
			apatGenerator.setErz(OreDictionary.getOreID("oreApatite"));
			GameRegistry.registerBlock(apatGenerator, "Apatite Generator");
			LanguageRegistry.addName(apatGenerator, "Apatit Generator");
		}
		//RP2
		if(OreDictionary.getOres("oreRuby").size() > 0){
			rubyGenerator = new GeneratorBlock(rubyGenID, 6, "Ruby");
			rubyGenerator.setCreativeTab(OG_Tab).setBlockName("Ruby Generator");
			rubyGenerator.setErz(OreDictionary.getOreID("oreRuby"));
			GameRegistry.registerBlock(rubyGenerator, "Ruby Generator");
			LanguageRegistry.addName(rubyGenerator, "Rubin Generator");
		}
		if(OreDictionary.getOres("oreSapphire").size() > 0){
			saphGenerator = new GeneratorBlock(saphGenID, 8, "Sapphire");
			saphGenerator.setCreativeTab(OG_Tab).setBlockName("Sapphire Generator");
			saphGenerator.setErz(OreDictionary.getOreID("oreSapphire"));
			GameRegistry.registerBlock(saphGenerator, "Sapphire Generator");
			LanguageRegistry.addName(saphGenerator, "Saphir Generator");
		}
		if(OreDictionary.getOres("oreGreenSapphire").size() > 0){
			smaragdGenerator = new GeneratorBlock(smaragdGenID, 7, "GreenSapphire");
			smaragdGenerator.setCreativeTab(OG_Tab).setBlockName("GreenSapphire Generator");
			smaragdGenerator.setErz(OreDictionary.getOreID("oreGreenSapphire"));
			GameRegistry.registerBlock(smaragdGenerator, "GreenSapphire Generator");
			LanguageRegistry.addName(smaragdGenerator, "Smaragd Generator");
		}
		if(OreDictionary.getOres("oreSilver").size() > 0){
			silverGenerator = new GeneratorBlock(silverGenID, 9, "Tungsten");
			silverGenerator.setCreativeTab(OG_Tab).setBlockName("Tungsten Generator");
			silverGenerator.setErz(OreDictionary.getOreID("oreSilver"));
			GameRegistry.registerBlock(silverGenerator, "Tungsten Generator");
			LanguageRegistry.addName(silverGenerator, "Silber Generator");
		}
		if(OreDictionary.getOres("oreNikolite").size() > 0){
			NikoGenerator = new GeneratorBlock(NikoGenID, 10, "Nikolite");
			NikoGenerator.setCreativeTab(OG_Tab).setBlockName("Nikolite Generator");
			NikoGenerator.setErz(OreDictionary.getOreID("oreNikolite"));
			GameRegistry.registerBlock(NikoGenerator, "Nikolite Generator");
			LanguageRegistry.addName(NikoGenerator, "Nikolite Generator");
		}
	}

}
