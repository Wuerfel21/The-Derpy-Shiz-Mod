package net.wuerfel21.derpyshiz;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class DerpyCrafting {
	
	public static boolean alwaysFalse = false;

	public static ItemStack getStack(String s, int m) {
		return new ItemStack(GameRegistry.findItem("derpyshiz", s), 1, m);
	}

	public static ItemStack getBlock(String s, int m) {
		return new ItemStack(GameRegistry.findBlock("derpyshiz", s), 1, m);
	}

	public static void registerPickaxe(String name, String material) {
		GameRegistry.addRecipe(new ShapedOreRecipe(getStack(name, 0), "MMM", " s ", " s ", 'M', material, 's', "stickWood"));
	}

	public static void registerSword(String name, String material) {
		GameRegistry.addRecipe(new ShapedOreRecipe(getStack(name, 0), "M", "M", "s", 'M', material, 's', "stickWood"));
	}

	public static void registerHoe(String name, String material) {
		GameRegistry.addRecipe(new ShapedOreRecipe(getStack(name, 0), "MM", " s", " s", 'M', material, 's', "stickWood"));
	}

	public static void registerShovel(String name, String material) {
		GameRegistry.addRecipe(new ShapedOreRecipe(getStack(name, 0), "M", "s", "s", 'M', material, 's', "stickWood"));
	}

	public static void registerAxe(String name, String material) {
		GameRegistry.addRecipe(new ShapedOreRecipe(getStack(name, 0), "MM", "Ms", " s", 'M', material, 's', "stickWood"));
	}

	public static void registerArmor(String name, String material) {
		GameRegistry.addRecipe(new ShapedOreRecipe(getStack("helmet_" + name, 0), "MMM", "M M", 'M', material));
		GameRegistry.addRecipe(new ShapedOreRecipe(getStack("chestplate_" + name, 0), "M M", "MMM", "MMM", 'M', material));
		GameRegistry.addRecipe(new ShapedOreRecipe(getStack("leggings_" + name, 0), "MMM", "M M", "M M", 'M', material));
		GameRegistry.addRecipe(new ShapedOreRecipe(getStack("boots_" + name, 0), "M M", "M M", 'M', material));
	}

	public static void registerSet(String name, String material) {
		registerPickaxe("pickaxe_" + name, material);
		registerSword("sword_" + name, material);
		registerHoe("hoe_" + name, material);
		registerShovel("shovel_" + name, material);
		registerAxe("axe_" + name, material);
		registerArmor(name, material);
	}

	public static void registerGear(int type, int amount, String material, String center) {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("derpyshiz", "gear"), amount, type), " M ", "MCM", " M ", 'M', material, 'C', center));
	}

	public static void registerGear(int type, int amount, Item material, String center) {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("derpyshiz", "gear"), amount, type), " M ", "MCM", " M ", 'M', material, 'C', center));
	}

	public static void registerDeco(int meta, String material, String block) {
		GameRegistry.addRecipe(new ShapedOreRecipe(getStack("block", meta), "MMM", "MMM", "MMM", 'M', material));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("derpyshiz", "ore_item"), 9, meta), "B", 'B', block));
	}

	public static void registerCirclet(String name, String material) {
		GameRegistry.addRecipe(new ShapedOreRecipe(getStack(name, 0), "ggg", "GMG", "ggg", 'M', material, 'g', "nuggetGold", 'G', "ingotGold"));
	}

	public static void registerNugget(String nugget, String material, int nuggetMeta, ItemStack materialOut) {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("derpyshiz", "nugget"), 9, nuggetMeta), "M", 'M', material));
		GameRegistry.addRecipe(new ShapedOreRecipe(materialOut, "nnn", "nnn", "nnn", 'n', nugget));
	}

	public static void registerCrafting() {

		WoolCrafting.register();

		registerSet("titanium", "ingotTitanium");
		registerSet("ruby", "gemRuby");
		registerSet("amethyst", "gemAmethyst");
		registerSet("copper", "ingotCopper");

		registerSword("sword_amber", "chunkAmber");
		registerSword("sword_lapis", "blockLapis");

		registerCirclet("circlet_diamond", "nuggetDiamond");
		registerCirclet("circlet_ruby", "nuggetRuby");
		registerCirclet("circlet_amethyst", "nuggetAmethyst");
		registerCirclet("circlet_turquoise", "gemTurquoise");
		registerCirclet("circlet_fakediamond", "gemFakediamond");

		GameRegistry.addRecipe(new ShapedOreRecipe(getStack("axe_turquoise", 0), "MMM", "MsM", " s ", 'M', "gemTurquoise", 's', "stickWood"));
		registerHoe("hoe_fakediamond", "gemFakediamond");

		GameRegistry.addRecipe(new ShapedOreRecipe(getStack("hammer", 0), "M", "s", 'M', "ingotIron", 's', "stickWood"));

		GameRegistry.addRecipe(new ShapedOreRecipe(getStack("sword_enderium", 0), " M ", "MMM", " s ", 'M', "ingotEnderium", 's', "stickWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(getStack("sword_darkness", 0), "MMM", "MMM", " h ", 'M', "gemDarkness", 'h', "materialHorn"));
		GameRegistry.addRecipe(new ShapedOreRecipe(getStack("dark_cloak", 0), "hMh", "wWw", "wMw", 'M', "gemDarkness", 'w', new ItemStack(Blocks.wool, 1, 15), 'W', "blockWool", 'h', "materialHorn"));

		GameRegistry.addRecipe(new ShapedOreRecipe(getStack("sword_wuerfelium", 0), "M", "M", "h", 'M', "gemWuerfelium", 'h', "materialHorn"));
		GameRegistry.addRecipe(new ShapedOreRecipe(getStack("pickaxe_wuerfelium", 0), "MMM", " h ", " h ", 'M', "gemWuerfelium", 'h', "materialHorn"));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(GameRegistry.findItem("derpyshiz", "plank"), 4, 0), "treeEbony"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(GameRegistry.findItem("derpyshiz", "plank"), 4, 1), "treeMagic"));

		GameRegistry.addRecipe(new ShapelessOreRecipe(getStack("snowflake_obsidian", 0), "blockObsidian", "gemQuartz", "gemQuartz"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findBlock("derpyshiz", "coarse_stone"), 3, 0), "SGS", "GSG", "SGS", 'S', "stone", 'G', "blockGravel"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findBlock("derpyshiz", "coarse_stone"), 1, 1), "sss", "sCs", "sss", 'C', "stoneCoarse", 's', "stickWood"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findBlock("derpyshiz", "axis"), 2), "sss", 's', "stickWood"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("derpyshiz", "torch_fluorite_red"), 8, 0), "ttt", "tMt", "ttt", 'M', "gemFluoriteBrown", 't', new ItemStack(GameRegistry.findItem("minecraft", "torch"), 1, 0)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("derpyshiz", "torch_fluorite_green"), 8, 0), "ttt", "tMt", "ttt", 'M', "gemFluoritePink", 't', new ItemStack(GameRegistry.findItem("minecraft", "torch"), 1, 0)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("derpyshiz", "torch_fluorite_blue"), 8, 0), "ttt", "tMt", "ttt", 'M', "gemFluoriteRed", 't', new ItemStack(GameRegistry.findItem("minecraft", "torch"), 1, 0)));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("derpyshiz", "lamp"), 3, 0), "gtg", "tMt", "gtg", 'M', "gemFluoriteBrown", 't', getStack("torch_fluorite_red", 0), 'g', "paneGlass"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("derpyshiz", "lamp"), 3, 1), "gtg", "tMt", "gtg", 'M', "gemFluoritePink", 't', getStack("torch_fluorite_green", 0), 'g', "paneGlass"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("derpyshiz", "lamp"), 3, 2), "gtg", "tMt", "gtg", 'M', "gemFluoriteRed", 't', getStack("torch_fluorite_blue", 0), 'g', "paneGlass"));

		GameRegistry.addRecipe(new ShapelessOreRecipe(getBlock("kewl_bush", 0), "gemFakediamond", Blocks.deadbush));

		GameRegistry.addRecipe(new ShapedOreRecipe(getStack("tdm", 0), "D D", "DDD", 'D', "gemDiamond"));

		registerGear(1, 1, "plankEbony", "plankEbony");
		registerGear(2, 1, "plankMagic", "plankMagic");
		registerGear(0, 1, "plankWood", "plankWood");
		registerGear(3, 1, "ingotIron", "ingotIron");
		registerGear(4, 2, "foodLasagne", "cropWheat");
		registerGear(5, 1, "ingotCopper", "ingotCopper");
		registerGear(6, 1, "ingotTin", "ingotTin");
		registerGear(7, 1, "ingotLead", "ingotLead");
		registerGear(8, 1, "ingotGold", "ingotGold");
		registerGear(9, 1, "ingotTitanium", "ingotTitanium");
		registerGear(10, 2, "gemRuby", "gemDiamond");
		registerGear(11, 1, "ingotElectrimite", "dustRedstone");
		registerGear(12, 2, "ingotEnderium", "gemAmethyst");

		GameRegistry.addRecipe(new ShapedOreRecipe(getStack("rotameter", 0), "G", "C", "s", 'G', "gearGold", 'C', Items.compass, 's', "stickWood"));

		registerNugget("nuggetIron", "ingotIron", 0, new ItemStack(Items.iron_ingot));
		registerNugget("nuggetCopper", "ingotCopper", 1, getStack("ore_item", 9));
		registerNugget("nuggetTin", "ingotTin", 2, getStack("ore_item", 13));
		registerNugget("nuggetLead", "ingotLead", 3, getStack("ore_item", 14));
		registerNugget("nuggetElectrimite", "ingotElectrimite", 4, getStack("ore_item", 11));
		registerNugget("nuggetEnderium", "ingotEnderium", 5, getStack("ore_item", 10));
		registerNugget("nuggetTitanium", "ingotTitanium", 6, getStack("ore_item", 2));
		registerNugget("nuggetDiamond", "gemDiamond", 7, new ItemStack(Items.diamond));
		registerNugget("nuggetRuby", "gemRuby", 8, getStack("ore_item", 3));
		registerNugget("nuggetDarkness", "gemDarkness", 9, getStack("ore_item", 12));
		registerNugget("nuggetAmethyst", "gemAmethyst", 10, getStack("ore_item", 5));

		registerDeco(0, "chunkAmber", "blockAmber");
		registerDeco(1, "gemFakediamond", "blockFakediamond");
		registerDeco(2, "ingotTitanium", "blockTitanium");
		registerDeco(3, "gemRuby", "blockRuby");
		registerDeco(4, "gemTurquoise", "blockTurquoise");
		registerDeco(5, "gemAmethyst", "blockAmethyst");
		registerDeco(6, "gemFluoriteBrown", "blockFluoriteBrown");
		registerDeco(7, "gemFluoriteRed", "blockFluoriteRed");
		registerDeco(8, "gemFluoritePink", "blockFluoritePink");
		registerDeco(9, "ingotCopper", "blockCopper");
		registerDeco(10, "ingotEnderium", "blockEnderium");
		registerDeco(11, "ingotElectrimite", "blockElectrimite");
		registerDeco(12, "gemDarkness", "blockDarkness");
		registerDeco(13, "ingotTin", "blockTin");
		registerDeco(14, "ingotLead", "blockLead");
		registerDeco(15, "gemWuerfelium", "blockWuerfelium");

		GameRegistry.addRecipe(new ShapedOreRecipe(GameRegistry.findBlock("derpyshiz", "block_lasagne"), "LLL", "LLL", "LLL", 'L', "foodLasagne"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("derpyshiz", "lasagne"), 9), "B", 'B', GameRegistry.findBlock("derpyshiz", "block_lasagne")));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findBlock("derpyshiz", "seizure_wool"), 4), "WgW", "gcg", "WgW", 'g', "dustGlowstone", 'W', "blockWool", 'c', "circuitRedstone"));

		// Redstone Circuit
		GameRegistry.addRecipe(new ShapedOreRecipe(getStack("circuit", 0), "cqc", "rrr", "igi", 'q', "gemQuartz", 'i', "nuggetIron", 'g', "nuggetGold", 'r', "dustRedstone", 'c', "nuggetCopper")); // Redstone
		if (alwaysFalse) {
		// Advanced Redstone Circuit
		GameRegistry.addRecipe(new ShapedOreRecipe(getStack("circuit", 1), "iFi", "gCg", "iRi", 'i', "nuggetIron", 'F', "gemFluorite", 'g', "dustGlowstone", 'C', "circuitRedstone", 'R', "blockRedstone"));
		// Normal Electric Circuit
		GameRegistry.addRecipe(new ShapedOreRecipe(getStack("circuit", 2), "cec", "ere", "igi", 'i', "nuggetIron", 'g', "nuggetGold", 'r', "dustRedstone", 'c', "nuggetCopper", 'e', "nuggetElectrimite"));
		// Advanced Electric Circuit
		GameRegistry.addRecipe(new ShapedOreRecipe(getStack("circuit", 3), "cAc", "gCg", "cEc", 'c', "nuggetCopper", 'A', "chunkAmber", 'g', "dustGlowstone", 'C', "circuitElectric", 'E', "ingotElectrimite"));
		}
		
		GameRegistry.addRecipe(new ShapedOreRecipe(getBlock("housing",0),"waw","aga","waw",'w',"plankWood",'a',"blockAxis",'g',"gearWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(getBlock("housing",1),"tit","ihi","tit",'t',"ingotTin",'i',"ingotIron",'h',"housingNormal"));
		
		for (int i = 0; i < 16; i++) {
			GameRegistry.addShapedRecipe(new ItemStack(GameRegistry.findBlock("derpyshiz", "pattern_carpet"), 3, i), "WW", 'W', getStack("pattern_wool", i));
		}

		GameRegistry.addSmelting(getBlock("ore", 2), getStack("ore_item", 2), 0.9f);
		GameRegistry.addSmelting(getBlock("ore", 9), getStack("ore_item", 9), 0.5f);
		GameRegistry.addSmelting(getBlock("ore", 10), getStack("ore_item", 10), 1.5f);
		GameRegistry.addSmelting(getBlock("ore", 11), getStack("ore_item", 11), 1.5f);
		GameRegistry.addSmelting(getBlock("ore", 13), getStack("ore_item", 13), 0.7f);
		GameRegistry.addSmelting(getBlock("ore", 14), getStack("ore_item", 14), 1f);

		// millstone stuffz
		DerpyRegistry.millstoneRecipes.put(new ItemStack(Items.iron_ingot, 1), new DerpyRegistry.BasicMachineEntry(new ItemStack(DerpyItems.dusts, 1, 0), 0.3f));
		DerpyRegistry.millstoneRecipes.put(new ItemStack(Items.gold_ingot, 1), new DerpyRegistry.BasicMachineEntry(new ItemStack(DerpyItems.dusts, 1, 1), 0.3f));
		DerpyRegistry.millstoneRecipes.put(new ItemStack(Items.diamond, 1), new DerpyRegistry.BasicMachineEntry(new ItemStack(DerpyItems.dusts, 1, 2), 0.3f));
		DerpyRegistry.millstoneRecipes.put(new ItemStack(DerpyItems.oreItems, 1, 3), new DerpyRegistry.BasicMachineEntry(new ItemStack(DerpyItems.dusts, 1, 3), 0.3f));
		DerpyRegistry.millstoneRecipes.put(new ItemStack(DerpyItems.oreItems, 1, 9), new DerpyRegistry.BasicMachineEntry(new ItemStack(DerpyItems.dusts, 1, 4), 0.3f));
		DerpyRegistry.millstoneRecipes.put(new ItemStack(DerpyItems.oreItems, 1, 13), new DerpyRegistry.BasicMachineEntry(new ItemStack(DerpyItems.dusts, 1, 5), 0.3f));
		DerpyRegistry.millstoneRecipes.put(new ItemStack(DerpyItems.oreItems, 1, 14), new DerpyRegistry.BasicMachineEntry(new ItemStack(DerpyItems.dusts, 1, 6), 0.3f));
		DerpyRegistry.millstoneRecipes.put(new ItemStack(DerpyItems.oreItems, 1, 2), new DerpyRegistry.BasicMachineEntry(new ItemStack(DerpyItems.dusts, 1, 7), 0.3f));
		DerpyRegistry.millstoneRecipes.put(new ItemStack(DerpyItems.oreItems, 1, 11), new DerpyRegistry.BasicMachineEntry(new ItemStack(DerpyItems.dusts, 1, 8), 0.3f));
		DerpyRegistry.millstoneRecipes.put(new ItemStack(Items.ghast_tear, 3), new DerpyRegistry.BasicMachineEntry(new ItemStack(DerpyItems.dusts, 1, 9), 0.4f));
		DerpyRegistry.millstoneRecipes.put(new ItemStack(DerpyItems.oreItems, 1, 5), new DerpyRegistry.BasicMachineEntry(new ItemStack(DerpyItems.dusts, 1, 10), 0.3f));
		DerpyRegistry.millstoneRecipes.put(new ItemStack(Blocks.obsidian, 1), new DerpyRegistry.BasicMachineEntry(new ItemStack(DerpyItems.dusts, 11), 0.3f));
		DerpyRegistry.millstoneRecipes.put(new ItemStack(DerpyItems.oreItems, 1, 0), new DerpyRegistry.BasicMachineEntry(new ItemStack(DerpyItems.dusts, 1, 12), 0.3f));
		DerpyRegistry.millstoneRecipes.put(new ItemStack(Items.ender_pearl, 1), new DerpyRegistry.BasicMachineEntry(new ItemStack(DerpyItems.dusts, 1, 13), 0.3f));
	}

	public static void registerOredict() {
		OreDictionary.registerOre("chunkAmber", getStack("ore_item", 0));
		OreDictionary.registerOre("gemFakediamond", getStack("ore_item", 1));
		OreDictionary.registerOre("ingotTitanium", getStack("ore_item", 2));
		OreDictionary.registerOre("gemRuby", getStack("ore_item", 3));
		OreDictionary.registerOre("gemTurquoise", getStack("ore_item", 4));
		OreDictionary.registerOre("gemAmethyst", getStack("ore_item", 5));
		OreDictionary.registerOre("gemFluorite", getStack("ore_item", 6));
		OreDictionary.registerOre("gemFluorite", getStack("ore_item", 7));
		OreDictionary.registerOre("gemFluorite", getStack("ore_item", 8));
		OreDictionary.registerOre("gemFluoriteBrown", getStack("ore_item", 6));
		OreDictionary.registerOre("gemFluoriteRed", getStack("ore_item", 7));
		OreDictionary.registerOre("gemFluoritePink", getStack("ore_item", 8));
		OreDictionary.registerOre("ingotCopper", getStack("ore_item", 9));
		OreDictionary.registerOre("ingotEnderium", getStack("ore_item", 10));
		OreDictionary.registerOre("ingotElectrimite", getStack("ore_item", 11));
		OreDictionary.registerOre("gemDarkness", getStack("ore_item", 12));
		OreDictionary.registerOre("ingotTin", getStack("ore_item", 13));
		OreDictionary.registerOre("ingotLead", getStack("ore_item", 14));
		OreDictionary.registerOre("gemWuerfelium", getStack("ore_item", 15));

		OreDictionary.registerOre("blockAmber", getBlock("block", 0));
		OreDictionary.registerOre("blockFakediamond", getBlock("block", 1));
		OreDictionary.registerOre("blockTitanium", getBlock("block", 2));
		OreDictionary.registerOre("blockRuby", getBlock("block", 3));
		OreDictionary.registerOre("blockTurquoise", getBlock("block", 4));
		OreDictionary.registerOre("blockAmethyst", getBlock("block", 5));
		OreDictionary.registerOre("blockFluorite", getBlock("block", 6));
		OreDictionary.registerOre("blockFluorite", getBlock("block", 7));
		OreDictionary.registerOre("blockFluorite", getBlock("block", 8));
		OreDictionary.registerOre("blockFluoriteBrown", getBlock("block", 6));
		OreDictionary.registerOre("blockFluoriteRed", getBlock("block", 7));
		OreDictionary.registerOre("blockFluoritePink", getBlock("block", 8));
		OreDictionary.registerOre("blockCopper", getBlock("block", 9));
		OreDictionary.registerOre("blockEnderium", getBlock("block", 10));
		OreDictionary.registerOre("blockElectrimite", getBlock("block", 11));
		OreDictionary.registerOre("blockDarkness", getBlock("block", 12));
		OreDictionary.registerOre("blockTin", getBlock("block", 13));
		OreDictionary.registerOre("blockLead", getBlock("block", 14));
		OreDictionary.registerOre("blockWuerfelium", getBlock("block", 15));

		OreDictionary.registerOre("oreAmber", getBlock("ore", 0));
		OreDictionary.registerOre("oreFakediamond", getBlock("ore", 1));
		OreDictionary.registerOre("oreTitanium", getBlock("ore", 2));
		OreDictionary.registerOre("oreRuby", getBlock("ore", 3));
		OreDictionary.registerOre("oreTurquoise", getBlock("ore", 4));
		OreDictionary.registerOre("oreAmethyst", getBlock("ore", 5));
		OreDictionary.registerOre("oreFluorite", getBlock("ore", 6));
		OreDictionary.registerOre("oreFluorite", getBlock("ore", 7));
		OreDictionary.registerOre("oreFluorite", getBlock("ore", 8));
		OreDictionary.registerOre("oreFluoriteBrown", getBlock("ore", 6));
		OreDictionary.registerOre("oreFluoriteRed", getBlock("ore", 7));
		OreDictionary.registerOre("oreFluoritePink", getBlock("ore", 8));
		OreDictionary.registerOre("oreCopper", getBlock("ore", 9));
		OreDictionary.registerOre("oreEnderium", getBlock("ore", 10));
		OreDictionary.registerOre("oreElectrimite", getBlock("ore", 11));
		OreDictionary.registerOre("oreDarkness", getBlock("ore", 12));
		OreDictionary.registerOre("oreTin", getBlock("ore", 13));
		OreDictionary.registerOre("oreLead", getBlock("ore", 14));
		OreDictionary.registerOre("oreWuerfelium", getBlock("ore", 15));

		OreDictionary.registerOre("blockWool", getBlock("pattern_wool", 0));
		OreDictionary.registerOre("blockWool", getBlock("pattern_wool", 1));
		OreDictionary.registerOre("blockWool", getBlock("pattern_wool", 2));
		OreDictionary.registerOre("blockWool", getBlock("pattern_wool", 3));
		OreDictionary.registerOre("blockWool", getBlock("pattern_wool", 4));
		OreDictionary.registerOre("blockWool", getBlock("pattern_wool", 5));
		OreDictionary.registerOre("blockWool", getBlock("pattern_wool", 6));
		OreDictionary.registerOre("blockWool", getBlock("pattern_wool", 7));
		OreDictionary.registerOre("blockWool", getBlock("pattern_wool", 8));
		OreDictionary.registerOre("blockWool", getBlock("pattern_wool", 9));
		OreDictionary.registerOre("blockWool", getBlock("pattern_wool", 10));
		OreDictionary.registerOre("blockWool", getBlock("pattern_wool", 11));
		OreDictionary.registerOre("blockWool", getBlock("pattern_wool", 12));
		OreDictionary.registerOre("blockWool", getBlock("pattern_wool", 13));
		OreDictionary.registerOre("blockWool", getBlock("pattern_wool", 14));
		OreDictionary.registerOre("blockWool", getBlock("pattern_wool", 15));

		OreDictionary.registerOre("woolColordots", getBlock("pattern_wool", 0));
		OreDictionary.registerOre("woolLines", getBlock("pattern_wool", 1));
		OreDictionary.registerOre("woolChecker", getBlock("pattern_wool", 2));
		OreDictionary.registerOre("woolDotted", getBlock("pattern_wool", 3));
		OreDictionary.registerOre("woolStriped", getBlock("pattern_wool", 4));
		OreDictionary.registerOre("woolSquares", getBlock("pattern_wool", 5));
		OreDictionary.registerOre("woolBright", getBlock("pattern_wool", 6));
		OreDictionary.registerOre("woolHeart", getBlock("pattern_wool", 7));
		OreDictionary.registerOre("woolRainbow", getBlock("pattern_wool", 8));
		OreDictionary.registerOre("woolSwiss", getBlock("pattern_wool", 9));
		OreDictionary.registerOre("woolGermany", getBlock("pattern_wool", 10));
		OreDictionary.registerOre("woolBavaria", getBlock("pattern_wool", 11));
		OreDictionary.registerOre("woolMojang", getBlock("pattern_wool", 12));
		OreDictionary.registerOre("woolNotch", getBlock("pattern_wool", 13));
		OreDictionary.registerOre("woolJeb", getBlock("pattern_wool", 14));
		OreDictionary.registerOre("woolPig", getBlock("pattern_wool", 15));

		OreDictionary.registerOre("gearWood", getStack("gear", 0));
		OreDictionary.registerOre("gearWood", getStack("gear", 1));
		OreDictionary.registerOre("gearWood", getStack("gear", 2));
		OreDictionary.registerOre("gearEbony", getStack("gear", 1));
		OreDictionary.registerOre("gearMagic", getStack("gear", 2));
		OreDictionary.registerOre("gearIron", getStack("gear", 3));
		OreDictionary.registerOre("gearLasagne", getStack("gear", 4));
		OreDictionary.registerOre("gearCopper", getStack("gear", 5));
		OreDictionary.registerOre("gearTin", getStack("gear", 6));
		OreDictionary.registerOre("gearLead", getStack("gear", 7));
		OreDictionary.registerOre("gearGold", getStack("gear", 8));
		OreDictionary.registerOre("gearTitanium", getStack("gear", 9));
		OreDictionary.registerOre("gearRuby", getStack("gear", 10));
		OreDictionary.registerOre("gearElectrimite", getStack("gear", 11));
		OreDictionary.registerOre("gearEnderium", getStack("gear", 12));

		OreDictionary.registerOre("nuggetIron", getStack("nugget", 0));
		OreDictionary.registerOre("nuggetCopper", getStack("nugget", 1));
		OreDictionary.registerOre("nuggetTin", getStack("nugget", 2));
		OreDictionary.registerOre("nuggetLead", getStack("nugget", 3));
		OreDictionary.registerOre("nuggetElectrimite", getStack("nugget", 4));
		OreDictionary.registerOre("nuggetEnderium", getStack("nugget", 5));
		OreDictionary.registerOre("nuggetTitanium", getStack("nugget", 6));
		OreDictionary.registerOre("nuggetDiamond", getStack("nugget", 7));
		OreDictionary.registerOre("nuggetRuby", getStack("nugget", 8));
		OreDictionary.registerOre("nuggetDarkness", getStack("nugget", 9));
		OreDictionary.registerOre("nuggetAmethyst", getStack("nugget", 10));

		OreDictionary.registerOre("dustIron", getStack("dust", 0));
		OreDictionary.registerOre("dustGold", getStack("dust", 1));
		OreDictionary.registerOre("dustDiamond", getStack("dust", 2));
		OreDictionary.registerOre("dustRuby", getStack("dust", 3));
		OreDictionary.registerOre("dustCopper", getStack("dust", 4));
		OreDictionary.registerOre("dustTin", getStack("dust", 5));
		OreDictionary.registerOre("dustLead", getStack("dust", 6));
		OreDictionary.registerOre("dustTitanium", getStack("dust", 7));
		OreDictionary.registerOre("dustElectrimite", getStack("dust", 8));
		OreDictionary.registerOre("dustGhast", getStack("dust", 9));
		OreDictionary.registerOre("dustAmethyst", getStack("dust", 10));
		OreDictionary.registerOre("dustObsidian", getStack("dust", 11));
		OreDictionary.registerOre("dustAmber", getStack("dust", 12));
		OreDictionary.registerOre("dustEnderpearl", getStack("dust", 13));

		OreDictionary.registerOre("circuitRedstone", getStack("circuit", 0));
		OreDictionary.registerOre("circuitRedstoneAdvanced", getStack("circuit", 1));
		OreDictionary.registerOre("circuitElectric", getStack("circuit", 2));
		OreDictionary.registerOre("circuitElectricAdvanced", getStack("circuit", 3));

		OreDictionary.registerOre("treeEbony", getBlock("log", 0));
		OreDictionary.registerOre("treeMagic", getBlock("log", 1));
		OreDictionary.registerOre("treeWood", getBlock("log", 0));
		OreDictionary.registerOre("treeWood", getBlock("log", 1));

		OreDictionary.registerOre("leavesEbony", getBlock("leaves", 0));
		OreDictionary.registerOre("leavesMagic", getBlock("leaves", 1));
		OreDictionary.registerOre("treeLeaves", getBlock("leaves", 0));
		OreDictionary.registerOre("treeLeaves", getBlock("leaves", 1));

		OreDictionary.registerOre("plankEbony", getBlock("plank", 0));
		OreDictionary.registerOre("plankMagic", getBlock("plank", 1));
		OreDictionary.registerOre("plankWood", getBlock("plank", 0));
		OreDictionary.registerOre("plankWood", getBlock("plank", 1));

		OreDictionary.registerOre("materialHorn", getStack("horn", 0));

		OreDictionary.registerOre("foodLasagne", getStack("lasagne", 0));

		OreDictionary.registerOre("stoneCoarse", getBlock("coarse_stone", 0));
		OreDictionary.registerOre("stoneCoarseFramed", getBlock("coarse_stone", 1));

		OreDictionary.registerOre("blockAxis", getBlock("axis", 0));
		OreDictionary.registerOre("housingNormal", getBlock("housing",0));
		OreDictionary.registerOre("housingAdvanced", getBlock("housing",1));

		// Vanilla tweaks
		// ForgePeope.tasks.oreDict.brain == null

		for (int i = 0; i < 16; i++) {
			OreDictionary.registerOre("blockWool", new ItemStack(Blocks.wool, 1, i));
		}

		OreDictionary.registerOre("blockObsidian", Blocks.obsidian);
		OreDictionary.registerOre("blockGravel", Blocks.gravel);
	}

}
