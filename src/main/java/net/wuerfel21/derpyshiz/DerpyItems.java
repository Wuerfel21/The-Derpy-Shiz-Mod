package net.wuerfel21.derpyshiz;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraftforge.common.util.EnumHelper;
import net.wuerfel21.derpyshiz.items.ArmorStone;
import net.wuerfel21.derpyshiz.items.ArmorWater;
import net.wuerfel21.derpyshiz.items.BaseCap;
import net.wuerfel21.derpyshiz.items.DarkCloak;
import net.wuerfel21.derpyshiz.items.DarkSword;
import net.wuerfel21.derpyshiz.items.DerpyArmor;
import net.wuerfel21.derpyshiz.items.DerpyAxe;
import net.wuerfel21.derpyshiz.items.DerpyCircuits;
import net.wuerfel21.derpyshiz.items.DerpyDusts;
import net.wuerfel21.derpyshiz.items.DerpyGears;
import net.wuerfel21.derpyshiz.items.DerpyHammer;
import net.wuerfel21.derpyshiz.items.DerpyHoe;
import net.wuerfel21.derpyshiz.items.DerpyNuggets;
import net.wuerfel21.derpyshiz.items.DerpyPickaxe;
import net.wuerfel21.derpyshiz.items.DerpyShovel;
import net.wuerfel21.derpyshiz.items.DerpySpawnEgg;
import net.wuerfel21.derpyshiz.items.DerpySword;
import net.wuerfel21.derpyshiz.items.ExtraIngots;
import net.wuerfel21.derpyshiz.items.FireSword;
import net.wuerfel21.derpyshiz.items.HorseLasagne;
import net.wuerfel21.derpyshiz.items.ItemRotameter;
import net.wuerfel21.derpyshiz.items.ItemTDM;
import net.wuerfel21.derpyshiz.items.LongAxe;
import net.wuerfel21.derpyshiz.items.LongHoe;
import net.wuerfel21.derpyshiz.items.LongPickaxe;
import net.wuerfel21.derpyshiz.items.LongShovel;
import net.wuerfel21.derpyshiz.items.LongSword;
import net.wuerfel21.derpyshiz.items.NaturalSword;
import net.wuerfel21.derpyshiz.items.OreItems;
import net.wuerfel21.derpyshiz.items.RainbowCookie;
import net.wuerfel21.derpyshiz.items.TopHat;
import net.wuerfel21.derpyshiz.items.WaterSword;
import net.wuerfel21.derpyshiz.items.WindSword;
import net.wuerfel21.derpyshiz.items.WuerfeliumPickaxe;
import net.wuerfel21.derpyshiz.items.WuerfeliumSword;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class DerpyItems {
	public static ToolMaterial TITANIUM;
	public static ToolMaterial RUBY;
	public static ToolMaterial AMETHYST;
	public static ToolMaterial AMBER;
	public static ToolMaterial LAPIS;
	public static ToolMaterial TURQUOISE;
	public static ToolMaterial FAKEDIAMOND;
	public static ToolMaterial COPPER;
	public static ToolMaterial ENDERIUM;
	public static ToolMaterial DARKNESS;
	public static ToolMaterial WUERFELIUM;

	public static ToolMaterial FIRE;
	public static ToolMaterial WATER;
	public static ToolMaterial NATURAL;
	public static ToolMaterial WIND;
	
	public static ToolMaterial LONG;

	public static ArmorMaterial ARMOR_TITANIUM;
	public static ArmorMaterial ARMOR_RUBY;
	public static ArmorMaterial ARMOR_AMETHYST;
	public static ArmorMaterial ARMOR_COPPER;

	public static ArmorMaterial ARMOR_STONE;
	public static ArmorMaterial ARMOR_WATER;

	public static ArmorMaterial ARMOR_CIRCLET_DIAMOND;
	public static ArmorMaterial ARMOR_CIRCLET_RUBY;
	public static ArmorMaterial ARMOR_CIRCLET_AMETHYST;
	public static ArmorMaterial ARMOR_CIRCLET_TURQUOISE;
	public static ArmorMaterial ARMOR_CIRCLET_FAKEDIAMOND;

	public static ArmorMaterial ARMOR_DARK_CLOAK;
	
	public static ArmorMaterial ARMOR_TOP_HAT;
	public static ArmorMaterial ARMOR_CAP;
	public static ArmorMaterial ARMOR_CAP_YOLO;

	public static Item shearIcon;
	public static Item oreItems;
	public static Item extraIngots;
	public static Item dusts;
	public static Item nuggets;
	public static Item cookie;
	public static Item spawnegg;

	public static Item[] stoneArmor = new Item[4];

	public static final void init() {
		TITANIUM = EnumHelper.addToolMaterial("TITANIUM", 2, 1000, 5.75f, 2.25f, 10);
		RUBY = EnumHelper.addToolMaterial("RUBY", 3, 1000, 6f, 2.75f, 15);
		AMETHYST = EnumHelper.addToolMaterial("AMETHYST", 4, 3000, 11.0f, 4.0f, 18);
		AMBER = EnumHelper.addToolMaterial("AMBER", 1, 300, 5.0f, 1.50f, 25);
		LAPIS = EnumHelper.addToolMaterial("LAPIS", 1, 180, 4.5f, 1.25f, 35);
		TURQUOISE = EnumHelper.addToolMaterial("TURQUOISE", 3, 450, 2.0f, 1.5f, 20);
		FAKEDIAMOND = EnumHelper.addToolMaterial("FAKEDIAMOND", 0, 400, 5.25f, 0f, 1);
		COPPER = EnumHelper.addToolMaterial("COPPER", 1, 200, 5f, 1.75f, 10);
		ENDERIUM = EnumHelper.addToolMaterial("ENDERIUM", 3, 1500, 9.5f, 4.75f, 5);
		DARKNESS = EnumHelper.addToolMaterial("DARKNESS", 2, 750, 1f, 2.25f, 20);
		WUERFELIUM = EnumHelper.addToolMaterial("WUERFELIUM", 2, 300, 3f, 2.1f, 2);

		FIRE = EnumHelper.addToolMaterial("FIRE", 0, 750, 1f, 2f, 10);
		WATER = EnumHelper.addToolMaterial("WATER", 0, 650, 1f, 2f, 10);
		NATURAL = EnumHelper.addToolMaterial("NATURAL", 0, 650, 1f, 3f, 10);
		WIND = EnumHelper.addToolMaterial("WIND", 0, 650, 1f, 2f, 10);
		
		LONG = EnumHelper.addToolMaterial("LONG", 2, 850, 6f, 2.50f, 11);
		
		ARMOR_TITANIUM = EnumHelper.addArmorMaterial("ARMOR_TITANIUM", 25, new int[] { 2, 7, 5, 3 }, 8);
		ARMOR_RUBY = EnumHelper.addArmorMaterial("ARMOR_RUBY", 30, new int[] { 3, 7, 6, 3 }, 8);
		ARMOR_AMETHYST = EnumHelper.addArmorMaterial("ARMOR_AMETHYST", 40, new int[] { 4, 9, 7, 4 }, 16);
		ARMOR_COPPER = EnumHelper.addArmorMaterial("ARMOR_COPPER", 10, new int[] { 2, 4, 4, 2 }, 6);

		ARMOR_STONE = EnumHelper.addArmorMaterial("ARMOR_STONE", 7, new int[] { 3, 5, 4, 1 }, 0);
		ARMOR_WATER = EnumHelper.addArmorMaterial("ARMOR_WATER", 14, new int[] { 2, 6, 5, 3 }, 1);

		ARMOR_CIRCLET_DIAMOND = EnumHelper.addArmorMaterial("ARMOR_CIRCLET_DIAMOND", 20, new int[] { 2, 0, 0, 0 }, 8);
		ARMOR_CIRCLET_RUBY = EnumHelper.addArmorMaterial("ARMOR_CIRCLET_RUBY", 19, new int[] { 2, 0, 0, 0 }, 6);
		ARMOR_CIRCLET_AMETHYST = EnumHelper.addArmorMaterial("ARMOR_CIRCLET_AMETHYST", 22, new int[] { 3, 0, 0, 0 }, 10);
		ARMOR_CIRCLET_TURQUOISE = EnumHelper.addArmorMaterial("ARMOR_CIRCLET_TURQUOISE", 15, new int[] { 1, 0, 0, 0 }, 4);
		ARMOR_CIRCLET_FAKEDIAMOND = EnumHelper.addArmorMaterial("ARMOR_CIRCLET_DIAMOND", 5, new int[] { 0, 0, 0, 0 }, 0);

		ARMOR_DARK_CLOAK = EnumHelper.addArmorMaterial("ARMOR_DARK_CLOAK", 20, new int[] { 0, 3, 0, 0 }, 7);
		
		ARMOR_TOP_HAT = EnumHelper.addArmorMaterial("ARMOR_TOP_HAT", 14, new int[] { 1, 0, 0, 0 }, 0);
		ARMOR_CAP = EnumHelper.addArmorMaterial("ARMOR_CAP", 10, new int[] { 1, 0, 0, 0 }, 0);
		ARMOR_CAP_YOLO = EnumHelper.addArmorMaterial("ARMOR_CAP_YOLO", 12, new int[] { 1, 0, 0, 0 }, 5);

		GameRegistry.registerItem(spawnegg = new DerpySpawnEgg(), "spawn_egg");
		
		GameRegistry.registerItem(oreItems = new OreItems(), "ore_item");
		GameRegistry.registerItem(extraIngots = new ExtraIngots(), "extra_ingot");
		GameRegistry.registerItem(dusts = new DerpyDusts(), "dust");

		GameRegistry.registerItem(new DerpyHammer(ToolMaterial.IRON).setTextureName("derpyshiz:hammer").setUnlocalizedName("derpyshiz.hammer"), "hammer");

		GameRegistry.registerItem(new DerpyPickaxe(TITANIUM, oreItems, 2).setUnlocalizedName("derpyshiz.pickaxe_titanium").setTextureName("derpyshiz:pickaxe_titanium"), "pickaxe_titanium");
		GameRegistry.registerItem(new DerpySword(TITANIUM, oreItems, 2).setUnlocalizedName("derpyshiz.sword_titanium").setTextureName("derpyshiz:sword_titanium"), "sword_titanium");
		GameRegistry.registerItem(new DerpyHoe(TITANIUM, oreItems, 2).setUnlocalizedName("derpyshiz.hoe_titanium").setTextureName("derpyshiz:hoe_titanium"), "hoe_titanium");
		GameRegistry.registerItem(new DerpyShovel(TITANIUM, oreItems, 2).setUnlocalizedName("derpyshiz.shovel_titanium").setTextureName("derpyshiz:shovel_titanium"), "shovel_titanium");
		GameRegistry.registerItem(new DerpyAxe(TITANIUM, oreItems, 2).setUnlocalizedName("derpyshiz.axe_titanium").setTextureName("derpyshiz:axe_titanium"), "axe_titanium");

		GameRegistry.registerItem(new DerpyPickaxe(RUBY, oreItems, 3).setUnlocalizedName("derpyshiz.pickaxe_ruby").setTextureName("derpyshiz:pickaxe_ruby"), "pickaxe_ruby");
		GameRegistry.registerItem(new DerpySword(RUBY, oreItems, 3).setUnlocalizedName("derpyshiz.sword_ruby").setTextureName("derpyshiz:sword_ruby"), "sword_ruby");
		GameRegistry.registerItem(new DerpyHoe(RUBY, oreItems, 3).setUnlocalizedName("derpyshiz.hoe_ruby").setTextureName("derpyshiz:hoe_ruby"), "hoe_ruby");
		GameRegistry.registerItem(new DerpyShovel(RUBY, oreItems, 3).setUnlocalizedName("derpyshiz.shovel_ruby").setTextureName("derpyshiz:shovel_ruby"), "shovel_ruby");
		GameRegistry.registerItem(new DerpyAxe(RUBY, oreItems, 3).setUnlocalizedName("derpyshiz.axe_ruby").setTextureName("derpyshiz:axe_ruby"), "axe_ruby");

		GameRegistry.registerItem(new DerpyPickaxe(AMETHYST, oreItems, 5).setUnlocalizedName("derpyshiz.pickaxe_amethyst").setTextureName("derpyshiz:pickaxe_amethyst"), "pickaxe_amethyst");
		GameRegistry.registerItem(new DerpySword(AMETHYST, oreItems, 5).setUnlocalizedName("derpyshiz.sword_amethyst").setTextureName("derpyshiz:sword_amethyst"), "sword_amethyst");
		GameRegistry.registerItem(new DerpyHoe(AMETHYST, oreItems, 5).setUnlocalizedName("derpyshiz.hoe_amethyst").setTextureName("derpyshiz:hoe_amethyst"), "hoe_amethyst");
		GameRegistry.registerItem(new DerpyShovel(AMETHYST, oreItems, 5).setUnlocalizedName("derpyshiz.shovel_amethyst").setTextureName("derpyshiz:shovel_amethyst"), "shovel_amethyst");
		GameRegistry.registerItem(new DerpyAxe(AMETHYST, oreItems, 5).setUnlocalizedName("derpyshiz.axe_amethyst").setTextureName("derpyshiz:axe_amethyst"), "axe_amethyst");

		GameRegistry.registerItem(new DerpyPickaxe(COPPER, oreItems, 9).setUnlocalizedName("derpyshiz.pickaxe_copper").setTextureName("derpyshiz:pickaxe_copper"), "pickaxe_copper");
		GameRegistry.registerItem(new DerpySword(COPPER, oreItems, 9).setUnlocalizedName("derpyshiz.sword_copper").setTextureName("derpyshiz:sword_copper"), "sword_copper");
		GameRegistry.registerItem(new DerpyHoe(COPPER, oreItems, 9).setUnlocalizedName("derpyshiz.hoe_copper").setTextureName("derpyshiz:hoe_copper"), "hoe_copper");
		GameRegistry.registerItem(new DerpyShovel(COPPER, oreItems, 9).setUnlocalizedName("derpyshiz.shovel_copper").setTextureName("derpyshiz:shovel_copper"), "shovel_copper");
		GameRegistry.registerItem(new DerpyAxe(COPPER, oreItems, 9).setUnlocalizedName("derpyshiz.axe_copper").setTextureName("derpyshiz:axe_copper"), "axe_copper");

		GameRegistry.registerItem(new DerpySword(AMBER, oreItems, 0).setUnlocalizedName("derpyshiz.sword_amber").setTextureName("derpyshiz:sword_amber"), "sword_amber");

		GameRegistry.registerItem(new DerpySword(LAPIS, Item.getItemFromBlock(Blocks.lapis_block), 0).setUnlocalizedName("derpyshiz.sword_lapis").setTextureName("derpyshiz:sword_lapis"), "sword_lapis");

		GameRegistry.registerItem(new DerpySword(ENDERIUM, oreItems, 10).setUnlocalizedName("derpyshiz.sword_enderium").setTextureName("derpyshiz:sword_enderium"), "sword_enderium");

		GameRegistry.registerItem(new DarkSword(DARKNESS, oreItems, 12).setUnlocalizedName("derpyshiz.sword_darkness").setTextureName("derpyshiz:sword_darkness"), "sword_darkness");

		GameRegistry.registerItem(new DerpyAxe(TURQUOISE, oreItems, 4).setUnlocalizedName("derpyshiz.axe_turquoise").setTextureName("derpyshiz:axe_turquoise"), "axe_turquoise");

		GameRegistry.registerItem(new DerpyHoe(FAKEDIAMOND, oreItems, 1).setUnlocalizedName("derpyshiz.hoe_fakediamond").setTextureName("derpyshiz:hoe_fakediamond"), "hoe_fakediamond");

		GameRegistry.registerItem(new FireSword(FIRE, extraIngots, 0).setUnlocalizedName("derpyshiz.sword_fire").setTextureName("derpyshiz:sword_fire"), "sword_fire");
		GameRegistry.registerItem(new WaterSword(WATER, extraIngots, 1).setUnlocalizedName("derpyshiz.sword_water").setTextureName("derpyshiz:sword_water"), "sword_water");
		GameRegistry.registerItem(new NaturalSword(NATURAL, extraIngots, 2).setUnlocalizedName("derpyshiz.sword_natural").setTextureName("derpyshiz:sword_natural"), "sword_natural");
		GameRegistry.registerItem(new WindSword(WIND, extraIngots, 3).setUnlocalizedName("derpyshiz.sword_wind").setTextureName("derpyshiz:sword_wind"), "sword_wind");
		
		GameRegistry.registerItem(new LongPickaxe(LONG, oreItems, 2).setUnlocalizedName("derpyshiz.pickaxe_long").setTextureName("derpyshiz:pickaxe_long"), "pickaxe_long");
		GameRegistry.registerItem(new LongSword(LONG, oreItems, 2).setUnlocalizedName("derpyshiz.sword_long").setTextureName("derpyshiz:sword_long"), "sword_long");
		GameRegistry.registerItem(new LongHoe(LONG, oreItems, 2).setUnlocalizedName("derpyshiz.hoe_long").setTextureName("derpyshiz:hoe_long"), "hoe_long");
		GameRegistry.registerItem(new LongShovel(LONG, oreItems, 2).setUnlocalizedName("derpyshiz.shovel_long").setTextureName("derpyshiz:shovel_long"), "shovel_long");
		GameRegistry.registerItem(new LongAxe(LONG, oreItems, 2).setUnlocalizedName("derpyshiz.axe_long").setTextureName("derpyshiz:axe_long"), "axe_long");
		
		GameRegistry.registerItem(new WuerfeliumSword(WUERFELIUM, oreItems, 15).setUnlocalizedName("derpyshiz.sword_wuerfelium"), "sword_wuerfelium");
		GameRegistry.registerItem(new WuerfeliumPickaxe(WUERFELIUM, oreItems, 15).setUnlocalizedName("derpyshiz.pickaxe_wuerfelium"), "pickaxe_wuerfelium");

		GameRegistry.registerItem(new DerpyArmor(ARMOR_TITANIUM, 0, oreItems, 2, "titanium").setUnlocalizedName("derpyshiz.helmet_titanium").setTextureName("derpyshiz:helmet_titanium"), "helmet_titanium");
		GameRegistry.registerItem(new DerpyArmor(ARMOR_TITANIUM, 1, oreItems, 2, "titanium").setUnlocalizedName("derpyshiz.chestplate_titanium").setTextureName("derpyshiz:chestplate_titanium"), "chestplate_titanium");
		GameRegistry.registerItem(new DerpyArmor(ARMOR_TITANIUM, 2, oreItems, 2, "titanium").setUnlocalizedName("derpyshiz.leggings_titanium").setTextureName("derpyshiz:leggings_titanium"), "leggings_titanium");
		GameRegistry.registerItem(new DerpyArmor(ARMOR_TITANIUM, 3, oreItems, 2, "titanium").setUnlocalizedName("derpyshiz.boots_titanium").setTextureName("derpyshiz:boots_titanium"), "boots_titanium");

		GameRegistry.registerItem(new DerpyArmor(ARMOR_RUBY, 0, oreItems, 3, "ruby").setUnlocalizedName("derpyshiz.helmet_ruby").setTextureName("derpyshiz:helmet_ruby"), "helmet_ruby");
		GameRegistry.registerItem(new DerpyArmor(ARMOR_RUBY, 1, oreItems, 3, "ruby").setUnlocalizedName("derpyshiz.chestplate_ruby").setTextureName("derpyshiz:chestplate_ruby"), "chestplate_ruby");
		GameRegistry.registerItem(new DerpyArmor(ARMOR_RUBY, 2, oreItems, 3, "ruby").setUnlocalizedName("derpyshiz.leggings_ruby").setTextureName("derpyshiz:leggings_ruby"), "leggings_ruby");
		GameRegistry.registerItem(new DerpyArmor(ARMOR_RUBY, 3, oreItems, 3, "ruby").setUnlocalizedName("derpyshiz.boots_ruby").setTextureName("derpyshiz:boots_ruby"), "boots_ruby");

		GameRegistry.registerItem(new DerpyArmor(ARMOR_AMETHYST, 0, oreItems, 5, "amethyst").setUnlocalizedName("derpyshiz.helmet_amethyst").setTextureName("derpyshiz:helmet_amethyst"), "helmet_amethyst");
		GameRegistry.registerItem(new DerpyArmor(ARMOR_AMETHYST, 1, oreItems, 5, "amethyst").setUnlocalizedName("derpyshiz.chestplate_amethyst").setTextureName("derpyshiz:chestplate_amethyst"), "chestplate_amethyst");
		GameRegistry.registerItem(new DerpyArmor(ARMOR_AMETHYST, 2, oreItems, 5, "amethyst").setUnlocalizedName("derpyshiz.leggings_amethyst").setTextureName("derpyshiz:leggings_amethyst"), "leggings_amethyst");
		GameRegistry.registerItem(new DerpyArmor(ARMOR_AMETHYST, 3, oreItems, 5, "amethyst").setUnlocalizedName("derpyshiz.boots_amethyst").setTextureName("derpyshiz:boots_amethyst"), "boots_amethyst");

		GameRegistry.registerItem(new DerpyArmor(ARMOR_COPPER, 0, oreItems, 9, "copper").setUnlocalizedName("derpyshiz.helmet_copper").setTextureName("derpyshiz:helmet_copper"), "helmet_copper");
		GameRegistry.registerItem(new DerpyArmor(ARMOR_COPPER, 1, oreItems, 9, "copper").setUnlocalizedName("derpyshiz.chestplate_copper").setTextureName("derpyshiz:chestplate_copper"), "chestplate_copper");
		GameRegistry.registerItem(new DerpyArmor(ARMOR_COPPER, 2, oreItems, 9, "copper").setUnlocalizedName("derpyshiz.leggings_copper").setTextureName("derpyshiz:leggings_copper"), "leggings_copper");
		GameRegistry.registerItem(new DerpyArmor(ARMOR_COPPER, 3, oreItems, 9, "copper").setUnlocalizedName("derpyshiz.boots_copper").setTextureName("derpyshiz:boots_copper"), "boots_copper");

		GameRegistry.registerItem(stoneArmor[0] = new ArmorStone(ARMOR_STONE, 0, Item.getItemFromBlock(Blocks.cobblestone), 0, "stone").setUnlocalizedName("derpyshiz.helmet_stone").setTextureName("derpyshiz:helmet_stone"), "helmet_stone");
		GameRegistry.registerItem(stoneArmor[1] = new ArmorStone(ARMOR_STONE, 1, Item.getItemFromBlock(Blocks.cobblestone), 0, "stone").setUnlocalizedName("derpyshiz.chestplate_stone").setTextureName("derpyshiz:chestplate_stone"), "chestplate_stone");
		GameRegistry.registerItem(stoneArmor[2] = new ArmorStone(ARMOR_STONE, 2, Item.getItemFromBlock(Blocks.cobblestone), 0, "stone").setUnlocalizedName("derpyshiz.leggings_stone").setTextureName("derpyshiz:leggings_stone"), "leggings_stone");
		GameRegistry.registerItem(stoneArmor[3] = new ArmorStone(ARMOR_STONE, 3, Item.getItemFromBlock(Blocks.cobblestone), 0, "stone").setUnlocalizedName("derpyshiz.boots_stone").setTextureName("derpyshiz:boots_stone"), "boots_stone");

		GameRegistry.registerItem(new ArmorWater(ARMOR_WATER, 0, Items.water_bucket, 0, "water").setUnlocalizedName("derpyshiz.helmet_water").setTextureName("derpyshiz:helmet_water"), "helmet_water");
		GameRegistry.registerItem(new ArmorWater(ARMOR_WATER, 1, Items.water_bucket, 0, "water").setUnlocalizedName("derpyshiz.chestplate_water").setTextureName("derpyshiz:chestplate_water"), "chestplate_water");
		GameRegistry.registerItem(new ArmorWater(ARMOR_WATER, 2, Items.water_bucket, 0, "water").setUnlocalizedName("derpyshiz.leggings_water").setTextureName("derpyshiz:leggings_water"), "leggings_water");
		GameRegistry.registerItem(new ArmorWater(ARMOR_WATER, 3, Items.water_bucket, 0, "water").setUnlocalizedName("derpyshiz.boots_water").setTextureName("derpyshiz:boots_water"), "boots_water");

		GameRegistry.registerItem(new DerpyArmor(ARMOR_CIRCLET_DIAMOND, 0, Items.gold_ingot, 0, "circlet_diamond").setUnlocalizedName("derpyshiz.circlet_diamond").setTextureName("derpyshiz:circlet_diamond").setCreativeTab(CreativeTabs.tabMisc), "circlet_diamond");
		GameRegistry.registerItem(new DerpyArmor(ARMOR_CIRCLET_RUBY, 0, Items.gold_ingot, 0, "circlet_ruby").setUnlocalizedName("derpyshiz.circlet_ruby").setTextureName("derpyshiz:circlet_ruby").setCreativeTab(CreativeTabs.tabMisc), "circlet_ruby");
		GameRegistry.registerItem(new DerpyArmor(ARMOR_CIRCLET_AMETHYST, 0, Items.gold_ingot, 0, "circlet_amethyst").setUnlocalizedName("derpyshiz.circlet_amethyst").setTextureName("derpyshiz:circlet_amethyst").setCreativeTab(CreativeTabs.tabMisc), "circlet_amethyst");
		GameRegistry.registerItem(new DerpyArmor(ARMOR_CIRCLET_TURQUOISE, 0, Items.gold_ingot, 0, "circlet_turquoise").setUnlocalizedName("derpyshiz.circlet_turquoise").setTextureName("derpyshiz:circlet_turquoise").setCreativeTab(CreativeTabs.tabMisc), "circlet_turquoise");
		GameRegistry.registerItem(new DerpyArmor(ARMOR_CIRCLET_FAKEDIAMOND, 0, Items.gold_ingot, 0, "circlet_diamond").setUnlocalizedName("derpyshiz.circlet_fakediamond").setTextureName("derpyshiz:circlet_diamond").setCreativeTab(CreativeTabs.tabMisc), "circlet_fakediamond");

		GameRegistry.registerItem(new DarkCloak(ARMOR_DARK_CLOAK, 1, oreItems, 12).setUnlocalizedName("derpyshiz.dark_cloak").setTextureName("derpyshiz:dark_cloak"), "dark_cloak");

		GameRegistry.registerItem(new TopHat(ARMOR_TOP_HAT, Item.getItemFromBlock(Blocks.wool), 15, "black"), "top_hat_black");
		GameRegistry.registerItem(new TopHat(ARMOR_TOP_HAT, Item.getItemFromBlock(Blocks.wool), 0, "white"), "top_hat_white");
		GameRegistry.registerItem(new TopHat(ARMOR_TOP_HAT, Item.getItemFromBlock(DerpyBlocks.patternWool), 8, "rainbow"), "top_hat_rainbow");
		
		GameRegistry.registerItem(new BaseCap(ARMOR_CAP_YOLO, Item.getItemFromBlock(Blocks.wool), 5, "yolo"), "cap_yolo");
		GameRegistry.registerItem(new BaseCap(ARMOR_CAP, Item.getItemFromBlock(Blocks.wool), 0, "black"), "cap_black");
		GameRegistry.registerItem(new BaseCap(ARMOR_CAP, Item.getItemFromBlock(Blocks.wool), 6, "pink"), "cap_pink");
		
		GameRegistry.registerItem(new Item().setUnlocalizedName("derpyshiz.horn").setTextureName("derpyshiz:horn").setCreativeTab(CreativeTabs.tabMaterials), "horn");

		GameRegistry.registerItem(new HorseLasagne(), "lasagne");
		GameRegistry.registerItem(cookie = new RainbowCookie(), "rainbow_cookie");

		GameRegistry.registerItem(nuggets = new DerpyNuggets(), "nugget");
		GameRegistry.registerItem(new DerpyGears(), "gear");
		GameRegistry.registerItem(new ItemRotameter(), "rotameter");

		GameRegistry.registerItem(new DerpyCircuits(), "circuit");

		GameRegistry.registerItem(new ItemTDM(), "tdm");
	}

	public static void damageItem(ItemStack stack, int amount, EntityLivingBase entity) {
		if (!(entity instanceof EntityPlayer) || !((EntityPlayer) entity).capabilities.isCreativeMode) {
			if (stack.isItemStackDamageable()) {
				if (stack.attemptDamageItem(amount, entity.getRNG())) {
					entity.renderBrokenItemStack(stack);
					--stack.stackSize;

					if (entity instanceof EntityPlayer) {
						EntityPlayer entityplayer = (EntityPlayer) entity;
						entityplayer.addStat(StatList.objectBreakStats[Item.getIdFromItem(stack.getItem())], 1);

						if (stack.stackSize == 0) {
							entityplayer.destroyCurrentEquippedItem();
						}
					}

					if (stack.stackSize < 0) {
						stack.stackSize = 0;
					}

					stack.setItemDamage(0);
				}
			}
		}
	}

}
